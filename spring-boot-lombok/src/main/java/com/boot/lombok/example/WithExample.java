package com.boot.lombok.example;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.With;

/**
 * https://projectlombok.org/features/With
 * 实现对原对象进行克隆，并改变其一个属性
 *
 * @author lizhifu
 * @date 2020/12/29
 */
@With
@ToString
@AllArgsConstructor
public class WithExample {
    private String name;
    private Integer age;
    public static void main(String[] args) {
        WithExample example1 = new WithExample( "test", 18);
        System.out.println(example1);
        WithExample example2 = example1.withAge(22);
        System.out.println(example2);
    }
}
