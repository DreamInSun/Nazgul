// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ParsedCountDto.java

package com.ccnode.codegenerator.methodnameparser.parsedresult.count;

import java.util.List;

public class ParsedCountDto
{

	private List parsedCounts;
	private List errors;

	public ParsedCountDto()
	{
	}

	public List getParsedCounts()
	{
		return parsedCounts;
	}

	public void setParsedCounts(List parsedCounts)
	{
		this.parsedCounts = parsedCounts;
	}

	public List getErrors()
	{
		return errors;
	}

	public void setErrors(List errors)
	{
		this.errors = errors;
	}
}
