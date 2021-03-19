package com.boot.im.type;

/**
 * 消息动作
 * @author lizhifu
 */
public enum MsgActionEnum {
	
	CONNECT(0, "初始化连接"),
	CHAT(1, "聊天消息"),
	RECEIVE(2, "消息接收"),
	KEEP(3, "保持心跳");

	public final Integer TYPE;
	public final String CONTENT;

	MsgActionEnum(Integer TYPE, String CONTENT){
		this.TYPE = TYPE;
		this.CONTENT = CONTENT;
	}

	public static MsgActionEnum getEnum(Integer type) {
		MsgActionEnum[] msgActionEnums = values();
		for (MsgActionEnum msgActionEnum : msgActionEnums) {
			if (msgActionEnum.TYPE == type) {
				return msgActionEnum;
			}
		}
		return null;
	}

	public int getTYPE() {
		return TYPE;
	}

	public String getCONTENT() {
		return CONTENT;
	}
}
