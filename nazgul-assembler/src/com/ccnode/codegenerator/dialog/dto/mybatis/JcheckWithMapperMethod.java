// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   JcheckWithMapperMethod.java

package com.ccnode.codegenerator.dialog.dto.mybatis;

import javax.swing.JCheckBox;

// Referenced classes of package com.ccnode.codegenerator.dialog.dto.mybatis:
//			MapperMethod, ClassMapperMethod

public class JcheckWithMapperMethod
{

	private JCheckBox jCheckBox;
	private MapperMethod mapperMethod;
	private ClassMapperMethod classMapperMethod;

	public JcheckWithMapperMethod()
	{
	}

	public ClassMapperMethod getClassMapperMethod()
	{
		return classMapperMethod;
	}

	public void setClassMapperMethod(ClassMapperMethod classMapperMethod)
	{
		this.classMapperMethod = classMapperMethod;
	}

	public JCheckBox getjCheckBox()
	{
		return jCheckBox;
	}

	public void setjCheckBox(JCheckBox jCheckBox)
	{
		this.jCheckBox = jCheckBox;
	}

	public MapperMethod getMapperMethod()
	{
		return mapperMethod;
	}

	public void setMapperMethod(MapperMethod mapperMethod)
	{
		this.mapperMethod = mapperMethod;
	}
}
