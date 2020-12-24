package com.boot.api.controller;

import com.boot.api.annotation.ApiVersion;
import com.boot.api.base.ReturnCode;
import com.boot.api.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * index
 *
 * @author lizhifu
 * @date 2020/12/24
 */
@RestController
public class IndexController {
    @ApiVersion
    @GetMapping(value = "{version}/")
    public String index(int i){
        if(i >= 10){
            throw new CustomException(ReturnCode.SYSTEM_ERROR.getCode(),ReturnCode.SYSTEM_ERROR.getMsg());
        }
        return "index";
    }
    @ApiVersion(value = 2)
    @GetMapping(value = "{version}/")
    public String index2(int i){
        if(i >= 10){
            throw new CustomException(ReturnCode.SYSTEM_ERROR.getCode(),ReturnCode.SYSTEM_ERROR.getMsg());
        }
        return "index2";
    }
}
