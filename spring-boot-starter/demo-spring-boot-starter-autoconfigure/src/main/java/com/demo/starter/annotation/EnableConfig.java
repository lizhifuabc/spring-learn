package com.demo.starter.annotation;

import com.demo.starter.config.DemoServiceAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 使用@EnableXxx机制
 * 作用：引入starter需要使用 @EnableRobot 开启功能
 * 和 org.springframework.boot.autoconfigure.AutoConfiguration.imports 的功能是一样的
 * @author lizhifu
 * @since 2023/7/25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(DemoServiceAutoConfiguration.class)
public @interface EnableConfig {

}
