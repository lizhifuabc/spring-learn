import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
/**
* @description ${tableInfo.tableComment}
* @author ${paramInfo.authorName}
* @date ${.now?string('yyyy-MM-dd')}
*/
@Data
public class ${tableInfo.className} implements Serializable {

    private static final long serialVersionUID = 1L;

<#if tableColumnInfoList?exists && tableColumnInfoList?size gt 0>
    <#list tableColumnInfoList as tableColumn >
    /**
    * ${tableColumn.columnComment}
    */
    private ${tableColumn.fieldClass} ${tableColumn.fieldName};
    </#list>
</#if>
}