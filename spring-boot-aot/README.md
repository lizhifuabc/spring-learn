# AOT
AOT(Ahead-Of-Time Compilation)技术,可以提前编译Spring应用为本地代码
1. 启动时间更快
   预编译后的应用无需JIT即时编译,可以省去启动时的编译开销,大幅提升启动速度。
2. 减小内存使用
   AOT预编译后可以直接生成机器码,运行时无需加载大量JAR包和类元数据,可以减少内存消耗。
3. 原生编译提高性能
   AOT使用原生编译,生成的代码更优化,运行时性能可以得到提升。
4. 减小应用大小
   可以将编译后的机器码打包进可执行文件,减少发布包大小。
5. 支持原生编译
   可以将Spring应用编译为本地应用,不再依赖JVM,expanding to non-JVM environments.
## Spring Boot 3.x的AOT功能
1. 升级到Spring Boot 3.0或更高版本
2. 在插件中添加AOT配置,如<plugins><plugin>spring-boot-maven-plugin</plugin></plugins>
3. 编译时增加`-Pnative`参数即可生成编译后的本地应用
4. 运行编译后的本地应用,无需再提供JAR包,直接运行可执行文件即可。