package com.mybatis.gen.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
/**
* [${genTable.tableComment}]
* @author ${genTable.author}
* @date ${.now?string('yyyy-MM-dd')}
*/
@Data
public class ${genTable.className} implements Serializable {

    private static final long serialVersionUID = 1L;

<#if genTableColumns?exists && genTableColumns?size gt 0>
    <#list genTableColumns as tableColumn >
    /**
    * ${tableColumn.columnComment}
    */
    private ${tableColumn.javaType} ${tableColumn.javaField};
    </#list>
</#if>
}