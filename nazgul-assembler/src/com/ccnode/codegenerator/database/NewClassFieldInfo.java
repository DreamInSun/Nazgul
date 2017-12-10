// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   NewClassFieldInfo.java

package com.ccnode.codegenerator.database;


public class NewClassFieldInfo
{

	private String fieldName;
	private String fieldShortType;

	public NewClassFieldInfo()
	{
	}

	public String getFieldName()
	{
		return fieldName;
	}

	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	public String getFieldShortType()
	{
		return fieldShortType;
	}

	public void setFieldShortType(String fieldShortType)
	{
		this.fieldShortType = fieldShortType;
	}
}
