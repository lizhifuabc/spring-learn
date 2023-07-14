package com.boot.banner;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

/**
 * banner 配置
 *
 * @author lizhifu
 * @since 2023/7/14
 */
@Slf4j
public class BannerApplicationRunner implements ApplicationRunner {
    @Resource
    private ApplicationContext applicationContext;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Environment env = applicationContext.getEnvironment();
        log.info("系统服务启动成功,环境信息:{}",env);

    }
}
