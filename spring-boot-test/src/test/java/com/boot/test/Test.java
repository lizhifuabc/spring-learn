package com.boot.test;

import java.io.*;

/**
 * 测试代码
 *
 * @author lizhifu
 * @date 2020/12/13
 */
public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {

        FileInputStream fis=new FileInputStream("/Users/lizhifu/Downloads/cloud/file.txt");
        BufferedInputStream bis=new BufferedInputStream(fis);

        FileOutputStream outputStream = new FileOutputStream("/Users/lizhifu/Downloads/cloud/file2.txt");
        BufferedOutputStream bos=new BufferedOutputStream(outputStream);

        int size = 0;
        byte[] buffer = new byte[1024];
        while((size = bis.read(buffer)) != -1){
            System.out.println("开始写入"+size);
            bos.write(buffer, 0, size);
        }
        System.out.println("写入结束");
        Thread.sleep(1000000000);
        System.out.println("程序结束");
    }
}
