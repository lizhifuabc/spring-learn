package com.boot.payment;

import com.boot.payment.properties.WxProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;
import java.util.stream.Stream;

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
        Map<String, WxProperties.Detail> data = wxProperties.getData();
        data.keySet().forEach(key-> System.out.printf(key+":"+data.get(key)));
    }
}
