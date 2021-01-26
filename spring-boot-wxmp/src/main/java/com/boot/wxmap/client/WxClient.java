package com.boot.wxmap.client;

import com.boot.wxmap.base.WxMappingJackson2HttpMessageConverter;
import com.boot.wxmap.handle.WechatPayResponseErrorHandler;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * 微信请求客户端
 *
 * @author lizhifu
 * @date 2021/1/25
 */
@Slf4j
public class WxClient {
    /**
     * rest 请求
     */
    private RestOperations restOperations;
    /**
     * 默认构造函数
     */
    private WxClient(){
        applyDefaultRestTemplate();
    }

    /**
     * 请求地址
     */
    private String url;
    /**
     * 构建参数
     */
    private Object param;

    private Consumer<ResponseEntity<ObjectNode>> responseBodyConsumer;
    private BiFunction<String, Object, RequestEntity<?>> requestEntityBiFunction;
    /**
     * 初始化RestTemplate
     */
    private void applyDefaultRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        DefaultResponseErrorHandler errorHandler = new WechatPayResponseErrorHandler();
        restTemplate.setErrorHandler(errorHandler);
        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        this.restOperations = restTemplate;
    }
    /**
     * 创建WxClient
     * @return WxClient
     */
    public static WxClient create(){
        return new WxClient();
    }
    /**
     * 设置请求地址和参数
     * @param url
     * @param param
     * @return
     */
    public WxClient withUri(String url, Object param) {
        this.url = url;
        this.param = param;
        return this;
    }
    /**
     * 设置function
     * @param requestEntityBiFunction
     * @return
     */
    public WxClient withFunction(BiFunction<String, Object, RequestEntity<?>> requestEntityBiFunction) {
        this.requestEntityBiFunction = requestEntityBiFunction;
        return this;
    }
    /**
     * 设置consumer
     * @param responseBodyConsumer
     * @return
     */
    public WxClient withConsumer(Consumer<ResponseEntity<ObjectNode>> responseBodyConsumer) {
        this.responseBodyConsumer = responseBodyConsumer;
        return this;
    }
    /**
     * 执行
     */
    public void execute() {
        RequestEntity<?> requestEntity = this.requestEntityBiFunction.apply(this.url, this.param);
        ResponseEntity<ObjectNode> responseEntity = this.restOperations.exchange(requestEntity, ObjectNode.class);
        responseBodyConsumer.accept(responseEntity);
    }
}
