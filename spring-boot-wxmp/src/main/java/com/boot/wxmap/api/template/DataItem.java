package com.boot.wxmap.api.template;

import lombok.Builder;
import lombok.Data;

/**
 * 模板数据
 *
 * @author lizhifu
 * @date 2021/1/26
 */
@Data
@Builder
public class DataItem {
    /**
     * 内容
     */
    private String value;
    /**
     * 颜色
     */
    private String color;
}
