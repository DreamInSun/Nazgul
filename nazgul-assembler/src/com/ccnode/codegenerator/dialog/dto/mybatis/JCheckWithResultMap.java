// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   JCheckWithResultMap.java

package com.ccnode.codegenerator.dialog.dto.mybatis;

import javax.swing.JCheckBox;

// Referenced classes of package com.ccnode.codegenerator.dialog.dto.mybatis:
//			ResultMap

public class JCheckWithResultMap
{

	private JCheckBox jCheckBox;
	private ResultMap resultMap;

	public JCheckWithResultMap()
	{
	}

	public JCheckBox getjCheckBox()
	{
		return jCheckBox;
	}

	public void setjCheckBox(JCheckBox jCheckBox)
	{
		this.jCheckBox = jCheckBox;
	}

	public ResultMap getResultMap()
	{
		return resultMap;
	}

	public void setResultMap(ResultMap resultMap)
	{
		this.resultMap = resultMap;
	}
}
