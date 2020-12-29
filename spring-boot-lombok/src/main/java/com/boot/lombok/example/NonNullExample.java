package com.boot.lombok.example;

import lombok.NonNull;

/**
 * https://projectlombok.org/features/NonNull
 *
 * @author lizhifu
 * @date 2020/12/29
 */
public class NonNullExample {
    private String name;

    public NonNullExample(@NonNull String name) {
        this.name = name;
    }

    public NonNullExample(String name,String nam) {
        if (name == null) {
            throw new NullPointerException("person is marked @NonNull but is null");
        }
        this.name = name;
    }

    public static void main(String[] args) {
        new NonNullExample(null);
    }
}
