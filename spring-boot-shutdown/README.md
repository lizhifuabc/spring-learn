# SpringBoot优雅停机
## actuator方式

```java
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
 </dependency>
```

```properties
# 工程基础配置
server.port=8080
server.servlet.context-path=/learn
# 暴露节点
management.endpoint.shutdown.enabled=true
management.endpoints.web.exposure.include=shutdown
```

post请求：curl -X POST http://localhost:8080/learn/actuator/shutdown

## app.pid

```java
@SpringBootApplication
public class SpringBootLearnApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootLearnApplication.class);
        application.addListeners(new ApplicationPidFileWriter("/Users/lizhifu/Downloads/cloud/app.pid"));
        application.run();
    }
}
```

关闭命令：

xargs kill -9 ： xargs 命令是用来把前面命令的输出结果（PID）作为"kill -9"命令的参数，并执行该命令。"kill -9"会强行杀掉指定进程。 

```shell
cat /Users/lizhifu/Downloads/cloud/app.pid | xargs kill
```

