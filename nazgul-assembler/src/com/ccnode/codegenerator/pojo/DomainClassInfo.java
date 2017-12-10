// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DomainClassInfo.java

package com.ccnode.codegenerator.pojo;

import com.intellij.psi.PsiClass;

// Referenced classes of package com.ccnode.codegenerator.pojo:
//			DomainClassSourceType

public class DomainClassInfo
{

	private PsiClass domainClass;
	private DomainClassSourceType domainClassSourceType;

	public DomainClassInfo()
	{
	}

	public PsiClass getDomainClass()
	{
		return domainClass;
	}

	public void setDomainClass(PsiClass domainClass)
	{
		this.domainClass = domainClass;
	}

	public DomainClassSourceType getDomainClassSourceType()
	{
		return domainClassSourceType;
	}

	public void setDomainClassSourceType(DomainClassSourceType domainClassSourceType)
	{
		this.domainClassSourceType = domainClassSourceType;
	}
}
