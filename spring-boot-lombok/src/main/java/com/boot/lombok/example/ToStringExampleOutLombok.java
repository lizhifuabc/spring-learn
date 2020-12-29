package com.boot.lombok.example;

/**
 * ToStringExampleOutLombok
 *
 * @author lizhifu
 * @date 2020/12/29
 */
public class ToStringExampleOutLombok {
    private String name;
    private Integer age;

    public ToStringExampleOutLombok(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString() {
        return "ToStringExample(age=" + this.age + ")";
    }

    public static void main(String[] args) {
        ToStringExampleOutLombok example = new ToStringExampleOutLombok("test",18);
        System.out.println(example);
    }
}
