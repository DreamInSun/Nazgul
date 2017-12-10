// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ParsedErrorBase.java

package com.ccnode.codegenerator.methodnameparser.parsedresult.base;


public class ParsedErrorBase
{

	protected Integer lastState;
	protected String remaining;

	public ParsedErrorBase()
	{
	}

	public String getRemaining()
	{
		return remaining;
	}

	public void setRemaining(String remaining)
	{
		this.remaining = remaining;
	}

	public Integer getLastState()
	{
		return lastState;
	}

	public void setLastState(Integer lastState)
	{
		this.lastState = lastState;
	}
}
