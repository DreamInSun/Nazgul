// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SqliteAutoCompleteHandler.java

package com.ccnode.codegenerator.database.handler.sqlite;

import com.ccnode.codegenerator.database.handler.AutoCompleteHandler;
import com.ccnode.codegenerator.util.PsiClassUtil;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;

// Referenced classes of package com.ccnode.codegenerator.database.handler.sqlite:
//			SqliteHandlerUtils

public class SqliteAutoCompleteHandler
	implements AutoCompleteHandler
{

	private static volatile SqliteAutoCompleteHandler mInstance;

	private SqliteAutoCompleteHandler()
	{
	}

	public static SqliteAutoCompleteHandler getInstance()
	{
		if (mInstance == null)
			synchronized (com/ccnode/codegenerator/database/handler/sqlite/SqliteAutoCompleteHandler)
			{
				if (mInstance == null)
					mInstance = new SqliteAutoCompleteHandler();
			}
		return mInstance;
	}

	public boolean isSupportedParam(PsiParameter psiParameter)
	{
		return SqliteHandlerUtils.getTypePropByQulitifiedName(PsiClassUtil.convertToObjectText(psiParameter.getType().getCanonicalText())) != null;
	}
}
