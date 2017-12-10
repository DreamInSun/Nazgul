// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DatabaseInfo.java

package com.ccnode.codegenerator.datasourceToolWindow.dbInfo;

import java.util.List;

public class DatabaseInfo
{

	private String databaseName;
	private List tableInfoList;

	public DatabaseInfo()
	{
	}

	public String getDatabaseName()
	{
		return databaseName;
	}

	public void setDatabaseName(String databaseName)
	{
		this.databaseName = databaseName;
	}

	public List getTableInfoList()
	{
		return tableInfoList;
	}

	public void setTableInfoList(List tableInfoList)
	{
		this.tableInfoList = tableInfoList;
	}
}
