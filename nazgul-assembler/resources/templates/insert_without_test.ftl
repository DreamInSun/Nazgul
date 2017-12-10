
        INSERT INTO ${tableName} (
        <#list finalFields as filedAndColumn>
        <#if filedAndColumn?is_last>
            ${filedAndColumn.column}
        <#else>
            ${filedAndColumn.column},
        </#if>
        </#list>
        ) VALUES (
        <#list finalFields as filedAndColumn>
        <#if filedAndColumn?is_last>
            ${r"#"}{${pojoName}.${filedAndColumn.field}}
        <#else>
            ${r"#"}{${pojoName}.${filedAndColumn.field}},
        </#if>
        </#list>
        )
