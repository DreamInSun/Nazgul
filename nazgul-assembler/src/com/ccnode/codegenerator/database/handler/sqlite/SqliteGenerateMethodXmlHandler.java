// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SqliteGenerateMethodXmlHandler.java

package com.ccnode.codegenerator.database.handler.sqlite;

import com.ccnode.codegenerator.database.handler.BaseQueryBuilder;
import com.ccnode.codegenerator.database.handler.GenerateMethodXmlHandler;
import com.ccnode.codegenerator.methodnameparser.buidler.MethodNameParsedResult;
import com.ccnode.codegenerator.methodnameparser.buidler.QueryInfo;

// Referenced classes of package com.ccnode.codegenerator.database.handler.sqlite:
//			SqliteQueryBuilderHandler

public class SqliteGenerateMethodXmlHandler
	implements GenerateMethodXmlHandler
{

	private static volatile SqliteGenerateMethodXmlHandler mInstance;
	private static volatile BaseQueryBuilder baseQueryBuilder;

	private SqliteGenerateMethodXmlHandler()
	{
	}

	public static SqliteGenerateMethodXmlHandler getInstance()
	{
		if (mInstance == null)
			synchronized (com/ccnode/codegenerator/database/handler/sqlite/SqliteGenerateMethodXmlHandler)
			{
				if (mInstance == null)
					mInstance = new SqliteGenerateMethodXmlHandler();
			}
		return mInstance;
	}

	public QueryInfo buildQueryInfoByMethodNameParsedResult(MethodNameParsedResult result)
	{
		if (baseQueryBuilder == null)
			synchronized (com/ccnode/codegenerator/database/handler/sqlite/SqliteGenerateMethodXmlHandler)
			{
				baseQueryBuilder = new BaseQueryBuilder(new SqliteQueryBuilderHandler());
			}
		return baseQueryBuilder.buildQueryInfoByMethodNameParsedResult(result);
	}
}
