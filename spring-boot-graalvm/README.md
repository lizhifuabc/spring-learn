# GraalVM

## 1. 简介

GraalVM是一个兼容HotSpot和JDK的新一代Java虚拟机。
- 可以将Java应用程序编译成本地可执行文件,可以提高启动时间和减少内存使用。这就是所谓的“本机映像”功能。
- 支持其他语言,如JavaScript,Python,Ruby和R。这些语言可以与Java代码相互调用和集成。
- 新的即时编译器,可以提高Java应用程序的性能。
- 多语言虚拟机,支持运行不同语言的应用程序。
- 更好的内存使用和启动时间,可提高Java应用程序的性能。
- 提供了新的工具和API来分析Java应用程序的性能和内存使用情况。这有助于更轻松地优化您的应用程序。
- 支持创建本机映像,这些映像可以在不需要安装JDK的情况下运行Java应用程序。
- 具有 polyglot 编程能力，您可以在同一程序中混合Java,JavaScript,Python,Ruby和R代码。
- 可以根据需要选择不同的Java编译器(GraalVM Compiler或HotSpot Compiler)。提供了更多的灵活性。
- 
总之,GraalVM的目标是成为下一代多语言虚拟机,可以大大简化在同一平台上运行不同语言的应用程序。它提供了许多有助于优化和改进Java应用程序性能的功能。

打包成本地镜像：

1、打成jar包:  注意修改 jar包内的 MANIFEST.MF 文件，指定Main-Class的全类名
      - java -jar xxx.jar 就可以执行。
      - 切换机器，安装java环境。默认解释执行，启动速度慢，运行速度慢
2、打成本地镜像（可执行文件）：
      - native-image -cp  你的jar包/路径  你的主类  -o 输出的文件名
      - native-image -cp boot3-15-aot-common-1.0-SNAPSHOT.jar com.atguigu.MainApplication -o Demo

并不是所有的Java代码都能支持本地打包；
SpringBoot保证Spring应用的所有程序都能在AOT的时候提前告知graalvm怎么处理？

 - 动态能力损失：反射的代码：（动态获取构造器，反射创建对象，反射调用一些方法）；
   解决方案：额外处理（SpringBoot 提供了一些注解）：提前告知 graalvm 反射会用到哪些方法、构造器
 - 配置文件损失：
   解决方案：额外处理（配置中心）：提前告知 graalvm 配置文件怎么处理
 - 【好消息：新版GraalVM可以自动进行预处理，不用我们手动进行补偿性的额外处理。】
 二进制里面不能包含的，不能动态的都得提前处理；

 不是所有框架都适配了 AOT特性；Spring全系列栈适配OK

application.properties
a(){
    //ssjsj  bcde();
    //提前处理
}