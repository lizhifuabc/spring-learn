<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${genInfo.mapperPackage}.${genTable.className}Mapper">

    <resultMap id="BaseResultMap" type="${genInfo.domainPackage}.${genTable.className}" >
        <#if genTableColumns?exists && genTableColumns?size gt 0>
            <#list genTableColumns as tableColumns >
                <result column="${tableColumns.columnName}" property="${tableColumns.javaField}" />
            </#list>
        </#if>
    </resultMap>

    <sql id="Base_Column_List">
        <#if genTableColumns?exists && genTableColumns?size gt 0>
            <#list genTableColumns as tableColumns >
                ${tableColumns.columnName}<#if tableColumns_has_next>,</#if>
            </#list>
        </#if>
    </sql>

    <insert id="insert" useGeneratedKeys="true" keyColumn="${genTable.pkColumn.columnName}" keyProperty="${genTable.pkColumn.javaField}" parameterType="${genInfo.domainPackage}.${genTable.className}">
        INSERT INTO ${genTable.tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#if genTableColumns?exists && genTableColumns?size gt 0>
                <#list genTableColumns as tableColumns >
                    <#if tableColumns.isPk != "1" >
                        <#if tableColumns.javaType == "String" >
                            <if test= "null != ${tableColumns.javaField} and '' != ${tableColumns.javaField}">
                            ${tableColumns.columnName}<#if tableColumns_has_next>,</#if>
                            ${r"</if>"}
                         </#if>
                        <#if tableColumns.javaType != "String" >
                            <if test= "null != ${tableColumns.javaField}">
                            ${tableColumns.columnName}<#if tableColumns_has_next>,</#if>
                            ${r"</if>"}
                        </#if>
                    </#if>
                </#list>
            </#if>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#if genTableColumns?exists && genTableColumns?size gt 0>
                <#list genTableColumns as tableColumns >
                    <#if tableColumns.isPk != "1" >
                        <#if tableColumns.javaType == "String" >
                            <if test= "null != ${tableColumns.javaField} and '' != ${tableColumns.javaField}">
                            ${r"#{"}${tableColumns.javaField}${r"}"}<#if tableColumns_has_next>,</#if>
                            ${r"</if>"}
                        </#if>
                        <#if tableColumns.javaType != "String" >
                            <if test= "null != ${tableColumns.javaField}">
                            ${r"#{"}${tableColumns.javaField}${r"}"}<#if tableColumns_has_next>,</#if>
                            ${r"</if>"}
                        </#if>
                    </#if>
                </#list>
            </#if>
        </trim>
    </insert>

    <delete id="deleteByPrimaryKey" >
        DELETE FROM ${genTable.tableName}
        WHERE ${genTable.pkColumn.columnName} = ${r"#{"}${genTable.pkColumn.javaField}${r"}"}
    </delete>

    <#list genTableColumns as tableColumns >
        <#if tableColumns.isUni == "1">
    <select id="selectBy${tableColumns.javaField?cap_first}" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List" />
        FROM ${genTable.tableName}
        WHERE ${tableColumns.columnName} = ${r"#{"}${tableColumns.javaField}${r"}"}
    </select>
        </#if>
    </#list>

    <#list genTableUniques as tableColumns >
        <select id="selectBy${tableColumns.constraintName?cap_first}" resultMap="BaseResultMap">
            SELECT <include refid="Base_Column_List" />
            FROM ${genTable.tableName} WHERE 1 = 1
            <#list tableColumns.genTableColumnUniques as unique >
                and ${unique.columnName} = ${r"#{"}${unique.javaField}${r"}"}
            </#list>
        </select>
    </#list>

    <update id="updateByPrimaryKey" parameterType="${genInfo.domainPackage}.${genTable.className}">
        UPDATE ${genTable.tableName}
        <set>
            <#list genTableColumns as tableColumns >
                <#if tableColumns.isPk != "1">
                    <#if tableColumns.javaType != "String" >
                        <if test = "null != ${tableColumns.javaField}">${tableColumns.columnName} = ${r"#{"}${tableColumns.javaField}${r"}"}<#if tableColumns_has_next>,</#if>${r"</if>"}
                    </#if>
                    <#if tableColumns.javaType == "String" >
                        <if test = "null != ${tableColumns.javaField} and '' != ${tableColumns.javaField}">${tableColumns.columnName} = ${r"#{"}${tableColumns.javaField}${r"}"}<#if tableColumns_has_next>,</#if>${r"</if>"}
                    </#if>
                </#if>
            </#list>
        </set>
        WHERE ${genTable.pkColumn.columnName} = ${r"#{"}${genTable.pkColumn.javaField}${r"}"}
    </update>

    <select id="selectByPrimaryKey" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List" />
        FROM ${genTable.tableName}
        WHERE ${genTable.pkColumn.columnName} = ${r"#{"}${genTable.pkColumn.javaField}${r"}"}
    </select>

    <select id="selectList" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List" />
        FROM ${genTable.tableName} where 1 = 1
        <#list genTableColumns as tableColumns >
            <#if tableColumns.javaType == "String" >
                <if test = "null != ${tableColumns.javaField} and '' != ${tableColumns.javaField}">and ${tableColumns.columnName} = ${r"#{"}${tableColumns.javaField}${r"}"}<#if tableColumns_has_next></#if>${r"</if>"}
            </#if>
            <#if tableColumns.javaType != "String" >
                <if test = "null != ${tableColumns.javaField}">and ${tableColumns.columnName} = ${r"#{"}${tableColumns.javaField}${r"}"}<#if tableColumns_has_next></#if>${r"</if>"}
            </#if>
        </#list>
    </select>

</mapper>