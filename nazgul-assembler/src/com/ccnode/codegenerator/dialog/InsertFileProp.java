// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   InsertFileProp.java

package com.ccnode.codegenerator.dialog;


public class InsertFileProp
{

	private String name;
	private String folderPath;
	private String packageName;
	private String fullPath;
	private String qutifiedName;

	public InsertFileProp()
	{
	}

	public String getQutifiedName()
	{
		return qutifiedName;
	}

	public void setQutifiedName(String qutifiedName)
	{
		this.qutifiedName = qutifiedName;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getFolderPath()
	{
		return folderPath;
	}

	public void setFolderPath(String folderPath)
	{
		this.folderPath = folderPath;
	}

	public String getPackageName()
	{
		return packageName;
	}

	public void setPackageName(String packageName)
	{
		this.packageName = packageName;
	}

	public String getFullPath()
	{
		return fullPath;
	}

	public void setFullPath(String fullPath)
	{
		this.fullPath = fullPath;
	}
}
