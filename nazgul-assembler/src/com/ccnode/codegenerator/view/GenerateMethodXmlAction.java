// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenerateMethodXmlAction.java

package com.ccnode.codegenerator.view;

import com.ccnode.codegenerator.database.DatabaseComponenent;
import com.ccnode.codegenerator.genmethodxml.GenMethodResult;
import com.ccnode.codegenerator.genmethodxml.GenMethodXmlInvoker;
import com.ccnode.codegenerator.log.Log;
import com.ccnode.codegenerator.log.LogFactory;
import com.ccnode.codegenerator.util.*;
import com.ccnode.codegenerator.validate.PaidValidator;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.intellij.codeInsight.CodeInsightUtil;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class GenerateMethodXmlAction extends PsiElementBaseIntentionAction
{

	public static final String GENERATE_DAOXML = "generate daoxml";
	public static final String INSERT_INTO = "insert into";
	private static Log log = LogFactory.getLogger(com/ccnode/codegenerator/view/GenerateMethodXmlAction);
	static final boolean $assertionsDisabled = !com/ccnode/codegenerator/view/GenerateMethodXmlAction.desiredAssertionStatus();

	public GenerateMethodXmlAction()
	{
	}

	public void invoke(Project project, Editor editor, PsiElement element)
		throws IncorrectOperationException
	{
		if (project == null)
			$$$reportNull$$$0(0);
		if (element == null)
			$$$reportNull$$$0(1);
		boolean validate = PaidValidator.validate(project);
		if (!validate)
			return;
		Stopwatch started = Stopwatch.createStarted();
		PsiElement parent = element.getParent();
		TextRange textRange = null;
		List methodName = Lists.newArrayList();
		if (parent instanceof PsiMethod)
			return;
		if (parent instanceof PsiJavaCodeReferenceElement)
		{
			String text = parent.getText();
			methodName.add(text);
			textRange = parent.getTextRange();
		} else
		if (element instanceof PsiWhiteSpace)
		{
			PsiElement lastMatchedElement = findLastMatchedElement(element);
			String text = lastMatchedElement.getText();
			textRange = lastMatchedElement.getTextRange();
			methodName.add(text);
		}
		GenMethodResult result = (new GenMethodXmlInvoker(methodName, project, textRange, element)).invoke();
		if (result == null)
			return;
		if (result.isHasCursor())
			CodeInsightUtil.positionCursor(project, result.getCursorFile(), result.getCursorElement());
		StringBuilder methodNameBuilder = new StringBuilder();
		String s;
		for (Iterator iterator = methodName.iterator(); iterator.hasNext(); methodNameBuilder.append((new StringBuilder()).append(s).append(" ,").toString()))
			s = (String)iterator.next();

		methodNameBuilder.deleteCharAt(methodNameBuilder.length() - 1);
		long elapsed = started.elapsed(TimeUnit.MILLISECONDS);
		log.info((new StringBuilder()).append("generate dao xml use with time in mill second is:").append(elapsed).append(" and the method name is:").append(methodNameBuilder.toString()).append(" used database is:").append(DatabaseComponenent.currentDatabase()).toString());
	}

	public boolean isAvailable(Project project, Editor editor, PsiElement element)
	{
		if (project == null)
			$$$reportNull$$$0(2);
		if (element == null)
			$$$reportNull$$$0(3);
		if (!isAvailbleForElement(element))
			return false;
		PsiClass containingClass = PsiElementUtil.getContainingClass(element);
		if (!$assertionsDisabled && containingClass == null)
			throw new AssertionError();
		PsiElement leftBrace = containingClass.getLBrace();
		if (leftBrace == null)
			return false;
		if (element instanceof PsiMethod)
			return false;
		PsiElement parent = element.getParent();
		if (parent instanceof PsiMethod)
			return false;
		if (element instanceof PsiWhiteSpace)
		{
			PsiElement element1 = findLastMatchedElement(element);
			return element1 != null;
		}
		if (parent instanceof PsiJavaCodeReferenceElement)
		{
			PsiJavaCodeReferenceElement referenceElement = (PsiJavaCodeReferenceElement)parent;
			String text = referenceElement.getText().toLowerCase();
			if (MethodNameUtil.checkValidTextStarter(text).isValid())
				return true;
		}
		return false;
	}

	private PsiElement findLastMatchedElement(PsiElement element)
	{
		PsiElement prevSibling;
		for (prevSibling = element.getPrevSibling(); prevSibling != null && isIgnoreText(prevSibling.getText()); prevSibling = prevSibling.getPrevSibling());
		if (prevSibling != null)
		{
			String lowerCase = prevSibling.getText().toLowerCase();
			if (MethodNameUtil.checkValidTextStarter(lowerCase).isValid())
				return prevSibling;
		}
		return null;
	}

	private boolean isIgnoreText(String text)
	{
		return text.equals("") || text.equals("\n") || text.equals(" ");
	}

	public String getText()
	{
		"generate daoxml";
		if ("generate daoxml" == null)
			$$$reportNull$$$0(4);
		return;
	}

	public static boolean isAvailbleForElement(PsiElement psiElement)
	{
		if (psiElement == null)
			return false;
		PsiClass containingClass = PsiElementUtil.getContainingClass(psiElement);
		if (containingClass == null)
			return false;
		Module srcMoudle = ModuleUtilCore.findModuleForPsiElement(containingClass);
		if (srcMoudle == null)
			return false;
		return !containingClass.isAnnotationType() && !(containingClass instanceof PsiAnonymousClass) && containingClass.isInterface();
	}

	public String getFamilyName()
	{
		"generate daoxml";
		if ("generate daoxml" == null)
			$$$reportNull$$$0(5);
		return;
	}

	private static void $$$reportNull$$$0(int i)
	{
		String s;
		switch (i)
		{
		case 0: // '\0'
		case 1: // '\001'
		case 2: // '\002'
		case 3: // '\003'
		default:
			s = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
			break;

		case 4: // '\004'
		case 5: // '\005'
			s = "@NotNull method %s.%s must not return null";
			break;
		}
		int j;
		s;
		switch (i)
		{
		case 0: // '\0'
		case 1: // '\001'
		case 2: // '\002'
		case 3: // '\003'
		default:
			j = 3;
			break;

		case 4: // '\004'
		case 5: // '\005'
			j = 2;
			break;
		}
		new Object[j];
		i;
		JVM INSTR tableswitch 0 5: default 144
	//	               0 144
	//	               1 153
	//	               2 144
	//	               3 153
	//	               4 162
	//	               5 162;
		   goto _L1 _L1 _L2 _L1 _L2 _L3 _L3
_L1:
		JVM INSTR dup ;
		0;
		"project";
		JVM INSTR aastore ;
		  goto _L4
_L2:
		JVM INSTR dup ;
		0;
		"element";
		JVM INSTR aastore ;
		  goto _L4
_L3:
		JVM INSTR dup ;
		0;
		"com/ccnode/codegenerator/view/GenerateMethodXmlAction";
		JVM INSTR aastore ;
_L4:
		i;
		JVM INSTR tableswitch 0 5: default 212
	//	               0 212
	//	               1 212
	//	               2 212
	//	               3 212
	//	               4 221
	//	               5 230;
		   goto _L5 _L5 _L5 _L5 _L5 _L6 _L7
_L5:
		JVM INSTR dup ;
		1;
		"com/ccnode/codegenerator/view/GenerateMethodXmlAction";
		JVM INSTR aastore ;
		  goto _L8
_L6:
		JVM INSTR dup ;
		1;
		"getText";
		JVM INSTR aastore ;
		  goto _L8
_L7:
		JVM INSTR dup ;
		1;
		"getFamilyName";
		JVM INSTR aastore ;
_L8:
		i;
		JVM INSTR tableswitch 0 5: default 280
	//	               0 280
	//	               1 280
	//	               2 289
	//	               3 289
	//	               4 298
	//	               5 298;
		   goto _L9 _L9 _L9 _L10 _L10 _L11 _L11
_L9:
		JVM INSTR dup ;
		2;
		"invoke";
		JVM INSTR aastore ;
		  goto _L11
_L10:
		JVM INSTR dup ;
		2;
		"isAvailable";
		JVM INSTR aastore ;
_L11:
		String.format();
		i;
		JVM INSTR tableswitch 0 5: default 344
	//	               0 344
	//	               1 344
	//	               2 344
	//	               3 344
	//	               4 355
	//	               5 355;
		   goto _L12 _L12 _L12 _L12 _L12 _L13 _L13
_L12:
		JVM INSTR new #334 <Class IllegalArgumentException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalArgumentException();
		  goto _L14
_L13:
		JVM INSTR new #338 <Class IllegalStateException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalStateException();
_L14:
		throw ;
	}

}
