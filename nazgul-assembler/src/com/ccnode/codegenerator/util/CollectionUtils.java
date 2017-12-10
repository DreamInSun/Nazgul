// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CollectionUtils.java

package com.ccnode.codegenerator.util;

import java.util.List;

public class CollectionUtils
{

	public CollectionUtils()
	{
	}

	public static boolean isEmpty(List s)
	{
		return s == null || s.size() == 0;
	}
}
