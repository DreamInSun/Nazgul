// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   QueryParser.java

package com.ccnode.codegenerator.methodnameparser;

import com.ccnode.codegenerator.methodnameparser.buidler.QueryBuilder;
import com.ccnode.codegenerator.methodnameparser.parsedresult.count.ParsedCountDto;
import com.ccnode.codegenerator.methodnameparser.parsedresult.delete.ParsedDeleteDto;
import com.ccnode.codegenerator.methodnameparser.parsedresult.find.ParsedFindDto;
import com.ccnode.codegenerator.methodnameparser.parsedresult.update.ParsedUpdateDto;
import com.ccnode.codegenerator.methodnameparser.parser.CountParser;
import com.ccnode.codegenerator.methodnameparser.parser.DeleteParser;
import com.ccnode.codegenerator.methodnameparser.parser.FindParser;
import com.ccnode.codegenerator.methodnameparser.parser.UpdateParser;
import com.ccnode.codegenerator.pojo.MethodXmlPsiInfo;
import java.util.List;

// Referenced classes of package com.ccnode.codegenerator.methodnameparser:
//			KeyWordConstants, QueryParseDto

public class QueryParser
{

	public QueryParser()
	{
	}

	public static QueryParseDto parse(List props, MethodXmlPsiInfo info)
	{
		String methodLower = info.getMethodName().toLowerCase();
		if (methodLower.startsWith("find"))
		{
			ParsedFindDto parse = (new FindParser(methodLower, props)).parse();
			return QueryBuilder.buildFindResult(parse.getParsedFinds(), parse.getParsedFindErrors(), info);
		}
		if (methodLower.startsWith("update"))
		{
			ParsedUpdateDto dto = (new UpdateParser(methodLower, props)).parse();
			return QueryBuilder.buildUpdateResult(dto.getUpdateList(), dto.getErrorList(), info);
		}
		if (methodLower.startsWith("delete"))
		{
			ParsedDeleteDto parse = (new DeleteParser(methodLower, props)).parse();
			return QueryBuilder.buildDeleteResult(parse.getParsedDeletes(), parse.getErrors(), info);
		}
		if (methodLower.startsWith("count"))
		{
			ParsedCountDto parse = (new CountParser(methodLower, props)).parse();
			return QueryBuilder.buildCountResult(parse.getParsedCounts(), parse.getErrors(), info);
		} else
		{
			return null;
		}
	}
}
