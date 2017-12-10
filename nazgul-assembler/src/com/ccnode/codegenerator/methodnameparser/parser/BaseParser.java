// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseParser.java

package com.ccnode.codegenerator.methodnameparser.parser;

import com.ccnode.codegenerator.methodnameparser.KeyWordConstants;
import java.util.List;

public class BaseParser
{

	protected String methodName;
	protected String props[];
	protected String lowerProps[];
	protected static String linkOp[] = {
		"and", "or"
	};
	protected static String compareOp[] = {
		"between", "greaterthan", "greaterthanorequalto", "lessthanorequalto", "lessthan", "betweenorequalto", "isnotnull", "isnull", "notnull", "notlike", 
		"like", "notin", "not", "in", "startingwith", "endingwith", "before", "after", "containing"
	};
	protected static String functionOp[] = {
		"max", "min", "avg", "sum"
	};
	protected static String order[] = {
		"asc", "desc"
	};
	protected static String updatePrefixs[] = {
		"inc", "dec"
	};

	public BaseParser(String methodName, List props)
	{
		this.methodName = methodName;
		this.props = new String[props.size()];
		lowerProps = new String[props.size()];
		for (int i = 0; i < props.size(); i++)
		{
			this.props[i] = (String)props.get(i);
			lowerProps[i] = ((String)props.get(i)).toLowerCase();
		}

	}

}
