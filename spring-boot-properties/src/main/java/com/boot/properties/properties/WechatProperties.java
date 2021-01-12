package com.boot.properties.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 演示map
 *
 * @author lizhifu
 * @date 2021/1/12
 */
@Component
@PropertySource("classpath:wechat.properties")
@ConfigurationProperties(prefix = "wechat")
public class WechatProperties {
    /**
     * prefix的值+data变量名为properties key的前一部分,
     * 将key剩余的部分作为map的key, value作为map的value
     */
    private Map<String, String> data = new HashMap();

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WechatProperties{" +
                "data=" + data +
                '}';
    }
}
