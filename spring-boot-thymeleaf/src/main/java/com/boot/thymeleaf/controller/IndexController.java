package com.boot.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
}
