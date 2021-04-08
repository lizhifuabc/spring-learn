---
title: SpringBoot集成Nacos
date: 2021-04-08 12:02:28
tags:
- SpringBoot
- Nacos
  categories: SpringBoot

---

# Nacos

版本：2.0.0

下载地址：https://github.com/alibaba/nacos/releases

集成源码地址：https://github.com/lizhifuabc/spring-learn/tree/main/spring-boot-nacos

<!--more-->

## 启动

单节点：

```java
unzip nacos-server-1.0.0.zip
cd nacos/bin 
sh startup.sh -m standalone
```

启动日志：

```java
lizhifudeMBP:bin lizhifu$ sh startup.sh -m standalone
/Library/Java/JavaVirtualMachines/jdk-11.0.9.jdk/Contents/Home/bin/java  -Xms512m -Xmx512m -Xmn256m -Dnacos.standalone=true -Dnacos.member.list= -Xlog:gc*:file=/Users/lizhifu/Downloads/cloud/nacos/logs/nacos_gc.log:time,tags:filecount=10,filesize=102400 -Dloader.path=/Users/lizhifu/Downloads/cloud/nacos/plugins/health,/Users/lizhifu/Downloads/cloud/nacos/plugins/cmdb -Dnacos.home=/Users/lizhifu/Downloads/cloud/nacos -jar /Users/lizhifu/Downloads/cloud/nacos/target/nacos-server.jar  --spring.config.additional-location=file:/Users/lizhifu/Downloads/cloud/nacos/conf/ --logging.config=/Users/lizhifu/Downloads/cloud/nacos/conf/nacos-logback.xml --server.max-http-header-size=524288
nacos is starting with standalone
nacos is starting，you can check the /Users/lizhifu/Downloads/cloud/nacos/logs/start.out
```

查看日志：

`tail -f /Users/lizhifu/Downloads/cloud/nacos/logs/start.out`

```java
izhifudeMBP:bin lizhifu$ tail -f /Users/lizhifu/Downloads/cloud/nacos/logs/start.out
2021-04-08 12:06:25,306 INFO Creating filter chain: any request, [org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@6d8b7ea9, org.springframework.security.web.context.SecurityContextPersistenceFilter@6f986501, org.springframework.security.web.header.HeaderWriterFilter@74a85515, org.springframework.security.web.csrf.CsrfFilter@602298b, org.springframework.security.web.authentication.logout.LogoutFilter@44084713, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@22ca1242, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@5beb6be2, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@5a39e554, org.springframework.security.web.session.SessionManagementFilter@6eeb29c0, org.springframework.security.web.access.ExceptionTranslationFilter@5835e24a]

2021-04-08 12:06:25,452 INFO Initializing ExecutorService 'taskScheduler'

2021-04-08 12:06:25,483 INFO Exposing 16 endpoint(s) beneath base path '/actuator'

2021-04-08 12:06:25,608 INFO Tomcat started on port(s): 8848 (http) with context path '/nacos'

2021-04-08 12:06:25,614 INFO Nacos started successfully in stand alone mode. use embedded storage
```

# 地址

http://localhost:8848/nacos/#/login

nacos/nacos

# 配置示例

![image-20210408121720456](SpringBootNacos/image-20210408121720456.png)

# java示例

```java
@Controller
@RequestMapping("config")
public class ConfigController {
    /**
     * 通过 Nacos 的 @NacosValue 注解设置属性值。
     */
    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }
}
```

