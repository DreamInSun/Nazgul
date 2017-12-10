// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   OracleQueryBuilderHandler.java

package com.ccnode.codegenerator.database.handler.oracle;

import com.ccnode.codegenerator.database.handler.BaseQueryBuilder;
import com.ccnode.codegenerator.database.handler.QueryBuilderHandler;
import com.ccnode.codegenerator.methodnameparser.buidler.MethodNameParsedResult;
import com.ccnode.codegenerator.methodnameparser.buidler.QueryInfo;
import com.ccnode.codegenerator.methodnameparser.parsedresult.find.*;
import com.ccnode.codegenerator.pojo.FieldToColumnRelation;
import com.ccnode.codegenerator.util.GenCodeUtil;
import java.util.*;

public class OracleQueryBuilderHandler
	implements QueryBuilderHandler
{

	public OracleQueryBuilderHandler()
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
				Iterator iterator1 = find.getFetchProps().iterator();
				do
				{
					if (!iterator1.hasNext())
						break;
					FetchProp prop = (FetchProp)iterator1.next();
					if (prop.getFetchFunction() == null)
						builder.append((new StringBuilder()).append(" ").append(relation.getPropFormatColumn(prop.getFetchProp())).append(",").toString());
				} while (true);
				builder.deleteCharAt(builder.length() - 1);
			}
		}
		info.setSql(builder.toString());
	}

	public void handleFindAfterFromTable(QueryInfo info, MethodNameParsedResult parsedResult)
	{
		ParsedFind find = parsedResult.getParsedFind();
		if (find.getQueryRules() != null)
		{
			info.setParamInfos(new ArrayList());
			(new BaseQueryBuilder(this)).buildQuerySqlAndParam(find.getQueryRules(), info, parsedResult.getFieldMap(), parsedResult.getRelation());
		}
		if (find.getOrderByProps() != null)
		{
			info.setSql((new StringBuilder()).append(info.getSql()).append(" order by").toString());
			OrderByRule rule;
			for (Iterator iterator = find.getOrderByProps().iterator(); iterator.hasNext(); info.setSql((new StringBuilder()).append(info.getSql()).append(" ").append(parsedResult.getRelation().getPropFormatColumn(rule.getProp())).append(" ").append(rule.getOrder()).toString()))
				rule = (OrderByRule)iterator.next();

		}
		if (find.getLimit().intValue() > 0)
			if (find.getQueryRules() != null && find.getQueryRules().size() > 0)
				info.setSql((new StringBuilder()).append(info.getSql()).append(" and ROWNUM ").append(BaseQueryBuilder.cdata("<=")).append(find.getLimit()).toString());
			else
				info.setSql((new StringBuilder()).append(info.getSql()).append(" ROWNUM ").append(BaseQueryBuilder.cdata("<=")).append(find.getLimit()).toString());
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
