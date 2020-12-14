package com.boot.test;

import java.io.*;

public class BufferedByteCopyTest {

    public static void main(String[] args) {

        // 获取当前系统时间距离1970年1月1日0时0分0秒的毫秒数
        long g1 = System.currentTimeMillis();

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw;
        try {
            // 1.创建BufferedInputStream类型的对象与d:/02_IO流的框架结构.mp4文件关联
            //bis = new BufferedInputStream(new FileInputStream("d:/a.txt"));
            isr = new InputStreamReader(new FileInputStream("d:/a.txt"));
            // 2.创建BufferedOuputStream类型的对象与d:/IO流的框架结构.mp4文件关联
            //bos = new BufferedOutputStream(new FileOutputStream("d:/b.txt"));
            osw = new OutputStreamWriter(new FileOutputStream("d:/b.txt"));

            // 3.不断地从输入流中读取数据并写入到输出流中
            System.out.println("正在玩命地拷贝...");

            //byte[] bArr = new byte[8*1024];
            char[] bArr = new char[8*1024];
            int res = 0;
            while ((res = isr.read(bArr)) != -1) {
                //bos.write(bArr, 0, res);
                osw.write(bArr, 0, res);
                System.out.println("缓冲区大小是：" + bArr.length + "实际读取了" + res + "个字节，睡上1秒钟...");
                Thread.sleep(1000);
            }
            System.out.println("循环结束了...");
            Thread.sleep(10000);
            System.out.println("拷贝文件成功！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4.关闭流对象并释放有关的资源
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != bis) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        long g2 = System.currentTimeMillis();
        System.out.println("使用缓冲区拷贝视频文件消耗的时间为：" + (g2-g1)); // 44
    }
}
