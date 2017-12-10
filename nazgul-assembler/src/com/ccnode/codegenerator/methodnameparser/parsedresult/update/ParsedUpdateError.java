// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ParsedUpdateError.java

package com.ccnode.codegenerator.methodnameparser.parsedresult.update;

import com.ccnode.codegenerator.methodnameparser.parsedresult.base.ParsedErrorBase;

// Referenced classes of package com.ccnode.codegenerator.methodnameparser.parsedresult.update:
//			ParsedUpdate

public class ParsedUpdateError extends ParsedErrorBase
{

	private ParsedUpdate parsedUpdate;

	public ParsedUpdateError()
	{
	}

	public ParsedUpdate getParsedUpdate()
	{
		return parsedUpdate;
	}

	public void setParsedUpdate(ParsedUpdate parsedUpdate)
	{
		this.parsedUpdate = parsedUpdate;
	}
}
