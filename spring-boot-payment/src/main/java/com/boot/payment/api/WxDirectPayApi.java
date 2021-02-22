package com.boot.payment.api;

import com.boot.payment.client.WxPayClient;
import com.boot.payment.client.WxResponseEntity;
import com.boot.payment.enumeration.WxPayV3Type;
import com.boot.payment.enumeration.WxServer;
import com.boot.payment.param.TransactionQueryParams;
import com.boot.payment.param.WxPayParam;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 直连模式
 *
 * @author lizhifu
 * @date 2021/1/20
 */
@Slf4j
public class WxDirectPayApi extends BaseAbstractApi{
    /**
     * 统一下单API
     *
     */
    public WxResponseEntity<ObjectNode> jsPay(WxPayParam wxPayParam) {
        WxResponseEntity<ObjectNode> wxResponseEntity = new WxResponseEntity<>();
        WxPayClient.create()
                .withAppId(wxPayParam.getAppid())
                .withUri(WxPayV3Type.JSAPI,wxPayParam)
                .withFunction(this::payFunction)
                .withConsumer(responseEntity -> {
                    ObjectNode body = responseEntity.getBody();
                    body.put("appId", wxPayParam.getAppid());
                    long epochSecond = LocalDateTime.now()
                            .toEpochSecond(ZoneOffset.of("+8"));
                    String timestamp = String.valueOf(epochSecond);
                    body.put("timeStamp", timestamp);
                    String nonceStr =  String.valueOf(System.currentTimeMillis());
                    body.put("nonceStr",nonceStr);
                    String packageStr = "prepay_id=" + body.get("prepay_id").asText();
                    body.put("package", packageStr);
                    body.put("signType", "RSA");
                    // TODO
                    // String paySign = super.doRequestSign(appInfo.getPrivateKey(), appInfo.getAppId(), timestamp, nonceStr, packageStr);
                    //body.put("paySign", paySign);
                    wxResponseEntity.setHttpStatus(responseEntity.getStatusCodeValue());
                    wxResponseEntity.setBody(body);
                }).execute();
        log.info("WxDirectPayApi queryTransactionByOutTradeNo params {} return {}",wxPayParam,wxResponseEntity);
        return wxResponseEntity;
    }
    /**
     * 商户订单号查询
     *
     * @param params params
     * @return wxResponseEntity
     */
    public WxResponseEntity<ObjectNode> queryTransactionByOutTradeNo(TransactionQueryParams params) {
        WxResponseEntity<ObjectNode> wxResponseEntity = new WxResponseEntity<>();
        WxPayClient.create()
                .withAppId(params.getAppId())
                .withUri(WxPayV3Type.TRANSACTION_OUT_TRADE_NO,params)
                .withFunction(this::queryTransactionFunction)
                .withConsumer(wxResponseEntity::convert)
                .execute();
        log.info("WxDirectPayApi queryTransactionByOutTradeNo params {} return {}",params,wxResponseEntity);
        return wxResponseEntity;
    }

    /**
     * 订单查询
     * @param wxPayV3Type
     * @param o
     * @return
     */
    private RequestEntity<?> queryTransactionFunction(WxPayV3Type wxPayV3Type, Object o) {
        TransactionQueryParams params = (TransactionQueryParams) o;
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        //转换
        queryParams.add("mchid", params.getMchid());
        URI uri = UriComponentsBuilder.fromHttpUrl(wxPayV3Type.uri(WxServer.CHINA))
                .queryParams(queryParams)
                .build()
                .expand(params.getTransactionIdOrOutTradeNo())
                .toUri();
        return get(uri);
    }
    private RequestEntity<?> payFunction(WxPayV3Type wxPayV3Type, Object o) {
        WxPayParam wxPayParam = (WxPayParam) o;
//        WechatPayProperties.V3 v3 = this.wechatMetaBean().getV3();
//        wxPayParam.setAppid(v3.getAppId());
//        wxPayParam.setMchid(v3.getMchId());
//        String notifyUrl = v3.getDomain().concat(wxPayParam.getNotifyUrl());
//        wxPayParam.setNotifyUrl(notifyUrl);
        URI uri = UriComponentsBuilder.fromHttpUrl(wxPayV3Type.uri(WxServer.CHINA))
                .build()
                .toUri();
        return post(uri, wxPayParam);
    }
}
