package com.boot.upload;

import com.boot.upload.service.AliyunOssService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * AliyunOssService
 *
 * @author lizhifu
 * @date 2020/12/16
 */
@SpringBootTest
public class AliyunOssServiceTest {
    @Resource
    AliyunOssService aliyunOssService;
    @Test
    public void upload() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("/Users/lizhifu/Documents/遇风扬帆/常用图片/7ca4d0e450d85f0d6bd58f2d55dd287a.jpg");
        aliyunOssService.upload(fileInputStream,"12.jpg");
        String url = aliyunOssService.getUrl("12.jpg");
        System.out.println(url);
    }
}
