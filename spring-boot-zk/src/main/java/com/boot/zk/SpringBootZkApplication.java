package com.boot.zk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序
 * <p>
 *     zk 分布式锁，其实可以做的比较简单，就是某个节点尝试创建临时 znode，
 *     此时创建成功了就获取了这个锁；这个时候别的客户端来创建锁会失败
 *     ，只能注册个监听器监听这个锁。释放锁就是删除这个 znode，一旦释放掉就会通知客户端，
 *     然后有一个等待着的客户端就可以再次重新加锁。
 * </p>
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
