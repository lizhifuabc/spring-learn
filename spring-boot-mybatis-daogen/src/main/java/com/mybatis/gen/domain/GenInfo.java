package com.mybatis.gen.domain;

import lombok.Data;

/**
 * GenInfo
 *
 * @author lizhifu
 * @date 2021/2/8
 */
@Data
public class GenInfo {
    /**
     * 是否去除表前缀
     */
    private boolean removePrefix = true;
    /**
     * 表前缀
     */
    private String prefix;
    /**
     * mybatis包
     */
    private String mybatisPackage;
    /**
     * serviceImpl包
     */
    private String serviceImplPackage;
    /**
     * service包
     */
    private String servicePackage;
    /**
     * mapper包
     */
    private String mapperPackage;
    /**
     * controller包
     */
    private String controllerPackage;
    /**
     * 实体包
     */
    private String entityPackage;
    /**
     * 作者
     */
    private String author;
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 模板路径
     */
    private String templatePath;
    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 文件路径
     */
    private String filePath;

    public String getFilePath() {
        String path = System.getProperty("file.separator");
        String java = filePath + System.getProperty("file.separator") + "java" + System.getProperty("file.separator");
        if(templateName.equals("domain")){
            return java + entityPackage.replaceAll("\\.", System.getProperty("file.separator")) + path;
        }
        if(templateName.equals("mapper")){
            return java + mapperPackage.replaceAll("\\.", System.getProperty("file.separator")) + path;
        }
        if(templateName.equals("service")){
            return java + servicePackage.replaceAll("\\.", System.getProperty("file.separator")) + path;
        }
        if(templateName.equals("controller")){
            return java + controllerPackage.replaceAll("\\.", System.getProperty("file.separator")) + path;
        }
        if(templateName.equals("service_impl")){
            return java + serviceImplPackage.replaceAll("\\.", System.getProperty("file.separator")) + path;
        }
        if(templateName.equals("mybatis")){
            return filePath + System.getProperty("file.separator")+"resources"
                    + System.getProperty("file.separator")+ mybatisPackage
                    + System.getProperty("file.separator");
        }
        return filePath;
    }
}
