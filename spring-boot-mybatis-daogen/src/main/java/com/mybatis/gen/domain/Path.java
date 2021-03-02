package com.mybatis.gen.domain;

import java.io.File;

/**
 * 位置
 *
 * @author lizhifu
 * @date 2021/3/2
 */
public class Path {
    public static void main(String[] args) {
        System.out.println("当前程序所在目录：" + System.getProperty("user.dir"));
        File f = new File(Path.class.getResource("/").getPath());
        System.out.println(f);
        String a = "com.a.b";
        System.out.println(a.replaceAll("\\.","/"));
    }
}
