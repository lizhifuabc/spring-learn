package com.doc.demo;

import com.doc.demo.type.StatusTypeStrategy;

/**
 * StatusTypeStrategy
 *
 * @author lizhifu
 * @date 2021/4/8
 */
public class StatusTypeStrategyTest {
    public static void main(String[] args) {
        System.out.println(StatusTypeStrategy.DEAL.status("DEAL"));
        System.out.println(StatusTypeStrategy.SUCCESS.status("DEAL"));
    }
}
