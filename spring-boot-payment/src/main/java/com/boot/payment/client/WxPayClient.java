package com.boot.payment.client;

import com.boot.payment.init.WxMetaData;
import com.boot.payment.enumeration.WxPayV3Type;
import com.boot.payment.handler.WechatPayResponseErrorHandler;
import com.boot.payment.init.WxMetaDataConstant;
import com.boot.payment.sign.WxSignProvide;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * 微信client
 *
 * @author lizhifu
 * @date 2021/1/19
 */
@Slf4j
public class WxPayClient {
    /**
     * rest 请求
     */
    private RestOperations restOperations;
    /**
     * 地址
     */
    private WxPayV3Type wxPayV3Type;
    /**
     * 构建参数
     */
    private Object param;
    /**
     * appId
     */
    private String appId;
    private Consumer<ResponseEntity<ObjectNode>> responseBodyConsumer;
    private BiFunction<WxPayV3Type, Object, RequestEntity<?>> requestEntityBiFunction;

    /**
     * 默认构造函数
     */
    private WxPayClient(){
        applyDefaultRestTemplate();
    }

    /**
     * 初始化RestTemplate
     */
    private void applyDefaultRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        DefaultResponseErrorHandler errorHandler = new WechatPayResponseErrorHandler();
        restTemplate.setErrorHandler(errorHandler);
        this.restOperations = restTemplate;
    }
    /**
     * 创建WxPayClient
     * @return WxPayClient
     */
    public static WxPayClient create(){
        return new WxPayClient();
    }

    /**
     * 设置appId
     * @param appId
     * @return
     */
    public WxPayClient withAppId(String appId){
        this.appId = appId;
        return this;
    }

    /**
     * 设置请求地址和参数
     * @param wxPayV3Type
     * @param param
     * @return
     */
    public WxPayClient withUri(WxPayV3Type wxPayV3Type, Object param) {
        this.wxPayV3Type = wxPayV3Type;
        this.param = param;
        return this;
    }

    /**
     * 设置consumer
     * @param responseBodyConsumer
     * @return
     */
    public WxPayClient withConsumer(Consumer<ResponseEntity<ObjectNode>> responseBodyConsumer) {
        this.responseBodyConsumer = responseBodyConsumer;
        return this;
    }

    /**
     * 设置function
     * @param requestEntityBiFunction
     * @return
     */
    public WxPayClient withFunction(BiFunction<WxPayV3Type, Object, RequestEntity<?>> requestEntityBiFunction) {
        this.requestEntityBiFunction = requestEntityBiFunction;
        return this;
    }
    /**
     * 执行
     */
    public void execute() {
        RequestEntity<?> requestEntity = this.requestEntityBiFunction.apply(this.wxPayV3Type, this.param);
        WxRequestEntity<?> wxRequestEntity = WxRequestEntity.of(requestEntity, this.responseBodyConsumer);
        wxRequestEntity = this.header(wxRequestEntity);
        log.info("请求参数为:{}",wxRequestEntity);
        ResponseEntity<ObjectNode> responseEntity = this.restOperations.exchange(wxRequestEntity, ObjectNode.class);
        Consumer<ResponseEntity<ObjectNode>> responseConsumer = wxRequestEntity.getResponseBodyConsumer();
        responseConsumer.accept(responseEntity);
    }

    /**
     * 设置header
     * @param requestEntity
     * @param <T>
     * @return
     */
    private <T> WxRequestEntity<T> header(WxRequestEntity<T> requestEntity) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", authorization(requestEntity));
        return requestEntity.headers(headers);
    }
    /**
     * 设置请求头authorization
     * @return
     */
    private String authorization(WxRequestEntity<?> wxRequestEntity){
        WxMetaData wxMetaData = WxMetaDataConstant.INSTANCE.getWxMetaData(this.appId);
        UriComponents uri = UriComponentsBuilder.fromUri(wxRequestEntity.getUrl()).build();
        String canonicalUrl = uri.getPath();
        String encodedQuery = uri.getQuery();
        if (encodedQuery != null) {
            canonicalUrl += "?" + encodedQuery;
        }
        HttpMethod httpMethod = wxRequestEntity.getMethod();
        String body = wxRequestEntity.hasBody() ? Objects.requireNonNull(wxRequestEntity.getBody()).toString() : "";
        String authorization = WxSignProvide.authorization(httpMethod.name(),canonicalUrl,body,wxMetaData.getDetail().getSerialNo(), wxMetaData.getDetail().getMchId(),wxMetaData.getPrivateKey());
        return authorization;
    }
}
