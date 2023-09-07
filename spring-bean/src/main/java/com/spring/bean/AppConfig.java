package com.spring.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 构造函数实例化示例
 * 1.构造函数实例化
 * 2.静态工厂方法实例化
 * 3.实例工厂方法实例化
 *
 * @author lizhifu
 * @since 2023/9/7
 */
@Configuration
public class AppConfig {
    @Bean
    public UserService userService1(){
        return new UserService("1234");
    }

    @Bean
    public UserService userService2(){
        return UserStaticFactory.createUser();
    }

    @Bean
    public UserFactory userFactory(){
        return new UserFactory();
    }
    @Bean
    public UserService userService3(UserFactory userFactory){
        return userFactory.createUser();
    }
}
