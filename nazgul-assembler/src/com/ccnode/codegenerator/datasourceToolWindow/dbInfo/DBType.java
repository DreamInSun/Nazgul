// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DBType.java

package com.ccnode.codegenerator.datasourceToolWindow.dbInfo;

import com.google.common.collect.Lists;
import java.util.List;

public final class DBType extends Enum
{

	public static final DBType Oracle;
	public static final DBType MySql;
	public static final DBType SqlServer;
	public static final DBType PostgreSQL;
	public static final DBType Sqlite;
	private final String driverClass;
	private final String connectionUrlPattern;
	private final String connectorJarFile;
	private static final DBType $VALUES[];

	public static DBType[] values()
	{
		return (DBType[])$VALUES.clone();
	}

	public static DBType valueOf(String name)
	{
		return (DBType)Enum.valueOf(com/ccnode/codegenerator/datasourceToolWindow/dbInfo/DBType, name);
	}

	private DBType(String s, int i, String driverClass, String connectionUrlPattern, String connectorJarFile)
	{
		super(s, i);
		this.driverClass = driverClass;
		this.connectionUrlPattern = connectionUrlPattern;
		this.connectorJarFile = connectorJarFile;
	}

	public String getDriverClass()
	{
		return driverClass;
	}

	public String getConnectionUrlPattern()
	{
		return connectionUrlPattern;
	}

	public String getConnectorJarFile()
	{
		return connectorJarFile;
	}

	public static List getAllDatabaseSources()
	{
		List dbs = Lists.newArrayList();
		DBType values[] = values();
		DBType adbtype[] = values;
		int i = adbtype.length;
		for (int j = 0; j < i; j++)
		{
			DBType value = adbtype[j];
			dbs.add(value.name());
		}

		return dbs;
	}

	static 
	{
		Oracle = new DBType("Oracle", 0, "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@%s:%s:%s", "oracleJDBC.jar");
		MySql = new DBType("MySql", 1, "com.mysql.jdbc.Driver", "jdbc:mysql://%s:%s/%s?useUnicode=true&useSSL=false&characterEncoding=%s", "mysqlJDBC.jar");
		SqlServer = new DBType("SqlServer", 2, "com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://%s:%s;databaseName=%s", "sqlserverJDBC.jar");
		PostgreSQL = new DBType("PostgreSQL", 3, "org.postgresql.Driver", "jdbc:postgresql://%s:%s/%s", "postgresqlJDBC.jar");
		Sqlite = new DBType("Sqlite", 4, "org.postgresql.Driver", "jdbc:sqlite://%s", "sqliteJDBC.jar");
		$VALUES = (new DBType[] {
			Oracle, MySql, SqlServer, PostgreSQL, Sqlite
		});
	}
}
