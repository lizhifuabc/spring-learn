package com.boot.thymeleaf.controller;

import com.boot.thymeleaf.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * index
 *
 * @author lizhifu
 * @date 2021/1/18
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public ModelAndView index(){
        Random random = new Random();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/index");
        modelAndView.addObject("hello","hello:"+random.nextInt());
        return modelAndView;
    }
    @ResponseBody
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
