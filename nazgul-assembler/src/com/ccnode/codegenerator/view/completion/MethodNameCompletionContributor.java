// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MethodNameCompletionContributor.java

package com.ccnode.codegenerator.view.completion;

import com.ccnode.codegenerator.methodnameparser.buidler.ParsedMethodEnum;
import com.ccnode.codegenerator.pojo.DomainClassInfo;
import com.ccnode.codegenerator.util.*;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import java.util.*;

public class MethodNameCompletionContributor extends CompletionContributor
{

	private static List textEndList = new ArrayList() {

			
			{
				add("find");
				add("update");
				add("and");
				add("by");
				add("count");
			}
	};

	public MethodNameCompletionContributor()
	{
	}

	public void beforeCompletion(CompletionInitializationContext context)
	{
		if (context == null)
			$$$reportNull$$$0(0);
	}

	public void fillCompletionVariants(CompletionParameters parameters, CompletionResultSet result)
	{
		if (parameters == null)
			$$$reportNull$$$0(1);
		if (result == null)
			$$$reportNull$$$0(2);
		if (parameters.getCompletionType() != CompletionType.BASIC)
			return;
		PsiElement element = parameters.getPosition();
		PsiElement originalPosition = parameters.getOriginalPosition();
		PsiFile topLevelFile = InjectedLanguageUtil.getTopLevelFile(element);
		if (topLevelFile == null || !(topLevelFile instanceof PsiJavaFile))
			return;
		PsiClass containingClass = PsiElementUtil.getContainingClass(originalPosition);
		if (containingClass == null || !containingClass.isInterface())
			return;
		String text = originalPosition.getText();
		MethodNameCheckReuslt methodNameResult = MethodNameUtil.checkValidTextStarter(text);
		if (!methodNameResult.isValid())
			return;
		ParsedMethodEnum parsedMethodEnum = methodNameResult.getParsedMethodEnum();
		DomainClassInfo domainClassInfo = PsiClassUtil.getDomainClassInfo(containingClass);
		if (domainClassInfo == null)
			return;
		PsiClass pojoClass = domainClassInfo.getDomainClass();
		if (pojoClass == null)
			return;
		List strings = PsiClassUtil.extractProps(pojoClass);
		List formatProps = new ArrayList();
		String s;
		for (Iterator iterator = strings.iterator(); iterator.hasNext(); formatProps.add((new StringBuilder()).append(s.substring(0, 1).toUpperCase()).append(s.substring(1)).toString()))
			s = (String)iterator.next();

		String lower = text.toLowerCase();
		boolean defaultrecommed = false;
		List afterlower = textEndList.iterator();
		do
		{
			if (!afterlower.hasNext())
				break;
			String end = (String)afterlower.next();
			if (lower.endsWith(end))
			{
				defaultrecommed = true;
				LookupElementBuilder builder;
				for (Iterator iterator1 = formatProps.iterator(); iterator1.hasNext(); result.addElement(builder))
				{
					String prop = (String)iterator1.next();
					builder = LookupElementBuilder.create((new StringBuilder()).append(text).append(prop).toString());
				}

			}
			if (lower.equals("find"))
			{
				result.addElement(LookupElementBuilder.create((new StringBuilder()).append(text).append("First").toString()));
				result.addElement(LookupElementBuilder.create((new StringBuilder()).append(text).append("One").toString()));
			}
			if (parsedMethodEnum.equals(ParsedMethodEnum.UPDATE) && (lower.equals("update") || lower.endsWith("and")))
				result.addElement(LookupElementBuilder.create((new StringBuilder()).append(text).append("Inc").toString()));
		} while (true);
		if (defaultrecommed)
			return;
		afterlower = new ArrayList();
		if (lower.indexOf("by") != -1)
		{
			if (lower.endsWith("g"))
			{
				afterlower.add("reaterThan");
				afterlower.add("reaterThanOrEqualTo");
			}
			if (lower.endsWith("l"))
			{
				afterlower.add("essThan");
				afterlower.add("essThanOrEqualTo");
				afterlower.add("ike");
			}
			if (lower.endsWith("b"))
			{
				afterlower.add("etween");
				afterlower.add("etweenOrEqualTo");
				afterlower.add("efore");
			}
			if (lower.endsWith("i"))
			{
				afterlower.add("n");
				afterlower.add("sNotNull");
			}
			if (lower.endsWith("n"))
			{
				afterlower.add("otIn");
				afterlower.add("otLike");
				afterlower.add("ot");
				afterlower.add("otNull");
			}
			if (lower.endsWith("o"))
				afterlower.add("r");
			if (lower.endsWith("a"))
				afterlower.add("fter");
			if (lower.endsWith("s"))
				afterlower.add("tartingwith");
			if (lower.endsWith("e"))
				afterlower.add("ndingwith");
			if (lower.endsWith("c"))
				afterlower.add("ontaining");
		}
		if (parsedMethodEnum.equals(ParsedMethodEnum.FIND))
		{
			if (lower.endsWith("w"))
				afterlower.add("ithPage");
			if (lower.endsWith("m"))
			{
				afterlower.add("ax");
				afterlower.add("in");
			}
			if (lower.endsWith("a"))
			{
				afterlower.add("vg");
				afterlower.add("nd");
			}
			if (lower.endsWith("s"))
				afterlower.add("um");
			if (lower.indexOf("orderby") != -1 && lower.endsWith("d"))
				afterlower.add("esc");
		}
		if (parsedMethodEnum.equals(ParsedMethodEnum.FIND) || parsedMethodEnum.equals(ParsedMethodEnum.COUNT))
		{
			if (lower.endsWith("o"))
				afterlower.add("rderBy");
			if (lower.equals("findd") || lower.equals("countd"))
				afterlower.add("istinct");
		}
		if (parsedMethodEnum.equals(ParsedMethodEnum.UPDATE))
		{
			if (lower.endsWith("i"))
				afterlower.add("nc");
			if (lower.endsWith("d"))
				afterlower.add("ec");
		}
		char u = Character.toLowerCase(text.charAt(text.length() - 1));
		Iterator iterator2 = strings.iterator();
		do
		{
			if (!iterator2.hasNext())
				break;
			String prop = (String)iterator2.next();
			if (Character.toLowerCase(prop.charAt(0)) == u && prop.length() > 1)
				afterlower.add(prop.substring(1));
		} while (true);
		if (afterlower.size() > 0)
		{
			LookupElementBuilder builder;
			for (Iterator iterator3 = afterlower.iterator(); iterator3.hasNext(); result.addElement(builder))
			{
				String after = (String)iterator3.next();
				builder = LookupElementBuilder.create((new StringBuilder()).append(text).append(after).toString());
			}

		}
	}

