// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   OrderByRule.java

package com.ccnode.codegenerator.methodnameparser.parsedresult.find;


public class OrderByRule
{

	private String prop;
	private String order;

	public OrderByRule()
	{
		order = "asc";
	}

	public String getProp()
	{
		return prop;
	}

	public void setProp(String prop)
	{
		this.prop = prop;
	}

	public String getOrder()
	{
		return order;
	}

	public void setOrder(String order)
	{
		this.order = order;
	}
}
