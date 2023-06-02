package com.boot.lombok.example;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * https://projectlombok.org/features/constructor
 *
 * <p>
 * @NoArgsConstructor：生成无参构造函数。
 * @RequiredArgsConstructor：生成包含必须参数的构造函数，使用@NonNull注解的类属性为必须参数。
 * @AllArgsConstructor：生成包含所有参数的构造函数
 * </p>
 * @author lizhifu
 * @date 2020/12/29
 */
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
public class ConstructorExample {
    @NonNull
    private String name;
    private Integer age;
    public static void main(String[] args) {
        //无参构造器
        ConstructorExample example1 = new ConstructorExample();
        //全部参数构造器
        ConstructorExample example2 = new ConstructorExample("test",18);
        //@NonNull注解的必须参数构造器
        ConstructorExample example3 = ConstructorExample.of("test");
    }
}
