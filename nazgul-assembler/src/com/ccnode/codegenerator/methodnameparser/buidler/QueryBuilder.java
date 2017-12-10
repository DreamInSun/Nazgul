// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   QueryBuilder.java

package com.ccnode.codegenerator.methodnameparser.buidler;

import com.ccnode.codegenerator.database.DatabaseComponenent;
import com.ccnode.codegenerator.database.handler.DatabaseFactory;
import com.ccnode.codegenerator.database.handler.GenerateMethodXmlHandler;
import com.ccnode.codegenerator.dialog.ChooseParsedResultToUseDialog;
import com.ccnode.codegenerator.methodnameparser.QueryParseDto;
import com.ccnode.codegenerator.methodnameparser.dailog.AddIfTestDialog;
import com.ccnode.codegenerator.methodnameparser.parsedresult.base.ParsedBase;
import com.ccnode.codegenerator.methodnameparser.parsedresult.base.ParsedErrorBase;
import com.ccnode.codegenerator.methodnameparser.parsedresult.count.ParsedCount;
import com.ccnode.codegenerator.methodnameparser.parsedresult.count.ParsedCountError;
import com.ccnode.codegenerator.methodnameparser.parsedresult.delete.ParsedDelete;
import com.ccnode.codegenerator.methodnameparser.parsedresult.delete.ParsedDeleteError;
import com.ccnode.codegenerator.methodnameparser.parsedresult.find.ParsedFind;
import com.ccnode.codegenerator.methodnameparser.parsedresult.find.ParsedFindError;
import com.ccnode.codegenerator.methodnameparser.parsedresult.update.ParsedUpdate;
import com.ccnode.codegenerator.methodnameparser.parsedresult.update.ParsedUpdateError;
import com.ccnode.codegenerator.pojo.MethodXmlPsiInfo;
import com.ccnode.codegenerator.util.CollectionUtils;
import com.google.common.collect.Lists;
import com.intellij.openapi.project.Project;
import java.util.*;
import org.apache.commons.lang.StringUtils;

// Referenced classes of package com.ccnode.codegenerator.methodnameparser.buidler:
//			MethodNameParsedResult, QueryInfo, ParsedMethodEnum

public class QueryBuilder
{

	public QueryBuilder()
	{
	}

	public static QueryParseDto buildFindResult(List parsedFinds, List errors, MethodXmlPsiInfo info)
	{
		QueryParseDto dto = new QueryParseDto();
		if (parsedFinds.size() == 0)
		{
			dto.setHasMatched(Boolean.valueOf(false));
			List errorMsgs = new ArrayList();
			String errorMsg;
			for (Iterator iterator = errors.iterator(); iterator.hasNext(); errorMsgs.add(errorMsg))
			{
				ParsedFindError error = (ParsedFindError)iterator.next();
				errorMsg = buildErrorMsg(error);
			}

			dto.setErrorMsg(errorMsgs);
			return dto;
		}
		List strings = Lists.newArrayList();
		ParsedFind choosedFind = null;
		if (parsedFinds.size() > 1)
		{
			for (int i = 0; i < parsedFinds.size(); i++)
				strings.add(((ParsedFind)parsedFinds.get(i)).getParsedResult());

			ChooseParsedResultToUseDialog chooseParsedResultToUseDialog = new ChooseParsedResultToUseDialog(info.getProject(), strings);
			boolean b = chooseParsedResultToUseDialog.showAndGet();
			if (!b)
			{
				dto.setHasMatched(Boolean.valueOf(false));
				dto.setDisplayErrorMsg(Boolean.valueOf(false));
				return dto;
			}
			choosedFind = (ParsedFind)parsedFinds.get(chooseParsedResultToUseDialog.getChoosedIndex().intValue());
		} else
		{
			choosedFind = (ParsedFind)parsedFinds.get(0);
		}
		addIfTestToQueryRule(choosedFind, info.getProject());
		QueryInfo e = DatabaseComponenent.currentHandler().getMethodXmlHandler().buildQueryInfoByMethodNameParsedResult(convertFromParsedFind(choosedFind, info));
		if (e != null)
		{
			dto.setQueryInfo(e);
			dto.setHasMatched(Boolean.valueOf(true));
		} else
		{
			dto.setHasMatched(Boolean.valueOf(false));
			dto.setDisplayErrorMsg(Boolean.valueOf(false));
		}
		return dto;
	}

