package com.boot.lombok.example;

import lombok.Builder;
import lombok.ToString;

/**
 * https://projectlombok.org/features/Builder
 * 可以通过建造者模式来创建对象，建造者模式加链式调用
 * @author lizhifu
 * @date 2020/12/29
 */
@Builder
@ToString
public class BuilderExample {
    private String name;
    private Integer age;

    public static void main(String[] args) {
        BuilderExample example = BuilderExample.builder()
                .name("test")
                .age(18)
                .build();
        System.out.println(example);
    }
}
