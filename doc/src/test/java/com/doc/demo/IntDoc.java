package com.doc.demo;

import org.junit.jupiter.api.Test;

/**
 * IntDoc
 *
 * @author lizhifu
 * @date 2021/4/23
 */
public class IntDoc {
    @Test
    public void test3(){
        Integer i2 = 12;
        Integer i3 = new Integer(12);
        System.out.println(i2 == i3);//false
    }
    @Test
    public void test2(){
        Integer i2 = new Integer(12);
        Integer i3 = new Integer(12);
        System.out.println(i2 == i3);//false
    }
    @Test
    public void test(){
        int i = 127;
        Integer i2 = 127;
        System.out.println(i ==i2);//true
        int i1 = 128;
        Integer i3 = 128;
        System.out.println(i1 == i3);//true
    }
    public static void main(String[] args) {
        Integer a = 127;
        Integer b = 127;
        Integer e = Integer.valueOf(127);
        Integer d = new Integer(127);
        System.out.println(a == b);//true
        System.out.println(a == e);//true
        System.out.println(a == d);//false

        Integer i1 = 128;
        Integer i2 = 128;

        System.out.println(i1 == i2);//false
    }
}
