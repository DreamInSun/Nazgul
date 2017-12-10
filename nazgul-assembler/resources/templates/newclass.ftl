package ${classPackage};

<#list importList as import>
import ${import};
</#list>


public class ${className} {
    <#list fields as field>
    private ${field.fieldShortType} ${field.fieldName};
    </#list>
}