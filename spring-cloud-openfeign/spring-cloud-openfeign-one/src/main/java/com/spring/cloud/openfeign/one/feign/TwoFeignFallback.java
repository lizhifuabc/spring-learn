package com.spring.cloud.openfeign.one.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.stereotype.Component;

/**
 * Fallback class for {@link TwoFeign}.
 *
 * @author lizhifu
 * @since 2023/6/7
 */
@Component
@Slf4j
public class TwoFeignFallback implements TwoFeign{
    @Override
    public String two() {
        log.error("==================== twoFeign fallback ====================");
        throw new NoFallbackAvailableException("Boom!", new RuntimeException());
    }
}
