// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LogFactory.java

package com.ccnode.codegenerator.log;

import com.ccnode.codegenerator.log.handler.ErrorHandler;
import com.ccnode.codegenerator.log.handler.InfoHandler;
import com.ccnode.codegenerator.log.handler.LoggerHandler;
import com.ccnode.codegenerator.log.handler.SendToServerHandler;

// Referenced classes of package com.ccnode.codegenerator.log:
//			LogImpl, Log

public class LogFactory
{

	private static LoggerHandler chain = new InfoHandler(new ErrorHandler(new SendToServerHandler(null)));

	public LogFactory()
	{
	}

	public static Log getLogger(Class aClass)
	{
		return new LogImpl(aClass.getName(), chain);
	}

	public static Log getLogger(String name)
	{
		return new LogImpl(name, chain);
	}

}
