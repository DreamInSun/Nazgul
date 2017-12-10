// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MapperDto.java

package com.ccnode.codegenerator.dialog.dto;

import java.util.List;
import java.util.Map;

public class MapperDto
{

	private List resultMapList;
	private List sqls;
	private Map mapperMethodMap;

	public MapperDto()
	{
	}

	public List getResultMapList()
	{
		return resultMapList;
	}

	public void setResultMapList(List resultMapList)
	{
		this.resultMapList = resultMapList;
	}

	public List getSqls()
	{
		return sqls;
	}

	public void setSqls(List sqls)
	{
		this.sqls = sqls;
	}

	public Map getMapperMethodMap()
	{
		return mapperMethodMap;
	}

	public void setMapperMethodMap(Map mapperMethodMap)
	{
		this.mapperMethodMap = mapperMethodMap;
	}
}
