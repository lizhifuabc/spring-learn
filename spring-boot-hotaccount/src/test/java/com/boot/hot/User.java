package com.boot.hot;

import lombok.Data;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * User
 *
 * @author lizhifu
 * @date 2020/12/21
 */
@Data
public class User {
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
}
