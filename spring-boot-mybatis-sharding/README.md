# 机制
> spring拦截器
> 
> mybatis拦截器
# 核心
1. DataSourceAdvice
2. MybatisTabbleInterceptor
# 注意事项
在使用spring拦截器时，由于事务注解@Transactional也是基于aop,所以在自定义切面的时候要添加@Order(-1)，否则无法进行动态数据源的切换