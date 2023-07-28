package com.boot.wxmap.controller.message;

import com.boot.wxmap.api.message.LinkMessage;
import com.boot.wxmap.api.message.TextMessage;
import com.boot.wxmap.client.WxMessageClient;
import com.boot.wxmap.crypto.SHA1;
import com.boot.wxmap.util.ParseXmlUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * MessageController
 *
 * @author lizhifu
 * @date 2021/1/26
 */
@RestController
@Slf4j
public class MessageController {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @GetMapping("/token")
    public String token(@RequestParam(name = "signature", required = false) String signature,
                        @RequestParam(name = "timestamp", required = false) String timestamp,
                        @RequestParam(name = "nonce", required = false) String nonce,
                        @RequestParam(name = "echostr", required = false) String echostr){
        log.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature,
                timestamp, nonce, echostr);
        if (SHA1.checkSignature(timestamp, nonce, signature,"demo")) {
            return echostr;
        }
        return "非法请求";
    }
    @PostMapping("/token")
    public String post(@RequestBody String requestBody,
                       @RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestParam("openid") String openid,
                       @RequestParam(name = "encrypt_type", required = false) String encType,
                       @RequestParam(name = "msg_signature", required = false) String msgSignature) {
        log.info("\n接收微信请求：[openid=[{}], [signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                openid, signature, encType, msgSignature, timestamp, nonce, requestBody);
        if (!SHA1.checkSignature(timestamp, nonce, signature,"demo")) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }
        Map<String,String> result = ParseXmlUtil.parseTo(requestBody);
        log.info("\n接收微信请求：[result=[\n{}\n] ", result);
        WxMessageClient.create().withMsgType(result.get("MsgType"));
        TextMessage textMessage = TextMessage.builder()
                .fromUserName(result.get("ToUserName"))
                .toUserName(result.get("FromUserName"))
                .content("感谢您的关注！")
                .build();
        String out = ParseXmlUtil.objectToXml(textMessage);

        LinkMessage linkMessage = LinkMessage.builder()
                .fromUserName(result.get("ToUserName"))
                .toUserName(result.get("FromUserName"))
                .description("测试描述")
                .title("测试标题")
                .url("https://www.baidu.com/")
                .build();
        out = ParseXmlUtil.objectToXml(linkMessage);

        log.info("返回给微信的xml数据为{}",out);
        return out;
    }
}
