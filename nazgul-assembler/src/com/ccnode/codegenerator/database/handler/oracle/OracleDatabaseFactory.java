// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   OracleDatabaseFactory.java

package com.ccnode.codegenerator.database.handler.oracle;

import com.ccnode.codegenerator.database.handler.*;

// Referenced classes of package com.ccnode.codegenerator.database.handler.oracle:
//			OracleGenerateFilesHandler, OracleGenerateMethodXmlHandler, OracleUpdateFiledHandler, OracleAutoComplateHandler

public class OracleDatabaseFactory
	implements DatabaseFactory
{

	public OracleDatabaseFactory()
	{
	}

	public GenerateFileHandler getGenerateFileHandler()
	{
		return OracleGenerateFilesHandler.getInstance();
	}

	public GenerateMethodXmlHandler getMethodXmlHandler()
	{
		return OracleGenerateMethodXmlHandler.getInstance();
	}

	public UpdateFieldHandler getUpdateFieldHandler()
	{
		return OracleUpdateFiledHandler.getInstance();
	}

	public AutoCompleteHandler getAutoCompleteHandler()
	{
		return OracleAutoComplateHandler.getInstance();
	}
}
