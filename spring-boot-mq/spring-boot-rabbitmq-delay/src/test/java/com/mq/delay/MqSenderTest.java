package com.mq.delay;

import com.mq.delay.mq.MqSender;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;

/**
 * MqSenderTest
 *
 * @author lizhifu
 * @date 2021/4/9
 */
@SpringBootTest
public class MqSenderTest {
    @Resource
    MqSender mqSender;
    @Test
    public void test(){
        mqSender.sendDelay("MqSenderTest",6000);
    }
}
