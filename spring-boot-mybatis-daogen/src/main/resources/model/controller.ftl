package ${genInfo.controllerPackage};
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.extern.slf4j.Slf4j;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import ${genInfo.servicePackage}.${genTable.className}Service;
import ${genInfo.domainPackage}.${genTable.className};
import ${genInfo.basePackage}.base.BaseController;
import ${genInfo.basePackage}.base.R;

<#if genInfo.swagger>
    import io.swagger.annotations.*;
</#if>

/**
* ${genTable.tableComment}
* @author ${genTable.author}
* @date ${.now?string('yyyy-MM-dd')}
*/
<#if genInfo.swagger>
@Api(tags = "${genTable.tableComment}")
</#if>
@RestController
@RequestMapping(value = "/${genTable.className?uncap_first}")
@Slf4j
public class ${genTable.className}Controller extends BaseController{

    @Resource
    private ${genTable.className}Service ${genTable.className?uncap_first}Service;

    /**
    * 新增
    * @author ${genTable.author}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    <#if genInfo.swagger>
    @ApiOperation(value = "新增",httpMethod ="POST")
    </#if>
    @RequestMapping("/insert")
    public R insert(${genTable.className} ${genTable.className?uncap_first}){
        return new R().success(${genTable.className?uncap_first}Service.insert(${genTable.className?uncap_first}));
    }

    /**
    * 根据主键刪除
    * @author ${genTable.author}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    <#if genInfo.swagger>
    @ApiOperation(value = "根据主键刪除",httpMethod ="GET")
    </#if>
    @RequestMapping("/deleteByPrimaryKey")
    public R deleteByPrimaryKey(Long primaryKey){
        return new R().success(${genTable.className?uncap_first}Service.deleteByPrimaryKey(primaryKey));
    }

    /**
    * 根据主键更新
    * @author ${genTable.author}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    <#if genInfo.swagger>
    @ApiOperation(value = "根据主键更新",httpMethod ="POST")
    </#if>
    @RequestMapping("/updateByPrimaryKey")
    public R updateByPrimaryKey(${genTable.className} ${genTable.className?uncap_first}){
        return new R().success(${genTable.className?uncap_first}Service.updateByPrimaryKey(${genTable.className?uncap_first}));
    }

    /**
    * 根据主键查询
    * @author ${genTable.author}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    <#if genInfo.swagger>
    @ApiOperation(value = "根据主键查询",httpMethod ="GET")
    </#if>
    @RequestMapping("/selectByPrimaryKey")
    public R<${genTable.className}> selectByPrimaryKey(Long primaryKey){
        return new R().success(${genTable.className?uncap_first}Service.selectByPrimaryKey(primaryKey));
    }

    /**
    * 通用查询
    * @author ${genTable.author}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    <#if genInfo.swagger>
    @ApiOperation(value = "通用查询",httpMethod ="POST")
    </#if>
    @RequestMapping("/selectList")
    public R<${genTable.className}> selectList(@RequestParam(required = false, defaultValue = "1") int pageNum,
                        @RequestParam(required = false, defaultValue = "10") int pageSize,
                        ${genTable.className} ${genTable.className?uncap_first}) {
        PageHelper.startPage(pageNum, pageSize, false);
        return new R().success(${genTable.className?uncap_first}Service.selectList(${genTable.className?uncap_first}));
    }
<#list genTableColumns as tableColumns >
    <#if tableColumns.isUni == "1">
    /**
    * 根据唯一建[${tableColumns.javaField?uncap_first}]查询
    * @author ${genTable.author}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    <#if genInfo.swagger>
    @ApiOperation(value = "根据唯一建[${tableColumns.javaField?uncap_first}]查询",httpMethod ="GET")
    </#if>
    @RequestMapping("/selectBy${tableColumns.javaField?cap_first}")
    public R<${genTable.className}> selectBy${tableColumns.javaField?cap_first}(${tableColumns.javaType} ${tableColumns.javaField?uncap_first}){
        return new R().success(${genTable.className?uncap_first}Service.selectBy${tableColumns.javaField?cap_first}(${tableColumns.javaField?uncap_first}));
    }
    </#if>
</#list>
}
