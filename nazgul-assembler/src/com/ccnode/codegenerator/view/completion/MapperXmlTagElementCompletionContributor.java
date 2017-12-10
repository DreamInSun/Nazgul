// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MapperXmlTagElementCompletionContributor.java

package com.ccnode.codegenerator.view.completion;

import com.ccnode.codegenerator.constants.MyBatisXmlConstants;
import com.ccnode.codegenerator.util.MyPsiXmlUtils;
import com.ccnode.codegenerator.util.PsiClassUtil;
import com.google.common.collect.Lists;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.*;
import com.intellij.psi.xml.*;
import java.util.*;
import org.apache.commons.lang3.StringUtils;

public class MapperXmlTagElementCompletionContributor extends CompletionContributor
{

	public MapperXmlTagElementCompletionContributor()
	{
	}

	public void fillCompletionVariants(CompletionParameters parameters, CompletionResultSet result)
	{
		if (parameters == null)
			$$$reportNull$$$0(0);
		if (result == null)
			$$$reportNull$$$0(1);
		PsiElement element = parameters.getPosition();
		PsiElement parent = element.getParent();
		if (parent == null || !(parent instanceof XmlAttributeValue))
			return;
		XmlAttributeValue attributeValue = (XmlAttributeValue)parent;
		PsiElement parent1 = attributeValue.getParent();
		if (!(parent1 instanceof XmlAttribute))
			return;
		XmlAttribute attribute = (XmlAttribute)parent1;
		String name = attribute.getName();
		String startText = parameters.getOriginalPosition().getText();
		if (name.equals("property"))
			handleWithPropertyComplete(result, element, attribute, startText);
		else
		if (name.equals("refid"))
			handleWithRefidComplete(parameters, result, attribute, startText);
		else
		if (name.equals("resultMap"))
			handleWithResultMap(parameters, result, attribute, startText);
		else
		if (name.equals("test"))
			hendleWithTestComplete(parameters, result, element, attribute, startText);
		else
		if (name.equals("id"))
			handleWithMethodNameId(parameters, result, element, attribute, startText);
		else
		if (name.equals("keyProperty"))
			handleWithKeyPropertyComplete(parameters, result, attribute, startText);
	}

	private void handleWithKeyPropertyComplete(CompletionParameters parameters, CompletionResultSet result, XmlAttribute attribute, String startText)
	{
		if (parameters == null)
			$$$reportNull$$$0(2);
		if (result == null)
			$$$reportNull$$$0(3);
		XmlTag tag = attribute.getParent();
		if (tag == null)
			return;
		if (!tag.getName().equals("insert") && !tag.getName().equals("update"))
			return;
		String tagId = tag.getAttributeValue("id");
		if (StringUtils.isBlank(tagId))
			return;
		XmlFile xmlFile = (XmlFile)parameters.getOriginalFile();
		String namespace = MyPsiXmlUtils.findCurrentXmlFileNameSpace(xmlFile);
		PsiClass namespaceClass = PsiClassUtil.findClassOfQuatifiedType(xmlFile, namespace);
		if (namespaceClass == null)
			return;
		PsiMethod findMethod = PsiClassUtil.getClassMethodByMethodName(namespaceClass, tagId);
		if (findMethod == null)
			return;
		List lookUpResult = PsiClassUtil.extractMyBatisParam(findMethod);
		Iterator iterator = lookUpResult.iterator();
		do
		{
			if (!iterator.hasNext())
				break;
			String s = (String)iterator.next();
			if (startText.equals("\"") || s.startsWith(startText))
				result.addElement(LookupElementBuilder.create(s));
		} while (true);
	}

	private void hendleWithTestComplete(CompletionParameters parameters, CompletionResultSet result, PsiElement element, XmlAttribute attribute, String startText)
	{
		if (parameters == null)
			$$$reportNull$$$0(4);
		if (result == null)
			$$$reportNull$$$0(5);
		XmlTag tag = attribute.getParent();
		if (tag == null)
			return;
		if (!tag.getName().equals("if"))
			return;
		PsiFile originalFile = parameters.getOriginalFile();
		if (!(originalFile instanceof XmlFile))
			return;
		String lastWord = findLastWord(startText);
		if (lastWord == null)
			return;
		XmlFile xmlFile = (XmlFile)originalFile;
		XmlTag rootTag = xmlFile.getRootTag();
		if (rootTag == null || !rootTag.getName().equals("mapper"))
			return;
		String namespace = rootTag.getAttributeValue("namespace");
		if (StringUtils.isBlank(namespace))
			return;
		PsiClass classOfQuatifiedType = PsiClassUtil.findClassOfQuatifiedType(element, namespace);
		if (classOfQuatifiedType == null)
			return;
		String interfaceMethodName = MyPsiXmlUtils.findCurrentElementIntefaceMethodName(element);
		if (StringUtils.isBlank(interfaceMethodName))
			return;
		PsiMethod findedMethod = PsiClassUtil.getClassMethodByMethodName(classOfQuatifiedType, interfaceMethodName);
		if (findedMethod == null)
			return;
		List myBatisParams = PsiClassUtil.extractMyBatisParam(findedMethod);
		myBatisParams.add("null");
		myBatisParams.add("and");
		Iterator iterator = myBatisParams.iterator();
		do
		{
			if (!iterator.hasNext())
				break;
			String myBatisParam = (String)iterator.next();
			if (myBatisParam.startsWith(lastWord))
				result.addElement(LookupElementBuilder.create((new StringBuilder()).append(startText).append(myBatisParam.substring(lastWord.length())).toString()));
		} while (true);
	}

