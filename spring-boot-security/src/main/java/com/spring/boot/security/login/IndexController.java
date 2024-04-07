package com.spring.boot.security.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录
 *
 * @author lizhifu
 * @since 2023/6/8
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @GetMapping
    public String index() {
        return "index";
    }
}
