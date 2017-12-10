// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   QueryRule.java

package com.ccnode.codegenerator.methodnameparser.parsedresult.base;


public class QueryRule
{

	private String prop;
	private String operator;
	private String connector;
	private boolean useIfTest;

	public QueryRule()
	{
	}

	public boolean isUseIfTest()
	{
		return useIfTest;
	}

	public void setUseIfTest(boolean useIfTest)
	{
		this.useIfTest = useIfTest;
	}

	public String getProp()
	{
		return prop;
	}

	public void setProp(String prop)
	{
		this.prop = prop;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}

	public String getConnector()
	{
		return connector;
	}

	public void setConnector(String connector)
	{
		this.connector = connector;
	}
}
