package com.boot.wxmap;

import com.boot.wxmap.api.message.LinkMessage;
import com.boot.wxmap.util.ParseXmlUtil;

/**
 * LinkMessageTest
 *
 * @author lizhifu
 * @date 2021/2/18
 */
public class LinkMessageTest {
    public static void main(String[] args) {
        LinkMessage linkMessage = LinkMessage.builder()
                .fromUserName("gh_a3c19daba92f")
                .toUserName("o7CRC6o5HgKcL0kdLUARw8B8sW3M")
                .description("测试描述")
                .title("测试标题")
                .url("https://www.baidu.com/")
                .build();
        String out = ParseXmlUtil.objectToXml(linkMessage);
        System.out.println(out);
    }
}
