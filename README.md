# spring-learn

记录在日常过程用经常使用的方法、工程，方便去查找DEMO

# spring-boot

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

## spring-cloud
[spring-cloud-nacos-config](./spring-cloud-nacos-config)

[spring-cloud-nacos-discovery](./spring-cloud-nacos-discovery)

