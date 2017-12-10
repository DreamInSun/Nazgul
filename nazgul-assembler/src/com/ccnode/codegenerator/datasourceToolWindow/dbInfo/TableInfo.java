// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TableInfo.java

package com.ccnode.codegenerator.datasourceToolWindow.dbInfo;

import java.util.List;

public class TableInfo
{

	private String tableName;
	private List tableColumnInfos;

	public TableInfo()
	{
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public List getTableColumnInfos()
	{
		return tableColumnInfos;
	}

	public void setTableColumnInfos(List tableColumnInfos)
	{
		this.tableColumnInfos = tableColumnInfos;
	}
}
