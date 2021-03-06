package ${genInfo.domainPackage};

<#if genInfo.swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
/**
* [${genTable.tableComment}]
* @author ${genTable.author}
* @date ${.now?string('yyyy-MM-dd')}
*/
@Data
<#if genInfo.swagger>
@ApiModel(value = "${genTable.tableComment}", description = "${genTable.tableComment}")
</#if>
public class ${genTable.className} implements Serializable {

    private static final long serialVersionUID = 1L;

<#if genTableColumns?exists && genTableColumns?size gt 0>
    <#list genTableColumns as tableColumn >
    /**
    * ${tableColumn.columnComment}
    */
    <#if genInfo.swagger>
    @ApiModelProperty(value = "${tableColumn.columnComment}", required = false)
    </#if>
    private ${tableColumn.javaType} ${tableColumn.javaField};
    </#list>
</#if>
}