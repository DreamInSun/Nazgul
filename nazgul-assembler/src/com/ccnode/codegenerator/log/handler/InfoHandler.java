// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   InfoHandler.java

package com.ccnode.codegenerator.log.handler;

import com.ccnode.codegenerator.log.LogEvent;

// Referenced classes of package com.ccnode.codegenerator.log.handler:
//			LoggerHandler

public class InfoHandler extends LoggerHandler
{

	public InfoHandler(LoggerHandler next)
	{
		super(next);
	}

	public void handleRequest(LogEvent req)
	{
		super.handleRequest(req);
	}
}
