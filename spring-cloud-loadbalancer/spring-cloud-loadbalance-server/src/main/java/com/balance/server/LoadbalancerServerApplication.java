package com.balance.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


/**
 * 启动
 * 为了查看负载均衡的效果，可以运行多个实例
 * java -jar xxx.jar --server.port=8101
 * https://spring.io/guides/gs/spring-cloud-loadbalancer/
 * @author lizhifu
 * @date 2021/1/15
 */
@SpringBootApplication
@RestController
public class LoadbalancerServerApplication {
    private static Logger log = LoggerFactory.getLogger(LoadbalancerServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LoadbalancerServerApplication.class, args);
    }

}
