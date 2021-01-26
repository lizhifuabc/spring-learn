package com.boot.wxmap.api.template;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * 发送模板消息
 *
 * @author lizhifu
 * @date 2021/1/26
 */
@Data
@Builder
public class SendTemplateMessageParam {
    /**
     * 用户
     */
    private String touser;
    /**
     * 模板
     */
    private String template_id;
    /**
     * url地址
     */
    private String url;
    /**
     * 小程序
     */
    private Miniprogram miniprogram;
    /**
     * 数据
     */
    private Map<String, DataItem> data;
    /**
     * access_token
     */
    private String accessToken;
}
