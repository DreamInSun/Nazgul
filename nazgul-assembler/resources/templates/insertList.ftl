<#if currentDatabase=="MySql">
        INSERT INTO ${tableName}(
        <include refid="Base_Column_List"/>
        )VALUES
        <foreach collection="${pojoName}s" item="${pojoName}" index="index" separator=",">
            (
        <#list finalFields as fieldAndColumn>
            <#if fieldAndColumn?is_last>
            ${r"#"}{${pojoName}.${fieldAndColumn.field}}
            <#else>
            ${r"#"}{${pojoName}.${fieldAndColumn.field}},
            </#if>
        </#list>
            )
        </foreach>
        </#if>
        <#if currentDatabase=="Oracle">
        INSERT ALL
        <foreach collection="${pojoName}s" item="${pojoName}">
            INTO ${tableName} (
            <include refid="Base_Column_List"/>
            )VALUES(
            <#list finalFields as fieldAndColumn>
                <#if fieldAndColumn?is_last>
                ${r"#"}{${pojoName}.${fieldAndColumn.field}}
                <#else>
                ${r"#"}{${pojoName}.${fieldAndColumn.field}},
                </#if>
            </#list>
            )
        </foreach>
        select * from dual
        </#if>
