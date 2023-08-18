package com.boot.casbin.model;

/**
 * TODO
 *
 * @author lizhifu
 * @since 2023/8/18
 */
public record Data(
        String source,
        String data,
        long timestamp,
        String state
) {
}