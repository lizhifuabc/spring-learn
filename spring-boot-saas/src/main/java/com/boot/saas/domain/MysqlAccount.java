package com.boot.saas.domain;

import lombok.Data;

/**
 * MysqlAccount
 *
 * @author lizhifu
 * @date 2020/12/22
 */
@Data
public class MysqlAccount {
    private String id;
    /**
     * 租户
     */
    private String tenant;
    private String url;
    private String username;
    private String password;
}
