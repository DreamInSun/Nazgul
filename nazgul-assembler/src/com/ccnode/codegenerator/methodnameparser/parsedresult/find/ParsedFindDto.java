// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ParsedFindDto.java

package com.ccnode.codegenerator.methodnameparser.parsedresult.find;

import java.util.List;

public class ParsedFindDto
{

	private List parsedFinds;
	private List parsedFindErrors;

	public ParsedFindDto()
	{
	}

	public List getParsedFinds()
	{
		return parsedFinds;
	}

	public void setParsedFinds(List parsedFinds)
	{
		this.parsedFinds = parsedFinds;
	}

	public List getParsedFindErrors()
	{
		return parsedFindErrors;
	}

	public void setParsedFindErrors(List parsedFindErrors)
	{
		this.parsedFindErrors = parsedFindErrors;
	}
}
