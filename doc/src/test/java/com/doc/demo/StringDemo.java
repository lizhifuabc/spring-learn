package com.doc.demo;

/**
 * String简单解析
 *
 * @author lizhifu
 * @date 2021/1/22
 */
public class StringDemo {
    public static void main(String[] args) {
        test1();
    }
    public static void test(){
        String str1 = "ab";
        String str2 = "a" + "b";
    }
    public static void test1(){
        String str1 = "ab";
        String str2 = new String("ab");
    }
}
