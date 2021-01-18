package com.doc.demo;

import org.junit.jupiter.api.Test;

/**
 * 各种相等
 *
 * @author lizhifu
 * @date 2021/1/18
 */
public class JavaEqualTest {
    @Test
    public void test(){
        String a = "ab";
        String b = "a"+"b";
        System.out.println(a == b);
    }
    @Test
    public void test2(){
        String a = "ab";
        String b = new String("ab");
        System.out.println(a == b);
    }
    @Test
    public void test4(){
        String a = new String("ab");
        String b = new String("ab");
        System.out.println(a == b);
    }
    @Test
    public void test3(){
        String a = "ab";
        String b = new String("ab");
        System.out.println(a.equals(b));
    }
}
