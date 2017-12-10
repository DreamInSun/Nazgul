// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   FieldToColumnRelation.java

package com.ccnode.codegenerator.pojo;

import com.ccnode.codegenerator.database.DatabaseComponenent;
import java.util.Map;

public class FieldToColumnRelation
{

	private String resultMapId;
	private Boolean hasFullRelation;
	private Boolean hasJavaTypeResultMap;
	private Map filedToColumnMap;
	private Map fieldToJdbcTypeMap;

	public FieldToColumnRelation()
	{
	}

	public Map getFieldToJdbcTypeMap()
	{
		return fieldToJdbcTypeMap;
	}

	public void setFieldToJdbcTypeMap(Map fieldToJdbcTypeMap)
	{
		this.fieldToJdbcTypeMap = fieldToJdbcTypeMap;
	}

	public String getResultMapId()
	{
		return resultMapId;
	}

	public void setResultMapId(String resultMapId)
	{
		this.resultMapId = resultMapId;
	}

	public Map getFiledToColumnMap()
	{
		return filedToColumnMap;
	}

	public void setFiledToColumnMap(Map filedToColumnMap)
	{
		this.filedToColumnMap = filedToColumnMap;
	}

	public String getPropFormatColumn(String prop)
	{
		String s = (String)filedToColumnMap.get(prop.toLowerCase());
		return DatabaseComponenent.formatColumn(s);
	}

	public Boolean getHasFullRelation()
	{
		return hasFullRelation;
	}

	public void setHasFullRelation(Boolean hasFullRelation)
	{
		this.hasFullRelation = hasFullRelation;
	}

	public String getPropColumn(String prop)
	{
		return (String)filedToColumnMap.get(prop.toLowerCase());
	}

	public Boolean getHasJavaTypeResultMap()
	{
		return hasJavaTypeResultMap;
	}

	public void setHasJavaTypeResultMap(Boolean hasJavaTypeResultMap)
	{
		this.hasJavaTypeResultMap = hasJavaTypeResultMap;
	}

	public String getJdbcType(String prop)
	{
		String s = (String)fieldToJdbcTypeMap.get(prop.toLowerCase());
		return s != null ? (new StringBuilder()).append(",jdbcType=").append(s).toString() : "";
	}
}
