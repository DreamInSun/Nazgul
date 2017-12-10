// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   UpdateParser.java

package com.ccnode.codegenerator.methodnameparser.parser;

import com.ccnode.codegenerator.dialog.ParseTypeEnum;
import com.ccnode.codegenerator.methodnameparser.KeyWordConstants;
import com.ccnode.codegenerator.methodnameparser.parsedresult.update.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.ccnode.codegenerator.methodnameparser.parser:
//			BaseParser

public class UpdateParser extends BaseParser
{

	private List parsedUpdates;
	private List errors;

	public UpdateParser(String methodName, List props)
	{
		super(methodName, props);
		parsedUpdates = new ArrayList();
		errors = new ArrayList();
	}

	public ParsedUpdateDto parse()
	{
		int state = 0;
		int len = "update".length();
		ParsedUpdate parsedUpdate = new ParsedUpdate();
		parseMethods(state, methodName, len, parsedUpdate);
		ParsedUpdateDto dto = new ParsedUpdateDto();
		dto.setUpdateList(parsedUpdates);
		dto.setErrorList(errors);
		return dto;
	}

	private void parseMethods(int state, String methodName, int len, ParsedUpdate parsedUpdate)
	{
		if (methodName.length() == len)
		{
			if (isValidState(state))
			{
				parsedUpdates.add(parsedUpdate);
			} else
			{
				ParsedUpdateError error = new ParsedUpdateError();
				error.setParsedUpdate(parsedUpdate);
				error.setLastState(Integer.valueOf(state));
				error.setRemaining("");
				errors.add(error);
			}
			return;
		}
		String remaining = methodName.substring(len);
		boolean newParseUpdate = false;
		switch (state)
		{
		case 0: // '\0'
		{
			for (int i = 0; i < props.length; i++)
				if (remaining.startsWith(lowerProps[i]))
				{
					ParsedUpdate newUpdate = parsedUpdate.clone();
					newUpdate.addUpdateProps(props[i]);
					newUpdate.addParsePart(ParseTypeEnum.PROPERTY, props[i]);
					parseMethods(1, remaining, props[i].length(), newUpdate);
					newParseUpdate = true;
				}

			String as[] = updatePrefixs;
			int j = as.length;
			for (int i1 = 0; i1 < j; i1++)
			{
				String preProp = as[i1];
				if (remaining.startsWith(preProp))
				{
					ParsedUpdate newUpdate = parsedUpdate.clone();
					newUpdate.addPrefix(preProp);
					newUpdate.addParsePart(ParseTypeEnum.INCORDEC, preProp);
					parseMethods(5, remaining, preProp.length(), newUpdate);
					newParseUpdate = true;
				}
			}

			break;
		}

		case 5: // '\005'
		{
			for (int i = 0; i < props.length; i++)
				if (remaining.startsWith(lowerProps[i]))
				{
					ParsedUpdate newUpdate = parsedUpdate.clone();
					newUpdate.addUpdateProps(props[i]);
					newUpdate.addParsePart(ParseTypeEnum.PROPERTY, props[i]);
					parseMethods(1, remaining, props[i].length(), newUpdate);
					newParseUpdate = true;
				}

			break;
		}

		case 1: // '\001'
		{
			if (remaining.startsWith("and"))
			{
				ParsedUpdate newUpdate = parsedUpdate.clone();
				newUpdate.addParsePart(ParseTypeEnum.AND, "and");
				parseMethods(0, remaining, "and".length(), newUpdate);
				newParseUpdate = true;
			}
			if (remaining.startsWith("by"))
			{
				ParsedUpdate newUpdate = parsedUpdate.clone();
				newUpdate.addParsePart(ParseTypeEnum.BY, "by");
				parseMethods(2, remaining, "by".length(), newUpdate);
				newParseUpdate = true;
			}
			break;
		}

		case 2: // '\002'
		{
			for (int i = 0; i < props.length; i++)
				if (remaining.startsWith(lowerProps[i]))
				{
					ParsedUpdate newUpdate = parsedUpdate.clone();
					newUpdate.addQueryProp(props[i]);
					newUpdate.addParsePart(ParseTypeEnum.PROPERTY, props[i]);
					parseMethods(3, remaining, props[i].length(), newUpdate);
					newParseUpdate = true;
				}

			break;
		}

		case 3: // '\003'
		{
			String as1[] = linkOp;
			int k = as1.length;
			for (int j1 = 0; j1 < k; j1++)
			{
				String link = as1[j1];
				if (remaining.startsWith(link))
				{
					ParsedUpdate newUpdate = parsedUpdate.clone();
					newUpdate.addConnector(link);
					newUpdate.addParsePart(ParseTypeEnum.LINKOP, link);
					parseMethods(2, remaining, link.length(), newUpdate);
					newParseUpdate = true;
				}
			}

			as1 = compareOp;
			k = as1.length;
			for (int k1 = 0; k1 < k; k1++)
			{
				String comp = as1[k1];
				if (remaining.startsWith(comp))
				{
					ParsedUpdate newUpdate = parsedUpdate.clone();
					newUpdate.addQueryOperator(comp);
					newUpdate.addParsePart(ParseTypeEnum.COMPARATOR, comp);
					parseMethods(4, remaining, comp.length(), newUpdate);
					newParseUpdate = true;
				}
			}

			break;
		}

		case 4: // '\004'
		{
			String as2[] = linkOp;
			int l = as2.length;
			for (int l1 = 0; l1 < l; l1++)
			{
				String link = as2[l1];
				if (remaining.startsWith(link))
				{
					ParsedUpdate newUpdate = parsedUpdate.clone();
					newUpdate.addConnector(link);
					newUpdate.addParsePart(ParseTypeEnum.LINKOP, link);
					parseMethods(2, remaining, link.length(), newUpdate);
					newParseUpdate = true;
				}
			}

			break;
		}
		}
		if (!newParseUpdate)
		{
			ParsedUpdateError error = new ParsedUpdateError();
			error.setParsedUpdate(parsedUpdate);
			error.setRemaining(remaining);
			error.setLastState(Integer.valueOf(state));
			errors.add(error);
		}
	}

	private boolean isValidState(int state)
	{
		return state == 1 || state == 3 || state == 4;
	}
}
