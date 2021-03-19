package com.boot.im.type;


/**
 * 消息类型
 * @author lizhifu
 */
public enum MsgTypeEnum {

    TEXT(0, "文字"),
    IMG(1, "图片");

    private int CODE;
    private String TYPE;

    MsgTypeEnum(int CODE, String TYPE) {
        this.CODE = CODE;
        this.TYPE = TYPE;
    }

    public static MsgTypeEnum getEnum(int CODE) {
        MsgTypeEnum[] msgTypeEnums = values();
        for (MsgTypeEnum msgTypeEnum : msgTypeEnums) {
            if (msgTypeEnum.CODE == CODE) {
                return msgTypeEnum;
            }
        }
        return null;
    }

    public int getCODE() {
        return CODE;
    }

    public void setCODE(int CODE) {
        this.CODE = CODE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
}
