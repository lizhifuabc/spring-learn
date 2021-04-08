package com.doc.demo.type;

/**
 * 策略模式
 *
 * @author lizhifu
 * @date 2021/4/8
 */
public enum StatusTypeStrategy {
    SUCCESS(){
        @Override
        public String status(String status) {
            System.out.println("SUCCESS");
            return status;
        }
    },
    DEAL(){
        @Override
        public String status(String status) {
            System.out.println("DEAL");
            return status;
        }
    };
    public abstract String status(String status);
}
