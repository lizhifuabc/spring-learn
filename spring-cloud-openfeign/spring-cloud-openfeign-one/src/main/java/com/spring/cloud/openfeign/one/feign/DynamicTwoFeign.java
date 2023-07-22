package com.spring.cloud.openfeign.one.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 调用two服务
 *
 * @author lizhifu
 * @since 2023/6/6
 */
@FeignClient(name="base",url = "${feign.debug.url.system:}")
public interface DynamicTwoFeign {
    @GetMapping("/two/two")
    String two();
}
