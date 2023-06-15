# spring-boot-mybatis
1. springboot整合mybatis
2. 插件机制

## 插件机制

```java
public interface Interceptor {
    // 具体的拦截方法,具体的业务逻辑
    Object intercept(Invocation var1) throws Throwable;
		// 拦截器要拦截的对象,Plugin.wrap 方法会自动判断拦截器的签名和被拦截对象的接口是否匹配，如果匹配，才会通过动态代理拦截目标对象。一般不需要重写
    default Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
		// 递插件的参数，可以通过参数来改变插件的行为
    default void setProperties(Properties properties) {
    }
}
```

- Executor(执行器):拦截SQL执行 
- ParameterHandler(参数处理):拦截传参行为
- ResultSetHandler(结果集处理):拦截结果集转换行为
- StatementHandler(语句处理):拦截SQL解析与转换行为

使用场景：

- SQL执行日志,监控SQL性能
- SQL防注入,校验传入SQL 
- 分页插件,简化分页开发 
- 多数据源,路由到不同数据源
- 缓存插件,二级缓存扩展