	private static void addIfTestToQueryRule(ParsedBase choosedFind, Project project)
	{
		if (!CollectionUtils.isEmpty(choosedFind.getQueryRules()) && DatabaseComponenent.shouldAddIfTest())
		{
			AddIfTestDialog dialog = new AddIfTestDialog(project, choosedFind.getQueryRules());
			boolean b = dialog.showAndGet();
			return;
		} else
		{
			return;
		}
	}

	private static MethodNameParsedResult convertFromParsedFind(ParsedFind find, MethodXmlPsiInfo info)
	{
		MethodNameParsedResult parsedResult = convertFromInfo(info);
		parsedResult.setParsedType(ParsedMethodEnum.FIND);
		parsedResult.setParsedFind(find);
		return parsedResult;
	}

	private static MethodNameParsedResult convertFromParsedUpdate(ParsedUpdate update, MethodXmlPsiInfo info)
	{
		MethodNameParsedResult parsedResult = convertFromInfo(info);
		parsedResult.setParsedType(ParsedMethodEnum.UPDATE);
		parsedResult.setParsedUpdate(update);
		return parsedResult;
	}

	private static MethodNameParsedResult convertFromParsedDelete(ParsedDelete delete, MethodXmlPsiInfo info)
	{
		MethodNameParsedResult parsedResult = convertFromInfo(info);
		parsedResult.setParsedType(ParsedMethodEnum.DELETE);
		parsedResult.setParsedDelete(delete);
		return parsedResult;
	}

	private static MethodNameParsedResult convertFromParsedCount(ParsedCount count, MethodXmlPsiInfo info)
	{
		MethodNameParsedResult parsedResult = convertFromInfo(info);
		parsedResult.setParsedType(ParsedMethodEnum.COUNT);
		parsedResult.setParsedCount(count);
		return parsedResult;
	}

	private static MethodNameParsedResult convertFromInfo(MethodXmlPsiInfo info)
	{
		MethodNameParsedResult methodNameParsedResult = new MethodNameParsedResult();
		methodNameParsedResult.setMethodName(info.getMethodName());
		methodNameParsedResult.setRelation(info.getRelation());
		methodNameParsedResult.setTableName(info.getTableName());
		methodNameParsedResult.setFieldMap(info.getFieldMap());
		methodNameParsedResult.setPsiClassName(info.getPsiClassName());
		methodNameParsedResult.setPsiClassFullName(info.getPsiClassFullName());
		methodNameParsedResult.setSrcClass(info.getSrcClass());
		methodNameParsedResult.setProject(info.getProject());
		methodNameParsedResult.setMybatisXmlFile(info.getMybatisXmlFile());
		methodNameParsedResult.setAllColumnName(info.getAllColumnName());
		return methodNameParsedResult;
	}

	private static String buildErrorMsg(ParsedErrorBase error)
	{
		if (StringUtils.isEmpty(error.getRemaining()))
			return "the query not end legal";
		else
			return (new StringBuilder()).append("the remaining ").append(error.getRemaining()).append(" can't be parsed").toString();
	}

	public static QueryParseDto buildUpdateResult(List updateList, List errorList, MethodXmlPsiInfo info)
	{
		QueryParseDto dto = new QueryParseDto();
		if (updateList.size() == 0)
		{
			dto.setHasMatched(Boolean.valueOf(false));
			List errorMsgs = new ArrayList();
			ParsedUpdateError error;
			for (Iterator iterator = errorList.iterator(); iterator.hasNext(); errorMsgs.add(buildErrorMsg(error)))
				error = (ParsedUpdateError)iterator.next();

			dto.setErrorMsg(errorMsgs);
			return dto;
		}
		List strings = Lists.newArrayList();
		ParsedUpdate choosedFind = null;
		if (updateList.size() > 1)
		{
			for (int i = 0; i < updateList.size(); i++)
				strings.add(((ParsedUpdate)updateList.get(i)).getParsedResult());

			ChooseParsedResultToUseDialog chooseParsedResultToUseDialog = new ChooseParsedResultToUseDialog(info.getProject(), strings);
			boolean b = chooseParsedResultToUseDialog.showAndGet();
			if (!b)
			{
				dto.setHasMatched(Boolean.valueOf(false));
				dto.setDisplayErrorMsg(Boolean.valueOf(false));
				return dto;
			}
			choosedFind = (ParsedUpdate)updateList.get(chooseParsedResultToUseDialog.getChoosedIndex().intValue());
		} else
		{
			choosedFind = (ParsedUpdate)updateList.get(0);
		}
		QueryInfo e = DatabaseComponenent.currentHandler().getMethodXmlHandler().buildQueryInfoByMethodNameParsedResult(convertFromParsedUpdate(choosedFind, info));
		if (e != null)
		{
			dto.setQueryInfo(e);
			dto.setHasMatched(Boolean.valueOf(true));
		} else
		{
			dto.setHasMatched(Boolean.valueOf(false));
			dto.setDisplayErrorMsg(Boolean.valueOf(false));
		}
		return dto;
	}

