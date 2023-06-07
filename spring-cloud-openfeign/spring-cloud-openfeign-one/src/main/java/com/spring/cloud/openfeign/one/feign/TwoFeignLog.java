package com.spring.cloud.openfeign.one.feign;

import com.spring.cloud.openfeign.common.OpenFeignService;
import com.spring.cloud.openfeign.one.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 调用two服务
 *
 * @author lizhifu
 * @since 2023/6/6
 */
@FeignClient(contextId = "twoFeignLog", value = OpenFeignService.SERVICE_TWO,configuration = FeignConfig.class)
public interface TwoFeignLog {
    @GetMapping("/two/two")
    String two();
}
