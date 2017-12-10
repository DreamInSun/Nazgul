// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ParsedDeleteDto.java

package com.ccnode.codegenerator.methodnameparser.parsedresult.delete;

import java.util.List;

public class ParsedDeleteDto
{

	private List parsedDeletes;
	private List errors;

	public ParsedDeleteDto()
	{
	}

	public List getParsedDeletes()
	{
		return parsedDeletes;
	}

	public void setParsedDeletes(List parsedDeletes)
	{
		this.parsedDeletes = parsedDeletes;
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
