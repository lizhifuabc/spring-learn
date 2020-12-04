package com.learn.email.service.impl;

import com.learn.email.domain.EmailDTO;
import com.learn.email.service.EmailService;
import com.sun.deploy.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    public void sendSimpleMail(EmailDTO emailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sendUser);
        message.setTo(emailDTO.getTo());
        message.setSubject(emailDTO.getSubject());
        message.setText(emailDTO.getText());
        message.setCc(emailDTO.getCc());
        message.setBcc(emailDTO.getBcc());
        mailSender.send(message);
    }
}