	private String findLastWord(String startText)
	{
		if (StringUtils.isBlank(startText))
			return null;
		StringBuilder builder = new StringBuilder();
		for (int i = startText.length() - 1; i >= 0 && (Character.isLetterOrDigit(startText.charAt(i)) || startText.charAt(i) == '.'); i--)
			builder.append(startText.charAt(i));

		String s = builder.toString();
		if (s.length() == 0)
			return null;
		else
			return StringUtils.reverse(s);
	}

	private void handleWithMethodNameId(CompletionParameters parameters, CompletionResultSet result, PsiElement element, XmlAttribute attribute, String startText)
	{
		if (parameters == null)
			$$$reportNull$$$0(6);
		if (result == null)
			$$$reportNull$$$0(7);
		XmlTag tag = attribute.getParent();
		if (tag == null)
			return;
		if (!MyBatisXmlConstants.mapperMethodSet.contains(tag.getName()))
			return;
		PsiFile originalFile = parameters.getOriginalFile();
		if (!(originalFile instanceof XmlFile))
			return;
		XmlFile xmlFil = (XmlFile)originalFile;
		XmlTag rootTag = xmlFil.getRootTag();
		if (rootTag == null)
			return;
		String namespace = rootTag.getAttributeValue("namespace");
		if (StringUtils.isBlank(namespace))
			return;
		PsiClass classOfQuatifiedType = PsiClassUtil.findClassOfQuatifiedType(element, namespace);
		if (classOfQuatifiedType == null || !classOfQuatifiedType.isInterface())
			return;
		PsiMethod allMethods[] = classOfQuatifiedType.getMethods();
		List methodNames = Lists.newArrayList();
		PsiMethod apsimethod[] = allMethods;
		int i = apsimethod.length;
		for (int j = 0; j < i; j++)
		{
			PsiMethod allMethod = apsimethod[j];
			methodNames.add(allMethod.getName());
		}

		Iterator iterator = methodNames.iterator();
		do
		{
			if (!iterator.hasNext())
				break;
			String methodName = (String)iterator.next();
			if (startText.equals("\"") || methodName.startsWith(startText))
				result.addElement(LookupElementBuilder.create(methodName));
		} while (true);
	}

	private void handleWithResultMap(CompletionParameters parameters, CompletionResultSet result, XmlAttribute attribute, String startText)
	{
		if (parameters == null)
			$$$reportNull$$$0(8);
		if (result == null)
			$$$reportNull$$$0(9);
		XmlTag tag = attribute.getParent();
		if (tag == null)
			return;
		if (!tag.getName().equals("select"))
			return;
		PsiFile originalFile = parameters.getOriginalFile();
		if (!(originalFile instanceof XmlFile))
			return;
		XmlFile xmlFil = (XmlFile)originalFile;
		XmlTag rootTag = xmlFil.getRootTag();
		if (rootTag == null)
			return;
		XmlTag subTags[] = rootTag.getSubTags();
		List reslutMapIds = Lists.newArrayList();
		XmlTag axmltag[] = subTags;
		int i = axmltag.length;
		for (int j = 0; j < i; j++)
		{
			XmlTag subTag = axmltag[j];
			if (!subTag.getName().equals("resultMap"))
				continue;
			String resultMapId = subTag.getAttributeValue("id");
			if (StringUtils.isNotBlank(resultMapId))
				reslutMapIds.add(resultMapId);
		}

		Iterator iterator = reslutMapIds.iterator();
		do
		{
			if (!iterator.hasNext())
				break;
			String resultMapId = (String)iterator.next();
			if (startText.equals("\"") || resultMapId.startsWith(startText))
				result.addElement(LookupElementBuilder.create(resultMapId));
		} while (true);
	}

