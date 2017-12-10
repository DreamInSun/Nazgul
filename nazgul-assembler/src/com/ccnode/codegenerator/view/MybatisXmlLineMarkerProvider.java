// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MybatisXmlLineMarkerProvider.java

package com.ccnode.codegenerator.view;

import com.ccnode.codegenerator.util.IconUtils;
import com.ccnode.codegenerator.util.MyPsiXmlUtils;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.psi.*;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import java.util.*;

public class MybatisXmlLineMarkerProvider extends RelatedItemLineMarkerProvider
{

	private static Set tagNameSet = new HashSet() {

			
			{
				add("select");
				add("insert");
				add("update");
				add("delete");
			}
	};

	public MybatisXmlLineMarkerProvider()
	{
	}

	protected void collectNavigationMarkers(PsiElement element, Collection result)
	{
		if (element == null)
			$$$reportNull$$$0(0);
		if (!(element instanceof XmlTag))
			return;
		XmlTag theTag = (XmlTag)element;
		PsiFile psiFile = element.getContainingFile();
		if (!(psiFile instanceof XmlFile))
			return;
		XmlFile xmlFile = (XmlFile)psiFile;
		if (!xmlFile.getRootTag().getName().equals("mapper"))
			return;
		XmlTag tag = (XmlTag)element;
		if (!tagNameSet.contains(tag.getName()))
			return;
		PsiElement findedMethod = MyPsiXmlUtils.findMethodOfXmlTag(tag);
		if (findedMethod == null)
		{
			return;
		} else
		{
			result.add(NavigationGutterIconBuilder.create(IconUtils.mapperxmlIcon()).setAlignment(com.intellij.openapi.editor.markup.GutterIconRenderer.Alignment.CENTER).setTarget(findedMethod).setTooltipTitle("navigation to mapper class").createLineMarkerInfo(theTag.getAttribute("id")));
			return;
		}
	}

	private static void $$$reportNull$$$0(int i)
	{
		String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", new Object[] {
			"element", "com/ccnode/codegenerator/view/MybatisXmlLineMarkerProvider", "collectNavigationMarkers"
		});
		JVM INSTR new #157 <Class IllegalArgumentException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalArgumentException();
		throw ;
	}

}
