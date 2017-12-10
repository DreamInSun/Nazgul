// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ParsedFind.java

package com.ccnode.codegenerator.methodnameparser.parsedresult.find;

import com.ccnode.codegenerator.methodnameparser.parsedresult.base.ParsedBase;
import com.rits.cloning.Cloner;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.ccnode.codegenerator.methodnameparser.parsedresult.find:
//			FetchProp, OrderByRule

public class ParsedFind extends ParsedBase
{

	private List fetchProps;
	private Boolean distinct;
	private Boolean withPage;
	private Boolean returnList;
	private List groupByProps;
	private Integer limit;
	List orderByProps;

	public ParsedFind()
	{
		distinct = Boolean.valueOf(false);
		withPage = Boolean.valueOf(false);
		returnList = Boolean.valueOf(true);
		limit = Integer.valueOf(-1);
	}

	public Boolean getWithPage()
	{
		return withPage;
	}

	public void setWithPage(Boolean withPage)
	{
		this.withPage = withPage;
	}

	public ParsedFind clone()
	{
		return (ParsedFind)Cloner.standard().deepClone(this);
	}

	public void addFetchProps(String props)
	{
		if (fetchProps == null)
			fetchProps = new ArrayList();
		FetchProp e = new FetchProp();
		e.setFetchProp(props);
		fetchProps.add(e);
	}

	public void addFunction(String function)
	{
		if (fetchProps == null)
			fetchProps = new ArrayList();
		FetchProp e = new FetchProp();
		e.setFetchFunction(function);
		fetchProps.add(e);
	}

	public void addFunctionProp(String functionProp)
	{
		if (fetchProps == null)
		{
			throw new RuntimeException("add function prop, the fetchProp shall not be empty");
		} else
		{
			FetchProp lastFecthProp = (FetchProp)fetchProps.get(fetchProps.size() - 1);
			lastFecthProp.setFetchProp(functionProp);
			return;
		}
	}

	public void addOrderByProp(String prop)
	{
		if (orderByProps == null)
			orderByProps = new ArrayList();
		OrderByRule rule = new OrderByRule();
		rule.setProp(prop);
		orderByProps.add(rule);
	}

	public void addOrderByPropOrder(String order)
	{
		OrderByRule rule = (OrderByRule)orderByProps.get(orderByProps.size() - 1);
		rule.setOrder(order);
	}

	public Boolean getDistinct()
	{
		return distinct;
	}

	public void setDistinct(Boolean distinct)
	{
		this.distinct = distinct;
	}

	public Integer getLimit()
	{
		return limit;
	}

	public void setLimit(Integer limit)
	{
		this.limit = limit;
	}

	public List getFetchProps()
	{
		return fetchProps;
	}

	public List getOrderByProps()
	{
		return orderByProps;
	}

	public List getGroupByProps()
	{
		return groupByProps;
	}

	public Boolean getReturnList()
	{
		return returnList;
	}

	public void setReturnList(Boolean returnList)
	{
		this.returnList = returnList;
	}

	public volatile Object clone()
		throws CloneNotSupportedException
	{
		return clone();
	}
}
