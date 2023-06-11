package com.spring.boot.actuator.custom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * hello
 *
 * @author lizhifu
 * @since 2023/6/11
 */
@RestController
public class HelloController {
    private final CustomMeter customMeter;

    public HelloController(CustomMeter customMeter) {
        this.customMeter = customMeter;
    }

    @GetMapping("/hello")
    public String hello(){
        //业务调用
        customMeter.hello();
        return "hello";
    }
}
