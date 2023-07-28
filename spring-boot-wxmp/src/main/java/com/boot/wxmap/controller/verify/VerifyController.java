package com.boot.wxmap.controller.verify;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户在网页授权页同意授权给公众号后，微信会将授权数据传给一个回调页面，
 * 回调页面需在此域名下，以确保安全可靠。
 *
 * @author lizhifu
 * @date 2021/1/25
 */
@RestController
public class VerifyController {
    @GetMapping("MP_verify_AuwycCaGeHEVaZ62.txt")
    public String verify(){
        return "demo";
    }
}
