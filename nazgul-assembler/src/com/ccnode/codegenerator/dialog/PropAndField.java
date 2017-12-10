// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PropAndField.java

package com.ccnode.codegenerator.dialog;

import javax.swing.JTextField;

public class PropAndField
{

	private String prop;
	private JTextField field;

	public PropAndField()
	{
	}

	public String getProp()
	{
		return prop;
	}

	public void setProp(String prop)
	{
		this.prop = prop;
	}

	public JTextField getField()
	{
		return field;
	}

	public void setField(JTextField field)
	{
		this.field = field;
	}
}
