package com.boot.casbin.controller;

import com.boot.casbin.model.User;
import com.boot.casbin.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 授权登录入口
 *
 * @author lizhifu
 * @since 2023/8/18
 */
@RestController
@RequestMapping("/auth/")
public class AuthController {
    private final UserService userService;

    private final HttpSession httpSession;

    @Inject
    public AuthController(UserService userService, HttpSession httpSession) {
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password) {
        Optional<User> u = userService.login(httpSession.getId(), username, password);
        return u.map(user -> ResponseEntity.ok().body(user)).orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        userService.logout(httpSession.getId());
        return ResponseEntity.ok().body("注销成功!");
    }
}
