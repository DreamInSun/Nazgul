// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DbUtils.java

package com.ccnode.codegenerator.database;

import com.ccnode.codegenerator.methodnameparser.parsedresult.find.FetchProp;
import java.util.Map;

public class DbUtils
{

	public DbUtils()
	{
	}

	public static String buildSelectFunctionVal(FetchProp prop)
	{
		return (new StringBuilder()).append(prop.getFetchFunction()).append(prop.getFetchProp()).toString();
	}

	public static String getReturnClassFromFunction(Map fieldMap, String fetchFunction, String fetchProp)
	{
		String s = fetchFunction;
		byte byte0 = -1;
		switch (s.hashCode())
		{
		case 107876: 
			if (s.equals("max"))
				byte0 = 0;
			break;

		case 108114: 
			if (s.equals("min"))
				byte0 = 1;
			break;

		case 96978: 
			if (s.equals("avg"))
				byte0 = 2;
			break;

		case 114251: 
			if (s.equals("sum"))
				byte0 = 3;
			break;
		}
		switch (byte0)
		{
		case 0: // '\0'
		case 1: // '\001'
			return (String)fieldMap.get(fetchProp);

		case 2: // '\002'
		case 3: // '\003'
			return "java.math.BigDecimal";
		}
		return null;
	}
}
