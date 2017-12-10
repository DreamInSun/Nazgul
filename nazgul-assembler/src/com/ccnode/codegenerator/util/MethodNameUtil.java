// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MethodNameUtil.java

package com.ccnode.codegenerator.util;

import com.ccnode.codegenerator.methodnameparser.buidler.ParsedMethodEnum;

// Referenced classes of package com.ccnode.codegenerator.util:
//			MethodNameCheckReuslt

public class MethodNameUtil
{

	public MethodNameUtil()
	{
	}

	public static MethodNameCheckReuslt checkValidTextStarter(String text)
	{
		MethodNameCheckReuslt result = new MethodNameCheckReuslt();
		if (text.startsWith("find"))
		{
			result.setValid(true);
			result.setParsedMethodEnum(ParsedMethodEnum.FIND);
			return result;
		}
		if (text.startsWith("update"))
		{
			result.setValid(true);
			result.setParsedMethodEnum(ParsedMethodEnum.UPDATE);
			return result;
		}
		if (text.startsWith("delete"))
		{
			result.setValid(true);
			result.setParsedMethodEnum(ParsedMethodEnum.DELETE);
			return result;
		}
		if (text.startsWith("count"))
		{
			result.setValid(true);
			result.setParsedMethodEnum(ParsedMethodEnum.COUNT);
			return result;
		} else
		{
			return result;
		}
	}
}
