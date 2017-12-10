// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MyCloner.java

package com.ccnode.codegenerator.util;

import com.rits.cloning.Cloner;

public class MyCloner
{
	private static class HelperHolder
	{

		private static final Cloner myCloner = new Cloner();



		private HelperHolder()
		{
		}
	}


	private MyCloner()
	{
	}

	public static Cloner getCloner()
	{
		return HelperHolder.myCloner;
	}
}
