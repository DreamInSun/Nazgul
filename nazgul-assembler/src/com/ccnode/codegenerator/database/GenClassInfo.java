// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenClassInfo.java

package com.ccnode.codegenerator.database;

import java.util.List;
import java.util.Set;

public class GenClassInfo
{

	private String classPackageName;
	private String className;
	private Set importList;
	private List classFieldInfos;
	private String classFullPath;

	public GenClassInfo()
	{
	}

	public String getClassPackageName()
	{
		return classPackageName;
	}

	public String getClassFullPath()
	{
		return classFullPath;
	}

	public void setClassFullPath(String classFullPath)
	{
		this.classFullPath = classFullPath;
	}

	public void setClassPackageName(String classPackageName)
	{
		this.classPackageName = classPackageName;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public Set getImportList()
	{
		return importList;
	}

	public void setImportList(Set importList)
	{
		this.importList = importList;
	}

	public List getClassFieldInfos()
	{
		return classFieldInfos;
	}

	public void setClassFieldInfos(List classFieldInfos)
	{
		this.classFieldInfos = classFieldInfos;
	}
}
