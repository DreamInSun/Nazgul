// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenMethodResult.java

package com.ccnode.codegenerator.genmethodxml;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

public class GenMethodResult
{

	private PsiElement cursorElement;
	private boolean hasCursor;
	private PsiFile cursorFile;

	public GenMethodResult()
	{
	}

	public boolean isHasCursor()
	{
		return hasCursor;
	}

	public void setHasCursor(boolean hasCursor)
	{
		this.hasCursor = hasCursor;
	}

	public PsiFile getCursorFile()
	{
		return cursorFile;
	}

	public void setCursorFile(PsiFile cursorFile)
	{
		this.cursorFile = cursorFile;
	}

	public PsiElement getCursorElement()
	{
		return cursorElement;
	}

	public void setCursorElement(PsiElement cursorElement)
	{
		this.cursorElement = cursorElement;
	}
}
