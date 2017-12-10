// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MethodNameCheckReuslt.java

package com.ccnode.codegenerator.util;

import com.ccnode.codegenerator.methodnameparser.buidler.ParsedMethodEnum;

public class MethodNameCheckReuslt
{

	private boolean valid;
	private ParsedMethodEnum parsedMethodEnum;

	public MethodNameCheckReuslt()
	{
		valid = false;
	}

	public boolean isValid()
	{
		return valid;
	}

	public ParsedMethodEnum getParsedMethodEnum()
	{
		return parsedMethodEnum;
	}

	public void setValid(boolean valid)
	{
		this.valid = valid;
	}

	public void setParsedMethodEnum(ParsedMethodEnum parsedMethodEnum)
	{
		this.parsedMethodEnum = parsedMethodEnum;
	}
}
