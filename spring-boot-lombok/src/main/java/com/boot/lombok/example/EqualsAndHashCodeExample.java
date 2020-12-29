package com.boot.lombok.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * https://projectlombok.org/features/EqualsAndHashCode
 * 自动生成hashCode和equals方法，默认包含所有类属性，
 * @EqualsAndHashCode.Exclude可以排除属性的生成。
 * @author lizhifu
 * @date 2020/12/29
 */
@EqualsAndHashCode
@Getter
@Setter
public class EqualsAndHashCodeExample {
    @EqualsAndHashCode.Exclude
    private String name;
    private Integer age;
    public static void main(String[] args) {
        EqualsAndHashCodeExample example1 = new EqualsAndHashCodeExample();
        example1.setName("test");
        example1.setAge(20);
        EqualsAndHashCodeExample example2 = new EqualsAndHashCodeExample();
        example2.setAge(20);
        example2.setName("test2");
        //equals方法只对比age，返回true
        System.out.println(example1.equals(example2));
    }
}
