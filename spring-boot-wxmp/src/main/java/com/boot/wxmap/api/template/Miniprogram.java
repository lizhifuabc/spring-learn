package com.boot.wxmap.api.template;

import lombok.Builder;
import lombok.Data;

/**
 * 小程序参数
 *
 * @author lizhifu
 * @date 2021/1/26
 */
@Data
@Builder
public class Miniprogram {
    /**
     * appId
     */
    private String appId;
    /**
     * 路径
     */
    private String pagePath;
}
