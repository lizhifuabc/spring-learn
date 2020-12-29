package com.boot.lombok.example;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * https://projectlombok.org/features/GetterSetter
 *
 * @author lizhifu
 * @date 2020/12/29
 */
public class GetterSetterExample {
    @Getter
    @Setter
    private int age = 10;
    /**
     * AccessLevel,默认是public类型的，如果需要的话可以修改方法的访问级别
     */
    @Setter(AccessLevel.PROTECTED) private String name;

    @Override public String toString() {
        return String.format("%s (age: %d)", name, age);
    }

    public static void main(String[] args) {
        GetterSetterExample example = new GetterSetterExample();
        example.setName("test");
        example.setAge(18);
        System.out.println(example.toString());
    }
}
