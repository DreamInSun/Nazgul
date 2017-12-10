// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Log.java

package com.ccnode.codegenerator.log;


public interface Log
{

	public abstract String getName();

	public abstract void info(Exception exception);

	public abstract void error(Exception exception);

	public abstract void info(String s);

	public abstract void info(String s, Exception exception);

	public abstract void error(String s, Exception exception);

	public abstract void error(String s);
}
