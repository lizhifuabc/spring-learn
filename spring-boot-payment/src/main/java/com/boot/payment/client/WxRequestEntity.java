package com.boot.payment.client;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.function.Consumer;

/**
 * 微信请求实体
 *
 * @author lizhifu
 * @date 2021/1/20
 */
@Getter
public class WxRequestEntity<T> extends RequestEntity<T> {
    /**
     * consumer
     */
    private final Consumer<ResponseEntity<ObjectNode>> responseBodyConsumer;

    public WxRequestEntity(T body, MultiValueMap<String, String> headers, HttpMethod method, URI url, Type type, Consumer<ResponseEntity<ObjectNode>> responseBodyConsumer) {
        super(body, headers, method, url, type);
        this.responseBodyConsumer = responseBodyConsumer;
    }

    /**
     * 设置HttpHeaders
     * @param httpHeaders
     * @return
     */
    public WxRequestEntity<T> headers(HttpHeaders httpHeaders) {
        return new WxRequestEntity<>(this.getBody(),
                httpHeaders,
                this.getMethod(),
                this.getUrl(),
                this.getType(),
                this.responseBodyConsumer);
    }

    /**
     * 创建
     * @param requestEntity
     * @param responseBodyConsumer
     * @return
     */
    public static WxRequestEntity<?> of(RequestEntity<?> requestEntity, Consumer<ResponseEntity<ObjectNode>> responseBodyConsumer) {
        return new WxRequestEntity<>(requestEntity.getBody(),
                requestEntity.getHeaders(),
                requestEntity.getMethod(),
                requestEntity.getUrl(),
                requestEntity.getType(),
                responseBodyConsumer);
    }
}
