package com.boot.properties;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.PropertyResolver;

/**
 * PropertyResolver 获取配置属性值
 *
 * @author lizhifu
 * @since 2023/8/13
 */
@SpringBootTest
public class PropertyResolverTest {
    @Resource
    private PropertyResolver env;

    @Test
    public void test() {
        System.out.println(env.getProperty("spring.application.name"));
    }
}
