// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DatabaseComponenent.java

package com.ccnode.codegenerator.database;

import com.ccnode.codegenerator.database.handler.DatabaseFactory;
import com.ccnode.codegenerator.database.handler.mysql.MysqlDatabaseFactory;
import com.ccnode.codegenerator.database.handler.oracle.OracleDatabaseFactory;
import com.ccnode.codegenerator.database.handler.sqlite.SqliteDatabaseFactory;
import com.ccnode.codegenerator.myconfigurable.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseComponenent
{

	private static Map databaseHandlerMap = new HashMap() {

			
			{
				put("MySql", new MysqlDatabaseFactory());
				put("Oracle", new OracleDatabaseFactory());
				put("Sqlite", new SqliteDatabaseFactory());
			}
	};
	private static MyBatisCodeHelperApplicationComponent myBatisCodeHelperApplicationComponent = MyBatisCodeHelperApplicationComponent.getInstance();

	public DatabaseComponenent()
	{
	}

	public static DatabaseFactory currentHandler()
	{
		DatabaseFactory databasefactory;
		String database = myBatisCodeHelperApplicationComponent.getState().getDefaultProfile().getDatabase();
		databasefactory = (DatabaseFactory)databaseHandlerMap.get(database);
		databasefactory;
		if (databasefactory == null)
			$$$reportNull$$$0(0);
		return;

	}

	public static String currentDatabase()
	{
		return myBatisCodeHelperApplicationComponent.getState().getDefaultProfile().getDatabase();
	}

	public static boolean shouldAddIfTest()
	{
		return myBatisCodeHelperApplicationComponent.getState().getDefaultProfile().getGenerateWithIfTest().booleanValue();
	}

	public static Profile getProfile()
	{
		return myBatisCodeHelperApplicationComponent.getState().getDefaultProfile();
	}

	public static String formatColumn(String column)
	{
		if (currentDatabase().equals("MySql"))
		{
			if (myBatisCodeHelperApplicationComponent.getState().getDefaultProfile().getMysqlUseWithDash().booleanValue())
				return (new StringBuilder()).append("`").append(column).append("`").toString();
			else
				return column;
		} else
		{
			return column;
		}
	}

	private static void $$$reportNull$$$0(int i)
	{
		String.format("@NotNull method %s.%s must not return null", new Object[] {
			"com/ccnode/codegenerator/database/DatabaseComponenent", "currentHandler"
		});
		JVM INSTR new #124 <Class IllegalStateException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalStateException();
		throw ;
	}

}
