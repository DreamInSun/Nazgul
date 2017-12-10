// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   QueryInfo.java

package com.ccnode.codegenerator.methodnameparser.buidler;

import com.ccnode.codegenerator.constants.SqlTypeEnum;
import com.ccnode.codegenerator.methodnameparser.parsedresult.base.ParsedBase;
import java.util.*;

// Referenced classes of package com.ccnode.codegenerator.methodnameparser.buidler:
//			ParamInfo

public class QueryInfo
{

	private String returnClass;
	private List paramInfos;
	private String methodReturnType;
	private String sql;
	private String id;
	private String returnMap;
	private SqlTypeEnum type;
	private Set importList;
	private ParsedBase parseQuery;

	public QueryInfo()
	{
	}

	public ParsedBase getParseQuery()
	{
		return parseQuery;
	}

	public void setParseQuery(ParsedBase parseQuery)
	{
		this.parseQuery = parseQuery;
	}

	public Set getImportList()
	{
		return importList;
	}

	public void setImportList(Set importList)
	{
		this.importList = importList;
	}

	public SqlTypeEnum getType()
	{
		return type;
	}

	public void setType(SqlTypeEnum type)
	{
		this.type = type;
	}

	public String getMethodReturnType()
	{
		return methodReturnType;
	}

	public void setMethodReturnType(String methodReturnType)
	{
		this.methodReturnType = methodReturnType;
	}

	public String getReturnClass()
	{
		return returnClass;
	}

	public void setReturnClass(String returnClass)
	{
		this.returnClass = returnClass;
	}

	public List getParamInfos()
	{
		return paramInfos;
	}

	public void setParamInfos(List paramInfos)
	{
		this.paramInfos = paramInfos;
	}

	public String getSql()
	{
		return sql;
	}

	public void setSql(String sql)
	{
		this.sql = sql;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getReturnMap()
	{
		return returnMap;
	}

	public void setReturnMap(String returnMap)
	{
		this.returnMap = returnMap;
	}

	public void addParams(ParamInfo info)
	{
		if (paramInfos == null)
			paramInfos = new ArrayList();
		paramInfos.add(info);
	}
}
