// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ClassMapperMethod.java

package com.ccnode.codegenerator.dialog.dto.mybatis;


public class ClassMapperMethod
{

	private String methodName;
	private String paramAnno;
	private boolean list;

	public ClassMapperMethod()
	{
	}

	public String getMethodName()
	{
		return methodName;
	}

	public void setMethodName(String methodName)
	{
		this.methodName = methodName;
	}

	public String getParamAnno()
	{
		return paramAnno;
	}

	public void setParamAnno(String paramAnno)
	{
		this.paramAnno = paramAnno;
	}

	public boolean getList()
	{
		return list;
	}

	public void setList(Boolean list)
	{
		this.list = list.booleanValue();
	}
}
