package com.boot.wxmap.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * 基础类
 *
 * @author lizhifu
 * @date 2021/1/20
 */
public abstract class BaseAbstractApi {
    /**
     * post请求
     * @param uri
     * @param params
     * @return
     */
    protected RequestEntity<?> post(URI uri, Object params) {
        ObjectMapper mapper = new ObjectMapper();
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
        return RequestEntity.get(uri).build();
    }

    /**
     * 简单get
     * @param url
     * @param param
     * @return
     */
    protected RequestEntity<?> defaultGet(String url, Object param) {
        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .build()
                .toUri();
        return post(uri,param);
    }

    /**
     * 简单post
     * @param url
     * @param param
     * @return
     */
    protected RequestEntity<?> defaultPost(String url, Object param) {
        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .build()
                .toUri();
        return post(uri,param);
    }
}
