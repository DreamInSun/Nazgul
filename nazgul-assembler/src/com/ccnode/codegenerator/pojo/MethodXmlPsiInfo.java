// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MethodXmlPsiInfo.java

package com.ccnode.codegenerator.pojo;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.xml.XmlFile;
import java.util.Map;

// Referenced classes of package com.ccnode.codegenerator.pojo:
//			FieldToColumnRelation

public class MethodXmlPsiInfo
{

	private String methodName;
	private FieldToColumnRelation relation;
	private String tableName;
	private Map fieldMap;
	private String allColumnName;
	private String psiClassName;
	private XmlFile mybatisXmlFile;
	private String psiClassFullName;
	private PsiClass serviceClass;
	private PsiClass serviceInterfaceClass;
	private Project project;
	private PsiClass srcClass;

	public MethodXmlPsiInfo()
	{
	}

	public XmlFile getMybatisXmlFile()
	{
		return mybatisXmlFile;
	}

	public void setMybatisXmlFile(XmlFile mybatisXmlFile)
	{
		this.mybatisXmlFile = mybatisXmlFile;
	}

	public Project getProject()
	{
		return project;
	}

	public void setProject(Project project)
	{
		this.project = project;
	}

	public PsiClass getSrcClass()
	{
		return srcClass;
	}

	public void setSrcClass(PsiClass srcClass)
	{
		this.srcClass = srcClass;
	}

	public String getPsiClassFullName()
	{
		return psiClassFullName;
	}

	public void setPsiClassFullName(String psiClassFullName)
	{
		this.psiClassFullName = psiClassFullName;
	}

	public Map getFieldMap()
	{
		return fieldMap;
	}

	public void setFieldMap(Map fieldMap)
	{
		this.fieldMap = fieldMap;
	}

	public String getPsiClassName()
	{
		return psiClassName;
	}

	public void setPsiClassName(String psiClassName)
	{
		this.psiClassName = psiClassName;
	}

	public FieldToColumnRelation getRelation()
	{
		return relation;
	}

	public void setRelation(FieldToColumnRelation relation)
	{
		this.relation = relation;
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getMethodName()
	{
		return methodName;
	}

	public void setMethodName(String methodName)
	{
		this.methodName = methodName;
	}

	public String getAllColumnName()
	{
		return allColumnName;
	}

	public void setAllColumnName(String allColumnName)
	{
		this.allColumnName = allColumnName;
	}

	public PsiClass getServiceClass()
	{
		return serviceClass;
	}

	public void setServiceClass(PsiClass serviceClass)
	{
		this.serviceClass = serviceClass;
	}

	public PsiClass getServiceInterfaceClass()
	{
		return serviceInterfaceClass;
	}

	public void setServiceInterfaceClass(PsiClass serviceInterfaceClass)
	{
		this.serviceInterfaceClass = serviceInterfaceClass;
	}
}
