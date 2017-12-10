// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ParsedUpdateDto.java

package com.ccnode.codegenerator.methodnameparser.parsedresult.update;

import java.util.List;

public class ParsedUpdateDto
{

	private List updateList;
	private List errorList;

	public ParsedUpdateDto()
	{
	}

	public List getUpdateList()
	{
		return updateList;
	}

	public void setUpdateList(List updateList)
	{
		this.updateList = updateList;
	}

	public List getErrorList()
	{
		return errorList;
	}

	public void setErrorList(List errorList)
	{
		this.errorList = errorList;
	}
}
