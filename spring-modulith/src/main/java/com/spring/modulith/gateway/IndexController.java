package com.spring.modulith.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * index
 *
 * @author lizhifu
 * @since 2024/4/12
 */
@RestController
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
