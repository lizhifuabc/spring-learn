package com.mybatis.gen.domain;

import lombok.Data;
import lombok.NonNull;

/**
 * GenInfo
 *
 * @author lizhifu
 * @date 2021/2/8
 */
@Data
public class GenInfo {
    /**
     * base包名称
     */
    private String basePackage;
    /**
     * mapper包名称
     */
    private String mapperPackage;
    /**
     * mybatis配置文件包名称
     */
    private String mybatisPackage;
    /**
     * 实体包名称
     */
    private String domainPackage;
    /**
     * service Impl包名称
     */
    private String serviceImplPackage;
    /**
     * service包名称
     */
    private String servicePackage;
    /**
     * controller包名称
     */
    private String controllerPackage;
    /**
     * 是否生成swagger
     */
    private boolean swagger = false;
    /**
     * 是否去除表前缀
     */
    private boolean removePrefix = true;
    /**
     * 表前缀
     */
    private String prefix;
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
        if(templateName.equals("domain")){
            return filePath + System.getProperty("file.separator")+"java"+System.getProperty("file.separator")
                    + domainPackage.replaceAll("\\.", System.getProperty("file.separator"))
                    + System.getProperty("file.separator");
        }
        if(templateName.equals("mapper")){
            return filePath + System.getProperty("file.separator")+"java"+System.getProperty("file.separator")
                    + mapperPackage.replaceAll("\\.", System.getProperty("file.separator"))
                    + System.getProperty("file.separator");
        }
        if(templateName.equals("service")){
            return filePath + System.getProperty("file.separator")+"java"+System.getProperty("file.separator")
                    + servicePackage.replaceAll("\\.", System.getProperty("file.separator"))
                    + System.getProperty("file.separator");
        }
        if(templateName.equals("controller")){
            return filePath + System.getProperty("file.separator")+"java"+System.getProperty("file.separator")
                    + controllerPackage.replaceAll("\\.", System.getProperty("file.separator"))
                    + System.getProperty("file.separator");
        }
        if(templateName.equals("service_impl")){
            return filePath + System.getProperty("file.separator")+"java"+System.getProperty("file.separator")
                    + serviceImplPackage.replaceAll("\\.", System.getProperty("file.separator"))
                    + System.getProperty("file.separator");
        }
        if(templateName.equals("mybatis")){
            return filePath + System.getProperty("file.separator")+"resources"+System.getProperty("file.separator")
                    + mybatisPackage.replaceAll("\\.", System.getProperty("file.separator"))
                    + System.getProperty("file.separator");
        }
        return filePath;
    }
}
