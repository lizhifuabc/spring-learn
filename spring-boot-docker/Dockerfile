# FROM:基础镜像,这里用openjdk17
# ARG:定义变量,这里定义JAR包名称
# COPY:将构建好的JAR包拷贝到镜像中
# ENTRYPOINT:在容器启动时执行的命令,这里执行java -jar启动的应用
# EXPOSE:暴露端口,这里暴露8080端口

# 整包构建
# 基于OpenJDK Dragonwell 17的Docker镜像, 由阿里巴巴集团开发并维护
FROM alibabadragonwell/dragonwell:17-anolis
# 创建目录
RUN mkdir -p /spring-boot-docker
# 指定工作目录
WORKDIR /spring-boot-docker
# 定义变量JAR_FILE,用于获取构建生成的JAR包名称
ARG JAR_FILE=target/spring-boot-docker.jar
# 将构建好的JAR包复制到镜像中,重命名为app.jar
COPY ${JAR_FILE} app.jar
# EXPOSE:暴露端口,这里暴露8080端口
EXPOSE 8080
# 设置环境变量TZ与JAVA_OPTS,用于指定时区与JVM参数
ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"
# 启动命令，等待60秒后启动应用
CMD sleep 60; java $JAVA_OPTS -jar app.jar