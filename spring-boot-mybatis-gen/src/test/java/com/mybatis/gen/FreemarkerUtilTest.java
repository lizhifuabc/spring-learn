package com.mybatis.gen;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.statement.SQLColumnDefinition;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateTableStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlCreateTableParser;
import com.alibaba.druid.util.JdbcConstants;
import com.mybatis.gen.controller.ParamInfo;
import com.mybatis.gen.parse.TableColumnInfo;
import com.mybatis.gen.parse.TableColumnParse;
import com.mybatis.gen.parse.TableInfo;
import com.mybatis.gen.parse.TableNameParse;
import com.mybatis.gen.util.FreemarkerUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FreemarkerUtil
 *
 * @author lizhifu
 * @date 2021/2/1
 */
//@SpringBootTest
public class FreemarkerUtilTest {
    @SneakyThrows
    @Test
    public void test(){
        Map map = new HashMap();
        String sql = "create table zfollow_open.user_info_detail (\n" +
                "    info_detail_id    bigint(20)  primary key  not null auto_increment   comment 'ID',\n" +
                "    info_id      bigint   not null comment '用户信息ID',\n" +
                "    user_name      varchar(32)   default '' comment '姓名',\n" +
                "    phone      varchar(32)   default '' comment '手机号码',\n" +
                "    gender char(1) default '' comment '性别',\n" +
                "    age varchar(32)   default '' comment '年龄',\n" +
                "    define char(1)   default '' comment '是否确诊',\n" +
                "    define_hospital varchar(32)   default '' comment '确诊医院',\n" +
                "    define_disease varchar(32)   default '' comment '确诊医院',\n" +
                "    define_date varchar(32)  comment '确诊时间' default '' ,\n" +
                "    index info_id(info_id)\n" +
                ") engine=innodb  comment = '用户详细信息';";
        ParamInfo paramInfo = new ParamInfo();
        paramInfo.setAuthorName("lizhifu");
        paramInfo.setSql(sql);
        List<TableColumnInfo> tableColumnInfoList= new ArrayList<>();
        //格式化输出
        DbType dbType = JdbcConstants.MYSQL;
        String result = SQLUtils.format(paramInfo.getSql(), dbType);
        MySqlCreateTableParser parser = new MySqlCreateTableParser(result);
        MySqlCreateTableStatement mySqlCreateTableStatement = parser.parseCreateTable(true);
        TableInfo tableInfo = TableNameParse.create().withClassType("Model").withTable(mySqlCreateTableStatement).parse();
        map.put("tableInfo",tableInfo);
        List<SQLColumnDefinition> sqlColumnDefinitionList = mySqlCreateTableStatement.getColumnDefinitions();
        for (int i = 0; i < sqlColumnDefinitionList.size(); i++) {
            SQLColumnDefinition sqlColumnDefinition = sqlColumnDefinitionList.get(i);
//            TableColumnInfo tableColumnInfo = TableColumnParse.create().withTable(sqlColumnDefinition).parse();
//            tableColumnInfoList.add(tableColumnInfo);
        }
        map.put("paramInfo",paramInfo);
        map.put("tableColumnInfoList",tableColumnInfoList);
        String res = FreemarkerUtil.processString("src/main/resources/model/model.ftl",map);
        System.out.println(res);
    }
}
