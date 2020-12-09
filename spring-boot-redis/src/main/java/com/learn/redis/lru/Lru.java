package com.learn.redis.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 当内存不足以容纳新写入数据时，在键空间中，移除最近最少使用的 key
 * LRU(Least Recently Used):最近最少使用
 * @author lizhifu
 * @date 2020/12/9
 */
public class Lru<K,V> extends LinkedHashMap<K,V> {
    /**
     * 最大
     */
    private final int MAX_CACHE_SIZE;

    public Lru (int cacheSize){
        //true：按照访问顺序来进行排序，最近访问的放在头部，最老访问的放在尾部。
        //+1:根据cacheSize和加载因子计算hashmap的capactiy,确保当达到cacheSize上限时不会触发hashmap的扩容
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        MAX_CACHE_SIZE = cacheSize;
    }
    /**
     * 钩子方法，通过put新增键值对的时候，若该方法返回true
     * 便移除该map中最老的键和值
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // 当 map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据。
        return size() > MAX_CACHE_SIZE;
    }
}
