package com.doc.demo.beanutils;

/**
 * ParamTest3
 *
 * @author lizhifu
 * @since 2023/9/7
 */
public class ParamTest3 {
    public static void main(String[] args) {
        String str  = "ab";
        change(str);
        System.out.println("出了方法str："+str);
    }
    public static void change(String str1){
        System.out.println("初始进入方法str："+str1);
        // 对 i1 进行赋值
        str1 = "cd";
        System.out.println("赋值之后尚未出方法str："+str1);
    }
}