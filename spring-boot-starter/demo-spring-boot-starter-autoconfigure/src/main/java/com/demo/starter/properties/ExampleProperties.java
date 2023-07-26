package com.demo.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置信息
 *
 * @author lizhifu
 * @since  2021/1/15
 */
@ConfigurationProperties(prefix = "example")
public class ExampleProperties {
    private String say;

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say;
    }
}
