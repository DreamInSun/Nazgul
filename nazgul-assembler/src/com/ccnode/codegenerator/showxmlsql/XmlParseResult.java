// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   XmlParseResult.java

package com.ccnode.codegenerator.showxmlsql;


public class XmlParseResult
{

	private String sql;
	private Boolean isValid;
	private String errorText;

	public XmlParseResult()
	{
	}

	public String getSql()
	{
		return sql;
	}

	public Boolean getIsValid()
	{
		return isValid;
	}

	public String getErrorText()
	{
		return errorText;
	}

	public void setSql(String sql)
	{
		this.sql = sql;
	}

	public void setIsValid(Boolean isValid)
	{
		this.isValid = isValid;
	}

	public void setErrorText(String errorText)
	{
		this.errorText = errorText;
	}
}
