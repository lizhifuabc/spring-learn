package com.spring.boot.graalvm;

import com.spring.boot.graalvm.service.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 启动
 *
 * @author lizhifu
 * @since 2023/6/2
 */
@RestController
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/hello")
    public String hello() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //反射调用
        Object invoke = "sayHello";
        //1、反射获取sayHello方法
        Method sayHello = HelloService.class.getMethod(invoke.toString());
        //2、利用反射获取无参构造器，并创建出对象
        HelloService instance = HelloService.class.getConstructor().newInstance();
        //3、反射执行 instance 的 sayHello 方法
        invoke = sayHello.invoke(instance);
        //4、新版的 GraalVM 可以处理反射，直接编译了。
        return invoke.toString();
    }
}
