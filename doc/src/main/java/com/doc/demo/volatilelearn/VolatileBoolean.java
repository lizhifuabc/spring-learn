package com.doc.demo.volatilelearn;

/**
 * volatile:原子性,针对 boolean
 *
 * @author lizhifu
 * @since 2023/7/17
 */
public class VolatileBoolean {
    volatile boolean flag;

    public void toTrue(){
        flag = true;
    }

    public void methodA(){
        while(!flag){
            System.out.println("methodA");
        }
    }
}
