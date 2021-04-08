package com.doc.demo.type;

/**
 * 状态
 *
 * @author lizhifu
 * @date 2021/3/8
 */
public enum StatusDemoType {
    DEAL("DEAL","处理中"),
    SUCCESS("SUCCESS","成功");
    private final String type;
    private final String message;
    StatusDemoType(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
