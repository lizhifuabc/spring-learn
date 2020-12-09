package com.redis.advanced.breakdown;

import com.redis.advanced.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 缓存击穿(热点key)
 * 三方系统
 * <li>用户A,A1,A2</li>
 * <li>缓存B</li>
 * <li>数据库C</li>
 * <li>简单请求流程：A(A1,A2..)-->B-->C，所有的用户访问id=1000L的数据，此时此key的缓存突然失效，那么直接击穿C</li>
 * @author lizhifu
 * @date 2020/12/9
 */
@RestController
public class CacheBreakDownController {
    @Resource
    private RedisService redisService;
    @GetMapping("cacheBreakDown")
    public String cacheBreakDown(){
        String cacheBreakDown = redisService.getCacheObject("cacheBreakDown");
        //此时缓存失效
        if(cacheBreakDown == null){
            cacheBreakDown = "走数据库查询：数据库可能被打穿";
        }
        return cacheBreakDown;
    }
}
