package com.learn.email.service;

import com.learn.email.domain.EmailDTO;
import com.learn.email.domain.MimeEmailDTO;

import javax.mail.MessagingException;

/**
 * EmailService
 *
 * @author lizhifu
 * @date 2020/12/4
 */
public interface EmailService {
    /**
     * 发送文本邮件
     *
     * @param emailDTO 邮件参数
     */
    void sendSimpleMail(EmailDTO emailDTO);

    void sendMimeMail(MimeEmailDTO mimeEmailDTO) throws MessagingException;
}
