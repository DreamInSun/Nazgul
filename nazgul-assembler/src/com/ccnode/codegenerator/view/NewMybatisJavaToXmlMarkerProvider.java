// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   NewMybatisJavaToXmlMarkerProvider.java

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

/**
 * @deprecated Class NewMybatisJavaToXmlMarkerProvider is deprecated
 */

public class NewMybatisJavaToXmlMarkerProvider extends RelatedItemLineMarkerProvider
{

	public NewMybatisJavaToXmlMarkerProvider()
	{
	}

	protected void collectNavigationMarkers(PsiElement element, Collection result)
	{
		if (element == null)
			$$$reportNull$$$0(0);
		if (element instanceof PsiClass)
		{
			PsiClass containingClass = (PsiClass)element;
			if (!containingClass.isInterface())
				return;
			Project project = element.getProject();
			String qualifiedName = containingClass.getQualifiedName();
			XmlFile theXmlFile = null;
			PsiFile filesByName[] = PsiShortNamesCache.getInstance(project).getFilesByName((new StringBuilder()).append(containingClass.getName()).append(".xml").toString());
			if (filesByName.length == 0)
			{
				theXmlFile = handleWithFileNotFound(containingClass, project, qualifiedName, result);
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
					if (namespace != null && namespace.getValue().equals(qualifiedName))
						theXmlFile = xmlFile;
				}

			}
			if (theXmlFile != null)
				result.add(NavigationGutterIconBuilder.create(IconUtils.useMyBatisIcon()).setAlignment(com.intellij.openapi.editor.markup.GutterIconRenderer.Alignment.CENTER).setTarget(theXmlFile).setTooltipTitle("navigation to mapper xml").createLineMarkerInfo(containingClass.getNameIdentifier()));
			PsiMethod methods[] = containingClass.getMethods();
			PsiMethod apsimethod[] = methods;
			int k = apsimethod.length;
			for (int l = 0; l < k; l++)
			{
				PsiMethod method = apsimethod[l];
				XmlTag tagForMethodName = MyPsiXmlUtils.findTagForMethodName(theXmlFile, method.getName());
				if (tagForMethodName != null)
					result.add(NavigationGutterIconBuilder.create(IconUtils.useMyBatisIcon()).setAlignment(com.intellij.openapi.editor.markup.GutterIconRenderer.Alignment.CENTER).setTarget(tagForMethodName).setTooltipTitle("navigation to mapper xml").createLineMarkerInfo(method.getNameIdentifier()));
			}

		}
	}

	private XmlFile handleWithFileNotFound(PsiClass theClass, Project project, final String qualifiedName, Collection result)
	{
		PsiSearchHelper searchService = (PsiSearchHelper)ServiceManager.getService(project, com/intellij/psi/search/PsiSearchHelper);
		final List xmlFiles = new ArrayList();
		Module moduleForPsiElement = ModuleUtilCore.findModuleForPsiElement(theClass);
		if (moduleForPsiElement == null)
			return null;
		searchService.processUsagesInNonJavaFiles("mapper", new PsiNonJavaFileReferenceProcessor() {

			final String val$qualifiedName;
			final List val$xmlFiles;
			final NewMybatisJavaToXmlMarkerProvider this$0;

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
				this.this$0 = NewMybatisJavaToXmlMarkerProvider.this;
				qualifiedName = s;
				xmlFiles = list;
				super();
			}
		}, GlobalSearchScope.moduleScope(moduleForPsiElement));
		if (xmlFiles.size() == 0)
			return null;
		else
			return (XmlFile)xmlFiles.get(0);
	}

	private static void $$$reportNull$$$0(int i)
	{
		String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", new Object[] {
			"element", "com/ccnode/codegenerator/view/NewMybatisJavaToXmlMarkerProvider", "collectNavigationMarkers"
		});
		JVM INSTR new #271 <Class IllegalArgumentException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalArgumentException();
		throw ;
	}
}
