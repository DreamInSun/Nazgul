// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CountParser.java

package com.ccnode.codegenerator.methodnameparser.parser;

import com.ccnode.codegenerator.dialog.ParseTypeEnum;
import com.ccnode.codegenerator.methodnameparser.KeyWordConstants;
import com.ccnode.codegenerator.methodnameparser.parsedresult.count.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.ccnode.codegenerator.methodnameparser.parser:
//			BaseParser

public class CountParser extends BaseParser
{

	private List counts;
	private List errors;

	public CountParser(String methodName, List props)
	{
		super(methodName, props);
		counts = new ArrayList();
		errors = new ArrayList();
	}

	public ParsedCountDto parse()
	{
		int state = 0;
		int len = "count".length();
		ParsedCount count = new ParsedCount();
		parseMethods(state, methodName, len, count);
		ParsedCountDto dto = new ParsedCountDto();
		dto.setParsedCounts(counts);
		dto.setErrors(errors);
		return dto;
	}

	private void parseMethods(int state, String methodName, int len, ParsedCount count)
	{
		if (methodName.length() == len)
		{
			if (isValidEndState(state))
			{
				counts.add(count);
			} else
			{
				ParsedCountError error = new ParsedCountError();
				error.setParsedCount(count);
				error.setLastState(Integer.valueOf(state));
				error.setRemaining("");
				errors.add(error);
			}
			return;
		}
		String remaining = methodName.substring(len);
		boolean newParsedCount = false;
		switch (state)
		{
		case 0: // '\0'
		{
			for (int i = 0; i < props.length; i++)
				if (remaining.startsWith(lowerProps[i]))
				{
					ParsedCount clone = count.clone();
					clone.addFetchProps(props[i]);
					clone.addParsePart(ParseTypeEnum.PROPERTY, props[i]);
					parseMethods(1, remaining, props[i].length(), clone);
					newParsedCount = true;
				}

			if (remaining.startsWith("distinct"))
			{
				ParsedCount clone = count.clone();
				clone.setDistinct(true);
				clone.addParsePart(ParseTypeEnum.DISTINCT, "distinct");
				parseMethods(2, remaining, "distinct".length(), clone);
				newParsedCount = true;
			}
			if (remaining.startsWith("by"))
			{
				ParsedCount clone = count.clone();
				clone.addParsePart(ParseTypeEnum.BY, "by");
				parseMethods(4, remaining, "by".length(), clone);
				newParsedCount = true;
			}
			break;
		}

		case 1: // '\001'
		{
			if (remaining.startsWith("by"))
			{
				ParsedCount clone = count.clone();
				clone.addParsePart(ParseTypeEnum.BY, "by");
				parseMethods(4, remaining, "by".length(), clone);
				newParsedCount = true;
			}
			break;
		}

		case 2: // '\002'
		{
			for (int i = 0; i < props.length; i++)
				if (remaining.startsWith(lowerProps[i]))
				{
					ParsedCount clone = count.clone();
					clone.addFetchProps(props[i]);
					clone.addParsePart(ParseTypeEnum.PROPERTY, props[i]);
					parseMethods(3, remaining, props[i].length(), clone);
					newParsedCount = true;
				}

			break;
		}

		case 3: // '\003'
		{
			if (remaining.startsWith("and"))
			{
				ParsedCount clone = count.clone();
				clone.addParsePart(ParseTypeEnum.AND, "and");
				parseMethods(2, remaining, "and".length(), clone);
				newParsedCount = true;
			}
			if (remaining.startsWith("by"))
			{
				ParsedCount clone = count.clone();
				clone.addParsePart(ParseTypeEnum.BY, "by");
				parseMethods(4, remaining, "by".length(), clone);
				newParsedCount = true;
			}
			break;
		}

		case 4: // '\004'
		{
			for (int i = 0; i < props.length; i++)
				if (remaining.startsWith(lowerProps[i]))
				{
					ParsedCount clone = count.clone();
					clone.addQueryProp(props[i]);
					clone.addParsePart(ParseTypeEnum.PROPERTY, props[i]);
					parseMethods(5, remaining, props[i].length(), clone);
					newParsedCount = true;
				}

			break;
		}

		case 5: // '\005'
		{
			String as[] = linkOp;
			int j = as.length;
			for (int l = 0; l < j; l++)
			{
				String link = as[l];
				if (remaining.startsWith(link))
				{
					ParsedCount clone = count.clone();
					clone.addConnector(link);
					clone.addParsePart(ParseTypeEnum.LINKOP, link);
					parseMethods(4, remaining, link.length(), clone);
					newParsedCount = true;
				}
			}

			as = compareOp;
			j = as.length;
			for (int i1 = 0; i1 < j; i1++)
			{
				String comp = as[i1];
				if (remaining.startsWith(comp))
				{
					ParsedCount clone = count.clone();
					clone.addQueryOperator(comp);
					clone.addParsePart(ParseTypeEnum.COMPARATOR, comp);
					parseMethods(6, remaining, comp.length(), clone);
					newParsedCount = true;
				}
			}

			break;
		}

		case 6: // '\006'
		{
			String as1[] = linkOp;
			int k = as1.length;
			for (int j1 = 0; j1 < k; j1++)
			{
				String link = as1[j1];
				if (remaining.startsWith(link))
				{
					ParsedCount clone = count.clone();
					clone.addConnector(link);
					clone.addParsePart(ParseTypeEnum.LINKOP, link);
					parseMethods(4, remaining, link.length(), clone);
					newParsedCount = true;
				}
			}

			break;
		}
		}
		if (!newParsedCount)
		{
			ParsedCountError error = new ParsedCountError();
			error.setParsedCount(count);
			error.setRemaining(remaining);
			error.setLastState(Integer.valueOf(state));
			errors.add(error);
		}
	}

	private boolean isValidEndState(int state)
	{
		return state == 0 || state == 1 || state == 3 || state == 5 || state == 6;
	}

	public static void main(String args[])
	{
		String methodName = "count";
		List props = new ArrayList();
		props.add("id");
		props.add("name");
		props.add("username");
		ParsedCountDto parse = (new CountParser(methodName.toLowerCase(), props)).parse();
		parse.getParsedCounts();
	}
}
