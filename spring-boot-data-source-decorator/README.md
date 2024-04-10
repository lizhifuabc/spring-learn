# 记录SQL语句、SQL参数、慢SQL日志


为什么不在配置中包装DataSource？

您可以手动包装DataSource，而不是使用库，但是此库还提供

能够使用Spring Boot提供的@ConfigurationProperties（Spring.datasource.hikari.*，Spring.datasource.dbcp2.*）

通过部署属性decorator.datasource.enabled=true/false禁用装饰

通过spring-properties application.properties/yml配置代理，并通过在spring上下文中定义bean自定义代理

https://github.com/gavlyukovskiy/spring-boot-data-source-decorator
