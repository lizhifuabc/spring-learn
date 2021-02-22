package com.boot.payment.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.RequestEntity;

import java.net.URI;

/**
 * 基础类
 *
 * @author lizhifu
 * @date 2021/1/20
 */
public abstract class BaseAbstractApi {
    private final ObjectMapper mapper;

    private void applyObjectMapper(ObjectMapper mapper) {
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        SimpleModule module = new JavaTimeModule();
        mapper.registerModule(module);
    }

    public BaseAbstractApi(){
        this.mapper = new ObjectMapper();
        applyObjectMapper(this.mapper);
    }
    /**
     * post请求
     * @param uri
     * @param params
     * @return
     */
    protected RequestEntity<?> post(URI uri, Object params) {
        try {
            return RequestEntity.post(uri).body(mapper.writeValueAsString(params));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("wx JsonProcessingException");
        }
    }

    /**
     * get请求
     * @param uri
     * @return
     */
    protected RequestEntity<?> get(URI uri) {
        return RequestEntity.get(uri) .build();
    }
}
