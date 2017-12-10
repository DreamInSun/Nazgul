// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ParsedBase.java

package com.ccnode.codegenerator.methodnameparser.parsedresult.base;

import com.ccnode.codegenerator.dialog.ParseTypeEnum;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.ccnode.codegenerator.methodnameparser.parsedresult.base:
//			QueryRule

public class ParsedBase
{

	protected List queryRules;
	protected String parsedResult;

	public ParsedBase()
	{
	}

	public String getParsedResult()
	{
		return parsedResult;
	}

	public void setParsedResult(String parsedResult)
	{
		this.parsedResult = parsedResult;
	}

	public void addParsePart(ParseTypeEnum parseTypeEnum, String value)
	{
		if (parsedResult == null)
			parsedResult = (new StringBuilder()).append("| parsedType:").append(parseTypeEnum.name()).append(" parse value:").append(value).toString();
		else
			parsedResult = (new StringBuilder()).append(parsedResult).append("| parsedType:").append(parseTypeEnum.name()).append(" parse value:").append(value).toString();
	}

	public void addQueryProp(String queryProp)
	{
		if (queryRules == null)
			queryRules = new ArrayList();
		QueryRule rule = new QueryRule();
		rule.setProp(queryProp);
		queryRules.add(rule);
	}

	public void addQueryOperator(String operator)
	{
		QueryRule rule = (QueryRule)queryRules.get(queryRules.size() - 1);
		rule.setOperator(operator);
	}

	public void addConnector(String connector)
	{
		QueryRule rule = (QueryRule)queryRules.get(queryRules.size() - 1);
		rule.setConnector(connector);
	}

	public List getQueryRules()
	{
		return queryRules;
	}
}
