package com.boot.payment;

import com.boot.payment.properties.WxProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * WxProperties
 *
 * @author lizhifu
 * @date 2021/1/12
 */
@SpringBootTest
public class WxPropertiesTest {
    @Resource
    WxProperties wxProperties;
    @Test
    public void test(){
        System.out.println("WxPropertiesTest.test:"+wxProperties.getAppV3Secret("wx524534x423134"));
        System.out.println("WxPropertiesTest.test:"+wxProperties.getMchId("wx524534x423134"));
    }
}
