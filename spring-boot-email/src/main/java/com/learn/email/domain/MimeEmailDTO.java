package com.learn.email.domain;

import org.springframework.core.io.InputStreamSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 复杂邮件类型
 *
 * @author lizhifu
 * @date 2020/12/7
 */
public class MimeEmailDTO extends EmailDTO{
    /**
     * 内容类型
     */
    private String contentType;

    /**
     * 附件
     */
    private Map<String,InputStreamSource> attachmentMap;
    /**
     * 正文静态资源
     */
    private Map<String,InputStreamSource> inlineMap;

    public Map<String, InputStreamSource> getInlineMap() {
        return inlineMap;
    }

    public void setInlineMap(Map<String, InputStreamSource> inlineMap) {
        this.inlineMap = inlineMap;
    }

    public Map<String, InputStreamSource> getAttachmentMap() {
        return attachmentMap;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setAttachmentMap(Map<String, InputStreamSource> attachmentMap) {
        this.attachmentMap = attachmentMap;
    }

    /**
     * 添加附件
     * @param attachmentFilename 附件名称
     * @param inputStreamSource 附件流
     */
    public void addAttachment(String attachmentFilename, InputStreamSource inputStreamSource){
        if(attachmentMap == null){
            attachmentMap = new HashMap<String, InputStreamSource>();
        }
        attachmentMap.put(attachmentFilename,inputStreamSource);
    }
    /**
     * 添加正文静态资源
     * @param contentId contentId
     * @param inputStreamSource 流
     */
    public void addInline(String contentId, InputStreamSource inputStreamSource, String contentType){
        this.contentType = contentType;
        if(inlineMap == null){
            inlineMap = new HashMap<String, InputStreamSource>();
        }
        inlineMap.put(contentId,inputStreamSource);
    }
}
