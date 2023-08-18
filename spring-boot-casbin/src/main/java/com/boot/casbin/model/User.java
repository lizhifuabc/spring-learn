package com.boot.casbin.model;

/**
 * 用户
 *
 * @author lizhifu
 * @since 2023/8/18
 */
public class User {
    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
}
