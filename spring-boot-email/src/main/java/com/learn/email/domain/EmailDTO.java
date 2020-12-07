package com.learn.email.domain;

import java.util.Arrays;

/**
 * EmailDTO
 * 邮件发送
 * @author lizhifu
 * @date 2020/12/4
 */
public class EmailDTO{
    /**
     * 收件人地址
     */
    private String[] to;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String text;
    /**
     * 抄送
     */
    private String[] cc;

    /**
     * 暗抄送
     */
    private String[] bcc;

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    @Override
    public String toString() {
        return "EmailDTO{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", cc=" + Arrays.toString(cc) +
                ", bcc=" + Arrays.toString(bcc) +
                '}';
    }
}
