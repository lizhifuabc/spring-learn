# Reactive响应式/反应式编程

Reactive响应式(反应式)编程是一种新的编程风格，其特点是异步或并发、事件驱动、推送PUSH机制以及观察者模式的衍生。

# SpringBoot

Spring WebFlux 是一个异步非阻塞式的 Web 框架， 可以运行在支持 Servlet 3.1 非阻塞IO的Servlet 容器上，或者其他异步运行环境，如 Netty、Undertow。它可以充分利用多核 CPU 资源去处理大量的并发请求，非常适合低延迟、高吞吐量的应用场景。

- SpringBoot WebMVC应用，都需要依赖spring-boot-starter-web ，内部包含Tomcat
- WebFlux不需要依赖spring-boot-starter-web 只依赖 spring-boot-starter-webflux 即可
- WebFlux内部使用Netty来运行应用，Netty是基于异步和事件驱动的

#  Flux和Mono

Flux 和 Mono 都是 Publisher （发布者也可称为被观察者），也就是数据流的源头。

- Mono 表示的是包含 0 或者 1 个元素的异步序列
- Flux 表示的是包含 0 到 N 个元素的异步序列