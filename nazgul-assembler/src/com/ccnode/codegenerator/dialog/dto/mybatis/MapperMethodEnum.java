// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MapperMethodEnum.java

package com.ccnode.codegenerator.dialog.dto.mybatis;


public final class MapperMethodEnum extends Enum
{

	public static final MapperMethodEnum INSERT;
	public static final MapperMethodEnum UPDATE;
	public static final MapperMethodEnum SELECT;
	public static final MapperMethodEnum DELETE;
	private static final MapperMethodEnum $VALUES[];

	public static MapperMethodEnum[] values()
	{
		return (MapperMethodEnum[])$VALUES.clone();
	}

	public static MapperMethodEnum valueOf(String name)
	{
		return (MapperMethodEnum)Enum.valueOf(com/ccnode/codegenerator/dialog/dto/mybatis/MapperMethodEnum, name);
	}

	private MapperMethodEnum(String s, int i)
	{
		super(s, i);
	}

	static 
	{
		INSERT = new MapperMethodEnum("INSERT", 0);
		UPDATE = new MapperMethodEnum("UPDATE", 1);
		SELECT = new MapperMethodEnum("SELECT", 2);
		DELETE = new MapperMethodEnum("DELETE", 3);
		$VALUES = (new MapperMethodEnum[] {
			INSERT, UPDATE, SELECT, DELETE
		});
	}
}
