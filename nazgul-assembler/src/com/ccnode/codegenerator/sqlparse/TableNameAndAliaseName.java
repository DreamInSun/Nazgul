// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TableNameAndAliaseName.java

package com.ccnode.codegenerator.sqlparse;


public class TableNameAndAliaseName
{

	private String tableName;
	private String aliaseName;

	public TableNameAndAliaseName()
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

	public String getAliaseName()
	{
		return aliaseName;
	}

	public void setAliaseName(String aliaseName)
	{
		this.aliaseName = aliaseName;
	}
}
