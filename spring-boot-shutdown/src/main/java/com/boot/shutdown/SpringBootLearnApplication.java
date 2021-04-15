package com.boot.shutdown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

/**
 * 启动程序
 * @author lizhifu
 * @date 2021/04/15
 */
@SpringBootApplication
public class SpringBootLearnApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootLearnApplication.class);
        application.addListeners(new ApplicationPidFileWriter("/Users/lizhifu/Downloads/cloud/app.pid"));
        application.run();
    }
}
