// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ParsedDeleteError.java

package com.ccnode.codegenerator.methodnameparser.parsedresult.delete;

import com.ccnode.codegenerator.methodnameparser.parsedresult.base.ParsedErrorBase;

// Referenced classes of package com.ccnode.codegenerator.methodnameparser.parsedresult.delete:
//			ParsedDelete

public class ParsedDeleteError extends ParsedErrorBase
{

	private ParsedDelete parsedDelete;

	public ParsedDeleteError()
	{
	}

	public ParsedDelete getParsedDelete()
	{
		return parsedDelete;
	}

	public void setParsedDelete(ParsedDelete parsedDelete)
	{
		this.parsedDelete = parsedDelete;
	}
}
