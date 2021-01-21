package com.boot.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * 多文件上传
 *
 * @author lizhifu
 * @date 2021/1/21
 */
@Controller
public class MultiUploadController {
    private static final String path = "/Users/lizhifu/Downloads/cloud/";
    @GetMapping("/multi/index")
    public String index(){
        return "multi";
    }
    @PostMapping("/multi/upload")
    @ResponseBody
    public String upload(@RequestPart MultipartFile[] files) throws IOException {
        StringBuffer result = new StringBuffer();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String filePath = path + fileName;
            File dest = new File(filePath);
            Files.copy(file.getInputStream(), dest.toPath());
            result.append(dest.getAbsolutePath()).append("<br>");
        }
        return "图片上传成功："+result.toString();
    }
}
