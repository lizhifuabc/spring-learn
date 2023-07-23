# Spring

| 项目            | 介绍 |
| --------------- | ---- |
| [spring-boot-web](./spring-boot-web) | Spring Boot Web 基础<br>Spring Boot test<br>自定义配置文件 <br/>多配置文件加载 actuator |
| [spring-boot-jpa](./spring-boot-jpa) | Spring Boot Jpa 框架基础 |
| [spring-boot-email](./spring-boot-email) | 1. 复杂邮件类型<br/>2. 发送文本邮件 |
| [spring-boot-log](./spring-boot-log) | 1. 日志打印<br/>2. 自定义操作日志记录注解，日志切面配置<br/>3. 基于 controller package 的日志记录<br/>4. 定义logback-spring.xml配置文件 |
| [spring-boot-mybatis](./spring-boot-mybatis) | mybatis |
| [spring-boot-init](./spring-boot-init) | 启动加载<br/>1. CommandLineRunner（注解的执行优先级是按value值从小到大顺序）<br/>2. InitializingBean<br/>3. @PostConstruct |
| [spring-boot-async](./spring-boot-async) | spring boot 异步任务 |
| [spring-boot-elasticsearch](./spring-boot-elasticsearch) | elasticsearch |
| [spring-boot-lombok](./spring-boot-lombok) | Spring Boot lombok，lombok使用实例 |
| [spring-boot-event](./spring-boot-event) | 1. google eventbus 事件总线<br/>2. spring application event 事件 |
| [spring-boot-properties](./spring-boot-properties) | 配置文件相关 |
| [spring-boot-guava-limit](./spring-boot-guava-limit) | *基于*  guava  限流 |
| [spring-boot-task](./spring-boot-task) | 1. 定时任务<br/>2. 指定线程池 |
| [spring-boot-websocket](./spring-boot-websocket) | 1. 消息推送<br/>2. 聊天室 |
| [spring-boot-upload](./spring-boot-upload) | *单文件上传*、*多文件上传*、阿里云oss文件上传 |
| [spring-batch](./spring-batch) | Spring Batch 轻量级的批量处理框架 |
| [spring-boot-mybatis-common](./spring-boot-mybatis-common) | 基于mybatis的`XMLMapperBuilder`机制实现Mybatis通用Mapper |
| [spring-boot-mybatis-common-example](./spring-boot-mybatis-common-example) | 基于mybatis的`XMLMapperBuilder`机制实现Mybatis通用Mapper使用案例 |
| [spring-boot-saas](./spring-boot-saas) | 多租户模式下，数据源切换：采用共享DB，独立Schema的方式进行 |
| [spring-boot-webflux](./spring-boot-webflux) | webflux函数式编程DEMO |
| [spring-boot-starter](./spring-boot-starter) | 自定义starter |
| [spring-boot-cors](./spring-boot-cors) | 跨域的三种实现方式 |
| [spring-boot-upload](./spring-boot-upload) | springboot文件上传、阿里云OSS文件上传 |
| [spring-boot-shutdown](./spring-boot-shutdown) | Springboot停机 |
| [spring-boot-idempotent](./spring-boot-idempotent) | 幂等：<br>基于数据库保证幂等性 乐观锁<br>redis实现幂等性 |
| [spring-boot-api](./spring-boot-api) | Springboot通用api接口层模式<br>validation 接口参数验证<br>自定义 validator 验证器 <br>API 接口版本控制<br>统一异常处理<br>自定义异常处理<br>统一返回结果 |
| [spring-cloud-openfeign](./spring-cloud-openfeign) | 开启日志<br>通信组件<br>数据压缩<br>负载均衡<br>HTTP Interface将 HTTP 服务，定义成一个包含特定注解标记的方法的 Java 接口 |
| [spring-boot-actuator](./spring-boot-actuator) | micrometer<br>grafana<br>Zipkin<br>自定义指标 |
| [spring-boot-log4j2](./spring-boot-log4j2) | log4j2 |
| [spring-boot-multitenant](./spring-boot-multitenant) | **分区（Partitioned）数据**：不同租户的数据都在一张表里，通过一个值（tenantId）来区分不同的租户。<br>**分结构（Schema）**：不同的租户数据放置在相同数据库实例的不同结构（Schema）中。<br>**分数据库（Database）**：不同租户的数据放置在不同的数据中。 |
| [spring-boot-flyway](./spring-boot-flyway) | Flyway 数据库版本管理工具 |
| [spring-boot-banner](./spring-boot-banner) | 自定义 banner starter 打印相关信息 banner.txt |
| [spring-boot-p6spy](./spring-boot-p6spy) | 开发使用，不建议生产直接使用 |
| [spring-boot-mybatis-flex](./spring-boot-mybatis-flex) | MyBatis-Flex 一个优雅的 MyBatis 增强框架。使用案例 |



# 基础

[java基础内容](./doc)

# Mybatis

[基于mybatis的Provider机制实现Mybatis通用Mapper](./spring-boot-mybatis-provider)

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

[spring-cloud-loadbalancer简单实例](./spring-cloud-loadbalancer)

[spring-boot-thymeleaf：springboot整合thymeleaf](./spring-boot-thymeleaf)

[spring-boot-rabbitmq:整合rabbitmq](./spring-boot-mq/spring-boot-rabbitmq)

[spring-boot-redis-redisson：springboot使用Redisson](./spring-boot-redis-redisson)

[spring-boot-elasticsearch：springboot使用elasticsearch](./spring-boot-elasticsearch)

[spring-boot-im：Springboot 整合 Netty](./spring-boot-im)

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

[spring-boot-pk:唯一主键生成方式](spring-boot-pk)

- Snowflake算法
- UUDI方式    
- 数据库自增
- mysql sequence

[spring-boot-hotaccount：热点账户](./spring-boot-hotaccount)

提供解决热点账户的几种思路。





## spring-cloud

[spring-cloud-nacos-config](./spring-cloud-nacos-config)

[spring-cloud-nacos-discovery](./spring-cloud-nacos-discovery)

# 其他
感谢[JetBrains](https://jb.gg/OpenSource) 提供的免费license
![JetBrains](./doc/assets/jetbrains-variant-4.svg)