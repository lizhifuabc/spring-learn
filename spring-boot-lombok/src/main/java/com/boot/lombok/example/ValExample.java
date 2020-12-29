package com.boot.lombok.example;

import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * https://projectlombok.org/features/val
 * 1：必须声明后面的表达式
 * 2：只能声明局部变量
 * 3：声明出来的局部变量为 final 类型
 * @author lizhifu
 * @date 2020/12/29
 */
public class ValExample {

    public String exampleNormal() {
        final ArrayList<String> example = new ArrayList<String>();
        example.add("Hello, World!");
        final String foo = example.get(0);
        return foo.toLowerCase();
    }
    public static String example() {
        val example = new ArrayList<String>();
        example.add("Hello, World!");
        val foo = example.get(0);
        System.out.println(foo.toLowerCase());
        return foo.toLowerCase();
    }



    public static void example2() {
        val map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");
        for (val entry : map.entrySet()) {
            System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
        }
    }
    public void example2Normal() {
        final HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");
        for (final Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
        }
    }
    public static void main(String[] args) {
        example();
        example2();
    }
}
