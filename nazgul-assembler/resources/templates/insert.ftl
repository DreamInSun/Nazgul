
        INSERT INTO ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
    <#list finalFields as filedAndColumn>
        <#if filedAndColumn?is_last>
            <if test="${pojoName}.${filedAndColumn.field}!=null"> ${filedAndColumn.column}</if>
        <#else>
            <if test="${pojoName}.${filedAndColumn.field}!=null"> ${filedAndColumn.column},</if>
        </#if>
    </#list>
        </trim>
        VALUES
        <trim prefix="(" suffix=")" suffixOverrides=",">
    <#list finalFields as filedAndColumn>
        <#if filedAndColumn?is_last>
            <if test="${pojoName}.${filedAndColumn.field}!=null"> ${r"#"}{${pojoName}.${filedAndColumn.field}}</if>
        <#else>
            <if test="${pojoName}.${filedAndColumn.field}!=null"> ${r"#"}{${pojoName}.${filedAndColumn.field}},</if>
        </#if>
    </#list>
        </trim>
