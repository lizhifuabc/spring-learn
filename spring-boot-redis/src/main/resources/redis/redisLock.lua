-- 删除锁的时候，找到 key 对应的 value，跟自己传过去的 value 做比较，如果是一样的才删除。
local temp =  redis.call("get",KEYS[1])
if temp then
if temp == ARGV[1] then
    return redis.call("del",KEYS[1])
else
    return 0
end
else
return 1
end
