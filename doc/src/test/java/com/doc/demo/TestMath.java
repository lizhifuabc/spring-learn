package com.doc.demo;

import org.junit.jupiter.api.Test;

/**
 * TestMath
 *
 * @author lizhifu
 * @date 2021/1/8
 */
public class TestMath {
    @Test
    public void test(){
        int a = 15;
        int b = 13;
        System.out.println("a的二进制"+Integer.toBinaryString(a));
        System.out.println("b的二进制"+Integer.toBinaryString(b));
        System.out.println("二进制结果:"+Integer.toBinaryString((a^b)));
        System.out.println("十进制结果:"+(a^b));
    }
    @Test
    public void test2(){
        int a = -11;
        System.out.println(a << 1);
        System.out.println("a的二进制"+Integer.toBinaryString(a));
        System.out.println(a & 1);
    }
    @Test
    public void test3(){
        int a = 15;
        int b = 14;
        System.out.println("a的二进制"+Integer.toBinaryString(a));
        System.out.println("b的二进制"+Integer.toBinaryString(b));
        System.out.println("二进制结果:"+Integer.toBinaryString((a&b)));
        System.out.println("十进制结果:"+(a&b));
    }
    @Test
    public void test4(){
        int  newCap = 0;
        int oldCap = 12;
        System.out.println(newCap = oldCap << 1);
        System.out.println(newCap);
    }
    @Test
    public void test5(){
        int a = 1<<20;
        System.out.println(a);
        System.out.println("a的二进制"+Integer.toBinaryString(a));
    }
    @Test
    public void test6(){
        String a = "a";
        String b = null;
        if ((a = b) == null)
            System.out.println("if:"+a);
        System.out.println(a);

    }
    @Test
    public void test7(){
        int a = 15;
        int b = 3;
        System.out.println(a % b);
    }
}
