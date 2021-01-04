# 概述

> 单例模式(Singleton Pattern)：单例模式确保某一个类只有一个实例，而且自行实例化并向整个系统提供这个实例，这个类称为单例类，它提供全局访问的方法。
>
> 单例模式的要点有三个：一是某个类只能有一个实例；二是它必须自行创建这个实例；三是它必须自行向整个系统提供这个实例。单例模式是一种对象创建型模式。单例模式又名单件模式或单态模式。

Mybatis中的单例模式：

1. ErrorContext：记录线程的执行环境错误信息
2. LogFactory：Mybatis的日志工厂

#ErrorContext

核心代码：

```java
public class ErrorContext {
  //ThreadLocal对象
  private static final ThreadLocal<ErrorContext> LOCAL = ThreadLocal.withInitial(ErrorContext::new);
  //私有构造函数
  private ErrorContext() {
  }
  //实例化
  public static ErrorContext instance() {
    return LOCAL.get();
  }
}
```

# LogFactory

核心代码：

```java
public final class LogFactory {
	private LogFactory() {
    // disable construction
  }
  //根据传入的类来构建Log
  public static Log getLog(Class<?> clazz) {
    return getLog(clazz.getName());
  }
  //根据传入的类名来构建Log
  public static Log getLog(String logger) {
    try {
      //构造函数，参数必须是一个，为String型，指明logger的名称
      return logConstructor.newInstance(logger);
    } catch (Throwable t) {
      throw new LogException("Error creating logger for logger " + logger + ".  Cause: " + t, t);
    }
  }
}
```

