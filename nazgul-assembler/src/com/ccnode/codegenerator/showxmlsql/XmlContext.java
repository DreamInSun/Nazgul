// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   XmlContext.java

package com.ccnode.codegenerator.showxmlsql;

import java.util.Map;

public class XmlContext
{

	private Map sqlIdMap;

	public XmlContext()
	{
	}

	public Map getSqlIdMap()
	{
		return sqlIdMap;
	}

	public void setSqlIdMap(Map sqlIdMap)
	{
		this.sqlIdMap = sqlIdMap;
	}
}
