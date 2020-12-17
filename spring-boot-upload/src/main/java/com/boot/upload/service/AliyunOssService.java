package com.boot.upload.service;

import java.io.InputStream;

/**
 * 阿里云oss服务
 *
 * @author lizhifu
 * @date 2020/12/16
 */
public interface AliyunOssService {
    /**
     * 文件流上传
     * @param file
     * @param fileName
     */
    public void upload(InputStream file, String fileName);

    /**
     * 获取链接
     * @param fileName
     * @return
     */
    public String getUrl(String fileName);
}
