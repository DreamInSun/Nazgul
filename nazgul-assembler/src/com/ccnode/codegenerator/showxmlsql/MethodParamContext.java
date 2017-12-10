// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MethodParamContext.java

package com.ccnode.codegenerator.showxmlsql;

import java.util.Map;

/**
 * @deprecated Class MethodParamContext is deprecated
 */

public class MethodParamContext
{

	private Map paramMap;
	private Map listParamSize;

	public MethodParamContext()
	{
	}

	public Map getParamMap()
	{
		return paramMap;
	}

	public Map getListParamSize()
	{
		return listParamSize;
	}

	public void setParamMap(Map paramMap)
	{
		this.paramMap = paramMap;
	}

	public void setListParamSize(Map listParamSize)
	{
		this.listParamSize = listParamSize;
	}
}
