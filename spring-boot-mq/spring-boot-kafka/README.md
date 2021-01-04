# 启动zookeeper

zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties

## Mac 安装 kafka

brew install kafka

#  修改Kafka服务配置文件

修改配置文件 /usr/local/etc/kafka/server.properties

解除注释: listeners=PLAINTEXT://localhost:9092

### 启动Kafka服务

kafka-server-start /usr/local/etc/kafka/server.properties

# 创建Topic

```bash
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
```

