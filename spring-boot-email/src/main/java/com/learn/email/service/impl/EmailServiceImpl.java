package com.learn.email.service.impl;

import com.learn.email.domain.EmailDTO;
import com.learn.email.domain.MimeEmailDTO;
import com.learn.email.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * EmailServiceImpl
 *
 * @author lizhifu
 * @date 2020/12/4
 */
@Service
public class EmailServiceImpl implements EmailService {
    @Resource
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sendUser;

    public void sendSimpleMail(EmailDTO emailDTO){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sendUser);
        message.setTo(emailDTO.getTo());
        message.setSubject(emailDTO.getSubject());
        message.setText(emailDTO.getText());
        message.setCc(emailDTO.getCc());
        message.setBcc(emailDTO.getBcc());
        mailSender.send(message);
    }

    public void sendMimeMail(MimeEmailDTO mimeEmailDTO) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sendUser);
        helper.setTo(mimeEmailDTO.getTo());
        helper.setSubject(mimeEmailDTO.getSubject());
        helper.setText(mimeEmailDTO.getText(),true);
        if(null != mimeEmailDTO.getCc() && mimeEmailDTO.getCc().length >0){
            helper.setCc(mimeEmailDTO.getCc());
        }
        if(null != mimeEmailDTO.getBcc() && mimeEmailDTO.getBcc().length >0){
            helper.setBcc(mimeEmailDTO.getBcc());
        }
        if(null != mimeEmailDTO.getAttachmentMap() && !mimeEmailDTO.getAttachmentMap().isEmpty()){
            for(String key : mimeEmailDTO.getAttachmentMap().keySet()){
                InputStreamSource value = mimeEmailDTO.getAttachmentMap().get(key);
                helper.addAttachment(key,value);
            }
        }
        if(null != mimeEmailDTO.getInlineMap() && !mimeEmailDTO.getInlineMap().isEmpty()){
            for(String key : mimeEmailDTO.getInlineMap().keySet()){
                InputStreamSource value = mimeEmailDTO.getInlineMap().get(key);
                helper.addInline(key,value,mimeEmailDTO.getContentType());
            }
        }
        mailSender.send(message);
    }
}
