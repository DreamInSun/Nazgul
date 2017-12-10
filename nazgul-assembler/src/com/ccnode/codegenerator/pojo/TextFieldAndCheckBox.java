// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TextFieldAndCheckBox.java

package com.ccnode.codegenerator.pojo;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class TextFieldAndCheckBox
{

	private JTextField jTextField;
	private JCheckBox jCheckBox;

	public TextFieldAndCheckBox()
	{
	}

	public JTextField getJTextField()
	{
		return jTextField;
	}

	public JCheckBox getJCheckBox()
	{
		return jCheckBox;
	}

	public void setJTextField(JTextField jTextField)
	{
		this.jTextField = jTextField;
	}

	public void setJCheckBox(JCheckBox jCheckBox)
	{
		this.jCheckBox = jCheckBox;
	}
}
