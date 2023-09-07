package com.spring.bean;

/**
 * 静态工厂方法实例化示例
 *
 * @author lizhifu
 * @since 2023/9/7
 */
public class UserStaticFactory {
    public static UserService createUser() {
        System.out.println("调用静态工厂方法");
        return new UserService("1234");
    }
}
