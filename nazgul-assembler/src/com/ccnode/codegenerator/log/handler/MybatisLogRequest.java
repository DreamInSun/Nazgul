// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MybatisLogRequest.java

package com.ccnode.codegenerator.log.handler;


public class MybatisLogRequest
{

	private String className;
	private String messages;
	private String loggerLevel;

	public MybatisLogRequest()
	{
	}

	public String getLoggerLevel()
	{
		return loggerLevel;
	}

	public void setLoggerLevel(String loggerLevel)
	{
		this.loggerLevel = loggerLevel;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public String getMessages()
	{
		return messages;
	}

	public void setMessages(String messages)
	{
		this.messages = messages;
	}
}
