// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenCodeUtil.java

package com.ccnode.codegenerator.util;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class GenCodeUtil
{

	public static final String ONE_RETRACT = "\t";
	public static final String TWO_RETRACT = "\t\t";
	public static final String THREE_RETRACT = "\t\t\t";
	public static final String FOUR_RETRACT = "\t\t\t\t";
	public static String MYSQL_TYPE = "";
	public static String PACKAGE_LINE = "";

	public GenCodeUtil()
	{
	}

	public static List grapOld(List oldList, String startKeyWord, String endKeyWord)
	{
		if (oldList == null)
			$$$reportNull$$$0(0);
		if (startKeyWord == null)
			$$$reportNull$$$0(1);
		if (endKeyWord == null)
			$$$reportNull$$$0(2);
		int startIndex = -1;
		int endIndex = -1;
		int i = 0;
		for (Iterator iterator = oldList.iterator(); iterator.hasNext();)
		{
			String line = (String)iterator.next();
			if (sqlContain(line, startKeyWord))
				startIndex = i;
			if (sqlContain(line, endKeyWord))
				endIndex = i;
			i++;
		}

		if (startIndex == -1 || endIndex == -1)
			return Lists.newArrayList();
		else
			return oldList.subList(startIndex, endIndex + 1);
	}

	public static String firstCharUpper(String prop)
	{
		return (new StringBuilder()).append(prop.substring(0, 1).toUpperCase()).append(prop.substring(1)).toString();
	}

	public static String pathToPackage(String path)
	{
		path = path.replace("/", ".");
		path = path.replace("\\", ".");
		if (path.startsWith("src.main.java."))
			path = path.replace("src.main.java.", "");
		if (path.startsWith("src.main."))
			path = path.replace("src.main.", "");
		if (path.startsWith("src."))
			path = path.replace("src.", "");
		if (path.startsWith("."))
			path = path.substring(1, path.length());
		return path;
	}

	public static boolean sqlContain(String sequence, String word)
	{
		if (word == null)
			$$$reportNull$$$0(3);
		if (StringUtils.isBlank(sequence))
			return false;
		else
			return StringUtils.containsIgnoreCase(StringUtils.deleteWhitespace(sequence), StringUtils.deleteWhitespace(word));
	}

	public static String getUnderScoreWithComma(String value)
	{
		if (value == null)
		{
			return "";
		} else
		{
			String to = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, value);
			return (new StringBuilder()).append("`").append(to).append("`").toString();
		}
	}

	public static String getUnderScore(String value)
	{
		if (value == null)
			return "";
		else
			return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, value);
	}

	public static String getLowerCamel(String value)
	{
		return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, value);
	}

	public static String getUpperStart(String value)
	{
		return (new StringBuilder()).append(value.substring(0, 1).toUpperCase()).append(value.substring(1)).toString();
	}

	public static String getCamelFromUnderScore(String value)
	{
		return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, value);
	}

	public static String getUpperCamelFromUnderScore(String value)
	{
		return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, value);
	}

	public static void main(String args1[])
	{
	}

	private static void $$$reportNull$$$0(int i)
	{
		"Argument for @NotNull parameter '%s' of %s.%s must not be null";
		new Object[3];
		i;
		JVM INSTR tableswitch 0 3: default 36
	//	               0 36
	//	               1 44
	//	               2 52
	//	               3 60;
		   goto _L1 _L1 _L2 _L3 _L4
_L1:
		JVM INSTR dup ;
		0;
		"oldList";
		JVM INSTR aastore ;
		  goto _L5
_L2:
		JVM INSTR dup ;
		0;
		"startKeyWord";
		JVM INSTR aastore ;
		  goto _L5
_L3:
		JVM INSTR dup ;
		0;
		"endKeyWord";
		JVM INSTR aastore ;
		  goto _L5
_L4:
		JVM INSTR dup ;
		0;
		"word";
		JVM INSTR aastore ;
_L5:
		JVM INSTR dup ;
		1;
		"com/ccnode/codegenerator/util/GenCodeUtil";
		JVM INSTR aastore ;
		i;
		JVM INSTR tableswitch 0 3: default 104
	//	               0 104
	//	               1 104
	//	               2 104
	//	               3 112;
		   goto _L6 _L6 _L6 _L6 _L7
_L6:
		JVM INSTR dup ;
		2;
		"grapOld";
		JVM INSTR aastore ;
		  goto _L8
_L7:
		JVM INSTR dup ;
		2;
		"sqlContain";
		JVM INSTR aastore ;
_L8:
		String.format();
		JVM INSTR new #204 <Class IllegalArgumentException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalArgumentException();
		throw ;
	}

}
