package com.boot.lombok.example;

import lombok.Builder;
import lombok.ToString;

/**
 * https://projectlombok.org/features/Builder
 * 可以通过建造者模式来创建对象，建造者模式加链式调用
 * @author lizhifu
 * @date 2020/12/29
 */
@Builder(toBuilder = true)
@ToString
public class ToBuilderExample {
    private String name;
    private Integer age;

    public static void main(String[] args) {
        ToBuilderExample example = ToBuilderExample.builder()
                .name("test")
                .age(18)
                .build();
        System.out.println(example);

        // 避免了需要重新设置所有字段的情况
        ToBuilderExample newExample = example.toBuilder()
                .name("李志福")
                .build();
        System.out.println(newExample);
    }
}
