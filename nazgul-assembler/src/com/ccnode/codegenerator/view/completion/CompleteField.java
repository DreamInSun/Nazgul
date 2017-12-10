// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CompleteField.java

package com.ccnode.codegenerator.view.completion;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CompleteField
{

	private String databaseType;
	private String tableName;
	private String fieldName;

	public CompleteField(String databaseType, String tableName, String fieldName)
	{
		this.databaseType = databaseType;
		this.tableName = tableName;
		this.fieldName = fieldName;
	}

	public String getDatabaseType()
	{
		return databaseType;
	}

	public void setDatabaseType(String databaseType)
	{
		this.databaseType = databaseType;
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getFieldName()
	{
		return fieldName;
	}

	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
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
			CompleteField that = (CompleteField)o;
			return (new EqualsBuilder()).append(databaseType, that.databaseType).append(tableName, that.tableName).append(fieldName, that.fieldName).isEquals();
		}
	}

	public int hashCode()
	{
		return (new HashCodeBuilder(17, 37)).append(databaseType).append(tableName).append(fieldName).toHashCode();
	}
}
