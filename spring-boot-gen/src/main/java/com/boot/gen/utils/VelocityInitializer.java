package com.boot.gen.utils;

import org.apache.velocity.app.Velocity;

import java.util.Properties;

/**
 * VelocityEngine初始化
 *
 * @author lizhifu
 * @date 2020/12/8
 */
public class VelocityInitializer {
    /**
     * 初始化vm方法
     */
    public static void initVelocity()
    {
        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
    }
}
