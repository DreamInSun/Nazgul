// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   OracleUpdateFiledHandler.java

package com.ccnode.codegenerator.database.handler.oracle;

import com.ccnode.codegenerator.database.handler.UpdateFieldHandler;
import com.intellij.psi.PsiField;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// Referenced classes of package com.ccnode.codegenerator.database.handler.oracle:
//			OracleHandlerUtils

public class OracleUpdateFiledHandler
	implements UpdateFieldHandler
{

	private static volatile OracleUpdateFiledHandler mInstance;

	private OracleUpdateFiledHandler()
	{
	}

	public static OracleUpdateFiledHandler getInstance()
	{
		if (mInstance == null)
			synchronized (OracleUpdateFiledHandler.class)
			{
				if (mInstance == null)
					mInstance = new OracleUpdateFiledHandler();
			}
		return mInstance;
	}

	public String generateUpdateSql(List newAddedProps, String tableName, List deletedFields)
	{
		return null;
	}

	@NotNull
	public List getRecommendDatabaseTypeOfFieldType(PsiField field)
	{
		List list = OracleHandlerUtils.getRecommendDatabaseTypeOfFieldType(field);
		return list;
	}


}
