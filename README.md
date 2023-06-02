# Spring Boot

| 项目            | 介绍 |
| --------------- | ---- |
| [spring-boot-web](./spring-boot-web) | Spring Boot Web 基础 Spring Boot test 自定义配置文件 多配置文件加载 actuator |
| [spring-boot-jpa](./spring-boot-jpa) | Spring Boot Jpa 框架基础 |
| [spring-boot-email](./spring-boot-email) | 1. 复杂邮件类型<br/>2. 发送文本邮件 |



# 基础

[java基础内容](./doc)

# Mybatis

[基于mybatis的Provider机制实现Mybatis通用Mapper](./spring-boot-mybatis-provider)

[基于mybatis的`XMLMapperBuilder`机制实现Mybatis通用Mapper](./spring-boot-mybatis-common)

[基于mybatis的分库分表实现思路:不涉及分布式事务](./spring-boot-mybatis-sharding)

[基于建表语句生成代码](./spring-boot-mybatis-gen)

[基于数据库生成代码](./spring-boot-mybatis-daogen)



# 进阶

[保障幂等性的几种方法：数据库+redis](./spring-boot-idempotent)

## 限流算法

[限流算法之计数器（固定窗口）算法](./spring-boot-guava-limit/固定窗口算法.md)

[限流算法之滑动窗口算法](./spring-boot-guava-limit/滑动窗口计数器算法.md)

限流算法之漏桶算法

[限流算法之令牌桶算法](./spring-boot-guava-limit/令牌桶算法.md)

## 缓存

[Java本地缓存之Caffine Cache](https://github.com/lizhifuabc/spring-learn/tree/main/spring-boot-caffeine)

[缓存雪崩](./spring-boot-redis-advanced/缓存雪崩.md)

[缓存击穿](./spring-boot-redis-advanced/缓存击穿.md)

[缓存穿透](./spring-boot-redis-advanced/缓存穿透.md)

[布隆过滤器](./spring-boot-redis-advanced/布隆过滤器.md)

# spring-learn

记录在日常过程用经常使用的方法、工程，方便去查找DEMO

[spring-boot-webflux函数式编程DEMO](./spring-boot-webflux)

[spring-boot-starter自定义starter](./spring-boot-starter)

[spring-cloud-loadbalancer简单实例](./spring-cloud-loadbalancer)

[spring-boot-cors：跨域的三种实现方式](./spring-boot-cors)

[spring-boot-thymeleaf：springboot整合thymeleaf](./spring-boot-thymeleaf)

[spring-boot-rabbitmq:整合rabbitmq](./spring-boot-mq/spring-boot-rabbitmq)

[spring-boot-upload：springboot文件上传](./spring-boot-upload)

[spring-boot-redis-redisson：springboot使用Redisson](./spring-boot-redis-redisson)

[spring-boot-elasticsearch：springboot使用elasticsearch](./spring-boot-elasticsearch)

[spring-boot-im：Springboot 整合 Netty](./spring-boot-im)

[spring-boot-shutdown：Springboot停机](./spring-boot-shutdown)

[spring-boot-api：Springboot通用api接口层模式](./spring-boot-api)

# 业务相关代码

[微信公众号对接](./spring-boot-wxmp)

[微信支付对接](./spring-boot-payment)

# spring-boot

[design-singleton](./spring-boot-design/design-singleton):单例模式

- 懒汉模式
- 双重锁校验
- 枚举单例
- 饿汉模式
- 类的内部类

[spring-boot-redis](./spring-boot-redis):redis

- RedisService简单工具service
- redis分布式锁：setIfAbsent
- redis分布式锁：lua脚本

[spring-boot-zk](./spring-boot-zk):zookeeper

- 伪分布式配置
- 分布式锁

[spring-boot-api](./spring-boot-api):api层设计

- 版本标记：`@ApiVersion`
- 统一异常处理：`GlobalExceptionHandler`
- 参数校验：`validation`
- 自定义参数校验：`@Amount`
- 接口版本号控制：`ApiVersionCondition`

[spring-boot-rabbitmq](./spring-boot-mq/spring-boot-rabbitmq)

- 延迟队列
- 死信队列
- 广播模式
- 主题模式
- 点对点模式

[spring-boot-mybatis-common](./spring-boot-mybatis-common): 通用mappe的一种实现思路

- 基于Mybatis解析xml文件
- 生成Document、Element，通过XMLMapperBuilder进行解析
- 使用demo：[spring-boot-mybatis-common-example](./spring-boot-mybatis-common-example)

[spring-boot-scheduling：定时任务](./spring-boot-scheduling)

[spring-boot-upload：文件上传](./spring-boot-upload)

- 阿里云OSS文件上传

[spring-boot-idempotent:幂等](./spring-boot-idempotent)

- 基于数据库保证幂等性
- 乐观锁：数据库表增加一个控制字段(version)
- redis实现幂等性

[spring-boot-pk:唯一主键生成方式](spring-boot-pk)

- Snowflake算法
- UUDI方式    
- 数据库自增
- mysql sequence

[spring-boot-hotaccount：热点账户](./spring-boot-hotaccount)

提供解决热点账户的几种思路。

[spring-boot-saas](./spring-boot-saas)

多租户模式下，数据源切换：采用共享DB，独立Schema的方式进行

## spring-cloud

[spring-cloud-nacos-config](./spring-cloud-nacos-config)

[spring-cloud-nacos-discovery](./spring-cloud-nacos-discovery)

# 其他
感谢[JetBrains](https://jb.gg/OpenSource) 提供的免费license
![JetBrains](./doc/assets/jetbrains-variant-4.svg)