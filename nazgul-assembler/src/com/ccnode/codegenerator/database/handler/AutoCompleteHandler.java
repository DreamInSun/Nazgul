// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AutoCompleteHandler.java

package com.ccnode.codegenerator.database.handler;

import com.intellij.psi.PsiParameter;

public interface AutoCompleteHandler
{

	public abstract boolean isSupportedParam(PsiParameter psiparameter);
}
