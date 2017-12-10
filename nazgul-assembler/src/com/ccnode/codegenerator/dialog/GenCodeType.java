// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenCodeType.java

package com.ccnode.codegenerator.dialog;


public final class GenCodeType extends Enum
{

	public static final GenCodeType INSERT;
	public static final GenCodeType UPDATE;
	private static final GenCodeType $VALUES[];

	public static GenCodeType[] values()
	{
		return (GenCodeType[])$VALUES.clone();
	}

	public static GenCodeType valueOf(String name)
	{
		return (GenCodeType)Enum.valueOf(com/ccnode/codegenerator/dialog/GenCodeType, name);
	}

	private GenCodeType(String s, int i)
	{
		super(s, i);
	}

	static 
	{
		INSERT = new GenCodeType("INSERT", 0);
		UPDATE = new GenCodeType("UPDATE", 1);
		$VALUES = (new GenCodeType[] {
			INSERT, UPDATE
		});
	}
}
