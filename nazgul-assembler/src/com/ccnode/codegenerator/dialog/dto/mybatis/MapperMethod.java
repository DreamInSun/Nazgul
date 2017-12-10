// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MapperMethod.java

package com.ccnode.codegenerator.dialog.dto.mybatis;

import com.intellij.psi.xml.XmlTag;

// Referenced classes of package com.ccnode.codegenerator.dialog.dto.mybatis:
//			MapperMethodEnum

public class MapperMethod
{

	private MapperMethodEnum type;
	private XmlTag xmlTag;

	public MapperMethod()
	{
	}

	public MapperMethodEnum getType()
	{
		return type;
	}

	public void setType(MapperMethodEnum type)
	{
		this.type = type;
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
