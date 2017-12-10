// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RenameResultMapSqlProcessor.java

package com.ccnode.codegenerator.view.rename;

import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.refactoring.listeners.RefactoringElementListener;
import com.intellij.refactoring.rename.RenamePsiElementProcessor;
import com.intellij.usageView.UsageInfo;
import com.intellij.util.IncorrectOperationException;
import java.io.PrintStream;

public class RenameResultMapSqlProcessor extends RenamePsiElementProcessor
{

	public RenameResultMapSqlProcessor()
	{
	}

	public boolean canProcessElement(PsiElement element)
	{
		if (element == null)
			$$$reportNull$$$0(0);
		return element instanceof XmlAttributeValue;
	}

	public void renameElement(PsiElement element, String newName, UsageInfo usages[], RefactoringElementListener listener)
		throws IncorrectOperationException
	{
		System.out.println("hello");
	}

	private static void $$$reportNull$$$0(int i)
	{
		String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", new Object[] {
			"element", "com/ccnode/codegenerator/view/rename/RenameResultMapSqlProcessor", "canProcessElement"
		});
		JVM INSTR new #69  <Class IllegalArgumentException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalArgumentException();
		throw ;
	}
}
