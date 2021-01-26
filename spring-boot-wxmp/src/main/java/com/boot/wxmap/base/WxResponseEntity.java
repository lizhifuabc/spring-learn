package com.boot.wxmap.base;

import lombok.Data;

/**
 * 微信响应实体
 *
 * @author lizhifu
 * @date 2021/1/26
 */
@Data
public class WxResponseEntity<T> {
    /**
     * body
     */
    private T body;
}
