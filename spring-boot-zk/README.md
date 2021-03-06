# 伪分布式配置

1. 解压完安装包后，进入zookeeper/conf/目录下，将改目录下的zoo_sample.cfg配置文件拷贝3份，依次 zoo1.cfg zoo2.cfg zoo3.cfg

2. 修改三个文件

   ```
   依次配置
   clientPort=2181--2182
   dataDir=dataDir=/Users/lizhifu/Downloads/cloud/zookeeper1--zookeeper3(记得创建文件)
   #server.1=IP:2888(数据同步端口):3888(Leader选举端口)
   server.1=localhost:2888:3888
   server.2=localhost:2889:3889
   server.3=localhost:2890:3890
   ```

3. 在各dataDir文件下创建名为myid的文件，文件内容对应服务器编号1\2\3

4. 依次启动服务,服务按照次序启动，启动时的选举算法是依次投票，这里的leader顺理就是2号配置文件启动的服务。其他的都是fllower,当启动第一台服务器的时候查看状态是不可用的，应为集群中节点未在半数以上。 集群中奇数和偶数对故障的容忍度是一致的，所以建议配置奇数个，并不是必须奇数。

   ```
   # 记得关闭防火墙
   ./zkServer.sh start /Users/lizhifu/Downloads/cloud/apache-zookeeper-3.6.2-bin/conf/zoo1.cfg
   ./zkServer.sh start /Users/lizhifu/Downloads/cloud/apache-zookeeper-3.6.2-bin/conf/zoo2.cfg
   ./zkServer.sh start /Users/lizhifu/Downloads/cloud/apache-zookeeper-3.6.2-bin/conf/zoo3.cfg
   ```

5. 查看状态

   ```
   sh zkServer.sh status /Users/lizhifu/Downloads/cloud/apache-zookeeper-3.6.2-bin/conf/zoo1.cfg
   sh zkServer.sh status /Users/lizhifu/Downloads/cloud/apache-zookeeper-3.6.2-bin/conf/zoo2.cfg
   sh zkServer.sh status /Users/lizhifu/Downloads/cloud/apache-zookeeper-3.6.2-bin/conf/zoo3.cfg
   ```

6. 启动客户端 sh zkCli.sh -server 127.0.0.1:port

常见问题：

java.net.ConnectException: Connection refused (Connection refused):记得三个都要启动

# 描述

# 概念

1. 分布式情况下，需要半数以上节点存活
2. 不适合保存大量数据，因为数据是保存在节点内，主要功能还是分布式的调度，并不是存储数据
3. 读的性能要远远大于写，因为写需要所有的服务器进行同步
4. 核心：数据管理(读写)+监控节点事件

## 为什么是奇数台服务器

主要是基于zookeeper的容错机制：存活的数量>宕机的数量。

比如一共有3台服务器，宕机了1台，此时存活2>宕机1，还是可用状态。如果宕机两台，存活1<宕机2，不可用。

比如一共有4台服务器，宕机了1台，此时存活2>宕机1，还是可用状态。如果宕机两台，存活2<宕机2，不可用。

综上：使用奇数台服务器。

## 分布式锁

zookeeper为什么可以做分布式锁？

1. 有序节点
2. 临时节点：会话结束或者会话超时可以自动删除
3. 事件监听



