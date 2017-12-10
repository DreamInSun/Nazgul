// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenCodeDialogUtil.java

package com.ccnode.codegenerator.dialog;

import com.ccnode.codegenerator.database.DatabaseComponenent;
import com.ccnode.codegenerator.database.handler.DatabaseFactory;
import com.ccnode.codegenerator.database.handler.GenerateFileHandler;
import com.ccnode.codegenerator.dialog.datatype.ClassFieldInfo;
import java.util.*;

public class GenCodeDialogUtil
{

	public GenCodeDialogUtil()
	{
	}

	static Map extractMap(List propFields)
	{
		Map fieldTypeMap = new HashMap();
		ClassFieldInfo info;
		for (Iterator iterator = propFields.iterator(); iterator.hasNext(); fieldTypeMap.put(info.getFieldName(), DatabaseComponenent.currentHandler().getGenerateFileHandler().getRecommendDatabaseTypeOfFieldType(info.getPsiField())))
			info = (ClassFieldInfo)iterator.next();

		return fieldTypeMap;
	}
}
