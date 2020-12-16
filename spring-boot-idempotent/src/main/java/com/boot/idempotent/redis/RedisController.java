package com.boot.idempotent.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis实现幂等性
 * <p>A-->获取token，此时服务端将token存入redis中-->A带token请求接口-->后端验证token</p>
 * <p>这里只是一个展示，在并发情况下，执行 Redis 查找数据与删除需要保证原子性，
 * 否则很可能在并发下无法保证幂等性，可以使用分布式锁或者使用 Lua 表达式,参照{@link RedisLock}<p/>
 * @author lizhifu
 * @date 2020/12/16
 */
@RestController
public class RedisController {
    @Resource
    public RedisTemplate redisTemplate;
    @GetMapping("/redis")
    public String redis(HttpServletRequest request,String token){
        Map<String, Object> nowDataMap = new HashMap<String, Object>();
        nowDataMap.put("repeatParams", request.getParameterMap());
        nowDataMap.put("repeatTime", System.currentTimeMillis());
        // 请求地址（作为存放redis的key值）
        String url = request.getRequestURI();
        // 唯一标识（指定key + 消息头）
        String cache_repeat_key = "repeat_submit:" + token;
        //获取redis存储的值
        Object sessionObj = this.getCacheObject(cache_repeat_key);
        //redis内有值，证明不是第一次请求，判断参数
        if (sessionObj != null)
        {
            Map<String, Object> sessionMap = (Map<String, Object>) sessionObj;
            if (sessionMap.containsKey(url))
            {
                Map<String, Object> preDataMap = (Map<String, Object>) sessionMap.get(url);
                if (compareParams(nowDataMap, preDataMap) && compareTime(nowDataMap, preDataMap))
                {
                    return "fail";
                }
            }
        }
        Map<String, Object> cacheMap = new HashMap<String, Object>();
        cacheMap.put(url, nowDataMap);
        redisTemplate.opsForValue().set(cache_repeat_key, cacheMap, 10L, TimeUnit.SECONDS);
        return "success";
    }
    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key)
    {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }
    /**
     * 判断参数是否相同
     */
    private boolean compareParams(Map<String, Object> nowMap, Map<String, Object> preMap)
    {
        String nowParams = (String) nowMap.get("repeatParams");
        String preParams = (String) preMap.get("repeatParams");
        return nowParams.equals(preParams);
    }

    /**
     * 判断两次间隔时间
     */
    private boolean compareTime(Map<String, Object> nowMap, Map<String, Object> preMap)
    {
        long time1 = (Long) nowMap.get("repeatTime");
        long time2 = (Long) preMap.get("repeatTime");
        if ((time1 - time2) < (10L * 1000))
        {
            return true;
        }
        return false;
    }
}
