package com.boot.casbin.service;

import com.boot.casbin.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户
 *
 * @author lizhifu
 * @since 2023/8/18
 */
@Service
@Slf4j
public class UserService {
    private final Map<String, User> sessions;
    private final Map<String, User> users;
    public UserService() {
        this.sessions = new ConcurrentHashMap<>();
        this.users = new HashMap<>();
        this.users.put("admin", new User("admin", "pwd"));
        this.users.put("user", new User("user", "pwd"));
    }

    /**
     * 登录
     * @param sessionId session
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    public Optional<User> login(String sessionId, String username, String password) {
        User u = users.get(username);
        if (u != null && u.verifyPassword(password)) {
            log.info("登录成功: {} {}", sessionId, username);
            sessions.put(sessionId, u);
            return Optional.of(u);
        }
        log.info("登录失败: {} {}", sessionId, username);
        return Optional.empty();
    }

    /**
     * 是否登录
     * @param sessionId session
     * @return 用户
     */
    public Optional<User> isAuthenticated(String sessionId) {
        return Optional.ofNullable(sessions.get(sessionId));
    }

    /**
     * 登出
     * @param sessionId session
     */
    public void logout(String sessionId) {
        log.info("logout: {}", sessionId);
        sessions.remove(sessionId);
    }
}
