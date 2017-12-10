<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${daoFullType}">
    <!--auto generated Code-->
    <resultMap id="BaseResultMap" type="${pojoFullType}">
    <#list fieldAndColumns as fieldAndColumn>
        <result column="${fieldAndColumn.column}" property="${fieldAndColumn.field}" jdbcType="${fieldAndColumn.jdbcType}"/>
    </#list>
    </resultMap>

    <!--auto generated Code-->
    <sql id="Base_Column_List">
    <#list fieldAndColumns as fieldAndColumn>
        <#if fieldAndColumn?is_last>
        ${fieldAndColumn.formattedColumn}
        <#else>
        ${fieldAndColumn.formattedColumn},
        </#if>
    </#list>
    </sql>

    <!--auto generated Code-->
    <insert id="insert"<#if useGeneratedKeys> useGeneratedKeys="true" keyProperty="${pojoName}.${primaryField}"</#if>>
        INSERT INTO ${tableName} (
    <#list fieldAndColumns as fieldAndColumn>
            ${fieldAndColumn.formattedColumn}<#rt>
        <#lt><#if !fieldAndColumn?is_last>,</#if>
    </#list>
        ) VALUES (
    <#list fieldAndColumns as fieldAndColumn>
            ${r"#"}{${pojoName}.${fieldAndColumn.field}<#if primaryJdbcType??>,jdbcType=${fieldAndColumn.jdbcType}</#if>}<#rt>
        <#lt><#if !fieldAndColumn?is_last>,</#if>
    </#list>
        )
    </insert>

    <!--auto generated Code-->
    <insert id="insertSelective"<#if useGeneratedKeys> useGeneratedKeys="true" keyProperty="${pojoName}.${primaryField}"</#if>>
        INSERT INTO ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list fieldAndColumns as fieldAndColumn>
            <if test="${pojoName}.${fieldAndColumn.field}!=null"> ${fieldAndColumn.formattedColumn},</if>
        </#list>
        </trim>
        VALUES
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list fieldAndColumns as fieldAndColumn>
            <if test="${pojoName}.${fieldAndColumn.field}!=null">${r"#"}{${pojoName}.${fieldAndColumn.field}<#if primaryJdbcType??>,jdbcType=${fieldAndColumn.jdbcType}</#if>},
            </if>
        </#list>
        </trim>
    </insert>

    <!--auto generated Code-->
<#if currentDatabase=="MySql">
    <insert id="insertList">
        INSERT INTO ${tableName} (
        <include refid="Base_Column_List"/>
        )VALUES
        <foreach collection="${pojoName}s" item="${pojoName}" index="index" separator=",">
            (
            <#list fieldAndColumns as fieldAndColumn>
            ${r"#"}{${pojoName}.${fieldAndColumn.field},jdbcType=${fieldAndColumn.jdbcType}}<#if !fieldAndColumn?is_last>,</#if>
            </#list>
            )
        </foreach>
    </insert>
</#if>
<#if currentDatabase=="Sqlite">
    <insert id="insertList">
        INSERT INTO ${tableName} (
        <include refid="Base_Column_List"/>
        )VALUES
        <foreach collection="${pojoName}s" item="${pojoName}" index="index" separator=",">
            (
            <#list fieldAndColumns as fieldAndColumn>
            ${r"#"}{${pojoName}.${fieldAndColumn.field},jdbcType=${fieldAndColumn.jdbcType}}<#if !fieldAndColumn?is_last>,</#if>
            </#list>
            )
        </foreach>
    </insert>
</#if>
<#if currentDatabase=="Oracle">
    <insert id="insertList">
        INSERT ALL
        <foreach collection="${pojoName}s" item="${pojoName}">
            INTO ${tableName} (
            <include refid="Base_Column_List"/>
            )VALUES(
            <#list fieldAndColumns as fieldAndColumn>
            ${r"#"}{${pojoName}.${fieldAndColumn.field}<#if primaryJdbcType??>,jdbcType=${fieldAndColumn.jdbcType}</#if>}<#rt>
                <#lt><#if !fieldAndColumn?is_last>,</#if>
            </#list>
            )
        </foreach>
        select * from dual
    </insert>
</#if>

    <!--auto generated Code-->
    <update id="update">
        UPDATE ${tableName}
        <set>
        <#list fieldAndColumns as fieldAndColumn>
            <#if fieldAndColumn?is_last>
            <if test="${pojoName}.${fieldAndColumn.field} != null"> ${fieldAndColumn.formattedColumn}= ${r"#"}{${pojoName}.${fieldAndColumn.field},jdbcType=${fieldAndColumn.jdbcType}}</if>
            <#else>
            <if test="${pojoName}.${fieldAndColumn.field} != null"> ${fieldAndColumn.formattedColumn}= ${r"#"}{${pojoName}.${fieldAndColumn.field},jdbcType=${fieldAndColumn.jdbcType}},</if>
            </#if>
        </#list>
        </set>
        WHERE ${primaryColumn} = ${r"#"}{${pojoName}.${primaryField},jdbcType=${primaryJdbcType}}
    </update>
</mapper>