	private static void $$$reportNull$$$0(int i)
	{
		"Argument for @NotNull parameter '%s' of %s.%s must not be null";
		new Object[3];
		i;
		JVM INSTR tableswitch 0 2: default 36
	//	               0 36
	//	               1 45
	//	               2 54;
		   goto _L1 _L1 _L2 _L3
_L1:
		JVM INSTR dup ;
		0;
		"context";
		JVM INSTR aastore ;
		  goto _L4
_L2:
		JVM INSTR dup ;
		0;
		"parameters";
		JVM INSTR aastore ;
		  goto _L4
_L3:
		JVM INSTR dup ;
		0;
		"result";
		JVM INSTR aastore ;
_L4:
		JVM INSTR dup ;
		1;
		"com/ccnode/codegenerator/view/completion/MethodNameCompletionContributor";
		JVM INSTR aastore ;
		i;
		JVM INSTR tableswitch 0 2: default 96
	//	               0 96
	//	               1 105
	//	               2 105;
		   goto _L5 _L5 _L6 _L6
_L5:
		JVM INSTR dup ;
		2;
		"beforeCompletion";
		JVM INSTR aastore ;
		  goto _L7
_L6:
		JVM INSTR dup ;
		2;
		"fillCompletionVariants";
		JVM INSTR aastore ;
_L7:
		String.format();
		JVM INSTR new #377 <Class IllegalArgumentException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalArgumentException();
		throw ;
	}

}
