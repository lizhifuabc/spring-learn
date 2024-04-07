package com.spring.boot.security.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录
 *
 * @author lizhifu
 * @since 2024/4/7
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserLoginService userLoginService;

    public LoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PostMapping
    public String login(LoginParam loginParam) {
        return userLoginService.login(loginParam.getUsername(),loginParam.getPassword());
    }
}
