package com.spring.boot.security.login;

import lombok.Data;

/**
 * 登录
 *
 * @author lizhifu
 * @since 2024/4/7
 */
@Data
public class LoginParam {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
