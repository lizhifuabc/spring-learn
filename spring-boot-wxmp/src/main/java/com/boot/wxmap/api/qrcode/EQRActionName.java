package com.boot.wxmap.api.qrcode;

/**
 * 二维码类型
 * @author Hybomyth
 *
 */
public enum EQRActionName {
	
	/**
	 * 临时的整型参数值
	 */
	QR_SCENE("QR_SCENE"),
	
	/**
	 * 临时的字符串参数值
	 */
	QR_STR_SCENE("QR_STR_SCENE"),
	
	/**
	 * 永久的整型参数值
	 */
	QR_LIMIT_SCENE("QR_LIMIT_SCENE"),
	
	/**
	 * 永久的字符串参数值
	 */
	QR_LIMIT_STR_SCENE("QR_LIMIT_STR_SCENE")
	;

	private String name;
	
	private EQRActionName(String qrName){
		this.name = qrName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
