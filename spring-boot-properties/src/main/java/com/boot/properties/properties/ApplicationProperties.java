package com.boot.properties.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 基础配置
 * @author lizhifu
 */
@Component
@Data
public class ApplicationProperties {
    @Value("${application.name}")
    private String name;
    @Value("${application.version}")
    private String version;
    @Override
    public String toString() {
        return "ApplicationProperties{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
