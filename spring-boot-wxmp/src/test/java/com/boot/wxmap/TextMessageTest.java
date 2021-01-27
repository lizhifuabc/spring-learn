package com.boot.wxmap;

import com.boot.wxmap.api.message.TextMessage;
import com.boot.wxmap.util.ParseXmlUtil;

/**
 * TextMessage
 *
 * @author lizhifu
 * @date 2021/1/27
 */
public class TextMessageTest {
    public static void main(String[] args) {
        TextMessage textMessage = TextMessage.builder()
                .fromUserName("gh_a3c19daba92f")
                .toUserName("o7CRC6o5HgKcL0kdLUARw8B8sW3M")
                .content("感谢您的关注！")
                .build();
        String out = ParseXmlUtil.objectToXml(textMessage);
        System.out.println(out);
    }
}
