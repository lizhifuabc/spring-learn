package com.redis.advanced.pierce;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.redis.advanced.service.BloomFilterHelper;
import com.redis.advanced.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * 缓存穿透：布隆过滤器
 * <p>布隆过滤器可以判断某个数据一定不存在，但是无法判断一定存在</>
 *
 * @author lizhifu
 * @date 2020/12/9
 */
@RestController
public class CachePierceBloomController {
    /**
     * 预计插入量:100
     * 可接受的错误率:0.01
     */
    private BloomFilterHelper<String> orderBloomFilterHelper = new BloomFilterHelper<>((Funnel<String>) (from, into) -> into.putString(from, Charsets.UTF_8)
            .putString(from, Charsets.UTF_8), 100 , 0.01);
    @Resource
    private RedisService redisService;
    @GetMapping("cachePierce/bloom")
    public boolean bloom(){
        boolean result = redisService.includeByBloomFilter(orderBloomFilterHelper, "cachePiercebloom", "cachePiercebloom");
        return result;
    }

    /**
     * 模拟加入数据
     */
    @GetMapping("cachePierce/bloom/init")
    public void init(){
        redisService.addByBloomFilter(orderBloomFilterHelper,"cachePiercebloom","cachePiercebloom");
    }
}
