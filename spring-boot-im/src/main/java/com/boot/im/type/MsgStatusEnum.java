package com.boot.im.type;

/**
 * 消息状态
 * @author lizhifu
 */
public enum MsgStatusEnum {

    UNREAD(0, "未读"),
    READ(1, "已读");

    public final int CODE;
    public final String TYPE;

    MsgStatusEnum(int CODE, String TYPE) {
        this.CODE = CODE;
        this.TYPE = TYPE;
    }

    public static MsgStatusEnum getEnum(int CODE) {
        MsgStatusEnum[] msgTypeEnums = values();
        for (MsgStatusEnum msgTypeEnum : msgTypeEnums) {
            if (msgTypeEnum.CODE == CODE) {
                return msgTypeEnum;
            }
        }
        return null;
    }

    public int getCODE() {
        return CODE;
    }

    public String getTYPE() {
        return TYPE;
    }
}
