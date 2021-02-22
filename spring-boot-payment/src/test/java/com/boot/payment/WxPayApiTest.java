package com.boot.payment;

import com.boot.payment.api.WxDirectPayApi;
import com.boot.payment.client.WxResponseEntity;
import com.boot.payment.param.TransactionQueryParams;
import com.boot.payment.param.WxPayParam;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * WxPayApiTest
 *
 * @author lizhifu
 * @date 2021/1/20
 */
@SpringBootTest
public class WxPayApiTest {
    @Test
    public void test(){
        System.out.println("WxPayApiTest");
        WxDirectPayApi wxDirectPayApi = new WxDirectPayApi();
        TransactionQueryParams param = new TransactionQueryParams();
        param.setAppId("wx524534x423234");
        param.setMchid("1605765479");
        param.setTransactionIdOrOutTradeNo(System.currentTimeMillis()+"");
        WxResponseEntity<ObjectNode> responseEntity =  wxDirectPayApi.queryTransactionByOutTradeNo(param);
        System.out.println(responseEntity);

//        WxPayParam wxPayParam = new WxPayParam();
//        wxPayParam.setAppid("wx524534x423234");
//        WxResponseEntity<ObjectNode> jsPay = wxDirectPayApi.jsPay(wxPayParam);
//        System.out.println("jsPay:"+jsPay);
    }
}
