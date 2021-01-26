package com.boot.wxmap.base;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义消息转换器
 *
 * @author lizhifu
 * @date 2021/1/26
 */
public class WxMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    /**
     * 新增TEXT_PLAIN  TEXT_HTML 支持
     */
    public WxMappingJackson2HttpMessageConverter(){
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        mediaTypes.add(MediaType.TEXT_HTML);
        setSupportedMediaTypes(mediaTypes);
    }
}
