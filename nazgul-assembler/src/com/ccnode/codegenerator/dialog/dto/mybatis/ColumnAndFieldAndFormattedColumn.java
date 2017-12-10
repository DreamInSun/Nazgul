// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ColumnAndFieldAndFormattedColumn.java

package com.ccnode.codegenerator.dialog.dto.mybatis;


// Referenced classes of package com.ccnode.codegenerator.dialog.dto.mybatis:
//			ColumnAndField

public class ColumnAndFieldAndFormattedColumn extends ColumnAndField
{

	private String formattedColumn;
	private String jdbcType;

	public ColumnAndFieldAndFormattedColumn()
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

	public String getFormattedColumn()
	{
		return formattedColumn;
	}

	public void setFormattedColumn(String formattedColumn)
	{
		this.formattedColumn = formattedColumn;
	}
}
