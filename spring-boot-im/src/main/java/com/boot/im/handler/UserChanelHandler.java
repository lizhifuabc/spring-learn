package com.boot.im.handler;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户id 和channel 的关联关系处理
 * @author lizhifu
 */
public class UserChanelHandler {

    private static Map<Long, Channel> manage = new ConcurrentHashMap<Long, Channel>();

    public static  void put(Long fromUserId,Channel channel){
        manage.put(fromUserId,channel);
    }

    public static Channel get(Long toUserId){
        return manage.get(toUserId);
    }
}
