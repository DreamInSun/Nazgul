// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MybatisJavaLineMarkerProvider.java

package com.ccnode.codegenerator.view;

import com.ccnode.codegenerator.util.IconUtils;
import com.ccnode.codegenerator.util.MyPsiXmlUtils;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.search.*;
import com.intellij.psi.xml.*;
import java.util.*;

public class MybatisJavaLineMarkerProvider extends RelatedItemLineMarkerProvider
{

	public MybatisJavaLineMarkerProvider()
	{
	}

	protected void collectNavigationMarkers(PsiElement element, Collection result)
	{
		if (element == null)
			$$$reportNull$$$0(0);
		if (element instanceof PsiMethod)
		{
			PsiMethod method = (PsiMethod)element;
			PsiClass containingClass = method.getContainingClass();
			if (!containingClass.isInterface())
				return;
			Project project = element.getProject();
			String qualifiedName = containingClass.getQualifiedName();
			PsiElement methodElement = null;
			PsiFile filesByName[] = PsiShortNamesCache.getInstance(project).getFilesByName((new StringBuilder()).append(containingClass.getName()).append(".xml").toString());
			if (filesByName.length == 0)
			{
				methodElement = handleWithFileNotFound(method, project, qualifiedName, result);
			} else
			{
				PsiFile apsifile[] = filesByName;
				int i = apsifile.length;
				for (int j = 0; j < i; j++)
				{
					PsiFile file = apsifile[j];
					if (!(file instanceof XmlFile))
						continue;
					XmlFile xmlFile = (XmlFile)file;
					XmlAttribute namespace = xmlFile.getRootTag().getAttribute("namespace");
					if (namespace == null || !namespace.getValue().equals(qualifiedName))
						continue;
					PsiElement psiElement = MyPsiXmlUtils.findTagForMethodName(xmlFile, method.getName());
					if (psiElement == null)
						continue;
					methodElement = psiElement;
					break;
				}

				if (methodElement == null)
					methodElement = handleWithFileNotFound(method, project, qualifiedName, result);
			}
			if (methodElement != null)
				result.add(NavigationGutterIconBuilder.create(IconUtils.mapperIcon()).setAlignment(com.intellij.openapi.editor.markup.GutterIconRenderer.Alignment.CENTER).setTarget(methodElement).setTooltipTitle("navigation to mapper xml").createLineMarkerInfo(method.getNameIdentifier()));
		}
	}

	private PsiElement handleWithFileNotFound(PsiMethod method, Project project, final String qualifiedName, Collection result)
	{
		if (method == null)
			$$$reportNull$$$0(1);
		PsiSearchHelper searchService = (PsiSearchHelper)ServiceManager.getService(project, com/intellij/psi/search/PsiSearchHelper);
		final List xmlFiles = new ArrayList();
		Module moduleForPsiElement = ModuleUtilCore.findModuleForPsiElement(method);
		if (moduleForPsiElement == null)
			return null;
		searchService.processUsagesInNonJavaFiles("mapper", new PsiNonJavaFileReferenceProcessor() {

			final String val$qualifiedName;
			final List val$xmlFiles;
			final MybatisJavaLineMarkerProvider this$0;

			public boolean process(PsiFile file, int startOffset, int endOffset)
			{
				if (file instanceof XmlFile)
				{
					XmlFile xmlFile = (XmlFile)file;
					if (xmlFile.getRootTag() != null)
					{
						XmlAttribute namespace = xmlFile.getRootTag().getAttribute("namespace");
						if (namespace != null && namespace.getValue().equals(qualifiedName))
						{
							xmlFiles.add(xmlFile);
							return false;
						}
					}
				}
				return true;
			}

			
			{
				this.this$0 = MybatisJavaLineMarkerProvider.this;
				qualifiedName = s;
				xmlFiles = list;
				super();
			}
		}, GlobalSearchScope.moduleScope(moduleForPsiElement));
		if (xmlFiles.size() == 0)
			return null;
		else
			return MyPsiXmlUtils.findTagForMethodName((XmlFile)xmlFiles.get(0), method.getName());
	}

	private static void $$$reportNull$$$0(int i)
	{
		"Argument for @NotNull parameter '%s' of %s.%s must not be null";
		new Object[3];
		i;
		JVM INSTR tableswitch 0 1: default 28
	//	               0 28
	//	               1 36;
		   goto _L1 _L1 _L2
_L1:
		JVM INSTR dup ;
		0;
		"element";
		JVM INSTR aastore ;
		  goto _L3
_L2:
		JVM INSTR dup ;
		0;
		"method";
		JVM INSTR aastore ;
_L3:
		JVM INSTR dup ;
		1;
		"com/ccnode/codegenerator/view/MybatisJavaLineMarkerProvider";
		JVM INSTR aastore ;
		i;
		JVM INSTR tableswitch 0 1: default 76
	//	               0 76
	//	               1 85;
		   goto _L4 _L4 _L5
_L4:
		JVM INSTR dup ;
		2;
		"collectNavigationMarkers";
		JVM INSTR aastore ;
		  goto _L6
_L5:
		JVM INSTR dup ;
		2;
		"handleWithFileNotFound";
		JVM INSTR aastore ;
_L6:
		String.format();
		JVM INSTR new #265 <Class IllegalArgumentException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalArgumentException();
		throw ;
	}
}
