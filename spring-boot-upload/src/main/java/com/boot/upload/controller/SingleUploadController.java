package com.boot.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * 单文件上传
 *
 * @author lizhifu
 * @date 2021/1/21
 */
@Controller
public class SingleUploadController {
    private static final String path = "/Users/lizhifu/Downloads/cloud/";
    @GetMapping("/single/index")
    public String index(){
        return "single";
    }
    @PostMapping("/single/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = path + fileName;
        File dest = new File(filePath);
        Files.copy(file.getInputStream(), dest.toPath());
        return "图片上传成功："+dest.getAbsolutePath();
    }
}
