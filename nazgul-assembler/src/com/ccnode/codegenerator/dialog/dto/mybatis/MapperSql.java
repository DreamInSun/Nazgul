// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MapperSql.java

package com.ccnode.codegenerator.dialog.dto.mybatis;

import com.intellij.psi.xml.XmlTag;

public class MapperSql
{

	private String id;
	private XmlTag tag;

	public MapperSql()
	{
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public XmlTag getTag()
	{
		return tag;
	}

	public void setTag(XmlTag tag)
	{
		this.tag = tag;
	}
}
