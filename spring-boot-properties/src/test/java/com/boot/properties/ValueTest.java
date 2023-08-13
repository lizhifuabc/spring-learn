package com.boot.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Value 获取配置属性值
 *
 * @author lizhifu
 * @since 2023/8/13
 */
@SpringBootTest
public class ValueTest {
    @Value("${spring.application.name:测试}")
    private String applicationName;
    @Test
    public void test() {
        System.out.println(applicationName);
    }
}
