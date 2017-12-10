// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   QueryParseDto.java

package com.ccnode.codegenerator.methodnameparser;

import com.ccnode.codegenerator.methodnameparser.buidler.QueryInfo;
import java.util.List;

public class QueryParseDto
{

	private QueryInfo queryInfo;
	private Boolean hasMatched;
	private Boolean displayErrorMsg;
	private List errorMsg;

	public QueryParseDto()
	{
		hasMatched = Boolean.valueOf(false);
		displayErrorMsg = Boolean.valueOf(true);
	}

	public Boolean getDisplayErrorMsg()
	{
		return displayErrorMsg;
	}

	public void setDisplayErrorMsg(Boolean displayErrorMsg)
	{
		this.displayErrorMsg = displayErrorMsg;
	}

	public QueryInfo getQueryInfo()
	{
		return queryInfo;
	}

	public void setQueryInfo(QueryInfo queryInfo)
	{
		this.queryInfo = queryInfo;
	}

	public Boolean getHasMatched()
	{
		return hasMatched;
	}

	public void setHasMatched(Boolean hasMatched)
	{
		this.hasMatched = hasMatched;
	}

	public List getErrorMsg()
	{
		return errorMsg;
	}

	public void setErrorMsg(List errorMsg)
	{
		this.errorMsg = errorMsg;
	}
}
