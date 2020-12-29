package com.boot.lombok.example;

import lombok.ToString;

/**
 * https://projectlombok.org/features/ToString
 * 使用@ToString注解可以自动生成toString方法，默认会包含所有类属性，
 * @ToString.Exclude注解可以排除属性的生成。
 * @author lizhifu
 * @date 2020/12/29
 */
@ToString
public class ToStringExample {
    @ToString.Exclude
    private String name;
    private Integer age;
    public ToStringExample(String name,Integer age){
        this.name = name;
        this.age = age;
    }
    public static void main(String[] args) {
        ToStringExample example = new ToStringExample("test",18);
        System.out.println(example);
    }
}
