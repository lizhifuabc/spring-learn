package com.boot.properties.controller;

import com.boot.properties.properties.*;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 配置文件获取
 *
 * @author lizhifu
 * @date 2020/11/30
 */
@RestController
public class PropertiesController {
    @Resource
    private ApplicationProperties applicationProperties;
    @Resource
    private ExtendProperties extendProperties;
    @Resource
    private DemoProperties demoProperties;
    @Resource
    private DemoYmlProperties demoYmlProperties;
    @Resource
    private WechatProperties wechatProperties;
    @GetMapping("getProperties")
    public String getProperties(){
        return applicationProperties.toString()+
                extendProperties.toString()+
                demoProperties.toString()+
                demoYmlProperties.toString()+
                wechatProperties.toString();
    }
}
