package com.boot.payment;

import com.boot.payment.client.WxPayClient;
import com.boot.payment.client.WxResponseEntity;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

/**
 * WxPayClient测试
 *
 * @author lizhifu
 * @date 2021/1/20
 */
public class WxPayClientTest {
    @Test
    public void test(){
        WxResponseEntity<ObjectNode> wechatResponseEntity = new WxResponseEntity<>();
        WxPayClient wxPayClient = WxPayClient.create().withConsumer(responseEntity ->{
            System.out.println(responseEntity);
        });
        WxPayClient wxPayClient2 = WxPayClient.create().withConsumer(wechatResponseEntity::convert);
        System.out.println("wxPayClient2:"+wechatResponseEntity.toString());
    }
}
