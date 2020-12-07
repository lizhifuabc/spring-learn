package com.boot.properties.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 基础配置
 * @author lizhifu
 */
@Component
public class ApplicationProperties {
    @Value("${application.name}")
    private String name;
    @Value("${application.version}")
    private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ApplicationProperties{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
