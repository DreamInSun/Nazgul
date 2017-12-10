// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PsiElementUtil.java

package com.ccnode.codegenerator.util;

import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;

public class PsiElementUtil
{

	public PsiElementUtil()
	{
	}

	public static PsiClass getContainingClass(PsiElement psiElement)
	{
		PsiClass psiClass = (PsiClass)PsiTreeUtil.getParentOfType(psiElement, PsiClass.class, false);
		if (psiClass == null)
		{
			com.intellij.psi.PsiFile containingFile = psiElement.getContainingFile();
			if (containingFile instanceof PsiClassOwner)
			{
				PsiClass classes[] = ((PsiClassOwner)containingFile).getClasses();
				if (classes.length == 1)
					return classes[0];
			}
		}
		return psiClass;
	}
}
