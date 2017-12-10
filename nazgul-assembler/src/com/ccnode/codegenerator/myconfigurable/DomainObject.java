// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DomainObject.java

package com.ccnode.codegenerator.myconfigurable;

import org.apache.commons.lang.builder.*;

public abstract class DomainObject
{

	public DomainObject()
	{
	}

	public boolean equals(Object obj)
	{
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}

	public String toString()
	{
		return ReflectionToStringBuilder.toString(this);
	}

	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
