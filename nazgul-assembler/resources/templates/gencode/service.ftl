package ${servicePackage};

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import ${pojoFullType};
import ${daoFullType};
<#if serviceInterfaceFullType??>
import ${serviceInterfaceFullType};
</#if>

@Service
public class ${serviceType} <#if serviceInterfaceName??>implements ${serviceInterfaceName}</#if>{

    @Resource
    private ${daoType} ${daoName};

    <#if serviceInterfaceName??>@Override</#if>
    public int insert(${pojoType} ${pojoName}){
        return ${daoName}.insert(${pojoName});
    }

    <#if serviceInterfaceName??>@Override</#if>
    public int insertSelective(${pojoType} ${pojoName}){
        return ${daoName}.insertSelective(${pojoName});
    }

    <#if serviceInterfaceName??>@Override</#if>
    public int insertList(List<${pojoType}> ${pojoName}s){
        return ${daoName}.insertList(${pojoName}s);
    }

    <#if serviceInterfaceName??>@Override</#if>
    public int update(${pojoType} ${pojoName}){
        return ${daoName}.update(${pojoName});
    }
}