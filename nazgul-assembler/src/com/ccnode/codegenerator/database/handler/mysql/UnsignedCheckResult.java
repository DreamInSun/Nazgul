// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   UnsignedCheckResult.java

package com.ccnode.codegenerator.database.handler.mysql;


public class UnsignedCheckResult
{

	private boolean unsigned;
	private String type;

	public UnsignedCheckResult()
	{
	}

	public boolean isUnsigned()
	{
		return unsigned;
	}

	public void setUnsigned(boolean unsigned)
	{
		this.unsigned = unsigned;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
}
