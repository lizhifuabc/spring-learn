package com.boot.upload.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.boot.upload.config.AliyunOssConfig;
import com.boot.upload.service.AliyunOssService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * 阿里云oss服务
 *
 * @author lizhifu
 * @date 2020/12/16
 */
@Service
public class AliyunOssServiceImpl implements AliyunOssService {
    @Resource
    private AliyunOssConfig aliyunOssConfig;
    @Override
    public void upload(InputStream file, String fileName) {
        // 创建OSS实例。
        OSS ossClient = new OSSClientBuilder().build(
                aliyunOssConfig.getEndpoint(),
                aliyunOssConfig.getKeyid(),
                aliyunOssConfig.getKeysecret());
        ossClient.putObject(aliyunOssConfig.getBucketname(), fileName, file);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Override
    public String getUrl(String fileName) {
        // 创建OSS实例。
        OSS ossClient = new OSSClientBuilder().build(
                aliyunOssConfig.getEndpoint(),
                aliyunOssConfig.getKeyid(),
                aliyunOssConfig.getKeysecret());
        // 设置URL过期时间为1小时。
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(aliyunOssConfig.getBucketname(), fileName, expiration);
        // 关闭OSSClient。
        ossClient.shutdown();
        return url.toString();
    }
}
