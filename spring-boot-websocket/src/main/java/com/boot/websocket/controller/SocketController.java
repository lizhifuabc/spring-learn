package com.boot.websocket.controller;

import com.boot.websocket.service.WsService;
import com.boot.websocket.session.WsSessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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
    @GetMapping("/index/{userId}")
    public ModelAndView socket(@PathVariable String userId) {
        ModelAndView mav = new ModelAndView("/socket"+userId);
        return mav;
    }

    /**
     * 推送数据
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/socket/push/{userId}")
    public void push(@PathVariable String userId) throws IOException {
        wsService.sendMsg(WsSessionManager.get(userId),"来自客户端发送的消息"+userId);
    }
}
