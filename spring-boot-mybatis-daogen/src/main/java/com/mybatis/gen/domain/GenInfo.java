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
            return filePath + System.getProperty("file.separator")+"java"
                    + System.getProperty("file.separator")+"com"
                    + System.getProperty("file.separator")+"mybatis"
                    + System.getProperty("file.separator")+"gen"
                    + System.getProperty("file.separator")+"domain"
                    + System.getProperty("file.separator");
        }
        if(templateName.equals("mapper")){
            return filePath + System.getProperty("file.separator")+"java"
                    + System.getProperty("file.separator")+"com"
                    + System.getProperty("file.separator")+"mybatis"
                    + System.getProperty("file.separator")+"gen"
                    + System.getProperty("file.separator")+"mapper"
                    + System.getProperty("file.separator");
        }
        if(templateName.equals("service")){
            return filePath + System.getProperty("file.separator")+"java"
                    + System.getProperty("file.separator")+"com"
                    + System.getProperty("file.separator")+"mybatis"
                    + System.getProperty("file.separator")+"gen"
                    + System.getProperty("file.separator")+"service"
                    + System.getProperty("file.separator");
        }
        if(templateName.equals("controller")){
            return filePath + System.getProperty("file.separator")+"java"
                    + System.getProperty("file.separator")+"com"
                    + System.getProperty("file.separator")+"mybatis"
                    + System.getProperty("file.separator")+"gen"
                    + System.getProperty("file.separator")+"controller"
                    + System.getProperty("file.separator");
        }
        if(templateName.equals("service_impl")){
            return filePath + System.getProperty("file.separator")+"java"
                    + System.getProperty("file.separator")+"com"
                    + System.getProperty("file.separator")+"mybatis"
                    + System.getProperty("file.separator")+"gen"
                    + System.getProperty("file.separator")+"service"
                    + System.getProperty("file.separator")+"impl"
                    + System.getProperty("file.separator");
        }
        if(templateName.equals("mybatis")){
            return filePath + System.getProperty("file.separator")+"resources"
                    + System.getProperty("file.separator")+"mybatis"
                    + System.getProperty("file.separator")+ "mybatis/sqlMap"
                    + System.getProperty("file.separator");
        }
        return filePath;
    }
}
