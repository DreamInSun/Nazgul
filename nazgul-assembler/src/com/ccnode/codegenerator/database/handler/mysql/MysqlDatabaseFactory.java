// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MysqlDatabaseFactory.java

package com.ccnode.codegenerator.database.handler.mysql;

import com.ccnode.codegenerator.database.handler.*;

// Referenced classes of package com.ccnode.codegenerator.database.handler.mysql:
//			MysqlGenerateFileHandler, MysqlGenerateMethodXmlHandler, MysqlUpdateFieldHandler, MysqlAutoCompleteHandler

public class MysqlDatabaseFactory
	implements DatabaseFactory
{

	public MysqlDatabaseFactory()
	{
	}

	public GenerateFileHandler getGenerateFileHandler()
	{
		return MysqlGenerateFileHandler.getInstance();
	}

	public GenerateMethodXmlHandler getMethodXmlHandler()
	{
		return MysqlGenerateMethodXmlHandler.getInstance();
	}

	public UpdateFieldHandler getUpdateFieldHandler()
	{
		return MysqlUpdateFieldHandler.getInstance();
	}

	public AutoCompleteHandler getAutoCompleteHandler()
	{
		return MysqlAutoCompleteHandler.getInstance();
	}
}
