package com.boot.wxmap;

import com.boot.wxmap.api.TemplateApi;
import com.boot.wxmap.api.template.SendTemplateMessageParam;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TemplateApiTest
 *
 * @author lizhifu
 * @date 2021/1/26
 */
@SpringBootTest
public class TemplateApiTest {
    @Test
    public void sendTemplateMessage(){
        SendTemplateMessageParam sendTemplateMessageParam = SendTemplateMessageParam.builder()
                .accessToken("41_Hp1Wkkf8u0wEm5uDZH1VUQ2NngjxDamlxJhjCqGF6Mm1rSh4h4imh8J4vkc4Ysl_aZ-CUcHmcblCIj6aQdJAq5udU-RAx40NOWFu5_-O0y2Xuc7Hj7oaj3qV4gjZZcItj3KoLR4kWv1dZkdAHPQeABAPXY")
                .template_id("iDSoeHVZ55j52NlHczFJgxhfUoLHug3LupTADzbvhA4")
                .touser("o7CRC6o5HgKcL0kdLUARw8B8sW3M")
                .build();
        TemplateApi templateApi = new TemplateApi();
        templateApi.sendTemplateMessage(sendTemplateMessageParam);
    }
}
