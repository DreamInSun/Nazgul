// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   OracleGenerateMethodXmlHandler.java

package com.ccnode.codegenerator.database.handler.oracle;

import com.ccnode.codegenerator.database.handler.BaseQueryBuilder;
import com.ccnode.codegenerator.database.handler.GenerateMethodXmlHandler;
import com.ccnode.codegenerator.methodnameparser.buidler.MethodNameParsedResult;
import com.ccnode.codegenerator.methodnameparser.buidler.QueryInfo;

// Referenced classes of package com.ccnode.codegenerator.database.handler.oracle:
//			OracleQueryBuilderHandler

public class OracleGenerateMethodXmlHandler
	implements GenerateMethodXmlHandler
{

	private static volatile OracleGenerateMethodXmlHandler mInstance;
	private static volatile BaseQueryBuilder baseQueryBuilder;

	private OracleGenerateMethodXmlHandler()
	{
	}

	public static OracleGenerateMethodXmlHandler getInstance()
	{
		if (mInstance == null)
			synchronized (com/ccnode/codegenerator/database/handler/oracle/OracleGenerateMethodXmlHandler)
			{
				if (mInstance == null)
					mInstance = new OracleGenerateMethodXmlHandler();
			}
		return mInstance;
	}

	public QueryInfo buildQueryInfoByMethodNameParsedResult(MethodNameParsedResult result)
	{
		if (baseQueryBuilder == null)
			synchronized (com/ccnode/codegenerator/database/handler/oracle/OracleGenerateMethodXmlHandler)
			{
				baseQueryBuilder = new BaseQueryBuilder(new OracleQueryBuilderHandler());
			}
		return baseQueryBuilder.buildQueryInfoByMethodNameParsedResult(result);
	}
}
