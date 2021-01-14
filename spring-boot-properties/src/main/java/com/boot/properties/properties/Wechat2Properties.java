package com.boot.properties.properties;

import lombok.Data;
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
@ConfigurationProperties(prefix = "wechat2")
@Data
public class Wechat2Properties {
    /**
     * prefix的值+data变量名为properties key的前一部分,
     * 将key剩余的部分作为map的key, value作为map的value
     */
    private Map<String, Detail> data = new HashMap();
    // 嵌套的configuration必须是静态类
    @Data
    public static class Detail{
        private String appSecret;
        private String appId;
    }
}
