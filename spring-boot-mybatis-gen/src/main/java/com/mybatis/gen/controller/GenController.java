package com.mybatis.gen.controller;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.statement.SQLColumnDefinition;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateTableStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlCreateTableParser;
import com.alibaba.druid.util.JdbcConstants;
import com.mybatis.gen.parse.TableColumnInfo;
import com.mybatis.gen.parse.TableColumnParse;
import com.mybatis.gen.parse.TableInfo;
import com.mybatis.gen.parse.TableNameParse;
import com.mybatis.gen.util.FreemarkerUtil;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成
 *
 * @author lizhifu
 * @date 2021/2/1
 */
@Controller
public class GenController {
    @RequestMapping(value = "model")
    @ResponseBody
    public String model(@ModelAttribute("paramInfo") ParamInfo paramInfo, ModelMap map) throws IOException, TemplateException {
        //格式化输出
        DbType dbType = JdbcConstants.MYSQL;
        String result = SQLUtils.format(paramInfo.getSql(), dbType);
        MySqlCreateTableParser parser = new MySqlCreateTableParser(result);
        MySqlCreateTableStatement mySqlCreateTableStatement = parser.parseCreateTable(true);

        TableInfo tableInfo = TableNameParse.create().withClassType("").withTable(mySqlCreateTableStatement).parse();
        List<TableColumnInfo> tableColumnInfoList = TableColumnParse.create().withTableInfo(tableInfo).withTable(mySqlCreateTableStatement.getColumnDefinitions()).parse();

        map.addAttribute("tableInfo",tableInfo);
        map.addAttribute("paramInfo",paramInfo);
        map.addAttribute("tableColumnInfoList",tableColumnInfoList);
        return FreemarkerUtil.processString(paramInfo.getType()+".ftl",map);
    }
    @RequestMapping(value = "mybatis")
    @ResponseBody
    public String mybatis(@ModelAttribute("paramInfo") ParamInfo paramInfo, ModelMap map) throws IOException, TemplateException {
        //格式化输出
        DbType dbType = JdbcConstants.MYSQL;
        String result = SQLUtils.format(paramInfo.getSql(), dbType);
        MySqlCreateTableParser parser = new MySqlCreateTableParser(result);
        MySqlCreateTableStatement mySqlCreateTableStatement = parser.parseCreateTable(true);

        TableInfo tableInfo = TableNameParse.create().withClassType("").withTable(mySqlCreateTableStatement).parse();
        List<TableColumnInfo> tableColumnInfoList = TableColumnParse.create().withTable(mySqlCreateTableStatement.getColumnDefinitions()).parse();

        map.addAttribute("tableInfo",tableInfo);
        map.addAttribute("paramInfo",paramInfo);
        map.addAttribute("tableColumnInfoList",tableColumnInfoList);
        return FreemarkerUtil.processString("model.ftl",map);
    }
    @RequestMapping(value = "index")
    public String index(){
        return "index";
    }
}
