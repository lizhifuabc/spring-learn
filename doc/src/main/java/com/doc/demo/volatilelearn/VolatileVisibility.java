package com.doc.demo.volatilelearn;

/**
 * volatile:可见性
 *
 * @author lizhifu
 * @since 2023/7/17
 */
public class VolatileVisibility {
    volatile int i = 0;
    public void add(){
        i++;
    }
}
