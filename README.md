# spring-learn

记录在日常过程用经常使用的方法、工程，方便去查找DEMO

# spring-boot

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