	private void handleWithRefidComplete(CompletionParameters parameters, CompletionResultSet result, XmlAttribute attribute, String startText)
	{
		if (parameters == null)
			$$$reportNull$$$0(10);
		if (result == null)
			$$$reportNull$$$0(11);
		XmlTag tag = attribute.getParent();
		if (tag == null)
			return;
		if (!tag.getName().equals("include"))
			return;
		PsiFile originalFile = parameters.getOriginalFile();
		if (!(originalFile instanceof XmlFile))
			return;
		XmlFile xmlFil = (XmlFile)originalFile;
		XmlTag rootTag = xmlFil.getRootTag();
		if (rootTag == null)
			return;
		XmlTag subTags[] = rootTag.getSubTags();
		List sqls = Lists.newArrayList();
		XmlTag axmltag[] = subTags;
		int i = axmltag.length;
		for (int j = 0; j < i; j++)
		{
			XmlTag subTag = axmltag[j];
			if (!subTag.getName().equals("sql"))
				continue;
			String sqlId = subTag.getAttributeValue("id");
			if (StringUtils.isNotBlank(sqlId))
				sqls.add(sqlId);
		}

		Iterator iterator = sqls.iterator();
		do
		{
			if (!iterator.hasNext())
				break;
			String sql = (String)iterator.next();
			if (startText.equals("\"") || sql.startsWith(startText))
				result.addElement(LookupElementBuilder.create(sql));
		} while (true);
	}

	private void handleWithPropertyComplete(CompletionResultSet result, PsiElement element, XmlAttribute attribute, String startText)
	{
		if (result == null)
			$$$reportNull$$$0(12);
		XmlTag tag = attribute.getParent();
		if (tag == null)
			return;
		if (!tag.getName().equals("result"))
			return;
		PsiElement parent2 = tag.getParent();
		if (!(parent2 instanceof XmlTag))
			return;
		XmlTag resultMapTag = (XmlTag)parent2;
		if (!resultMapTag.getName().equals("resultMap"))
			return;
		String resultTypeValue = resultMapTag.getAttributeValue("type");
		if (StringUtils.isBlank(resultTypeValue))
			return;
		PsiClass findedClass = PsiClassUtil.findClassOfQuatifiedType(element, resultTypeValue);
		if (findedClass == null)
			return;
		List props = PsiClassUtil.extractProps(findedClass);
		Iterator iterator = props.iterator();
		do
		{
			if (!iterator.hasNext())
				break;
			String prop = (String)iterator.next();
			if (startText.equals("\"") || prop.startsWith(startText))
				result.addElement(LookupElementBuilder.create(prop));
		} while (true);
	}

	private static void $$$reportNull$$$0(int i)
	{
		"Argument for @NotNull parameter '%s' of %s.%s must not be null";
		new Object[3];
		i;
		JVM INSTR tableswitch 0 12: default 76
	//	               0 76
	//	               1 85
	//	               2 76
	//	               3 85
	//	               4 76
	//	               5 85
	//	               6 76
	//	               7 85
	//	               8 76
	//	               9 85
	//	               10 76
	//	               11 85
	//	               12 85;
		   goto _L1 _L1 _L2 _L1 _L2 _L1 _L2 _L1 _L2 _L1 _L2 _L1 _L2 _L2
_L1:
		JVM INSTR dup ;
		0;
		"parameters";
		JVM INSTR aastore ;
		  goto _L3
_L2:
		JVM INSTR dup ;
		0;
		"result";
		JVM INSTR aastore ;
_L3:
		JVM INSTR dup ;
		1;
		"com/ccnode/codegenerator/view/completion/MapperXmlTagElementCompletionContributor";
		JVM INSTR aastore ;
		i;
		JVM INSTR tableswitch 0 12: default 168
	//	               0 168
	//	               1 168
	//	               2 177
	//	               3 177
	//	               4 186
	//	               5 186
	//	               6 195
	//	               7 195
	//	               8 204
	//	               9 204
	//	               10 213
	//	               11 213
	//	               12 222;
		   goto _L4 _L4 _L4 _L5 _L5 _L6 _L6 _L7 _L7 _L8 _L8 _L9 _L9 _L10
_L4:
		JVM INSTR dup ;
		2;
		"fillCompletionVariants";
		JVM INSTR aastore ;
		  goto _L11
_L5:
		JVM INSTR dup ;
		2;
		"handleWithKeyPropertyComplete";
		JVM INSTR aastore ;
		  goto _L11
_L6:
		JVM INSTR dup ;
		2;
		"hendleWithTestComplete";
		JVM INSTR aastore ;
		  goto _L11
_L7:
		JVM INSTR dup ;
		2;
		"handleWithMethodNameId";
		JVM INSTR aastore ;
		  goto _L11
_L8:
		JVM INSTR dup ;
		2;
		"handleWithResultMap";
		JVM INSTR aastore ;
		  goto _L11
_L9:
		JVM INSTR dup ;
		2;
		"handleWithRefidComplete";
		JVM INSTR aastore ;
		  goto _L11
_L10:
		JVM INSTR dup ;
		2;
		"handleWithPropertyComplete";
		JVM INSTR aastore ;
_L11:
		String.format();
		JVM INSTR new #378 <Class IllegalArgumentException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalArgumentException();
		throw ;
	}
}
