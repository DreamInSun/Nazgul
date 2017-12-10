// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExistXmlTagInfo.java

package com.ccnode.codegenerator.pojo;


// Referenced classes of package com.ccnode.codegenerator.pojo:
//			FieldToColumnRelation

public class ExistXmlTagInfo
{

	private String tableName;
	private FieldToColumnRelation fieldToColumnRelation;
	private boolean hasAllColumn;
	private String allColumnSqlId;
	private boolean hasResultMap;

	public ExistXmlTagInfo()
	{
	}

	public boolean isHasResultMap()
	{
		return hasResultMap;
	}

	public void setHasResultMap(boolean hasResultMap)
	{
		this.hasResultMap = hasResultMap;
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public FieldToColumnRelation getFieldToColumnRelation()
	{
		return fieldToColumnRelation;
	}

	public void setFieldToColumnRelation(FieldToColumnRelation fieldToColumnRelation)
	{
		this.fieldToColumnRelation = fieldToColumnRelation;
	}

	public boolean isHasAllColumn()
	{
		return hasAllColumn;
	}

	public void setHasAllColumn(boolean hasAllColumn)
	{
		this.hasAllColumn = hasAllColumn;
	}

	public String getAllColumnSqlId()
	{
		return allColumnSqlId;
	}

	public void setAllColumnSqlId(String allColumnSqlId)
	{
		this.allColumnSqlId = allColumnSqlId;
	}
}
