package com.boot.properties.properties;

import org.springframework.beans.factory.annotation.Value;
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
@ConfigurationProperties(prefix = "user")
@PropertySource(value = { "classpath:demo.properties" })
public class DemoProperties {
    private String namep;
    private Integer age;

    public String getNamep() {
        return namep;
    }

    public void setNamep(String namep) {
        this.namep = namep;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "DemoProperties{" +
                "namep='" + namep + '\'' +
                ", age=" + age +
                '}';
    }
}
