package com.boot.wxmap.api.qrcode;

import lombok.Builder;
import lombok.Data;

/**
 * 二维码详细信息
 * @author lizhifu
 */
@Data
@Builder
public class ActionInfo {

	/**
	 * 场景详情
	 */
	private Scene scene;
}
