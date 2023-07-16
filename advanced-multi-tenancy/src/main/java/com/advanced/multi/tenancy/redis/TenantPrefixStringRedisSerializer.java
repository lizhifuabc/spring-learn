package com.advanced.multi.tenancy.redis;

import com.advanced.multi.tenancy.support.TenantContextHolder;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

/**
 * 自定义redis序列化，加入租户信息
 *
 * @author lizhifu
 * @since 2023/7/16
 */
public class TenantPrefixStringRedisSerializer extends StringRedisSerializer {
    @Override
    public byte[] serialize(String key) {
        String tenant = TenantContextHolder.peek();
        if (StringUtils.hasText(tenant)) {
            key = tenant + ":" + key;
        }
        return super.serialize(key);
    }
}
