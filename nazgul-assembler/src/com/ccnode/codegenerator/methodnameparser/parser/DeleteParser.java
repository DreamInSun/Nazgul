// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DeleteParser.java

package com.ccnode.codegenerator.methodnameparser.parser;

import com.ccnode.codegenerator.dialog.ParseTypeEnum;
import com.ccnode.codegenerator.methodnameparser.KeyWordConstants;
import com.ccnode.codegenerator.methodnameparser.parsedresult.delete.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.ccnode.codegenerator.methodnameparser.parser:
//			BaseParser

public class DeleteParser extends BaseParser
{

	private List parsedDeletes;
	private List errors;

	public DeleteParser(String methodName, List props)
	{
		super(methodName, props);
		parsedDeletes = new ArrayList();
		errors = new ArrayList();
	}

	public ParsedDeleteDto parse()
	{
		int state = 0;
		int len = "delete".length();
		ParsedDelete parsedDelete = new ParsedDelete();
		parseMethods(state, methodName, len, parsedDelete);
		ParsedDeleteDto dto = new ParsedDeleteDto();
		dto.setParsedDeletes(parsedDeletes);
		dto.setErrors(errors);
		return dto;
	}

	private void parseMethods(int state, String methodName, int len, ParsedDelete parsedDelete)
	{
		if (methodName.length() == len)
		{
			if (isValidState(state))
			{
				parsedDeletes.add(parsedDelete);
			} else
			{
				ParsedDeleteError error = new ParsedDeleteError();
				error.setParsedDelete(parsedDelete);
				error.setRemaining("");
				error.setLastState(Integer.valueOf(state));
				errors.add(error);
			}
			return;
		}
		String remaining = methodName.substring(len);
		boolean newParseDelete = false;
		switch (state)
		{
		case 0: // '\0'
			if (remaining.startsWith("by"))
			{
				ParsedDelete newDelete = parsedDelete.clone();
				newDelete.addParsePart(ParseTypeEnum.BY, "by");
				parseMethods(1, remaining, "by".length(), newDelete);
				newParseDelete = true;
			}
			break;

		case 1: // '\001'
			for (int i = 0; i < props.length; i++)
				if (remaining.startsWith(lowerProps[i]))
				{
					ParsedDelete clone = parsedDelete.clone();
					clone.addQueryProp(props[i]);
					clone.addParsePart(ParseTypeEnum.PROPERTY, props[i]);
					parseMethods(2, remaining, props[i].length(), clone);
					newParseDelete = true;
				}

			break;

		case 2: // '\002'
			String as[] = linkOp;
			int j = as.length;
			for (int l = 0; l < j; l++)
			{
				String link = as[l];
				if (remaining.startsWith(link))
				{
					ParsedDelete clone = parsedDelete.clone();
					clone.addConnector(link);
					clone.addParsePart(ParseTypeEnum.LINKOP, link);
					parseMethods(1, remaining, link.length(), clone);
					newParseDelete = true;
				}
			}

			as = compareOp;
			j = as.length;
			for (int i1 = 0; i1 < j; i1++)
			{
				String comp = as[i1];
				if (remaining.startsWith(comp))
				{
					ParsedDelete clone = parsedDelete.clone();
					clone.addQueryOperator(comp);
					clone.addParsePart(ParseTypeEnum.COMPARATOR, comp);
					parseMethods(3, remaining, comp.length(), clone);
					newParseDelete = true;
				}
			}

			break;

		case 3: // '\003'
			String as1[] = linkOp;
			int k = as1.length;
			for (int j1 = 0; j1 < k; j1++)
			{
				String link = as1[j1];
				if (remaining.startsWith(link))
				{
					ParsedDelete clone = parsedDelete.clone();
					clone.addConnector(link);
					clone.addParsePart(ParseTypeEnum.LINKOP, link);
					parseMethods(1, remaining, link.length(), clone);
					newParseDelete = true;
				}
			}

			break;
		}
		if (!newParseDelete)
		{
			ParsedDeleteError error = new ParsedDeleteError();
			error.setParsedDelete(parsedDelete);
			error.setRemaining(remaining);
			error.setLastState(Integer.valueOf(state));
			errors.add(error);
		}
	}

	private boolean isValidState(int state)
	{
		return state == 0 || state == 2 || state == 3;
	}
}
