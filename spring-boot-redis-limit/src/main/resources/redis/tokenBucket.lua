 -- 当前规则令牌剩余数量存储key
local tokens_key = KEYS[1]
-- 当前规则上次调用时间
local timestamp_key = KEYS[2]
-- 速率
local rate = tonumber(ARGV[1])
-- 容量
local capacity = tonumber(ARGV[2])
-- 时间戳
local now = tonumber(ARGV[3])
-- 值为1
local requested = tonumber(ARGV[4])
-- 容量除以速率 计算填充时间
local fill_time = capacity/rate
-- 计算过期时间 取下限
local ttl = math.floor(fill_time*2)
-- 获取当前存有的令牌数
local last_tokens = tonumber(redis.call("get", tokens_key))
if last_tokens == nil then
-- 将令牌数量赋值为设定的容量
  last_tokens = capacity
end
-- 获取上一次调用时间
local last_refreshed = tonumber(redis.call("get", timestamp_key))
if last_refreshed == nil then
  last_refreshed = 0
end
-- 计算出上次调用和本次调用之间的时间差
local delta = math.max(0, now-last_refreshed)
-- 计算出当前剩余的令牌数量
local filled_tokens = math.min(capacity, last_tokens+(delta*rate))
--  判断当前令牌数量 数量>=1 代表获取成功
local allowed = filled_tokens >= requested
local new_tokens = filled_tokens
local allowed_num = 0
if allowed then
-- 申请一个令牌
  new_tokens = filled_tokens - requested
  allowed_num = 1
end
-- setex 设置过期key 过期时间 新值
redis.call("setex", tokens_key, ttl, new_tokens)
redis.call("setex", timestamp_key, ttl, now)

return { allowed_num, new_tokens }

