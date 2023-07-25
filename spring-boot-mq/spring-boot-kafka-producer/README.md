# kafka

## 安装
brew install kafka

kafka的安装目录：/usr/local/Cellar/kafka
kafka的配置文件目录：/usr/local/etc/kafka
kafka服务的配置文件：/usr/local/etc/kafka/server.properties
zookeeper配置文件： /usr/local/etc/kafka/zookeeper.properties

## 修改Kafka服务配置文件

修改配置文件 /usr/local/etc/kafka/server.properties
解除注释: listeners=PLAINTEXT://localhost:9092

## 启动zookeeper

方式1：cd /usr/local/Cellar/kafka/3.3.1_1/
   ./bin/zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties
方式2： zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties

## 启动Kafka服务
方式1：cd /usr/local/Cellar/kafka/3.3.1_1/
   ./bin/kafka-server-start /usr/local/etc/kafka/server.properties
方式2：kafka-server-start /usr/local/etc/kafka/server.properties

## 创建Topic

```bash
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
```

