package com.boot.properties.properties;

import com.boot.properties.factory.PropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * demo
 *
 * @author lizhifu
 * @date 2020/12/7
 */
@Component
@ConfigurationProperties(prefix = "useryml")
@PropertySource(value = { "classpath:demoyml.yml" },factory = PropertySourceFactory.class)
public class DemoYmlProperties {
    private String nameyml;
    private Integer ageyml;

    public String getNameyml() {
        return nameyml;
    }

    public void setNameyml(String nameyml) {
        this.nameyml = nameyml;
    }

    public Integer getAgeyml() {
        return ageyml;
    }

    public void setAgeyml(Integer ageyml) {
        this.ageyml = ageyml;
    }

    @Override
    public String toString() {
        return "DemoYmlProperties{" +
                "nameyml='" + nameyml + '\'' +
                ", ageyml=" + ageyml +
                '}';
    }
}
