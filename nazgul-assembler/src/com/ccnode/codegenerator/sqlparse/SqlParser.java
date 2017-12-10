// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SqlParser.java

package com.ccnode.codegenerator.sqlparse;

import com.ccnode.codegenerator.constants.MyBatisXmlConstants;
import com.ccnode.codegenerator.util.PsiClassUtil;
import com.ccnode.codegenerator.view.completion.MysqlCompleteCacheInteface;
import com.google.common.collect.Lists;
import com.intellij.codeInsight.lookup.*;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.psi.PsiClass;
import com.intellij.psi.xml.XmlTag;
import java.util.*;
import org.apache.commons.lang3.StringUtils;

// Referenced classes of package com.ccnode.codegenerator.sqlparse:
//			ParsedResult, TableNameAndFieldName, TableNameAndAliaseName, ParseContext, 
//			MethodRecommendCache

public class SqlParser
{

	public SqlParser()
	{
	}

	public static ParsedResult parse(ParseContext context)
	{
		List baseRecommends = Lists.newArrayList();
		ParsedResult result = new ParsedResult();
		result.setRecommedValues(new ArrayList());
		String currentWordStart = context.getCurrentWordStart();
		String beforeCurrentWordString = getBeforeRealString(currentWordStart);
		List beforeWord = extractWords(context.getBeforeText().toLowerCase());
		List afterWords = extractWords(context.getAfterText().toLowerCase());
		boolean currentIsSkipChar = isSkipChar(context.getAllText().charAt(context.getCursorOffSet() - 1));
		if (beforeWord.size() == 0 || beforeWord.size() == 1 && !currentIsSkipChar)
		{
			baseRecommends.add("insert into ");
			baseRecommends.add("select ");
			baseRecommends.add("update ");
			baseRecommends.add("delete ");
			result.setRecommedValues(convertToRecommeds(baseRecommends));
			return result;
		}
		MysqlCompleteCacheInteface cacheService = (MysqlCompleteCacheInteface)ServiceManager.getService(context.getProject(), com/ccnode/codegenerator/view/completion/MysqlCompleteCacheInteface);
		if (beforeWord.contains("select"))
		{
			if (checkTableNameRecommend(beforeWord, currentIsSkipChar))
			{
				List allTables = cacheService.getAllTables();
				result.setRecommedValues(convertToRecommeds(allTables));
				return result;
			}
			boolean beforeContainsFrom = beforeWord.contains("from");
			boolean afterContainsFrom = afterWords.contains("from");
			if (!beforeContainsFrom)
			{
				if (checkAsRecommed(beforeWord, currentIsSkipChar))
				{
					XmlTag currentTag = context.getCurrentTag();
					String attributeValue = currentTag.getAttributeValue("resultType");
					if (StringUtils.isNotEmpty(attributeValue))
					{
						PsiClass classOfQuatifiedType = PsiClassUtil.findClassOfQuatifiedType(ModuleUtilCore.findModuleForPsiElement(context.getCurrentXmlFile()), context.getProject(), attributeValue);
						if (classOfQuatifiedType != null)
						{
							List strings = PsiClassUtil.extractProps(classOfQuatifiedType);
							String string;
							for (Iterator iterator6 = strings.iterator(); iterator6.hasNext(); result.getRecommedValues().add(LookupElementBuilder.create(string).withRenderer(new LookupElementRenderer(string, attributeValue) {

		final String val$string;
		final String val$attributeValue;

		public void renderElement(LookupElement element, LookupElementPresentation presentation)
		{
			presentation.setItemText(string);
			presentation.setTypeText(attributeValue);
		}

			
			{
				string = s;
				attributeValue = s1;
				super();
			}
	})))
								string = (String)iterator6.next();

						}
					}
					return result;
				}
				if (!afterContainsFrom)
				{
					List allFields = cacheService.getAllFieldsWithTable();
					TableNameAndFieldName field;
					for (Iterator iterator = allFields.iterator(); iterator.hasNext(); result.getRecommedValues().add(getTableAndFieldElement(beforeCurrentWordString, field)))
						field = (TableNameAndFieldName)iterator.next();

					result.getRecommedValues().add(LookupElementBuilder.create("from "));
					result.getRecommedValues().add(LookupElementBuilder.create("as "));
					result.getRecommedValues().addAll(MethodRecommendCache.getRecommends(beforeCurrentWordString));
					return result;
				}
				List fields = getRecommendFromTableFields(afterWords, cacheService);
				TableNameAndFieldName field;
				for (Iterator iterator1 = fields.iterator(); iterator1.hasNext(); result.getRecommedValues().add(getTableAndFieldElement(beforeCurrentWordString, field)))
					field = (TableNameAndFieldName)iterator1.next();

				result.getRecommedValues().addAll(MethodRecommendCache.getRecommends(beforeCurrentWordString));
				return result;
			}
			boolean beforeContainWhereOrOn = beforeWord.contains("where") || beforeWord.contains("on");
			if (checkListContains(beforeWord, new String[] {
	"order", "by"
}))
			{
				List fields = getRecommendFromTableFields(beforeWord, cacheService);
				TableNameAndFieldName field;
				for (Iterator iterator2 = fields.iterator(); iterator2.hasNext(); result.getRecommedValues().add(getTableAndFieldElement(beforeCurrentWordString, field)))
					field = (TableNameAndFieldName)iterator2.next();

				result.getRecommedValues().addAll(MethodRecommendCache.getRecommends(beforeCurrentWordString));
				result.getRecommedValues().add(LookupElementBuilder.create("limit "));
				return result;
			}
			if (checkListContains(beforeWord, new String[] {
	"having"
}))
			{
				List fields = getRecommendFromTableFields(beforeWord, cacheService);
				TableNameAndFieldName field;
				for (Iterator iterator3 = fields.iterator(); iterator3.hasNext(); result.getRecommedValues().add(getTableAndFieldElement(beforeCurrentWordString, field)))
					field = (TableNameAndFieldName)iterator3.next();

				result.getRecommedValues().addAll(MethodRecommendCache.getRecommends(beforeCurrentWordString));
				result.getRecommedValues().add(LookupElementBuilder.create("limit "));
				result.getRecommedValues().add(LookupElementBuilder.create("order by "));
				return result;
			}
			if (checkListContains(beforeWord, new String[] {
	"group", "by"
}))
			{
				List fields = getRecommendFromTableFields(beforeWord, cacheService);
				TableNameAndFieldName field;
				for (Iterator iterator4 = fields.iterator(); iterator4.hasNext(); result.getRecommedValues().add(getTableAndFieldElement(beforeCurrentWordString, field)))
					field = (TableNameAndFieldName)iterator4.next();

				result.getRecommedValues().add(LookupElementBuilder.create("order by "));
				result.getRecommedValues().add(LookupElementBuilder.create("limit "));
				result.getRecommedValues().add(LookupElementBuilder.create("having "));
				return result;
			}
			if (beforeContainWhereOrOn)
			{
				List fields = getRecommendFromTableFields(beforeWord, cacheService);
				TableNameAndFieldName field;
				for (Iterator iterator5 = fields.iterator(); iterator5.hasNext(); result.getRecommedValues().add(getTableAndFieldElement(beforeCurrentWordString, field)))
					field = (TableNameAndFieldName)iterator5.next();

				result.getRecommedValues().add(LookupElementBuilder.create("order by "));
				result.getRecommedValues().add(LookupElementBuilder.create("limit "));
				result.getRecommedValues().add(LookupElementBuilder.create("having "));
				result.getRecommedValues().add(LookupElementBuilder.create("group by "));
				return result;
			}
			baseRecommends = Lists.newArrayList();
			baseRecommends.add("inner join ");
			baseRecommends.add("left join ");
			baseRecommends.add("right join ");
			baseRecommends.add("where ");
			if (beforeWord.contains("join"))
				baseRecommends.add("on ");
			result.getRecommedValues().addAll(convertToRecommeds(baseRecommends));
			result.getRecommedValues().add(LookupElementBuilder.create("order by "));
			result.getRecommedValues().add(LookupElementBuilder.create("limit "));
			result.getRecommedValues().add(LookupElementBuilder.create("having "));
			result.getRecommedValues().add(LookupElementBuilder.create("group by "));
			return result;
		} else
		{
			return result;
		}
	}

