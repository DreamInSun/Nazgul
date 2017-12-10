// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RequestType.java

package com.ccnode.codegenerator.enums;


public final class RequestType extends Enum
{

	public static final RequestType REGISTER;
	public static final RequestType GEN_CODE;
	public static final RequestType NONE;
	private Integer code;
	private String desc;
	private static final RequestType $VALUES[];

	public static RequestType[] values()
	{
		return (RequestType[])$VALUES.clone();
	}

	public static RequestType valueOf(String name)
	{
		return (RequestType)Enum.valueOf(RequestType.class, name);
	}

	private RequestType(String s, int i, Integer code, String desc)
	{
		super(s, i);
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode()
	{
		return code;
	}

	public String getDesc()
	{
		return desc;
	}

	public static RequestType fromName(String name)
	{
		RequestType arequesttype[] = values();
		int i = arequesttype.length;
		for (int j = 0; j < i; j++)
		{
			RequestType e = arequesttype[j];
			if (e.name().equals(name))
				return e;
		}

		return NONE;
	}

	public static RequestType fromCode(Integer code)
	{
		RequestType arequesttype[] = values();
		int i = arequesttype.length;
		for (int j = 0; j < i; j++)
		{
			RequestType e = arequesttype[j];
			if (e.getCode().equals(code))
				return e;
		}

		return NONE;
	}

	public static String codeToName(Integer code)
	{
		RequestType o = fromCode(code);
		return o.name();
	}

	public static Integer nameToCode(String name)
	{
		RequestType o = fromName(name);
		return o.getCode();
	}

	public Boolean equalWithName(String name)
	{
		return Boolean.valueOf(this == fromName(name));
	}

	public Boolean equalWithCode(Integer code)
	{
		return Boolean.valueOf(this == fromCode(code));
	}

	static 
	{
		REGISTER = new RequestType("REGISTER", 0, Integer.valueOf(0), "ע��");
		GEN_CODE = new RequestType("GEN_CODE", 1, Integer.valueOf(1), "");
		NONE = new RequestType("NONE", 2, Integer.valueOf(-1), "none");
		$VALUES = (new RequestType[] {
			REGISTER, GEN_CODE, NONE
		});
	}
}
