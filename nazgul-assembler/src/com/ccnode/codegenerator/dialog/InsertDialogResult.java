// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   InsertDialogResult.java

package com.ccnode.codegenerator.dialog;

import com.ccnode.codegenerator.pojo.ClassInfo;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.ccnode.codegenerator.dialog:
//			GenCodeProp

public class InsertDialogResult
{

	private List propList;
	private GenCodeProp primaryProp;
	private Map fileProps;
	private List multipleColumnIndex;
	private List multipleColumnUnique;
	private String tableName;
	private ClassInfo srcClass;

	public InsertDialogResult()
	{
		multipleColumnIndex = Lists.newArrayList();
		multipleColumnUnique = Lists.newArrayList();
	}

	public ClassInfo getSrcClass()
	{
		return srcClass;
	}

	public void setSrcClass(ClassInfo srcClass)
	{
		this.srcClass = srcClass;
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public List getPropList()
	{
		return propList;
	}

	public void setPropList(List propList)
	{
		this.propList = propList;
	}

	public GenCodeProp getPrimaryProp()
	{
		return primaryProp;
	}

	public void setPrimaryProp(GenCodeProp primaryProp)
	{
		this.primaryProp = primaryProp;
	}

	public Map getFileProps()
	{
		return fileProps;
	}

	public void setFileProps(Map fileProps)
	{
		this.fileProps = fileProps;
	}

	public List getMultipleColumnIndex()
	{
		return multipleColumnIndex;
	}

	public void setMultipleColumnIndex(List multipleColumnIndex)
	{
		this.multipleColumnIndex = multipleColumnIndex;
	}

	public List getMultipleColumnUnique()
	{
		return multipleColumnUnique;
	}

	public void setMultipleColumnUnique(List multipleColumnUnique)
	{
		this.multipleColumnUnique = multipleColumnUnique;
	}
}
