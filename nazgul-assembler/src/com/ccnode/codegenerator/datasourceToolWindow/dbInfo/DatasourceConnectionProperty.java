// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DatasourceConnectionProperty.java

package com.ccnode.codegenerator.datasourceToolWindow.dbInfo;


public class DatasourceConnectionProperty
{

	private final String databaseType;
	private final String url;
	private final String userName;
	private final String password;
	private final String database;

	public DatasourceConnectionProperty(String databaseType, String url, String userName, String password, String database)
	{
		this.databaseType = databaseType;
		this.url = url;
		this.userName = userName;
		this.password = password;
		this.database = database;
	}

	public String getDatabaseType()
	{
		return databaseType;
	}

	public String getUrl()
	{
		return url;
	}

	public String getUserName()
	{
		return userName;
	}

	public String getPassword()
	{
		return password;
	}

	public String getDatabase()
	{
		return database;
	}
}
