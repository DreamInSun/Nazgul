
        UPDATE ${tableName}
        <set>
        <#list finalFields as filedAndColumn>
            <#if filedAndColumn?is_last>
            <if test="${pojoName}.${filedAndColumn.field} != null"> ${filedAndColumn.column} = ${r"#"}{${pojoName}.${filedAndColumn.field}}</if>
            <#else>
            <if test="${pojoName}.${filedAndColumn.field} != null"> ${filedAndColumn.column} = ${r"#"}{${pojoName}.${filedAndColumn.field}},</if>
            </#if>
        </#list>
        </set>
