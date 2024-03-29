package ${genInfo.serviceImplPackage};

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.Map;
import java.util.List;
import ${genInfo.domainPackage}.${genTable.className};
import ${genInfo.servicePackage}.${genTable.className}Service;
import ${genInfo.mapperPackage}.${genTable.className}Mapper;

/**
* ${genTable.tableComment}
* @author ${genTable.author}
* @date ${.now?string('yyyy-MM-dd')}
*/
@Service
@Slf4j
public class ${genTable.className}ServiceImpl implements ${genTable.className}Service {

	@Resource
	private ${genTable.className}Mapper ${genTable.className?uncap_first}Mapper;

	/**
	* 新增
	* @author ${genTable.author}
	* @date ${.now?string('yyyy/MM/dd')}
	**/
	@Override
	public int insert(${genTable.className} ${genTable.className?uncap_first}){
		return ${genTable.className?uncap_first}Mapper.insert(${genTable.className?uncap_first});
	}

	/**
	* 根据主键刪除
	* @author ${genTable.author}
	* @date ${.now?string('yyyy/MM/dd')}
	**/
	@Override
	public int deleteByPrimaryKey(Long primaryKey){
		return ${genTable.className?uncap_first}Mapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	* 根据主键更新
	* @author ${genTable.author}
	* @date ${.now?string('yyyy/MM/dd')}
	**/
	@Override
	public int updateByPrimaryKey(${genTable.className} ${genTable.className?uncap_first}){
		return ${genTable.className?uncap_first}Mapper.updateByPrimaryKey(${genTable.className?uncap_first});
	}

	/**
	* 根据主键查询
	* @author ${genTable.author}
	* @date ${.now?string('yyyy/MM/dd')}
	**/
	@Override
	public ${genTable.className} selectByPrimaryKey(Long primaryKey){
		return ${genTable.className?uncap_first}Mapper.selectByPrimaryKey(primaryKey);
	}

	/**
	* 通用查询
	* @author ${genTable.author}
	* @date ${.now?string('yyyy/MM/dd')}
	**/
	@Override
	public List<${genTable.className}> selectList(${genTable.className} ${genTable.className?uncap_first}){
		return ${genTable.className?uncap_first}Mapper.selectList(${genTable.className?uncap_first});
	}

	<#list genTableColumns as tableColumns >
		<#if tableColumns.isUni == "1">
	/**
	* 根据唯一建[${tableColumns.javaField?uncap_first}]查询
	* @author ${genTable.author}
	* @date ${.now?string('yyyy/MM/dd')}
	**/
	@Override
	public ${genTable.className} selectBy${tableColumns.javaField?cap_first}(${tableColumns.javaType} ${tableColumns.javaField?uncap_first}){
		return ${genTable.className?uncap_first}Mapper.selectBy${tableColumns.javaField?cap_first}(${tableColumns.javaField?uncap_first});
	}
		</#if>
	</#list>
	<#list genTableUniques as tableColumns >
	/**
	* 根据唯一约束[${tableColumns.constraintName}]查询
	* @author ${genTable.author}
	* @date ${.now?string('yyyy/MM/dd')}
	**/
	@Override
	public ${genTable.className} selectBy${tableColumns.constraintName?cap_first}(${genTable.className} ${genTable.className?uncap_first}) {
		return ${genTable.className?uncap_first}Mapper.selectBy${tableColumns.constraintName?cap_first}(${genTable.className?uncap_first});
	}
	</#list>
}
