// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MysqlGenerateMethodXmlHandler.java

package com.ccnode.codegenerator.database.handler.mysql;

import com.ccnode.codegenerator.database.handler.BaseQueryBuilder;
import com.ccnode.codegenerator.database.handler.GenerateMethodXmlHandler;
import com.ccnode.codegenerator.methodnameparser.buidler.MethodNameParsedResult;
import com.ccnode.codegenerator.methodnameparser.buidler.QueryInfo;

// Referenced classes of package com.ccnode.codegenerator.database.handler.mysql:
//			MysqlQueryBuilderHandler

public class MysqlGenerateMethodXmlHandler
	implements GenerateMethodXmlHandler
{

	private static volatile MysqlGenerateMethodXmlHandler instance;
	private static volatile BaseQueryBuilder baseQueryBuilder;

	private MysqlGenerateMethodXmlHandler()
	{
	}

	public static MysqlGenerateMethodXmlHandler getInstance()
	{
		if (instance == null)
			synchronized (com/ccnode/codegenerator/database/handler/mysql/MysqlGenerateMethodXmlHandler)
			{
				if (instance == null)
					instance = new MysqlGenerateMethodXmlHandler();
			}
		return instance;
	}

	public QueryInfo buildQueryInfoByMethodNameParsedResult(MethodNameParsedResult result)
	{
		if (baseQueryBuilder == null)
			synchronized (com/ccnode/codegenerator/database/handler/mysql/MysqlGenerateMethodXmlHandler)
			{
				if (baseQueryBuilder == null)
					baseQueryBuilder = new BaseQueryBuilder(new MysqlQueryBuilderHandler());
			}
		return baseQueryBuilder.buildQueryInfoByMethodNameParsedResult(result);
	}
}
