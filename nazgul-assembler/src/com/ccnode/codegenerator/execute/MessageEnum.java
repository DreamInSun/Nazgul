// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MessageEnum.java

package com.ccnode.codegenerator.execute;


public final class MessageEnum extends Enum
{

	public static final MessageEnum ERROR;
	public static final MessageEnum INFO;
	private static final MessageEnum $VALUES[];

	public static MessageEnum[] values()
	{
		return (MessageEnum[])$VALUES.clone();
	}

	public static MessageEnum valueOf(String name)
	{
		return (MessageEnum)Enum.valueOf(com/ccnode/codegenerator/execute/MessageEnum, name);
	}

	private MessageEnum(String s, int i)
	{
		super(s, i);
	}

	static 
	{
		ERROR = new MessageEnum("ERROR", 0);
		INFO = new MessageEnum("INFO", 1);
		$VALUES = (new MessageEnum[] {
			ERROR, INFO
		});
	}
}
