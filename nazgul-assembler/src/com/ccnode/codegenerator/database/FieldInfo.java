// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   FieldInfo.java

package com.ccnode.codegenerator.database;


public class FieldInfo
{

	private String fieldName;
	private String fieldType;
	private String columnName;
	private String fieldModifier;

	public FieldInfo()
	{
	}

	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	public String getFieldName()
	{
		return fieldName;
	}

	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	public String getFieldType()
	{
		return fieldType;
	}

	public void setFieldType(String fieldType)
	{
		this.fieldType = fieldType;
	}

	public String getFieldModifier()
	{
		return fieldModifier;
	}

	public void setFieldModifier(String fieldModifier)
	{
		this.fieldModifier = fieldModifier;
	}
}
