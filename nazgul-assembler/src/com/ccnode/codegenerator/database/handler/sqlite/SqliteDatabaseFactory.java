// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SqliteDatabaseFactory.java

package com.ccnode.codegenerator.database.handler.sqlite;

import com.ccnode.codegenerator.database.handler.*;

// Referenced classes of package com.ccnode.codegenerator.database.handler.sqlite:
//			SqliteGenerateFilesHandler, SqliteGenerateMethodXmlHandler, SqliteUpdateFiledHandler, SqliteAutoCompleteHandler

public class SqliteDatabaseFactory
	implements DatabaseFactory
{

	public SqliteDatabaseFactory()
	{
	}

	public GenerateFileHandler getGenerateFileHandler()
	{
		return SqliteGenerateFilesHandler.getInstance();
	}

	public GenerateMethodXmlHandler getMethodXmlHandler()
	{
		return SqliteGenerateMethodXmlHandler.getInstance();
	}

	public UpdateFieldHandler getUpdateFieldHandler()
	{
		return SqliteUpdateFiledHandler.getInstance();
	}

	public AutoCompleteHandler getAutoCompleteHandler()
	{
		return SqliteAutoCompleteHandler.getInstance();
	}
}
