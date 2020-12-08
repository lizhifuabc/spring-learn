package com.boot.gen.service.impl;

import com.boot.gen.config.GenConfig;
import com.boot.gen.domain.GenTable;
import com.boot.gen.domain.GenTableColumn;
import com.boot.gen.mapper.GenTableColumnMapper;
import com.boot.gen.mapper.GenTableMapper;
import com.boot.gen.service.GenService;
import com.boot.gen.utils.GenUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import static com.boot.gen.utils.GenUtils.getImportList;

/**
 * 代码生成
 *
 * @author lizhifu
 * @date 2020/12/8
 */
@Service
public class GenServiceImpl implements GenService {
    @Resource
    private GenConfig genConfig;
    @Resource
    private GenTableColumnMapper genTableColumnMapper;
    @Resource
    private GenTableMapper genTableMapper;
    @Override
    public GenTable initGenTable(String tableName) {
        //查询表数据
        GenTable genTable = genTableMapper.selectTableByName(tableName);
        //查询表字段
        List<GenTableColumn> genTableColumnList = genTableColumnMapper.selectDbTableColumnsByName(tableName);
        //维护表字段
        genTable.setColumns(genTableColumnList);
        //维护主键信息
        genTable.setPkColumn(GenUtils.getPkColumn(genTableColumnList));
        //维护java类名
        genTable.setClassName(GenUtils.convertToCamelCase(tableName));
        return genTable;
    }

    @Override
    public GenTable initGenTableColumn(GenTable genTable) {
        List<GenTableColumn> genTableColumnList = genTable.getColumns();
        for (int i = 0; i < genTableColumnList.size(); i++) {
            GenTableColumn genTableColumn = genTableColumnList.get(i);
            // 设置java字段名
            genTableColumn.setJavaField(GenUtils.toCamelCase(genTableColumn.getColumnName()));
            //设置字段类型
            genTableColumn.setJavaType(genConfig.javaType.get(genTableColumn.getDataType()));
        }
        return genTable;
    }
    @Override
    public VelocityContext prepareContext(GenTable genTable) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tableName", genTable.getTableName());
        if(StringUtils.isEmpty(genConfig.functionName)){
            velocityContext.put("functionName",genTable.getTableComment());
        }else {
            velocityContext.put("functionName",genConfig.functionName);
        }
        velocityContext.put("ClassName", genTable.getClassName());
        velocityContext.put("className", StringUtils.uncapitalize(genTable.getClassName()));
        if(StringUtils.isEmpty(genConfig.moduleName)){
            velocityContext.put("moduleName", genTable.getClassName().toLowerCase());
        }else {
            velocityContext.put("moduleName", genConfig.moduleName);
        }
        velocityContext.put("basePackage", GenUtils.getPackagePrefix(genConfig.packageName));
        velocityContext.put("packageName", genConfig.packageName);
        velocityContext.put("author", genConfig.getAuthor());
        velocityContext.put("datetime", LocalDateTime.now());
        velocityContext.put("pkColumn", genTable.getPkColumn());
        velocityContext.put("importList", getImportList(genTable.getColumns()));
        velocityContext.put("columns", genTable.getColumns());
        velocityContext.put("table", genTable);
        return velocityContext;
    }
}
