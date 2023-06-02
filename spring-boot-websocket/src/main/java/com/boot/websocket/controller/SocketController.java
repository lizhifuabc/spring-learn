package com.boot.websocket.controller;

import com.boot.websocket.config.RedisKey;
import com.boot.websocket.service.SendMessageService;
import com.boot.websocket.service.WsService;
import com.boot.websocket.session.WsSessionManager;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * SocketController
 *
 * @author lizhifu
 * @date 2021/3/29
 */
@Controller
@RequestMapping("/socket")
@Slf4j
public class SocketController {
    @Resource
    private WsService wsService;
    @Resource
    private SendMessageService sendMessageService;
    @GetMapping("/index/{userId}")
    public ModelAndView socket(@PathVariable String userId) {
        ModelAndView mav = new ModelAndView("/socket"+userId);
        return mav;
    }

    /**
     * 推送数据
     * @param toUserId
     * @return
     */
    @ResponseBody
    @RequestMapping("/socket/push/{toUserId}")
    public void push(@PathVariable String toUserId) throws IOException {
//        sendMessageService.sendMessage(RedisKey.REDIS_MQ_CHAT,"来自客户端发送的消息"+userId);
        wsService.sendMsg(toUserId,"来自客户端发送的消息"+toUserId);
    }
}
