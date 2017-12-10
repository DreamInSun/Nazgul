// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenSqlService.java

package com.ccnode.codegenerator.genCode;

import com.ccnode.codegenerator.database.DatabaseComponenent;
import com.ccnode.codegenerator.database.handler.DatabaseFactory;
import com.ccnode.codegenerator.database.handler.GenerateFileHandler;
import com.ccnode.codegenerator.dialog.GenCodeProp;
import com.ccnode.codegenerator.dialog.InsertFileProp;
import com.ccnode.codegenerator.util.FileUtils;
import com.google.common.collect.Lists;
import java.util.List;

public class GenSqlService
{

	public GenSqlService()
	{
	}

	public static void generateSqlFile(InsertFileProp prop, List propList, GenCodeProp primaryKey, String tableName, List multipleColumnIndex, List multipleColumnUnique)
	{
		String sql = DatabaseComponenent.currentHandler().getGenerateFileHandler().generateSql(propList, primaryKey, tableName, multipleColumnIndex, multipleColumnUnique);
		List retList = Lists.newArrayList(new String[] {
			sql
		});
		FileUtils.writeFiles(prop, retList);
	}
}
