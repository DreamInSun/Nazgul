// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MethodNameParsedResult.java

package com.ccnode.codegenerator.methodnameparser.buidler;

import com.ccnode.codegenerator.methodnameparser.parsedresult.count.ParsedCount;
import com.ccnode.codegenerator.methodnameparser.parsedresult.delete.ParsedDelete;
import com.ccnode.codegenerator.methodnameparser.parsedresult.find.ParsedFind;
import com.ccnode.codegenerator.methodnameparser.parsedresult.update.ParsedUpdate;
import com.ccnode.codegenerator.pojo.FieldToColumnRelation;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.xml.XmlFile;
import java.util.Map;

// Referenced classes of package com.ccnode.codegenerator.methodnameparser.buidler:
//			ParsedMethodEnum

public class MethodNameParsedResult
{

	private ParsedMethodEnum parsedType;
	private String methodName;
	private FieldToColumnRelation relation;
	private XmlFile mybatisXmlFile;
	private String allColumnName;
	private Project project;
	private PsiClass srcClass;
	private String tableName;
	private Map fieldMap;
	private String psiClassName;
	private ParsedFind parsedFind;
	private ParsedUpdate parsedUpdate;
	private ParsedDelete parsedDelete;
	private ParsedCount parsedCount;
	private String psiClassFullName;

	public MethodNameParsedResult()
	{
	}

	public String getPsiClassFullName()
	{
		return psiClassFullName;
	}

	public void setPsiClassFullName(String psiClassFullName)
	{
		this.psiClassFullName = psiClassFullName;
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

	public ParsedMethodEnum getParsedType()
	{
		return parsedType;
	}

	public void setParsedType(ParsedMethodEnum parsedType)
	{
		this.parsedType = parsedType;
	}

	public String getMethodName()
	{
		return methodName;
	}

	public void setMethodName(String methodName)
	{
		this.methodName = methodName;
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

	public ParsedFind getParsedFind()
	{
		return parsedFind;
	}

	public void setParsedFind(ParsedFind parsedFind)
	{
		this.parsedFind = parsedFind;
	}

	public ParsedUpdate getParsedUpdate()
	{
		return parsedUpdate;
	}

	public void setParsedUpdate(ParsedUpdate parsedUpdate)
	{
		this.parsedUpdate = parsedUpdate;
	}

	public ParsedDelete getParsedDelete()
	{
		return parsedDelete;
	}

	public void setParsedDelete(ParsedDelete parsedDelete)
	{
		this.parsedDelete = parsedDelete;
	}

	public ParsedCount getParsedCount()
	{
		return parsedCount;
	}

	public void setParsedCount(ParsedCount parsedCount)
	{
		this.parsedCount = parsedCount;
	}

	public XmlFile getMybatisXmlFile()
	{
		return mybatisXmlFile;
	}

	public void setMybatisXmlFile(XmlFile mybatisXmlFile)
	{
		this.mybatisXmlFile = mybatisXmlFile;
	}

	public String getAllColumnName()
	{
		return allColumnName;
	}

	public void setAllColumnName(String allColumnName)
	{
		this.allColumnName = allColumnName;
	}
}
