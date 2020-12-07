package com.learn.email;

import com.learn.email.domain.EmailDTO;
import com.learn.email.domain.MimeEmailDTO;
import com.learn.email.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.ResourceUtils;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.File;
import java.io.FileNotFoundException;

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
        String[] to = {"1023571989@qq.com"};
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setTo(to);
        emailDTO.setSubject("您好2");
        emailDTO.setText("测试邮件");
//        emailDTO.setCc(cc);
        emailService.sendSimpleMail(emailDTO);

    }
    @Test
    public void testSendMimeMail() throws MessagingException, FileNotFoundException {
        String contentId = "xkcoding";
        String text = "<html><body>静态资源的邮件<br/><img src=\'cid:" + contentId + "\' ></body></html>";

        FileSystemResource file = new FileSystemResource(new File("/Users/lizhifu/Downloads/报价单.xlsx"));
        FileSystemResource fileImg = new FileSystemResource(new File("/Users/lizhifu/Downloads/timg.jpeg"));
        String[] to = {"1023571989@qq.com"};
        MimeEmailDTO emailDTO = new MimeEmailDTO();
        emailDTO.setTo(to);
        emailDTO.setSubject("您好，本期数据为");
        emailDTO.setText(text);
        emailDTO.addAttachment("你好",file);
        emailDTO.addAttachment("hello",file);

        emailDTO.addInline(contentId,fileImg,getContentType("/Users/lizhifu/Downloads/timg.jpeg"));
        emailDTO.addInline(contentId,fileImg,getContentType("/Users/lizhifu/Downloads/timg.jpeg"));
        emailService.sendMimeMail(emailDTO);

    }
    private static String getContentType(String fileUrl) {
        String contentType = null;
        try {
            contentType = new MimetypesFileTypeMap().getContentType(new File(fileUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("getContentType, File ContentType is : " + contentType);
        return contentType;
    }

}
