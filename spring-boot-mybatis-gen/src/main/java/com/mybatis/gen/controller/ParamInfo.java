package com.mybatis.gen.controller;

import lombok.Data;

/**
 * ParamInfo
 *
 * @author lizhifu
 * @date 2021/2/1
 */
@Data
public class ParamInfo {
    private String sql;
    private String type;
    private String authorName = "lizhifu";
    private String packageName = "com.zfollow.open";
}
