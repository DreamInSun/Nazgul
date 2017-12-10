// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MapperUtil.java

package com.ccnode.codegenerator.dialog;

import com.ccnode.codegenerator.database.DatabaseComponenent;
import com.ccnode.codegenerator.dialog.dto.mybatis.ClassMapperMethod;
import com.ccnode.codegenerator.dialog.dto.mybatis.ColumnAndField;
import com.ccnode.codegenerator.dialog.dto.mybatis.MapperMethodEnum;
import com.ccnode.codegenerator.enums.MethodName;
import com.ccnode.codegenerator.freemarker.TemplateConstants;
import com.ccnode.codegenerator.freemarker.TemplateUtil;
import com.ccnode.codegenerator.util.GenCodeUtil;
import com.ccnode.codegenerator.view.GenerateMethodXmlAction;
import com.google.common.collect.Maps;
import com.intellij.psi.PsiClass;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.ccnode.codegenerator.dialog:
//			GenCodeProp

public class MapperUtil
{

	public static final String SELECT = "select";
	public static final String FROM = "from";

	public MapperUtil()
	{
	}

	static String generateSql(List newAddedProps, List deleteFields, String sqlText, List existingFields)
	{
		sqlText = deleteEndEmptyChar(sqlText);
		String beforeWhere = sqlText;
		int start = 0;
		int end = sqlText.length();
		String lowerSqlText = sqlText.toLowerCase();
		int where = findMatchFor(lowerSqlText, "from");
		if (where != -1)
		{
			end = where;
			beforeWhere = sqlText.substring(0, where);
		}
		int select = findMatchFor(lowerSqlText, "select");
		if (select != -1)
		{
			start = select + "select".length();
			beforeWhere = beforeWhere.substring(select + "select".length());
		}
		if (beforeWhere.contains("("))
			return null;
		String split[] = beforeWhere.split(",");
		List beforeFormatted = new ArrayList();
		String as[] = split;
		int j = as.length;
		for (int k = 0; k < j; k++)
		{
			String uu = as[k];
			String term = trimUseLess(uu);
			boolean isDeleted = false;
			Iterator iterator = deleteFields.iterator();
			do
			{
				if (!iterator.hasNext())
					break;
				ColumnAndField deleteField = (ColumnAndField)iterator.next();
				if (!term.toLowerCase().equals(deleteField.getColumn().toLowerCase()))
					continue;
				isDeleted = true;
				break;
			} while (true);
			if (!isDeleted)
				beforeFormatted.add(uu);
		}

		String beforeInsert = "";
		for (int i = 0; i < beforeFormatted.size(); i++)
		{
			beforeInsert = (new StringBuilder()).append(beforeInsert).append((String)beforeFormatted.get(i)).toString();
			if (i != beforeFormatted.size() - 1)
				beforeInsert = (new StringBuilder()).append(beforeInsert).append(",").toString();
		}

		String newAddInsert = "";
		for (int i = 0; i < newAddedProps.size(); i++)
			newAddInsert = (new StringBuilder()).append(newAddInsert).append(",\n").append(DatabaseComponenent.formatColumn(((GenCodeProp)newAddedProps.get(i)).getColumnName())).toString();

		String newValueText = (new StringBuilder()).append(sqlText.substring(0, start)).append(beforeInsert).append(newAddInsert).append(sqlText.substring(end)).append("\n").toString();
		return newValueText;
	}

	private static int findMatchFor(String lowerSqlText, String where)
	{
		Pattern matcher = Pattern.compile((new StringBuilder()).append("\\b").append(where).append("\\b").toString());
		Matcher matcher1 = matcher.matcher(lowerSqlText);
		if (matcher1.find())
			return matcher1.start();
		else
			return -1;
	}

	private static String trimUseLess(String uu)
	{
		int len = uu.length();
		int start = 0;
		int end = uu.length();
		for (char c = uu.charAt(start++); start != len && c == '\n' || c == ' ' || c == '\t' || c == '`'; c = uu.charAt(start++));
		if (start == len)
			return "";
		for (char c = uu.charAt(--end); end >= start && c == '\n' || c == ' ' || c == '\t' || c == '`'; c = uu.charAt(--end));
		return uu.substring(start - 1, end + 1);
	}

	public static String generateMapperMethod(PsiClass myclass, List finalFields, String tableName, MapperMethodEnum type, ClassMapperMethod classMapperMethod)
	{
		if (tableName == null)
			tableName = "";
		String methodName = classMapperMethod.getMethodName();
		if (methodName.equals(MethodName.insert.name()))
			return genInsert(myclass, finalFields, tableName);
		if (methodName.equals(MethodName.insertList.name()))
			return genInsertList(myclass, finalFields, tableName);
		if (methodName.equals(MethodName.insertSelective.name()))
			return genInsertSelective(myclass, finalFields, tableName);
		if (methodName.equals(MethodName.update.name()))
			return genUpdateMethod(myclass, finalFields, tableName);
		else
			return null;
	}

