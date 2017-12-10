// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ParsedFindError.java

package com.ccnode.codegenerator.methodnameparser.parsedresult.find;

import com.ccnode.codegenerator.methodnameparser.parsedresult.base.ParsedErrorBase;

// Referenced classes of package com.ccnode.codegenerator.methodnameparser.parsedresult.find:
//			ParsedFind

public class ParsedFindError extends ParsedErrorBase
{

	private ParsedFind parsedFind;

	public ParsedFindError()
	{
	}

	public ParsedFind getParsedFind()
	{
		return parsedFind;
	}

	public void setParsedFind(ParsedFind parsedFind)
	{
		this.parsedFind = parsedFind;
	}
}
