package com.boot.cros.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * index
 *
 * @author lizhifu
 * @date 2020/12/29
 */
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