	private static boolean checkAsRecommed(List beforeWord, boolean currentIsSkipChar)
	{
		int size = beforeWord.size();
		if (size >= 2)
			if (currentIsSkipChar)
			{
				if (((String)beforeWord.get(size - 1)).equals("as"))
					return true;
			} else
			if (((String)beforeWord.get(size - 2)).equals("as"))
				return true;
		return false;
	}

	private static transient boolean checkListContains(List beforeWord, String contains[])
	{
		String as[] = contains;
		int i = as.length;
		for (int j = 0; j < i; j++)
		{
			String s = as[j];
			if (!beforeWord.contains(s))
				return false;
		}

		return true;
	}

	private static boolean checkTableNameRecommend(List beforeWord, boolean currentIsSkipChar)
	{
		int size = beforeWord.size();
		if (size >= 2)
			if (currentIsSkipChar)
			{
				if (((String)beforeWord.get(size - 1)).equals("join") || ((String)beforeWord.get(size - 1)).equals("from"))
					return true;
			} else
			if (((String)beforeWord.get(size - 2)).equals("join") || ((String)beforeWord.get(size - 2)).equals("from"))
				return true;
		return false;
	}

	private static LookupElementBuilder getTableAndFieldElement(String beforeCurrentWordString, TableNameAndFieldName field)
	{
		LookupElementBuilder lookupelementbuilder = LookupElementBuilder.create((new StringBuilder()).append(beforeCurrentWordString).append(field.getFieldName()).toString()).withRenderer(new LookupElementRenderer(field) {

			final TableNameAndFieldName val$field;

			public void renderElement(LookupElement element, LookupElementPresentation presentation)
			{
				presentation.setItemText((new StringBuilder()).append(field.getFieldName()).append("  (").append(field.getTableName()).append(")").toString());
			}

			
			{
				field = tablenameandfieldname;
				super();
			}
		});
		lookupelementbuilder;
		if (lookupelementbuilder == null)
			$$$reportNull$$$0(0);
		return;
	}

