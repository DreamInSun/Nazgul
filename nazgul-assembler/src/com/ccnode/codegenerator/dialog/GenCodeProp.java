// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenCodeProp.java

package com.ccnode.codegenerator.dialog;


public class GenCodeProp
{

	private String fieldName;
	private String columnName;
	private Boolean primaryKey;
	private Boolean unique;
	private String size;
	private String filedType;
	private String defaultValue;
	private Boolean canBeNull;
	private String jdbcType;
	private Boolean index;
	private Boolean hasDefaultValue;
	private String comment;

	public GenCodeProp()
	{
	}

	public String getJdbcType()
	{
		return jdbcType;
	}

	public void setJdbcType(String jdbcType)
	{
		this.jdbcType = jdbcType;
	}

	public Boolean getIndex()
	{
		return index;
	}

	public void setIndex(Boolean index)
	{
		this.index = index;
	}

	public Boolean getHasDefaultValue()
	{
		return hasDefaultValue;
	}

	public void setHasDefaultValue(Boolean hasDefaultValue)
	{
		this.hasDefaultValue = hasDefaultValue;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public String getFieldName()
	{
		return fieldName;
	}

	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	public Boolean getCanBeNull()
	{
		return canBeNull;
	}

	public void setCanBeNull(Boolean canBeNull)
	{
		this.canBeNull = canBeNull;
	}

	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	public Boolean getPrimaryKey()
	{
		return primaryKey;
	}

	public void setPrimaryKey(Boolean primaryKey)
	{
		this.primaryKey = primaryKey;
	}

	public Boolean getUnique()
	{
		return unique;
	}

	public void setUnique(Boolean unique)
	{
		this.unique = unique;
	}

	public String getSize()
	{
		return size;
	}

	public void setSize(String size)
	{
		this.size = size;
	}

	public String getFiledType()
	{
		return filedType;
	}

	public void setFiledType(String filedType)
	{
		this.filedType = filedType;
	}

	public String getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}
}
