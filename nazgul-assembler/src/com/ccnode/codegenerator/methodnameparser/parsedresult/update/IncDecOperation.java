// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IncDecOperation.java

package com.ccnode.codegenerator.methodnameparser.parsedresult.update;


public class IncDecOperation
{

	private String beforeAnno;
	private String operator;

	public IncDecOperation()
	{
	}

	public String getBeforeAnno()
	{
		return beforeAnno;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setBeforeAnno(String beforeAnno)
	{
		this.beforeAnno = beforeAnno;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}
}
