package com.doc.demo;

import com.doc.demo.type.StatusTypeSingleton;

/**
 * StatusTypeSingleton
 *
 * @author lizhifu
 * @date 2021/4/8
 */
public class StatusTypeSingletonTest {
    public static void main(String[] args) {
        StatusTypeSingleton.getInstance().getMsg();
    }
}
