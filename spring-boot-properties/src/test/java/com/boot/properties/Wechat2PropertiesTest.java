package com.boot.properties;

import com.boot.properties.properties.Wechat2Properties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Wechat2Properties
 *
 * @author lizhifu
 * @date 2021/1/14
 */
@SpringBootTest
public class Wechat2PropertiesTest {
    @Resource
    Wechat2Properties wechat2Properties;
    @Test
    public void test(){
        System.out.println(wechat2Properties.getData());
    }
}
