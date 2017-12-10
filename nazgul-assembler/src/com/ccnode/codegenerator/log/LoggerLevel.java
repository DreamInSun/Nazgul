// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LoggerLevel.java

package com.ccnode.codegenerator.log;


public final class LoggerLevel extends Enum
{

	public static final LoggerLevel INFO;
	public static final LoggerLevel ERROR;
	private static final LoggerLevel $VALUES[];

	public static LoggerLevel[] values()
	{
		return (LoggerLevel[])$VALUES.clone();
	}

	public static LoggerLevel valueOf(String name)
	{
		return (LoggerLevel)Enum.valueOf(com/ccnode/codegenerator/log/LoggerLevel, name);
	}

	private LoggerLevel(String s, int i)
	{
		super(s, i);
	}

	static 
	{
		INFO = new LoggerLevel("INFO", 0);
		ERROR = new LoggerLevel("ERROR", 1);
		$VALUES = (new LoggerLevel[] {
			INFO, ERROR
		});
	}
}
