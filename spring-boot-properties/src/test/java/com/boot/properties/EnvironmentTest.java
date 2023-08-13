package com.boot.properties;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

/**
 * Environment 获取配置属性值
 *
 * @author lizhifu
 * @since 2023/8/13
 */
@SpringBootTest
public class EnvironmentTest {
    @Resource
    private Environment env;

    @Test
    public void test() {
        System.out.println(env.getProperty("spring.application.name"));
    }
}
