# springboot视图

SpringBoot框架对很多常用的模板引擎技术（FreeMarker、Thymeleaf、Mustache等等）提供了支持。

# Thymeleaf

Thymeleaf是一个XML/XHTML/HTML5模板引擎，可用于Web与非Web环境中的应用开发。

### 标签

在HTML页面上使用Thymeleaf标签，Thymeleaf 标签能够动态地替换掉静态内容，使页面动态展示。

| **th**:标签 | **备注**                             |
| ----------- | ------------------------------------ |
| th:insert   | 替换内容到引入的文件    |
| th:replace  | 页面片段包含 |
| th:each     | 遍历   |
| th:if       | 条件判断                   |
| th:unless   | 条件判断                   |
| th:switch   | 条件判断           |
| th:case     | 条件判断           |
| th:value    | 属性值修改       |
| th:href     | 链接地址                     |
| th:src      | 链接地址                     |
| th:text     | 显示的文本内容         |

### 表达式

Thymeleaf模板引擎提供了多种标准表达式语法

| 说明     | 表达式语法 |
| -------- | ---------- |
| 变量     | ${...}     |
| 选择变量 | *{...}     |
| 消息     | #{...}     |
| 链接URL  | @{...}     |
| 片段     | ~{...}     |

- 变量${...}

  ```html
  ctx:上下文对象
  vars:上下文变量
  locale:上下文区域设置
  request:HttpServletRequest对象
  response:HttpServletResponse对象 
  session:HttpSession对象
  servletContext:ServletContext对象
  
  <span th:text="${#locale.country}">zh</span>
  ```

- 选择变量

  ```html
  <div th:object="${user}">
  <p>titile: <span th:text="*{name}">名称</span></p>
  </div>
  ```

- 消息

  国际化设置

- 链接

  ```html
  <a th:href="@{http://localhost:8080/user/user(id=${user.id})}">user</a>
  <a th:href="@{/user/user(id=${user.id})}">user</a>
  ```

- 片段

  ```html
  <div th:insert="~{demo::demo}"></div>
  <div th:replace="~{demo::demo}"></div>
  ```

  demo为模板名称，Thymeleaf会自动查找“/resources/templates/”目录下的demo模板，demo为片段名称

### spring boot 中使用

#### maven

```java
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```

#### application.properties
```properties
# Thymeleaf配置
# 缓存
spring.thymeleaf.cache = true
# 编码
spring.thymeleaf.encoding = UTF-8
# 模式
spring.thymeleaf.mode = HTML5
# 页面存放路径
spring.thymeleaf.prefix = classpath:/templates/
# 页面名称的后缀
spring.thymeleaf.suffix = .html
```



代码位置：