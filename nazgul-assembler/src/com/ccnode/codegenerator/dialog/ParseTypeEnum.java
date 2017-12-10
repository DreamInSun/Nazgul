// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ParseTypeEnum.java

package com.ccnode.codegenerator.dialog;


public final class ParseTypeEnum extends Enum
{

	public static final ParseTypeEnum PROPERTY;
	public static final ParseTypeEnum DISTINCT;
	public static final ParseTypeEnum ORDERBY;
	public static final ParseTypeEnum BY;
	public static final ParseTypeEnum LINKOP;
	public static final ParseTypeEnum AND;
	public static final ParseTypeEnum FIRST;
	public static final ParseTypeEnum COMPARATOR;
	public static final ParseTypeEnum FUNCTION;
	public static final ParseTypeEnum ORDER;
	public static final ParseTypeEnum ONE;
	public static final ParseTypeEnum PAGE_QUERY;
	public static final ParseTypeEnum INCORDEC;
	private static final ParseTypeEnum $VALUES[];

	public static ParseTypeEnum[] values()
	{
		return (ParseTypeEnum[])$VALUES.clone();
	}

	public static ParseTypeEnum valueOf(String name)
	{
		return (ParseTypeEnum)Enum.valueOf(com/ccnode/codegenerator/dialog/ParseTypeEnum, name);
	}

	private ParseTypeEnum(String s, int i)
	{
		super(s, i);
	}

	static 
	{
		PROPERTY = new ParseTypeEnum("PROPERTY", 0);
		DISTINCT = new ParseTypeEnum("DISTINCT", 1);
		ORDERBY = new ParseTypeEnum("ORDERBY", 2);
		BY = new ParseTypeEnum("BY", 3);
		LINKOP = new ParseTypeEnum("LINKOP", 4);
		AND = new ParseTypeEnum("AND", 5);
		FIRST = new ParseTypeEnum("FIRST", 6);
		COMPARATOR = new ParseTypeEnum("COMPARATOR", 7);
		FUNCTION = new ParseTypeEnum("FUNCTION", 8);
		ORDER = new ParseTypeEnum("ORDER", 9);
		ONE = new ParseTypeEnum("ONE", 10);
		PAGE_QUERY = new ParseTypeEnum("PAGE_QUERY", 11);
		INCORDEC = new ParseTypeEnum("INCORDEC", 12);
		$VALUES = (new ParseTypeEnum[] {
			PROPERTY, DISTINCT, ORDERBY, BY, LINKOP, AND, FIRST, COMPARATOR, FUNCTION, ORDER, 
			ONE, PAGE_QUERY, INCORDEC
		});
	}
}
