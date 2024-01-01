# 跨域

[Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#web.servlet.spring-mvc.cors)

1. @CrossOrigin 
2. 全局CORS配置

- Access-Control-Allow-Origin
  - Origin字段的具体值
  - *表示接受任意域名的请求
- Access-Control-Allow-Methods 
  - *表明服务器支持的所有跨域请求的方法。
  - 返回的是所有支持的方法，而不单是浏览器请求的那个方法。这是为了避免多次"预检"请求。
- Access-Control-Expose-Headers 
  - CORS请求时，XMLHttpRequest对象的getResponseHeader()方法只能拿到6个基本字段：Cache-Control、Content-Language、Content-Type、Expires、Last-Modified、Pragma。
  - 如果想拿到其他字段，就必须在Access-Control-Expose-Headers里面指定。
- Access-Control-Allow-Credentials 
  - 布尔值，表示是否允许发送Cookie.默认情况下，不发生Cookie，即：false。
  - 对服务器有特殊要求的请求，比如请求方法是PUT或DELETE，或者Content-Type字段的类型是application/json，这个值只能设为true。如果服务器不要浏览器发送Cookie，删除该字段即可。
- Access-Control-Max-Age 
  - 用来指定本次预检请求的有效期，单位为秒。在有效期间，不用发出另一条预检请求。

实际情况下，每次请求都会发出两次，一次OPTIONS，一次正常请求，此时配置Access-Control-Max-Age，可以避免每次都发出预检请求。

