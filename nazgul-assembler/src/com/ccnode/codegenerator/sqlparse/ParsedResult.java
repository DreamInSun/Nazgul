// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ParsedResult.java

package com.ccnode.codegenerator.sqlparse;

import java.util.List;

public class ParsedResult
{

	private String currentState;
	private List recommedValues;

	public ParsedResult()
	{
	}

	public String getCurrentState()
	{
		return currentState;
	}

	public void setCurrentState(String currentState)
	{
		this.currentState = currentState;
	}

	public List getRecommedValues()
	{
		return recommedValues;
	}

	public void setRecommedValues(List recommedValues)
	{
		this.recommedValues = recommedValues;
	}
}
