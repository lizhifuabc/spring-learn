# spring modulith

Spring Boot和Spring modulith的模块体系结构。

https://docs.spring.io/spring-modulith/reference/

单体架构 --- >>>  模块化单体架构(modulith) --- >>>  微服务

以**消息传递**的方式来实现同一个服务内部的**跨模块调用**(而不是直接互相调用API)。



## @ApplicationModuleListener

使用Spring Modulith提供的 @ApplicationModuleListener 接收应用程序事件。包含三种 Spring 注解：@Async、@Transactional和@TransactionalEventListener。

```java
@Async // 表示被标记的方法会被异步执行
@Transactional(
    propagation = Propagation.REQUIRES_NEW // 表示被标记的方法会在一个新的事务中运行。 
)
@TransactionalEventListener // 表示被标记的方法会作为事务事件监听器。 
@Documented
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationModuleListener {
    @AliasFor(
        annotation = Transactional.class,
        attribute = "readOnly"
    )
    // 设置事务是否为只读，默认为  false
    boolean readOnlyTransaction() default false;

    @AliasFor(
        annotation = EventListener.class,
        attribute = "id"
    )
    // 用于设置监听器的ID，默认为空字符串
    String id() default "";
}
```





参考内容：

https://github.com/piomin/sample-spring-modulith

https://piotrminkowski.com/2023/10/13/guide-to-modulith-with-spring-boot/