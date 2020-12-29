package com.boot.lombok.example;

/**
 * GetterSetterExample
 *
 * @author lizhifu
 * @date 2020/12/29
 */
public class GetterSetterExampleOutLombok {
    private int age = 10;

    private String name;

    @Override public String toString() {
        return String.format("%s (age: %d)", name, age);
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    protected void setName(String name) {
        this.name = name;
    }
}
