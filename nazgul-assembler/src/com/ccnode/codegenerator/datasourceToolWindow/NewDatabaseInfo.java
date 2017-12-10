// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   NewDatabaseInfo.java

package com.ccnode.codegenerator.datasourceToolWindow;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class NewDatabaseInfo
{

	private String databaseType;
	private String url;
	private String userName;
	private String password;
	private String database;

	public NewDatabaseInfo()
	{
	}

	public String getDatabaseType()
	{
		return databaseType;
	}

	public void setDatabaseType(String databaseType)
	{
		this.databaseType = databaseType;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
		{
			return false;
		} else
		{
			NewDatabaseInfo that = (NewDatabaseInfo)o;
			return (new EqualsBuilder()).append(databaseType, that.databaseType).append(url, that.url).append(userName, that.userName).append(password, that.password).append(database, that.database).isEquals();
		}
	}

	public int hashCode()
	{
		return (new HashCodeBuilder(17, 37)).append(databaseType).append(url).append(userName).append(password).append(database).toHashCode();
	}

	public String getDatabase()
	{
		return database;
	}

	public void setDatabase(String database)
	{
		this.database = database;
	}
}
