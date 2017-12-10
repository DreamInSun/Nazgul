// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LoggerHandler.java

package com.ccnode.codegenerator.log.handler;

import com.ccnode.codegenerator.log.LogEvent;

public abstract class LoggerHandler
{

	private LoggerHandler next;

	public LoggerHandler(LoggerHandler next)
	{
		this.next = next;
	}

	public void handleRequest(LogEvent req)
	{
		if (next != null)
			next.handleRequest(req);
	}
}