	public static QueryParseDto buildDeleteResult(List parsedDeletes, List errors, MethodXmlPsiInfo info)
	{
		QueryParseDto dto = new QueryParseDto();
		if (parsedDeletes.size() == 0)
		{
			dto.setHasMatched(Boolean.valueOf(false));
			List errorMsgs = new ArrayList();
			ParsedDeleteError error;
			for (Iterator iterator = errors.iterator(); iterator.hasNext(); errorMsgs.add(buildErrorMsg(error)))
				error = (ParsedDeleteError)iterator.next();

			dto.setErrorMsg(errorMsgs);
		}
		List strings = Lists.newArrayList();
		ParsedDelete choosedFind = null;
		if (parsedDeletes.size() > 1)
		{
			for (int i = 0; i < parsedDeletes.size(); i++)
				strings.add(((ParsedDelete)parsedDeletes.get(i)).getParsedResult());

			ChooseParsedResultToUseDialog chooseParsedResultToUseDialog = new ChooseParsedResultToUseDialog(info.getProject(), strings);
			boolean b = chooseParsedResultToUseDialog.showAndGet();
			if (!b)
			{
				dto.setHasMatched(Boolean.valueOf(false));
				dto.setDisplayErrorMsg(Boolean.valueOf(false));
				return dto;
			}
			choosedFind = (ParsedDelete)parsedDeletes.get(chooseParsedResultToUseDialog.getChoosedIndex().intValue());
		} else
		{
			choosedFind = (ParsedDelete)parsedDeletes.get(0);
		}
		QueryInfo e = DatabaseComponenent.currentHandler().getMethodXmlHandler().buildQueryInfoByMethodNameParsedResult(convertFromParsedDelete(choosedFind, info));
		if (e != null)
		{
			dto.setQueryInfo(e);
			dto.setHasMatched(Boolean.valueOf(true));
		} else
		{
			dto.setHasMatched(Boolean.valueOf(false));
			dto.setDisplayErrorMsg(Boolean.valueOf(false));
		}
		return dto;
	}

	public static QueryParseDto buildCountResult(List parsedCounts, List errors, MethodXmlPsiInfo info)
	{
		QueryParseDto dto = new QueryParseDto();
		if (parsedCounts.size() == 0)
		{
			dto.setHasMatched(Boolean.valueOf(false));
			List errorMsgs = new ArrayList();
			ParsedCountError error;
			for (Iterator iterator = errors.iterator(); iterator.hasNext(); errorMsgs.add(buildErrorMsg(error)))
				error = (ParsedCountError)iterator.next();

			dto.setErrorMsg(errorMsgs);
			return dto;
		}
		List strings = Lists.newArrayList();
		ParsedCount choosedFind = null;
		if (parsedCounts.size() > 1)
		{
			for (int i = 0; i < parsedCounts.size(); i++)
				strings.add(((ParsedCount)parsedCounts.get(i)).getParsedResult());

			ChooseParsedResultToUseDialog chooseParsedResultToUseDialog = new ChooseParsedResultToUseDialog(info.getProject(), strings);
			boolean b = chooseParsedResultToUseDialog.showAndGet();
			if (!b)
			{
				dto.setHasMatched(Boolean.valueOf(false));
				dto.setDisplayErrorMsg(Boolean.valueOf(false));
				return dto;
			}
			choosedFind = (ParsedCount)parsedCounts.get(chooseParsedResultToUseDialog.getChoosedIndex().intValue());
		} else
		{
			choosedFind = (ParsedCount)parsedCounts.get(0);
		}
		QueryInfo e = DatabaseComponenent.currentHandler().getMethodXmlHandler().buildQueryInfoByMethodNameParsedResult(convertFromParsedCount(choosedFind, info));
		if (e != null)
		{
			dto.setQueryInfo(e);
			dto.setHasMatched(Boolean.valueOf(true));
		} else
		{
			dto.setHasMatched(Boolean.valueOf(false));
			dto.setDisplayErrorMsg(Boolean.valueOf(false));
		}
		return dto;
	}
}
