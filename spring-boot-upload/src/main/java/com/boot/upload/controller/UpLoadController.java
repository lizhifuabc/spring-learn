package com.boot.upload.controller;

import com.boot.upload.service.AliyunOssService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import java.io.IOException;

/**
 * 文件上传
 *
 * @author lizhifu
 * @date 2020/12/17
 */
@Controller
public class UpLoadController {
    @Resource
    private AliyunOssService aliyunOssService;
    @GetMapping("/upload/index")
    public String index(){
        return "upload";
    }
    @PostMapping("/upload/upload")
    public String upload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        String fileName = file.getOriginalFilename();
        aliyunOssService.upload(file.getInputStream(),fileName);
        model.addAttribute("url",aliyunOssService.getUrl(fileName));
        return "uploadResult";
    }
}