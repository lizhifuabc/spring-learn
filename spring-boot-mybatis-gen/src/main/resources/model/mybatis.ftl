<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${paramInfo.packageName}.mapper.${tableInfo.className}Mapper">

    <resultMap id="BaseResultMap" type="${paramInfo.packageName}.domain.${tableInfo.className}" >
        <#if tableColumnInfoList?exists && tableColumnInfoList?size gt 0>
            <#list tableColumnInfoList as tableColumn >
                <result column="${tableColumn.columnName}" property="${tableColumn.fieldName}" />
            </#list>
        </#if>
    </resultMap>

    <sql id="Base_Column_List">
        <#if tableColumnInfoList?exists && tableColumnInfoList?size gt 0>
            <#list tableColumnInfoList as tableColumn >
                ${tableColumn.columnName}<#if tableColumn_has_next>,</#if>
            </#list>
        </#if>
    </sql>

    <insert id="insert" useGeneratedKeys="true" keyColumn="${tableInfo.keyColumn}" keyProperty="${tableInfo.keyProperty}" parameterType="${paramInfo.packageName}.domain.${tableInfo.className}">
        INSERT INTO ${tableInfo.tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#if tableColumnInfoList?exists && tableColumnInfoList?size gt 0>
                <#list tableColumnInfoList as tableColumn >
                    <#if !tableColumn.pk >
                        <if "null != ${tableColumn.fieldName} and '' != ${tableColumn.fieldName}">
                        ${tableColumn.columnName}<#if tableColumn_has_next>,</#if>
                        ${r"</if>"}
                    </#if>
                </#list>
            </#if>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#if tableColumnInfoList?exists && tableColumnInfoList?size gt 0>
                <#list tableColumnInfoList as tableColumn >
                    <#if !tableColumn.pk >
                        <if "null != ${tableColumn.fieldName} and '' != ${tableColumn.fieldName}">
                        ${r"#{"}${tableColumn.fieldName}${r"}"}<#if tableColumn_has_next>,</#if>
                        ${r"</if>"}
                    </#if>
                </#list>
            </#if>
        </trim>
    </insert>

    <delete id="deleteByPrimaryKey" >
        DELETE FROM ${tableInfo.tableName}
        WHERE ${tableInfo.keyColumn} =${r"#{"}${tableInfo.keyProperty}${r"}"}
    </delete>

    <update id="updateByPrimaryKey" parameterType="${paramInfo.packageName}.domain.${tableInfo.className}">
        UPDATE ${tableInfo.tableName}
        <set>
            <#list tableColumnInfoList as tableColumn >
                <#if !tableColumn.pk >
                    <if "null != ${tableColumn.fieldName} and '' != ${tableColumn.fieldName}">${tableColumn.columnName} = ${r"#{"}${tableColumn.fieldName}${r"}"}<#if tableColumn_has_next>,</#if>${r"</if>"}
                </#if>
            </#list>
        </set>
        WHERE ${tableInfo.keyColumn} =${r"#{"}${tableInfo.keyProperty}${r"}"}
    </update>


    <select id="selectByPrimaryKey" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List" />
        FROM ${tableInfo.tableName}
        WHERE ${tableInfo.keyColumn} =${r"#{"}${tableInfo.keyProperty}${r"}"}
    </select>

    <select id="selectPageList" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List" />
        FROM ${tableInfo.tableName}
        LIMIT ${r"#{offset}"}, ${r"#{pageSize}"}
    </select>

    <select id="count" resultType="java.lang.Integer">
        SELECT count(${tableInfo.keyColumn})
        FROM ${tableInfo.tableName}
    </select>

</mapper>