// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   FindParser.java

package com.ccnode.codegenerator.methodnameparser.parser;

import com.ccnode.codegenerator.dialog.ParseTypeEnum;
import com.ccnode.codegenerator.methodnameparser.KeyWordConstants;
import com.ccnode.codegenerator.methodnameparser.parsedresult.find.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.ccnode.codegenerator.methodnameparser.parser:
//			BaseParser

public class FindParser extends BaseParser
{

	private List finds;
	private List errors;

	public FindParser(String methodName, List props)
	{
		super(methodName, props);
		finds = new ArrayList();
		errors = new ArrayList();
	}

	public ParsedFindDto parse()
	{
		int state = 0;
		int len = "find".length();
		ParsedFind parsedFind = new ParsedFind();
		parseMethods(state, methodName, len, parsedFind);
		ParsedFindDto dto = new ParsedFindDto();
		dto.setParsedFinds(finds);
		dto.setParsedFindErrors(errors);
		return dto;
	}

	private void parseMethods(int state, String methodName, int len, ParsedFind parsedFind)
	{
		if (methodName.length() == len)
		{
			if (isValidEndState(state))
			{
				finds.add(parsedFind);
			} else
			{
				ParsedFindError error = new ParsedFindError();
				error.setParsedFind(parsedFind);
				error.setLastState(Integer.valueOf(state));
				error.setRemaining("");
				errors.add(error);
			}
			return;
		}
		String remaining = methodName.substring(len);
		boolean newParseFind = false;
		switch (state)
		{
		case 0: // '\0'
		{
			if (remaining.startsWith("distinct"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.setDistinct(Boolean.valueOf(true));
				newFind.addParsePart(ParseTypeEnum.DISTINCT, "distinct");
				parseMethods(1, remaining, "distinct".length(), newFind);
				newParseFind = true;
			}
			if (remaining.startsWith("first"))
				if (remaining.length() == "first".length())
				{
					ParsedFind newFind = createParseFind(parsedFind);
					newFind.setLimit(Integer.valueOf(1));
					newFind.setReturnList(Boolean.valueOf(false));
					newFind.addParsePart(ParseTypeEnum.FIRST, "first");
					parseMethods(2, remaining, "first".length(), newFind);
					newParseFind = true;
				} else
				{
					int limitCount = 0;
					int i = "first".length();
					do
					{
						if (i >= remaining.length())
							break;
						char c = remaining.charAt(i);
						if (c < '0' || c > '9')
							break;
						limitCount = limitCount * 10 + (c - 48);
						i++;
					} while (true);
					if (limitCount == 0)
						limitCount = 1;
					ParsedFind newFind = createParseFind(parsedFind);
					newFind.setLimit(Integer.valueOf(limitCount));
					newFind.setReturnList(Boolean.valueOf(true));
					newFind.addParsePart(ParseTypeEnum.FIRST, remaining.substring(0, i));
					parseMethods(2, remaining, i, newFind);
					newParseFind = true;
				}
			if (remaining.startsWith("one"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.setReturnList(Boolean.valueOf(false));
				newFind.addParsePart(ParseTypeEnum.ONE, "one");
				parseMethods(2, remaining, "one".length(), newFind);
				newParseFind = true;
			}
			for (int i = 0; i < props.length; i++)
				if (remaining.startsWith(lowerProps[i]))
				{
					ParsedFind newFind = createParseFind(parsedFind);
					newFind.addFetchProps(props[i]);
					newFind.addParsePart(ParseTypeEnum.PROPERTY, props[i]);
					parseMethods(3, remaining, props[i].length(), newFind);
					newParseFind = true;
				}

			if (remaining.startsWith("orderby"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.addParsePart(ParseTypeEnum.ORDERBY, "orderby");
				parseMethods(4, remaining, "orderby".length(), newFind);
				newParseFind = true;
			}
			if (remaining.startsWith("by"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.addParsePart(ParseTypeEnum.BY, "by");
				parseMethods(8, remaining, "by".length(), newFind);
				newParseFind = true;
			}
			String as[] = functionOp;
			int j = as.length;
			for (int k1 = 0; k1 < j; k1++)
			{
				String function = as[k1];
				if (remaining.startsWith(function))
				{
					ParsedFind newFind = createParseFind(parsedFind);
					newFind.addFunction(function);
					newFind.addParsePart(ParseTypeEnum.FUNCTION, function);
					parseMethods(11, remaining, function.length(), newFind);
					newParseFind = true;
				}
			}

			if (remaining.startsWith("withpage"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.addParsePart(ParseTypeEnum.PAGE_QUERY, "withpage");
				newFind.setWithPage(Boolean.valueOf(true));
				parseMethods(12, remaining, "withpage".length(), newFind);
				newParseFind = true;
			}
			break;
		}

		case 1: // '\001'
		{
			for (int i = 0; i < props.length; i++)
				if (remaining.startsWith(lowerProps[i]))
				{
					ParsedFind newFind = createParseFind(parsedFind);
					newFind.addFetchProps(props[i]);
					newFind.addParsePart(ParseTypeEnum.PROPERTY, props[i]);
					parseMethods(3, remaining, props[i].length(), newFind);
					newParseFind = true;
				}

			break;
		}

		case 2: // '\002'
		{
			for (int i = 0; i < props.length; i++)
				if (remaining.startsWith(lowerProps[i]))
				{
					ParsedFind newFind = createParseFind(parsedFind);
					newFind.addFetchProps(props[i]);
					newFind.addParsePart(ParseTypeEnum.PROPERTY, props[i]);
					parseMethods(3, remaining, props[i].length(), newFind);
					newParseFind = true;
				}

			if (remaining.startsWith("orderby"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				parseMethods(4, remaining, "orderby".length(), newFind);
				newParseFind = true;
			}
			if (remaining.startsWith("by"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.addParsePart(ParseTypeEnum.BY, "by");
				parseMethods(8, remaining, "by".length(), newFind);
				newParseFind = true;
			}
			if (remaining.startsWith("withpage"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.addParsePart(ParseTypeEnum.PAGE_QUERY, "withpage");
				newFind.setWithPage(Boolean.valueOf(true));
				parseMethods(12, remaining, "withpage".length(), newFind);
				newParseFind = true;
			}
			break;
		}

		case 3: // '\003'
		{
			if (remaining.startsWith("and"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.addParsePart(ParseTypeEnum.AND, "and");
				parseMethods(7, remaining, "and".length(), newFind);
				newParseFind = true;
			}
			if (remaining.startsWith("by"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.addParsePart(ParseTypeEnum.BY, "by");
				parseMethods(8, remaining, "by".length(), newFind);
				newParseFind = true;
			}
			if (remaining.startsWith("orderby"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.addParsePart(ParseTypeEnum.ORDERBY, "orderby");
				parseMethods(4, remaining, "orderby".length(), newFind);
				newParseFind = true;
			}
			if (remaining.startsWith("withpage"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.addParsePart(ParseTypeEnum.PAGE_QUERY, "withpage");
				newFind.setWithPage(Boolean.valueOf(true));
				parseMethods(12, remaining, "withpage".length(), newFind);
				newParseFind = true;
			}
			break;
		}

		case 4: // '\004'
		{
			for (int i = 0; i < props.length; i++)
				if (remaining.startsWith(lowerProps[i]))
				{
					ParsedFind newFind = createParseFind(parsedFind);
					newFind.addOrderByProp(props[i]);
					newFind.addParsePart(ParseTypeEnum.PROPERTY, props[i]);
					parseMethods(5, remaining, props[i].length(), newFind);
					newParseFind = true;
				}

			break;
		}

		case 5: // '\005'
		{
			String as1[] = order;
			int k = as1.length;
			for (int l1 = 0; l1 < k; l1++)
			{
				String orderbyw = as1[l1];
				if (remaining.startsWith(orderbyw))
				{
					ParsedFind newFind = createParseFind(parsedFind);
					newFind.addOrderByPropOrder(orderbyw);
					newFind.addParsePart(ParseTypeEnum.ORDER, orderbyw);
					parseMethods(6, remaining, orderbyw.length(), newFind);
					newParseFind = true;
				}
			}

			if (remaining.startsWith("and"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.addParsePart(ParseTypeEnum.AND, "and");
				parseMethods(4, remaining, "and".length(), newFind);
				newParseFind = true;
			}
			if (remaining.startsWith("withpage"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.addParsePart(ParseTypeEnum.PAGE_QUERY, "withpage");
				newFind.setWithPage(Boolean.valueOf(true));
				parseMethods(12, remaining, "withpage".length(), newFind);
				newParseFind = true;
			}
			break;
		}

		case 6: // '\006'
		{
			if (remaining.startsWith("and"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.addParsePart(ParseTypeEnum.AND, "and");
				parseMethods(4, remaining, "and".length(), newFind);
				newParseFind = true;
			}
			if (remaining.startsWith("withpage"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.addParsePart(ParseTypeEnum.PAGE_QUERY, "withpage");
				newFind.setWithPage(Boolean.valueOf(true));
				parseMethods(12, remaining, "withpage".length(), newFind);
				newParseFind = true;
			}
			break;
		}

		case 7: // '\007'
		{
			for (int i = 0; i < props.length; i++)
				if (remaining.startsWith(lowerProps[i]))
				{
					ParsedFind newFind = createParseFind(parsedFind);
					newFind.addFetchProps(props[i]);
					newFind.addParsePart(ParseTypeEnum.PROPERTY, props[i]);
					parseMethods(3, remaining, props[i].length(), newFind);
					newParseFind = true;
				}

			String as2[] = functionOp;
			int l = as2.length;
			for (int i2 = 0; i2 < l; i2++)
			{
				String function = as2[i2];
				if (remaining.startsWith(function))
				{
					ParsedFind newFind = createParseFind(parsedFind);
					newFind.setReturnList(Boolean.valueOf(false));
					newFind.addFunction(function);
					newFind.addParsePart(ParseTypeEnum.FUNCTION, function);
					parseMethods(11, remaining, function.length(), newFind);
					newParseFind = true;
				}
			}

			break;
		}

		case 8: // '\b'
		{
			for (int i = 0; i < props.length; i++)
				if (remaining.startsWith(lowerProps[i]))
				{
					ParsedFind newFind = createParseFind(parsedFind);
					newFind.addQueryProp(props[i]);
					newFind.addParsePart(ParseTypeEnum.PROPERTY, props[i]);
					parseMethods(9, remaining, props[i].length(), newFind);
					newParseFind = true;
				}

			break;
		}

		case 9: // '\t'
		{
			String as3[] = compareOp;
			int i1 = as3.length;
			for (int j2 = 0; j2 < i1; j2++)
			{
				String comp = as3[j2];
				if (remaining.startsWith(comp))
				{
					ParsedFind newFind = createParseFind(parsedFind);
					newFind.addParsePart(ParseTypeEnum.COMPARATOR, comp);
					newFind.addQueryOperator(comp);
					parseMethods(10, remaining, comp.length(), newFind);
					newParseFind = true;
				}
			}

			as3 = linkOp;
			i1 = as3.length;
			for (int k2 = 0; k2 < i1; k2++)
			{
				String link = as3[k2];
				if (remaining.startsWith(link))
				{
					ParsedFind newFind = createParseFind(parsedFind);
					newFind.addConnector(link);
					newFind.addParsePart(ParseTypeEnum.LINKOP, link);
					parseMethods(8, remaining, link.length(), newFind);
				}
			}

			if (remaining.startsWith("orderby"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.addParsePart(ParseTypeEnum.ORDERBY, "orderby");
				parseMethods(4, remaining, "orderby".length(), newFind);
				newParseFind = true;
			}
			if (remaining.startsWith("withpage"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.addParsePart(ParseTypeEnum.PAGE_QUERY, "withpage");
				newFind.setWithPage(Boolean.valueOf(true));
				parseMethods(12, remaining, "withpage".length(), newFind);
				newParseFind = true;
			}
			break;
		}

		case 10: // '\n'
		{
			if (remaining.startsWith("orderby"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.addParsePart(ParseTypeEnum.ORDERBY, "orderby");
				parseMethods(4, remaining, "orderby".length(), newFind);
				newParseFind = true;
			}
			String as4[] = linkOp;
			int j1 = as4.length;
			for (int l2 = 0; l2 < j1; l2++)
			{
				String link = as4[l2];
				if (remaining.startsWith(link))
				{
					ParsedFind newFind = createParseFind(parsedFind);
					newFind.addConnector(link);
					newFind.addParsePart(ParseTypeEnum.LINKOP, link);
					parseMethods(8, remaining, link.length(), newFind);
					newParseFind = true;
				}
			}

			if (remaining.startsWith("withpage"))
			{
				ParsedFind newFind = createParseFind(parsedFind);
				newFind.addParsePart(ParseTypeEnum.PAGE_QUERY, "withpage");
				newFind.setWithPage(Boolean.valueOf(true));
				parseMethods(12, remaining, "withpage".length(), newFind);
				newParseFind = true;
			}
			break;
		}

		case 11: // '\013'
		{
			for (int i = 0; i < props.length; i++)
				if (remaining.startsWith(lowerProps[i]))
				{
					ParsedFind newFind = createParseFind(parsedFind);
					newFind.addFunctionProp(props[i]);
					newFind.addParsePart(ParseTypeEnum.PROPERTY, props[i]);
					parseMethods(3, remaining, props[i].length(), newFind);
					newParseFind = true;
				}

			break;
		}
		}
		if (!newParseFind)
		{
			ParsedFindError error = new ParsedFindError();
			error.setParsedFind(parsedFind);
			error.setRemaining(remaining);
			error.setLastState(Integer.valueOf(state));
			errors.add(error);
		}
	}

	private ParsedFind createParseFind(ParsedFind parsedFind)
	{
		return parsedFind.clone();
	}

	private boolean isValidEndState(int state)
	{
		return state == 0 || state == 2 || state == 3 || state == 5 || state == 6 || state == 9 || state == 10 || state == 12;
	}
}
