# redis通过lua脚本实现限流算

- 固定窗口算法
- 令牌桶算法

# 固定窗口算法

> 1. 确定限流的key
> 2. 确定限流时间，使用redis的过期时间保障超时限流清空
> 3. 基于lua表达式，保障原子性操作

```lua
--lua 下标从 1 开始
-- 获取调用脚本时传入的第一个key值（用作限流的 key List）
local key = KEYS[1]
-- 获取调用脚本时传入的第1个参数值（限流大小）
local max = tonumber(ARGV[1])
-- 获取调用脚本时传入的第2个参数值（超时时间）
local ttl = tonumber(ARGV[2])

-- 获取当前流量大小
local current = tonumber(redis.call('get', key) or "0")
local next = current + 1
-- 是否超出限流
if next > max then
-- 达到限流大小 返回 0
return 0
else
-- key 中储存的数字加上指定的增量值，如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
-- 没有达到阈值 value + 1
redis.call("INCRBY", key, 1)
-- 每次访问均重新设置过期时间，单位毫秒
redis.call("PEXPIRE", key, ttl)
-- 返回(放行)
return next
end
```

# 令牌桶算法 

> 1. 初始化令牌桶：数量+初始化时间
> 2. 取令牌时判断剩余令牌数+补充时间间隔，满足剩余令牌数<1且时间间隔满足条件，进行令牌补充
> 3. 不满足拒绝请求(达到限流标准)

```lua
local result=1
redis.pcall("HMSET",KEYS[1],
		"last_mill_second",ARGV[1],
		"curr_permits",ARGV[2],
		"max_burst",ARGV[3],
		"rate",ARGV[4],
		"app",ARGV[5])
return result
```

```lua
-- 获取的令牌数量
local permits = tonumber(ARGV[1])
-- 当前时间
local currMillSecond = tonumber(ARGV[2])
-- 获取初始化的数据
local ratelimit_info = redis.pcall("HMGET",KEYS[1],"last_mill_second","curr_permits","max_burst","rate","app")
-- last_mill_second 最后时间毫秒
local last_mill_second = ratelimit_info[1]
-- curr_permits 当前可用的令牌
local curr_permits = tonumber(ratelimit_info[2])
-- max_burst 令牌桶最大值
local max_burst = tonumber(ratelimit_info[3])
-- rate 每秒生成几个令牌
local rate = tonumber(ratelimit_info[4])
-- app 应用
local app = tostring(ratelimit_info[5])
-- 为了防止key的冲突，需要设置app的值
if app == nil then
    return 0
end
local local_curr_permits = max_burst;
-- 非第一次获取令牌
if(type(last_mill_second) ~='boolean' and last_mill_second ~=nil) then
    -- 计算需要增加的令牌数量：((当前时间 -上次获取时间) / 1000) * rate = 每秒生成的令牌数量
    local reverse_permits = math.floor((currMillSecond - last_mill_second)/1000) * rate
    -- 如果需要增加，则设置上次获取时间为当前时间
    if(reverse_permits > 0) then
        redis.pcall("HMSET",KEYS[1],"last_mill_second",currMillSecond)
    end
    -- 当前令牌+此次增加令牌数量：不应该超过最大数量max_burst
    local expect_curr_permits = reverse_permits + curr_permits
    local_curr_permits = math.min(expect_curr_permits,max_burst);

else
    -- 第一次获取令牌，初始化时间为当前时间
    redis.pcall("HMSET",KEYS[1],"last_mill_second",currMillSecond)
end

local result = -1
-- 当前令牌数据>此次请求获取的令牌数量
if(local_curr_permits - permits > 0) then
    result = 1
    -- 当前令牌数据 - 此次请求获取的令牌数量
    redis.pcall("HMSET",KEYS[1],"curr_permits",local_curr_permits - permits)
else
    -- 当前令牌数据不足以支撑此次请求的令牌数量
    redis.pcall("HMSET",KEYS[1],"curr_permits",local_curr_permits)
end
return result
```

