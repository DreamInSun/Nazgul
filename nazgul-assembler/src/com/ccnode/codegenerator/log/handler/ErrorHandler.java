// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ErrorHandler.java

package com.ccnode.codegenerator.log.handler;

import com.ccnode.codegenerator.log.LogEvent;
import com.ccnode.codegenerator.log.LoggerLevel;

// Referenced classes of package com.ccnode.codegenerator.log.handler:
//			LoggerHandler

public class ErrorHandler extends LoggerHandler
{

	public ErrorHandler(LoggerHandler next)
	{
		super(next);
	}

	public void handleRequest(LogEvent req)
	{
		if (req.getLevel() != LoggerLevel.ERROR);
		super.handleRequest(req);
	}
}
