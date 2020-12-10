package com.boot.properties.controller;

import com.boot.properties.properties.ApplicationProperties;
import com.boot.properties.properties.DemoProperties;
import com.boot.properties.properties.DemoYmlProperties;
import com.boot.properties.properties.ExtendProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    @GetMapping("getProperties")
    public String getProperties(){
        return applicationProperties.toString()+extendProperties.toString()+demoProperties.toString()+demoYmlProperties.toString();
    }
}
