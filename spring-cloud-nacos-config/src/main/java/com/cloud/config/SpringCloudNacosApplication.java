package com.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * spring 启动程序
 * 官方文档：https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html
 * 注意：配置信息必须写在 bootstrap.yml（bootstrap.properties）
 * <p>
 *     bootstrap.yml（bootstrap.properties）用来在程序引导时执行，应用于更加早期配置信息读取，
 *     如可以使用来配置application.yml中使用到参数等。
 *     <li>使用SpringCloud Config配置中心时，这时需要在bootstrap配置文件中添加连接到配置中心的配置属性来加载外部配置中心的配置信息</li>
 *     <li>一些固定的不能被覆盖的属性</li>
 *     <li>一些加密/解密的场景</li>
 * </p>
 * <p>
 *     application.yml（application.properties) 应用程序特有配置信息，
 *     可以用来配置后续各个模块中需使用的公共参数等,主要用于SpringBoot项目的自动化配置.
 * </p>
 * <p>
 *     bootstrap.yml 先于 application.yml 加载
 * </p>
 * @author lizhifu
 */
@SpringBootApplication
public class SpringCloudNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNacosApplication.class, args);
    }
}
