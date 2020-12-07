package com.boot.nacos;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring 启动程序:nacos-boot不支持2.4.0,降级到2.1.0.RELEASE
 * https://github.com/nacos-group/nacos-spring-boot-project/issues/159
 * 下载nacos：https://nacos.io/zh-cn/docs/quick-start.html
 * 官方文档：https://nacos.io/zh-cn/docs/quick-start-spring-boot.html
 * <p>
 * Nacos 控制台添加配置：
 * <p>
 * Data ID：example
 * <p>
 * Group：DEFAULT_GROUP
 * <p>
 * 配置内容：useLocalCache=true
 * @author lizhifu
 */
@SpringBootApplication
//使用 @NacosPropertySource 加载 dataId 为 example 的配置源，并开启自动更新
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class SpringBootNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootNacosApplication.class, args);
    }
}
