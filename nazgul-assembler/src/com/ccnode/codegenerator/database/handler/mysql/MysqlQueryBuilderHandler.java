// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MysqlQueryBuilderHandler.java

package com.ccnode.codegenerator.database.handler.mysql;

import com.ccnode.codegenerator.database.DbUtils;
import com.ccnode.codegenerator.database.handler.BaseQueryBuilder;
import com.ccnode.codegenerator.database.handler.QueryBuilderHandler;
import com.ccnode.codegenerator.methodnameparser.buidler.MethodNameParsedResult;
import com.ccnode.codegenerator.methodnameparser.buidler.QueryInfo;
import com.ccnode.codegenerator.methodnameparser.parsedresult.find.*;
import com.ccnode.codegenerator.pojo.FieldToColumnRelation;
import com.ccnode.codegenerator.util.GenCodeUtil;
import java.util.Iterator;
import java.util.List;

public class MysqlQueryBuilderHandler
	implements QueryBuilderHandler
{

	public MysqlQueryBuilderHandler()
	{
	}

	public void handleFindBeforeFromTable(QueryInfo info, MethodNameParsedResult parsedResult, boolean queryAllTable)
	{
		ParsedFind find = parsedResult.getParsedFind();
		FieldToColumnRelation relation = parsedResult.getRelation();
		StringBuilder builder = new StringBuilder();
		if (queryAllTable)
		{
			builder.append((new StringBuilder()).append("\n\tselect <include refid=\"").append(parsedResult.getAllColumnName()).append("\"/>").toString());
		} else
		{
			builder.append("\n\tselect");
			if (find.getDistinct().booleanValue())
			{
				builder.append(" distinct(");
				FetchProp prop;
				for (Iterator iterator = find.getFetchProps().iterator(); iterator.hasNext(); builder.append((new StringBuilder()).append(relation.getPropFormatColumn(prop.getFetchProp())).append(",").toString()))
					prop = (FetchProp)iterator.next();

				builder.deleteCharAt(builder.length() - 1);
				builder.append(")");
			} else
			{
				for (Iterator iterator1 = find.getFetchProps().iterator(); iterator1.hasNext();)
				{
					FetchProp prop = (FetchProp)iterator1.next();
					if (prop.getFetchFunction() == null)
						builder.append((new StringBuilder()).append(" ").append(relation.getPropFormatColumn(prop.getFetchProp())).append(",").toString());
					else
						handleWithFunction(relation, builder, prop);
				}

				builder.deleteCharAt(builder.length() - 1);
			}
		}
		info.setSql(builder.toString());
	}

	public static void handleWithFunction(FieldToColumnRelation relation, StringBuilder builder, FetchProp prop)
	{
		String returnVal = DbUtils.buildSelectFunctionVal(prop);
		String s = prop.getFetchFunction();
		byte byte0 = -1;
		switch (s.hashCode())
		{
		case 107876: 
			if (s.equals("max"))
				byte0 = 0;
			break;

		case 108114: 
			if (s.equals("min"))
				byte0 = 1;
			break;

		case 96978: 
			if (s.equals("avg"))
				byte0 = 2;
			break;

		case 114251: 
			if (s.equals("sum"))
				byte0 = 3;
			break;
		}
		switch (byte0)
		{
		case 0: // '\0'
			builder.append(String.format(" max(%s) as %s,", new Object[] {
				relation.getPropFormatColumn(prop.getFetchProp()), returnVal
			}));
			break;

		case 1: // '\001'
			builder.append(String.format(" min(%s) as %s,", new Object[] {
				relation.getPropFormatColumn(prop.getFetchProp()), returnVal
			}));
			break;

		case 2: // '\002'
			builder.append(String.format(" avg(%s) as %s,", new Object[] {
				relation.getPropFormatColumn(prop.getFetchProp()), returnVal
			}));
			break;

		case 3: // '\003'
			builder.append(String.format(" sum(%s) as %s,", new Object[] {
				relation.getPropFormatColumn(prop.getFetchProp()), returnVal
			}));
			break;
		}
	}

	public void handleFindAfterFromTable(QueryInfo info, MethodNameParsedResult parsedResult)
	{
		ParsedFind find = parsedResult.getParsedFind();
		if (find.getQueryRules() != null)
			(new BaseQueryBuilder(this)).buildQuerySqlAndParam(find.getQueryRules(), info, parsedResult.getFieldMap(), parsedResult.getRelation());
		if (find.getOrderByProps() != null)
		{
			info.setSql((new StringBuilder()).append(info.getSql()).append(" order by").toString());
			OrderByRule rule;
			for (Iterator iterator = find.getOrderByProps().iterator(); iterator.hasNext(); info.setSql((new StringBuilder()).append(info.getSql()).append(" ").append(parsedResult.getRelation().getPropFormatColumn(rule.getProp())).append(" ").append(rule.getOrder()).toString()))
				rule = (OrderByRule)iterator.next();

		}
		if (find.getLimit().intValue() > 0)
			info.setSql((new StringBuilder()).append(info.getSql()).append(" limit ").append(find.getLimit()).toString());
	}

	public void handleUpdate(QueryInfo queryinfo, MethodNameParsedResult methodnameparsedresult)
	{
	}

	public void handleDelete(QueryInfo queryinfo, MethodNameParsedResult methodnameparsedresult)
	{
	}

	public void handleCount(QueryInfo queryinfo, MethodNameParsedResult methodnameparsedresult)
	{
	}
}
