package com.boot.lombok.example;

import lombok.Cleanup;

import java.io.*;

/**
 * https://projectlombok.org/features/Cleanup
 * 可以自动关闭资源
 * @author lizhifu
 * @date 2020/12/29
 */
public class CleanupExample {
    public static void normal(){
        String str = "Hello";
        ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        while (true) {
            int r = 0;
            try {
                r = in.read(b);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (r == -1) break;
            out.write(b, 0, r);
        }
        String outStr = out.toString();
        System.out.println(outStr);
    }
    public static void main(String[] args) throws IOException {
        String str = "Hello";
        @Cleanup ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
        @Cleanup ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        while (true) {
            int r = in.read(b);
            if (r == -1) break;
            out.write(b, 0, r);
        }
        String outStr = out.toString();
        System.out.println(outStr);
    }
}
