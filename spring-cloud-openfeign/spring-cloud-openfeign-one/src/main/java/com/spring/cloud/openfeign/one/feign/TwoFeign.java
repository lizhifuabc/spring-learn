package com.spring.cloud.openfeign.one.feign;

import com.spring.cloud.openfeign.common.OpenFeignService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 调用two服务
 *
 * @author lizhifu
 * @since 2023/6/6
 */
@FeignClient(contextId = "twoFeign", value = OpenFeignService.SERVICE_TWO,fallbackFactory = TwoFeignFallbackFactory.class)
public interface TwoFeign {
    @GetMapping("/two/two")
    String two();
}
