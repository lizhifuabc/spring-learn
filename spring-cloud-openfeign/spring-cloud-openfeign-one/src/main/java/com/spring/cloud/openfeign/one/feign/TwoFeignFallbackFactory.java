package com.spring.cloud.openfeign.one.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * TwoFeignFallbackFactory
 *
 * @author lizhifu
 * @since 2023/9/14
 */
@Component
@Slf4j
public class TwoFeignFallbackFactory implements FallbackFactory<TwoFeign> {
    @Override
    public TwoFeign create(Throwable cause) {
        return () -> {
            log.error("==================== twoFeign fallbackFactory ====================",cause);
            return "twoFeign fallbackFactory";
        };
    }
}
