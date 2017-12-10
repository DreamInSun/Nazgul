// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ParsedCountError.java

package com.ccnode.codegenerator.methodnameparser.parsedresult.count;

import com.ccnode.codegenerator.methodnameparser.parsedresult.base.ParsedErrorBase;

// Referenced classes of package com.ccnode.codegenerator.methodnameparser.parsedresult.count:
//			ParsedCount

public class ParsedCountError extends ParsedErrorBase
{

	private ParsedCount parsedCount;

	public ParsedCountError()
	{
	}

	public ParsedCount getParsedCount()
	{
		return parsedCount;
	}

	public void setParsedCount(ParsedCount parsedCount)
	{
		this.parsedCount = parsedCount;
	}
}
