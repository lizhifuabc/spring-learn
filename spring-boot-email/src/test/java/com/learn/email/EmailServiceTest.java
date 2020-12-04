package com.learn.email;

import com.learn.email.domain.EmailDTO;
import com.learn.email.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * EmailService test
 *
 * @author lizhifu
 * @date 2020/12/4
 */
@SpringBootTest
public class EmailServiceTest {
    @Resource
    EmailService emailService;

    @Test
    public void test(){
//        String[] cc = {"lizhifuabc@163.com"};
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setTo("1023571989@qq.com");
        emailDTO.setSubject("您好");
        emailDTO.setText("测试邮件");
//        emailDTO.setCc(cc);
        emailService.sendSimpleMail(emailDTO);

    }
}
