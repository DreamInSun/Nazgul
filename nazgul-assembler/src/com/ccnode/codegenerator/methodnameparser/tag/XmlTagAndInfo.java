// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   XmlTagAndInfo.java

package com.ccnode.codegenerator.methodnameparser.tag;

import com.ccnode.codegenerator.methodnameparser.buidler.QueryInfo;
import com.intellij.psi.xml.XmlTag;

public class XmlTagAndInfo
{

	private XmlTag xmlTag;
	private QueryInfo info;

	public XmlTagAndInfo()
	{
	}

	public QueryInfo getInfo()
	{
		return info;
	}

	public void setInfo(QueryInfo info)
	{
		this.info = info;
	}

	public XmlTag getXmlTag()
	{
		return xmlTag;
	}

	public void setXmlTag(XmlTag xmlTag)
	{
		this.xmlTag = xmlTag;
	}
}
