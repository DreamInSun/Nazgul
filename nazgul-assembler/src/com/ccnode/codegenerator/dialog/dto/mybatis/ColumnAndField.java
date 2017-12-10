// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ColumnAndField.java

package com.ccnode.codegenerator.dialog.dto.mybatis;


public class ColumnAndField
{

	protected String column;
	protected String field;

	public ColumnAndField()
	{
	}

	public ColumnAndField(String column, String field)
	{
		this.column = column;
		this.field = field;
	}

	public String getColumn()
	{
		return column;
	}

	public void setColumn(String column)
	{
		this.column = column;
	}

	public String getField()
	{
		return field;
	}

	public void setField(String field)
	{
		this.field = field;
	}
}
