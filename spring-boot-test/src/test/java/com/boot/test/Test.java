package com.boot.test;

import java.io.*;

/**
 * 测试代码
 *
 * @author lizhifu
 * @date 2020/12/13
 */
public class Test {
    public static void main(String[] args) throws IOException {
        FileOutputStream outputStream = new FileOutputStream("/Users/lizhifu/Downloads/cloud/file.txt");
        outputStream.write("hello".getBytes());
    }
}
