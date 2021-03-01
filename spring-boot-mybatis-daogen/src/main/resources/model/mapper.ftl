package com.mybatis.gen.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.mybatis.gen.domain.${genTable.className};

/**
 * ${genTable.tableComment}
 * @author ${genTable.author}
 * @date ${.now?string('yyyy-MM-dd')}
 */
public interface ${genTable.className}Mapper {

    /**
    * 新增
    * @author ${genTable.author}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    ${genTable.className} insert(${genTable.className} ${genTable.className?uncap_first});

    /**
    * 根据主键刪除
    * @author ${genTable.author}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    int deleteByPrimaryKey(Long primaryKey);

    /**
    * 根据主键更新
    * @author ${genTable.author}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    int updateByPrimaryKey(${genTable.className} ${genTable.className?uncap_first});

    /**
    * 根据主键查询
    * @author ${genTable.author}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    ${genTable.className} selectByPrimaryKey(Long primaryKey);

    /**
    * 通用查询
    * @author ${genTable.author}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    List<${genTable.className}> selectList(${genTable.className} ${genTable.className?uncap_first});

    <#list genTableColumns as tableColumns >
        <#if tableColumns.isUni == "1">
    /**
    * 根据唯一建[${tableColumns.javaField?uncap_first}]查询
    * @author ${genTable.author}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    ${genTable.className} selectBy${tableColumns.javaField?cap_first}(${tableColumns.javaType} ${tableColumns.javaField?uncap_first});
        </#if>
    </#list>
}
