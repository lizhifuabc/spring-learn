package com.boot.properties.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 扩展配置extend
 * @author lizhifu
 */
@ConfigurationProperties(prefix = "extend")
@Component
public class ExtendProperties {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ExtendProperties{" +
                "name='" + name + '\'' +
                '}';
    }
}