	private static String genUpdateMethod(PsiClass myClass, List finalFields, String tableName)
	{
		Map root = Maps.newHashMap();
		root.put("finalFields", finalFields);
		root.put("tableName", tableName);
		root.put("pojoName", GenCodeUtil.getLowerCamel(myClass.getName()));
		return TemplateUtil.processToString("update.ftl", root);
	}

	private static String genInsertList(PsiClass myClass, List finalFields, String tableName)
	{
		Map root = Maps.newHashMap();
		root.put("finalFields", finalFields);
		root.put("tableName", tableName);
		root.put("pojoName", GenCodeUtil.getLowerCamel(myClass.getName()));
		root.put("currentDatabase", DatabaseComponenent.currentDatabase());
		return TemplateUtil.processToString("insertList.ftl", root);
	}

	private static String genInsert(PsiClass myClass, List finalFields, String tableName)
	{
		Map root = Maps.newHashMap();
		root.put("finalFields", finalFields);
		root.put("tableName", tableName);
		root.put("pojoName", GenCodeUtil.getLowerCamel(myClass.getName()));
		String s = null;
		boolean useTest = false;
		if (useTest)
			s = TemplateUtil.processToString("insert.ftl", root);
		else
			s = TemplateUtil.processToString("insert_without_test.ftl", root);
		return s;
	}

	private static String genInsertSelective(PsiClass myClass, List finalFields, String tableName)
	{
		Map root = Maps.newHashMap();
		root.put("finalFields", finalFields);
		root.put("tableName", tableName);
		root.put("pojoName", GenCodeUtil.getLowerCamel(myClass.getName()));
		String s = null;
		boolean useTest = true;
		if (useTest)
			s = TemplateUtil.processToString("insert.ftl", root);
		else
			s = TemplateUtil.processToString("insert_without_test.ftl", root);
		return s;
	}

	public static String extractTable(String insertText)
	{
		if (insertText.length() == 0)
			return null;
		String formattedInsert = formatBlank(insertText).toLowerCase();
		int i = formattedInsert.indexOf("insert into");
		if (i == -1)
			return null;
		int s = i + "insert into".length() + 1;
		StringBuilder resBuilder = new StringBuilder();
		int j = s;
		do
		{
			if (j >= formattedInsert.length())
				break;
			char c = formattedInsert.charAt(j);
			if (isBlankChar(c))
				break;
			resBuilder.append(c);
			j++;
		} while (true);
		if (resBuilder.length() > 0)
			return resBuilder.toString();
		else
			return null;
	}

	private static String formatBlank(String insertText)
	{
		StringBuilder result = new StringBuilder();
		char firstChar = insertText.charAt(0);
		result.append(firstChar);
		boolean before = isBlankChar(firstChar);
		for (int i = 1; i < insertText.length(); i++)
		{
			char curChar = insertText.charAt(i);
			boolean cur = isBlankChar(curChar);
			if (!cur || !before)
			{
				result.append(curChar);
				before = cur;
			}
		}

		return result.toString();
	}

	private static boolean isBlankChar(char c)
	{
		return c == ' ' || c == '\t' || c == '\n' || c == '(' || c == '<' || c == ')' || c == '>';
	}

	public static String extractClassShortName(String fullName)
	{
		String s;
		int i = fullName.lastIndexOf(".");
		s = fullName.substring(i + 1);
		s;
		if (s == null)
			$$$reportNull$$$0(0);
		return;
	}

	public static String extractPackage(String fullName)
	{
		int i = fullName.lastIndexOf(".");
		if (i == -1)
			return null;
		else
			return fullName.substring(0, i);
	}

	public static String deleteEndEmptyChar(String text)
	{
		int end = text.length();
		int useEnd = end - 1;
		int j = end - 1;
		do
		{
			if (j < 0)
				break;
			char c = text.charAt(j);
			if (c != '\n' && c != '\t' && c != ' ')
			{
				useEnd = j;
				break;
			}
			j--;
		} while (true);
		return text.substring(0, useEnd + 1);
	}

	private static void $$$reportNull$$$0(int i)
	{
		String.format("@NotNull method %s.%s must not return null", new Object[] {
			"com/ccnode/codegenerator/dialog/MapperUtil", "extractClassShortName"
		});
		JVM INSTR new #356 <Class IllegalStateException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalStateException();
		throw ;
	}
}
