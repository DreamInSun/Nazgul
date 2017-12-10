// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   InsertFileType.java

package com.ccnode.codegenerator.dialog;


public final class InsertFileType extends Enum
{

	public static final InsertFileType SQL;
	public static final InsertFileType DAO;
	public static final InsertFileType MAPPER_XML;
	public static final InsertFileType SERVICE;
	public static final InsertFileType SERVICE_INTERFACE;
	private static final InsertFileType $VALUES[];

	public static InsertFileType[] values()
	{
		return (InsertFileType[])$VALUES.clone();
	}

	public static InsertFileType valueOf(String name)
	{
		return (InsertFileType)Enum.valueOf(com/ccnode/codegenerator/dialog/InsertFileType, name);
	}

	private InsertFileType(String s, int i)
	{
		super(s, i);
	}

	static 
	{
		SQL = new InsertFileType("SQL", 0);
		DAO = new InsertFileType("DAO", 1);
		MAPPER_XML = new InsertFileType("MAPPER_XML", 2);
		SERVICE = new InsertFileType("SERVICE", 3);
		SERVICE_INTERFACE = new InsertFileType("SERVICE_INTERFACE", 4);
		$VALUES = (new InsertFileType[] {
			SQL, DAO, MAPPER_XML, SERVICE, SERVICE_INTERFACE
		});
	}
}
