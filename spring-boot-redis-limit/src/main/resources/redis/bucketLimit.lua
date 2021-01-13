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