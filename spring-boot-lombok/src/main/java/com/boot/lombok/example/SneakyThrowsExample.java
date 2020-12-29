package com.boot.lombok.example;

import lombok.Lombok;
import lombok.SneakyThrows;

import java.io.UnsupportedEncodingException;

/**
 * https://projectlombok.org/features/SneakyThrows
 *
 * @author lizhifu
 * @date 2020/12/29
 */
public class SneakyThrowsExample {
    @SneakyThrows(UnsupportedEncodingException.class)
    public static String utf8ToString(byte[] bytes) {
        return new String(bytes, "UTF-8");
    }
    public String utf8ToString2(byte[] bytes) {
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw Lombok.sneakyThrow(e);
        }
    }
    public static void main(String[] args) {
        byte[] str = "Hello".getBytes();
        System.out.println(utf8ToString(str));
    }
}
