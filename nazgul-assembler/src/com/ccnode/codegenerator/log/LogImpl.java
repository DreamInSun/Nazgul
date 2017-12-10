// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LogImpl.java

package com.ccnode.codegenerator.log;

import com.ccnode.codegenerator.log.handler.LoggerHandler;

// Referenced classes of package com.ccnode.codegenerator.log:
//			LogEvent, Log, LoggerLevel

public class LogImpl
	implements Log
{

	private String name;
	private LoggerHandler chain;

	public LogImpl(String name, LoggerHandler chain)
	{
		this.name = name;
		this.chain = chain;
	}

	public String getName()
	{
		return name;
	}

	public void info(Exception e)
	{
		LogEvent event = new LogEvent();
		event.setName(name);
		event.setE(e);
		event.setLevel(LoggerLevel.INFO);
		chain.handleRequest(event);
	}

	public void error(Exception e)
	{
		LogEvent event = new LogEvent();
		event.setName(name);
		event.setE(e);
		event.setLevel(LoggerLevel.ERROR);
		chain.handleRequest(event);
	}

	public void info(String message)
	{
		LogEvent event = new LogEvent();
		event.setName(name);
		event.setMessage(message);
		event.setLevel(LoggerLevel.INFO);
		chain.handleRequest(event);
	}

	public void info(String message, Exception e)
	{
		LogEvent event = new LogEvent();
		event.setName(name);
		event.setMessage(message);
		event.setE(e);
		event.setLevel(LoggerLevel.INFO);
		chain.handleRequest(event);
	}

	public void error(String message, Exception e)
	{
		LogEvent event = new LogEvent();
		event.setMessage(message);
		event.setName(name);
		event.setE(e);
		event.setLevel(LoggerLevel.ERROR);
		chain.handleRequest(event);
	}

	public void error(String message)
	{
		LogEvent event = new LogEvent();
		event.setName(name);
		event.setMessage(message);
		event.setLevel(LoggerLevel.ERROR);
		chain.handleRequest(event);
	}
}
