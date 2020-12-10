package com.boot.zk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序
 * 依赖maven：https://github.com/apache/curator/tree/master/curator-examples/src/main/java
 * https://zookeeper.apache.org/
 * <li>启动zk:/zkServer.sh start</li>
 * <li>状态：./zkServer.sh status</li>
 * @author lizhifu
 * @date 2020/12/9
 */
@SpringBootApplication
public class SpringBootZkApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootZkApplication.class);
    }
}
