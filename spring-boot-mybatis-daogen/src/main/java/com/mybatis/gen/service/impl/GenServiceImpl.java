package com.mybatis.gen.service.impl;

import com.mybatis.gen.domain.GenInfo;
import com.mybatis.gen.domain.GenTable;
import com.mybatis.gen.domain.GenTableColumn;
import com.mybatis.gen.domain.GenTableUnique;
import com.mybatis.gen.mapper.GenTableMapper;
import com.mybatis.gen.service.GenService;
import com.mybatis.gen.util.gen.GenUtils;
import com.mybatis.gen.util.str.StringUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成
 *
 * @author lizhifu
 * @date 2021/3/17
 */
@Service
public class GenServiceImpl implements GenService {
    @Resource
    private GenTableMapper genTableMapper;
    @Override
    public GenTable genTable(GenInfo genInfo) {
        GenTable genTable = genTableMapper.selectDbTableByName(genInfo.getTableName());
        GenUtils.initTable(genTable,genInfo);
        return genTable;
    }

    @Override
    public List<GenTableColumn> genTableColumns(GenInfo genInfo, GenTable genTable) {
        List<GenTableColumn> genTableColumns =  genTableMapper.selectDbTableColumnsByName(genInfo.getTableName());
        genTableColumns.forEach(res->{
            GenUtils.initColumnField(res);
            if(res.getIsPk().equals("1")){
                genTable.setPkColumn(res);
            }
        });
        return genTableColumns;
    }

    @Override
    public List<GenTableUnique> genTableUniques(GenInfo genInfo) {
        List<GenTableUnique> genTableUniques = new ArrayList<>();
        List<GenTableUnique> dao =  genTableMapper.selectUniqueTableByName(genInfo.getTableName());
        dao.forEach(res->{
            if(res.getGenTableColumnUniques().size() == 1){
                return;
            }
            res.setConstraintName(StringUtils.toCamelCase(res.getConstraintName()));
            res.getGenTableColumnUniques().forEach(tableColumnUnique->{
                tableColumnUnique.setJavaField(StringUtils.toCamelCase(tableColumnUnique.getColumnName()));
            });
            GenTableUnique genTableUnique = new GenTableUnique();
            genTableUnique.setConstraintName(res.getConstraintName());
            genTableUnique.setGenTableColumnUniques(res.getGenTableColumnUniques());
            genTableUniques.add(genTableUnique);
        });
        return genTableUniques;
    }
}
