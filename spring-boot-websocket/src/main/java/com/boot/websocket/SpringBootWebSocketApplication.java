package com.boot.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序 TODO 集群模式
 * WebSocket是一种在单TCP连接下，提供全双工通讯通道的计算机通讯协议
 * 整个过程分两个阶段
 * 第一个阶段：HTTP请求，握手阶段，变更协议
 * 第二个阶段：成功转换为WebSocket协议进行通讯传输。
 * @author lizhifu
 * @since  2021/3/29
 */
@SpringBootApplication
public class SpringBootWebSocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebSocketApplication.class);
    }
}
