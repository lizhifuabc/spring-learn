package com.boot.aot.controller;

import com.boot.aot.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Demo
 *
 * @author lizhifu
 * @since 2023/7/24
 */
@RestController
public class DemoController {
    @GetMapping("/demo")
    public String demo() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 反射调用方法名称
        Object invoke = "demo";
        // 反射获取方法
        Method demo = DemoService.class.getMethod(invoke.toString());
        // 利用反射获取无参构造器，并创建出对象
        DemoService instance = DemoService.class.getConstructor().newInstance();
        // 反射执行 instance 的 demo 方法
        invoke = demo.invoke(instance);
        // 新版的 GraalVM 可以处理反射，直接编译了
        return "Spring Boot AOT: " + invoke.toString();
    }
}
