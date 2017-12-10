// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   OracleAutoComplateHandler.java

package com.ccnode.codegenerator.database.handler.oracle;

import com.ccnode.codegenerator.database.handler.AutoCompleteHandler;
import com.ccnode.codegenerator.util.PsiClassUtil;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;

// Referenced classes of package com.ccnode.codegenerator.database.handler.oracle:
//			OracleHandlerUtils

public class OracleAutoComplateHandler
	implements AutoCompleteHandler
{

	private static volatile OracleAutoComplateHandler mInstance;

	private OracleAutoComplateHandler()
	{
	}

	public static OracleAutoComplateHandler getInstance()
	{
		if (mInstance == null)
			synchronized (com/ccnode/codegenerator/database/handler/oracle/OracleAutoComplateHandler)
			{
				if (mInstance == null)
					mInstance = new OracleAutoComplateHandler();
			}
		return mInstance;
	}

	public boolean isSupportedParam(PsiParameter psiParameter)
	{
		return OracleHandlerUtils.getTypePropByQulitifiedName(PsiClassUtil.convertToObjectText(psiParameter.getType().getCanonicalText())) != null;
	}
}
