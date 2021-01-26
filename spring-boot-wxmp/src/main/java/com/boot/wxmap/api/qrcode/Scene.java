package com.boot.wxmap.api.qrcode;

import lombok.Builder;
import lombok.Data;

/**
 * 场景
 * @author lizhifu
 */
@Data
@Builder
public class Scene {

	/**
	 * 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	 */
	private Long scene_id;
	
	/**
	 * 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
	 */
	private String scene_str;
}
