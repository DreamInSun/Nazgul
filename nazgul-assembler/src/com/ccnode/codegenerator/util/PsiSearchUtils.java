// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PsiSearchUtils.java

package com.ccnode.codegenerator.util;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.*;
import com.intellij.psi.xml.*;
import java.util.ArrayList;
import java.util.List;

public class PsiSearchUtils
{

	public PsiSearchUtils()
	{
	}

	public static List searchMapperXml(Project project, Module moduleForPsiElement, String srcClassQualifiedName)
	{
		if (project == null)
			$$$reportNull$$$0(0);
		if (moduleForPsiElement != null) goto _L2; else goto _L1
_L1:
		ArrayList arraylist = new ArrayList();
		arraylist;
		if (arraylist == null)
			$$$reportNull$$$0(1);
		return;
_L2:
		List xmlFiles;
		PsiSearchHelper searchService = (PsiSearchHelper)ServiceManager.getService(project, com/intellij/psi/search/PsiSearchHelper);
		xmlFiles = new ArrayList();
		searchService.processUsagesInNonJavaFiles("mapper", new PsiNonJavaFileReferenceProcessor(srcClassQualifiedName, xmlFiles) {

			final String val$srcClassQualifiedName;
			final List val$xmlFiles;

			public boolean process(PsiFile file, int startOffset, int endOffset)
			{
				if (file instanceof XmlFile)
				{
					XmlFile xmlFile = (XmlFile)file;
					if (xmlFile.getRootTag() != null)
					{
						XmlAttribute namespace = xmlFile.getRootTag().getAttribute("namespace");
						if (namespace != null && namespace.getValue().equals(srcClassQualifiedName))
						{
							xmlFiles.add(xmlFile);
							return false;
						}
					}
				}
				return true;
			}

			
			{
				srcClassQualifiedName = s;
				xmlFiles = list;
				super();
			}
		}, GlobalSearchScope.moduleScope(moduleForPsiElement));
		xmlFiles;
		if (xmlFiles == null)
			$$$reportNull$$$0(2);
		return;
	}

	private static void $$$reportNull$$$0(int i)
	{
		String s;
		switch (i)
		{
		case 0: // '\0'
		default:
			s = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
			break;

		case 1: // '\001'
		case 2: // '\002'
			s = "@NotNull method %s.%s must not return null";
			break;
		}
		int j;
		s;
		switch (i)
		{
		case 0: // '\0'
		default:
			j = 3;
			break;

		case 1: // '\001'
		case 2: // '\002'
			j = 2;
			break;
		}
		new Object[j];
		i;
		JVM INSTR tableswitch 0 2: default 104
	//	               0 104
	//	               1 112
	//	               2 112;
		   goto _L1 _L1 _L2 _L2
_L1:
		JVM INSTR dup ;
		0;
		"project";
		JVM INSTR aastore ;
		  goto _L3
_L2:
		JVM INSTR dup ;
		0;
		"com/ccnode/codegenerator/util/PsiSearchUtils";
		JVM INSTR aastore ;
_L3:
		i;
		JVM INSTR tableswitch 0 2: default 148
	//	               0 148
	//	               1 156
	//	               2 156;
		   goto _L4 _L4 _L5 _L5
_L4:
		JVM INSTR dup ;
		1;
		"com/ccnode/codegenerator/util/PsiSearchUtils";
		JVM INSTR aastore ;
		  goto _L6
_L5:
		JVM INSTR dup ;
		1;
		"searchMapperXml";
		JVM INSTR aastore ;
_L6:
		i;
		JVM INSTR tableswitch 0 2: default 192
	//	               0 192
	//	               1 200
	//	               2 200;
		   goto _L7 _L7 _L8 _L8
_L7:
		JVM INSTR dup ;
		2;
		"searchMapperXml";
		JVM INSTR aastore ;
_L8:
		String.format();
		i;
		JVM INSTR tableswitch 0 2: default 232
	//	               0 232
	//	               1 243
	//	               2 243;
		   goto _L9 _L9 _L10 _L10
_L9:
		JVM INSTR new #87  <Class IllegalArgumentException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalArgumentException();
		  goto _L11
_L10:
		JVM INSTR new #92  <Class IllegalStateException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalStateException();
_L11:
		throw ;
	}
}
