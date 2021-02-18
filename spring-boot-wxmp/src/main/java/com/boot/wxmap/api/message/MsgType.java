package com.boot.wxmap.api.message;

/**
 * 消息类型
 * @author lizhifu
 */
public enum MsgType {

	/**
	 * 文本消息
	 */
	TEXT("text"),
	/**
	 * 链接消息
	 */
	LINK("link"),
	/**
	 * 消息转发到客服
	 */
	TRANSFER_CUSTOMER_SERVICE("transfer_customer_service");

	/**
	 * 消息类型名称
	 */
	private String name;

	/**
	 * 构造函数
	 * 
	 * @param name
	 */
	private MsgType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.valueOf(this.name);
	}
}
