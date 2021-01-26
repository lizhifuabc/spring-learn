package com.boot.wxmap.api.qrcode;

import lombok.Builder;
import lombok.Data;

/**
 * 生成带参数二维码
 * @author lizhifu
 */
@Data
@Builder
public class QRCodeParam {

	/**
	 * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
	 */
	private Long expire_seconds;
	
	/**
	 * 二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值
	 */
	private EQRActionName action_name;
	
	/**
	 * 二维码详细信息
	 */
	private ActionInfo action_info;
	/**
	 * access_token
	 */
	private String accessToken;
}
