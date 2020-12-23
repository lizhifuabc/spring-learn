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