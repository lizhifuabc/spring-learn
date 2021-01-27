package com.boot.wxmap.client;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;

/**
 * 微信通知消息
 *
 * @author lizhifu
 * @date 2021/1/27
 */
public class WxMessageClient {
    private String msgType;
    /**
     * 参数
     */
    private Object param;
    private Consumer<ResponseEntity<ObjectNode>> responseBodyConsumer;
    /**
     * 默认构造函数
     */
    private WxMessageClient(){}
    /**
     * 创建WxMessageClient
     * @return WxMessageClient
     */
    public static WxMessageClient create(){
        return new WxMessageClient();
    }

    public WxMessageClient withMsgType(String msgType){
        this.msgType = msgType;
        return this;
    }
}
