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
	 * 图片消息
	 */
	image("image"),
	/**
	 * 音频消息
	 */
	voice("voice"),
	/**
	 * 视频消息
	 */
	video("video"),
	
	/**
	 * 音乐消息
	 */
	music("music"),
	
	/**
	 * 短（小）视频消息
	 */
	shortvideo("shortvideo"),
	/**
	 * 地理位置消息
	 */
	location("location"),
	/**
	 * 链接消息
	 */
	link("link"),
	/**
	 * 事件消息
	 */
	event("event"), 
	
	/**
	 * 图文消息
	 */
	news("news");

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
