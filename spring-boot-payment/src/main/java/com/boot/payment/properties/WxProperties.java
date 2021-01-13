package com.boot.payment.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付基础参数：可以进行初始化
 *
 * @author lizhifu
 * @date 2021/1/12
 */
@Data
@Component
@PropertySource("classpath:wechat.properties")
@ConfigurationProperties(prefix = "wechat")
public class WxProperties {
    /**
     * 配置文件数据
     */
    private Map<String, String> data = new HashMap();

    public String getAppV3Secret(String appId){
        return data.get(appId+".appV3Secret");
    }
    public String getMchId(String appId){
        return data.get(appId+".mchId");
    }
}
