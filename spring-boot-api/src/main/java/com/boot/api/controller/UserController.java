package com.boot.api.controller;

import com.boot.api.base.R;
import com.boot.api.domain.User;
import com.boot.api.domain.UserQueryParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * User
 *
 * @author lizhifu
 * @date 2020/12/24
 */
@RestController
@Validated
public class UserController {
    @PostMapping("/user")
    public R<User> select(@NotNull(message = "name不能为空") String name){
        User user = new User();
        user.setName("小明");
        user.setAge(18);
        user.setSex(1);
        return new R().success(user);
    }
    @GetMapping("/user/query")
    public R<User> select(@Validated UserQueryParam userQueryParam){
        User user = new User();
        user.setName("小明2");
        user.setAge(18);
        user.setSex(1);
        return new R().success(user);
    }
}