	private static List convertToRecommeds(List baseRecommends)
	{
		List lookupElements = Lists.newArrayList();
		String baseRecommend;
		for (Iterator iterator = baseRecommends.iterator(); iterator.hasNext(); lookupElements.add(LookupElementBuilder.create(baseRecommend)))
			baseRecommend = (String)iterator.next();

		return lookupElements;
	}

	private static String getBeforeRealString(String currentWordStart)
	{
		for (int i = currentWordStart.length() - 1; i >= 0; i--)
			if (isSkipChar(currentWordStart.charAt(i)))
				return currentWordStart.substring(0, i + 1);

		return "";
	}

	private static List getRecommendFromTableFields(List words, MysqlCompleteCacheInteface cacheInteface)
	{
		List recommends = Lists.newArrayList();
		List tableNameAndAliaseNames = extractNameFrom(words);
		for (Iterator iterator = tableNameAndAliaseNames.iterator(); iterator.hasNext();)
		{
			TableNameAndAliaseName tableNameAndAliaseName = (TableNameAndAliaseName)iterator.next();
			if (tableNameAndAliaseName.getAliaseName() == null)
			{
				recommends.addAll(cacheInteface.getTableAllFields(tableNameAndAliaseName.getTableName()));
			} else
			{
				List tableAllFields = cacheInteface.getTableAllFields(tableNameAndAliaseName.getTableName());
				Iterator iterator1 = tableAllFields.iterator();
				while (iterator1.hasNext()) 
				{
					TableNameAndFieldName tableAllField = (TableNameAndFieldName)iterator1.next();
					String s = (new StringBuilder()).append(tableNameAndAliaseName.getAliaseName()).append(".").append(tableAllField.getFieldName()).toString();
					TableNameAndFieldName tableNameAndFieldName = new TableNameAndFieldName();
					tableNameAndFieldName.setFieldName(s);
					tableNameAndFieldName.setTableName(tableAllField.getTableName());
					recommends.add(tableNameAndFieldName);
				}
			}
		}

		return recommends;
	}

	private static List extractNameFrom(List words)
	{
		List tableNameAndAliaseNames = Lists.newArrayList();
		int size = words.size();
		for (int i = 0; i < words.size(); i++)
		{
			String s = (String)words.get(i);
			if (!s.equals("from") && !s.equals("join") || i >= size - 1)
				continue;
			TableNameAndAliaseName tableNameAndAliaseName = new TableNameAndAliaseName();
			String tableName = (String)words.get(i + 1);
			tableNameAndAliaseName.setTableName(tableName);
			if (i < size - 2)
			{
				String aliase = (String)words.get(i + 2);
				if (!aliase.equals("inner") && !aliase.equals("left") && !aliase.equals("right") && !aliase.equals("where") && !aliase.equals("on"))
					if (aliase.equals("as"))
					{
						if (i < size - 3)
							tableNameAndAliaseName.setAliaseName((String)words.get(i + 3));
					} else
					{
						tableNameAndAliaseName.setAliaseName(aliase);
					}
			}
			tableNameAndAliaseNames.add(tableNameAndAliaseName);
		}

		return tableNameAndAliaseNames;
	}

	private static List extractWords(String startText)
	{
		List words = Lists.newArrayList();
		String m = "";
		for (int i = 0; i < startText.length(); i++)
		{
			char c = startText.charAt(i);
			if (isSkipChar(c))
			{
				if (m.length() > 0)
				{
					words.add(m);
					m = "";
				}
			} else
			{
				m = (new StringBuilder()).append(m).append(c).toString();
			}
		}

		if (m.length() > 0)
			words.add(m);
		return words;
	}

	private static boolean isSkipChar(char c)
	{
		return c == ' ' || c == '\n' || c == '\t' || c == ',' || c == '(' || c == ')';
	}

	private static void $$$reportNull$$$0(int i)
	{
		String.format("@NotNull method %s.%s must not return null", new Object[] {
			"com/ccnode/codegenerator/sqlparse/SqlParser", "getTableAndFieldElement"
		});
		JVM INSTR new #424 <Class IllegalStateException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalStateException();
		throw ;
	}
}
