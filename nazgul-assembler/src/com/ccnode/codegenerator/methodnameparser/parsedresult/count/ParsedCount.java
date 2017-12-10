// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ParsedCount.java

package com.ccnode.codegenerator.methodnameparser.parsedresult.count;

import com.ccnode.codegenerator.methodnameparser.parsedresult.base.ParsedBase;
import com.rits.cloning.Cloner;
import java.util.ArrayList;
import java.util.List;

public class ParsedCount extends ParsedBase
{

	private boolean distinct;
	private List fetchProps;

	public ParsedCount()
	{
		distinct = false;
	}

	public void addFetchProps(String props)
	{
		if (fetchProps == null)
			fetchProps = new ArrayList();
		fetchProps.add(props);
	}

	public List getFetchProps()
	{
		return fetchProps;
	}

	public void setFetchProps(List fetchProps)
	{
		this.fetchProps = fetchProps;
	}

	public ParsedCount clone()
	{
		return (ParsedCount)Cloner.standard().deepClone(this);
	}

	public boolean isDistinct()
	{
		return distinct;
	}

	public void setDistinct(boolean distinct)
	{
		this.distinct = distinct;
	}

	public volatile Object clone()
		throws CloneNotSupportedException
	{
		return clone();
	}
}
