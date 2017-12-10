// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MyBatisMethodNotFoundFix.java

package com.ccnode.codegenerator.view.inspection;

import com.ccnode.codegenerator.constants.MyBatisXmlConstants;
import com.ccnode.codegenerator.util.*;
import com.intellij.codeInspection.*;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.xml.*;
import java.util.List;

public class MyBatisMethodNotFoundFix
	implements LocalQuickFix
{

	public MyBatisMethodNotFoundFix()
	{
	}

	public String getFamilyName()
	{
		"create mybatis xml";
		if ("create mybatis xml" == null)
			$$$reportNull$$$0(0);
		return;
	}

	public void applyFix(Project project, ProblemDescriptor descriptor)
	{
		if (project == null)
			$$$reportNull$$$0(1);
		if (descriptor == null)
			$$$reportNull$$$0(2);
		PsiElement psiElement = descriptor.getPsiElement();
		if (!(psiElement instanceof PsiMethod))
			return;
		PsiMethod method = (PsiMethod)psiElement;
		PsiClass currentClass = (PsiClass)PsiTreeUtil.getParentOfType(method, com/intellij/psi/PsiClass);
		String qualifiedName = currentClass.getQualifiedName();
		Module moduleForPsiElement = ModuleUtilCore.findModuleForPsiElement(method);
		List xmlFiles = PsiSearchUtils.searchMapperXml(project, moduleForPsiElement, qualifiedName);
		if (xmlFiles.size() != 1)
			return;
		XmlFile thexml = (XmlFile)xmlFiles.get(0);
		XmlTag rootTag = thexml.getRootTag();
		if (rootTag == null)
		{
			return;
		} else
		{
			rootTag.addSubTag(generateTag(rootTag, method), false);
			PsiDocumentManager manager = PsiDocumentManager.getInstance(project);
			com.intellij.openapi.editor.Document document = manager.getDocument(thexml);
			PsiDocumentUtils.commitAndSaveDocument(manager, document);
			XmlTag subTags[] = rootTag.getSubTags();
			XmlTag subTag = subTags[subTags.length - 1];
			OpenFileDescriptor fileDescriptor = new OpenFileDescriptor(project, thexml.getVirtualFile(), subTag.getValue().getTextRange().getStartOffset() + 1);
			FileEditorManager.getInstance(project).openTextEditor(fileDescriptor, true);
			return;
		}
	}

	private XmlTag generateTag(XmlTag rootTag, PsiMethod method)
	{
		XmlTag childTag = rootTag.createChildTag("select", "", "\n\n", false);
		childTag.setAttribute("id", method.getName());
		String value = PsiClassUtil.extractRealType(method.getReturnType());
		if (value != null)
			childTag.setAttribute("resultType", value);
		return childTag;
	}

	public volatile void applyFix(Project project, CommonProblemDescriptor commonproblemdescriptor)
	{
		applyFix(project, (ProblemDescriptor)commonproblemdescriptor);
	}

	private static void $$$reportNull$$$0(int i)
	{
		String s;
		switch (i)
		{
		case 0: // '\0'
		default:
			s = "@NotNull method %s.%s must not return null";
			break;

		case 1: // '\001'
		case 2: // '\002'
			s = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
			break;
		}
		int j;
		s;
		switch (i)
		{
		case 0: // '\0'
		default:
			j = 2;
			break;

		case 1: // '\001'
		case 2: // '\002'
			j = 3;
			break;
		}
		new Object[j];
		i;
		JVM INSTR tableswitch 0 2: default 104
	//	               0 104
	//	               1 112
	//	               2 120;
		   goto _L1 _L1 _L2 _L3
_L1:
		JVM INSTR dup ;
		0;
		"com/ccnode/codegenerator/view/inspection/MyBatisMethodNotFoundFix";
		JVM INSTR aastore ;
		  goto _L4
_L2:
		JVM INSTR dup ;
		0;
		"project";
		JVM INSTR aastore ;
		  goto _L4
_L3:
		JVM INSTR dup ;
		0;
		"descriptor";
		JVM INSTR aastore ;
_L4:
		i;
		JVM INSTR tableswitch 0 2: default 156
	//	               0 156
	//	               1 164
	//	               2 164;
		   goto _L5 _L5 _L6 _L6
_L5:
		JVM INSTR dup ;
		1;
		"getFamilyName";
		JVM INSTR aastore ;
		  goto _L7
_L6:
		JVM INSTR dup ;
		1;
		"com/ccnode/codegenerator/view/inspection/MyBatisMethodNotFoundFix";
		JVM INSTR aastore ;
_L7:
		i;
		JVM INSTR tableswitch 0 2: default 200
	//	               0 200
	//	               1 203
	//	               2 203;
		   goto _L8 _L8 _L9 _L9
_L9:
		JVM INSTR dup ;
		2;
		"applyFix";
		JVM INSTR aastore ;
_L8:
		String.format();
		i;
		JVM INSTR tableswitch 0 2: default 240
	//	               0 240
	//	               1 251
	//	               2 251;
		   goto _L10 _L10 _L11 _L11
_L10:
		JVM INSTR new #241 <Class IllegalStateException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalStateException();
		  goto _L12
_L11:
		JVM INSTR new #246 <Class IllegalArgumentException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalArgumentException();
_L12:
		throw ;
	}
}
