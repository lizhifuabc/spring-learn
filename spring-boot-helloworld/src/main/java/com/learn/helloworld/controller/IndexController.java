package com.learn.helloworld.controller;

import com.learn.helloworld.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * IndexController
 *
 * @author lizhifu
 * @date 2020/12/4
 */
@RestController
public class IndexController {
    @GetMapping("/")
    public String helloWorld(){
        return "helloWorld";
    }
    @GetMapping("/user")
    public List<User> user(){
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setName("小马");
        user.setAge(18);
        list.add(user);
        list.add(user);
        return list;
    }
}
