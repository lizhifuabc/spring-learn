package com.mybatis.gen;

import com.mybatis.gen.domain.GenInfo;
import com.mybatis.gen.domain.GenTable;
import com.mybatis.gen.domain.GenTableColumn;
import com.mybatis.gen.domain.GenTableUnique;
import com.mybatis.gen.service.GenService;
import com.mybatis.gen.util.gen.FreemarkerUtil;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ModelMap;

import jakarta.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * GenTableMapper
 *
 * @author lizhifu
 * @date 2021/2/8
 */
@SpringBootTest
public class GenTableMapperTest {
    @Resource
    GenService genService;
    static GenInfo genInfo = new GenInfo();
    @BeforeAll
    static void init(){
        genInfo.setBasePackage("com.mybatis.gen");
        genInfo.setMybatisPackage("mybatis.sqlMap");
        genInfo.setServicePackage("com.mybatis.gen.service");
        genInfo.setServiceImplPackage("com.mybatis.gen.service.impl");
        genInfo.setDomainPackage("com.mybatis.gen.domain");
        genInfo.setMapperPackage("com.mybatis.gen.mapper");
        genInfo.setControllerPackage("com.mybatis.gen.controller");

        genInfo.setAuthor("lizhifu");
        genInfo.setTableName("t_table_test");
        genInfo.setRemovePrefix(true);
        genInfo.setPrefix("t_");
        genInfo.setSwagger(true);
        genInfo.setTemplatePath("/Users/lizhifu/Documents/workspace/manage/spring-learn/spring-boot-mybatis-daogen/target/classes/model");
        genInfo.setFilePath("/Users/lizhifu/Documents/workspace/manage/spring-learn/spring-boot-mybatis-daogen/src/main");
    }
    @Test
    void exe(){
        List<String> list = new ArrayList();
        list.add("domain");
        list.add("mapper");
        list.add("mybatis");
        list.add("service");
        list.add("service_impl");
        list.add("controller");
        list.forEach(res->{
            genInfo.setTemplateName(res);
            try {
                exeDetail();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        });
    }
    void exeDetail() throws IOException, TemplateException {
        String filePath = genInfo.getFilePath();
        System.out.println("filePath:"+filePath);

        GenTable genTable = genService.genTable(genInfo);
        List<GenTableColumn> genTableColumns = genService.genTableColumns(genInfo,genTable);
        List<GenTableUnique> genTableUniques = genService.genTableUniques(genInfo);

        ModelMap map = new ModelMap();
        map.addAttribute("genTable",genTable);
        map.addAttribute("genInfo",genInfo);
        map.addAttribute("genTableColumns",genTableColumns);
        map.addAttribute("genTableUniques",genTableUniques);
        String result = FreemarkerUtil.processString(genInfo.getTemplateName()+".ftl",map,genInfo.getTemplatePath());
        System.out.println(result);
        String fileName = genTable.getClassName();
        if(genInfo.getTemplateName().equals("domain")){
            fileName = fileName + ".java";
        }
        if(genInfo.getTemplateName().equals("controller")){
            fileName = fileName + "Controller.java";
        }
        if(genInfo.getTemplateName().equals("service")){
            fileName = fileName + "Service.java";
        }
        if(genInfo.getTemplateName().equals("service_impl")){
            fileName = fileName + "ServiceImpl.java";
        }
        if(genInfo.getTemplateName().equals("mapper")){
            fileName = fileName + "Mapper.java";
        }
        if(genInfo.getTemplateName().equals("mybatis")){
            fileName = fileName + "Mapper.xml";
        }
        File file = new File(filePath + fileName);
        if(file.exists()){
            file.createNewFile();
        }
        System.out.println(file.getAbsolutePath());
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(result);
        out.close();
    }
}
