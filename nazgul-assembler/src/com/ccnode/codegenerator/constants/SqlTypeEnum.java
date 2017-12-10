// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SqlTypeEnum.java

package com.ccnode.codegenerator.constants;


public final class SqlTypeEnum extends Enum
{

	public static final SqlTypeEnum SELECT;
	public static final SqlTypeEnum UPDATE;
	public static final SqlTypeEnum DELETE;
	private String value;
	private static final SqlTypeEnum $VALUES[];

	public static SqlTypeEnum[] values()
	{
		return (SqlTypeEnum[])$VALUES.clone();
	}

	public static SqlTypeEnum valueOf(String name)
	{
		return (SqlTypeEnum)Enum.valueOf(com/ccnode/codegenerator/constants/SqlTypeEnum, name);
	}

	private SqlTypeEnum(String s, int i, String value)
	{
		super(s, i);
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}

	static 
	{
		SELECT = new SqlTypeEnum("SELECT", 0, "select");
		UPDATE = new SqlTypeEnum("UPDATE", 1, "update");
		DELETE = new SqlTypeEnum("DELETE", 2, "delete");
		$VALUES = (new SqlTypeEnum[] {
			SELECT, UPDATE, DELETE
		});
	}
}
