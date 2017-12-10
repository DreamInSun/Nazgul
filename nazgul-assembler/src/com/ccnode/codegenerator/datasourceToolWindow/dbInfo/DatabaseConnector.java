// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DatabaseConnector.java

package com.ccnode.codegenerator.datasourceToolWindow.dbInfo;

import com.ccnode.codegenerator.datasourceToolWindow.NewDatabaseInfo;
import com.ccnode.codegenerator.myconfigurable.DataBaseConstants;
import com.google.common.collect.Lists;
import com.intellij.notification.*;
import java.io.PrintStream;
import java.sql.*;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.ccnode.codegenerator.datasourceToolWindow.dbInfo:
//			DatabaseInfo, TableInfo, TableColumnInfo, DatasourceConnectionProperty, 
//			DBType

public class DatabaseConnector
{

	public DatabaseConnector()
	{
	}

	public static DatabaseInfo getDataBaseInfoFromConnection(DatasourceConnectionProperty datasourceConnectionProperty)
	{
		Connection conn;
		Statement stmt;
		DatabaseInfo databaseInfo;
		conn = null;
		stmt = null;
		databaseInfo = null;
		conn = getConnection(datasourceConnectionProperty);
		DatabaseMetaData metaData = conn.getMetaData();
		String schemaName = datasourceConnectionProperty.getDatabase();
		databaseInfo = new DatabaseInfo();
		databaseInfo.setDatabaseName(schemaName);
		List tableInfos = Lists.newArrayList();
		ResultSet tableTypes = metaData.getTableTypes();
		List tableTypeList = Lists.newArrayList();
		for (; tableTypes.next(); tableTypeList.add(tableTypes.getString(1)));
		ResultSet tables = metaData.getTables(null, schemaName, "%", null);
		List tableList = Lists.newArrayList();
		for (; tables.next(); tableList.add(tables.getString(3)));
		TableInfo info1;
		for (Iterator iterator = tableList.iterator(); iterator.hasNext(); tableInfos.add(info1))
		{
			String table = (String)iterator.next();
			info1 = new TableInfo();
			info1.setTableName(table);
			List tableColumnInfos = Lists.newArrayList();
			TableColumnInfo columnInfo;
			for (ResultSet columns = metaData.getColumns(null, schemaName, table, "%"); columns.next(); tableColumnInfos.add(columnInfo))
			{
				String columnName = columns.getString(4);
				columnInfo = new TableColumnInfo();
				String columnType = columns.getString(6);
				columnInfo.setFieldName(columnName);
				columnInfo.setFieldType(columnType);
			}

			info1.setTableColumnInfos(tableColumnInfos);
		}

		databaseInfo.setTableInfoList(tableInfos);
		System.out.println("Creating statement...");
		conn.close();
		SQLException se;
		try
		{
			if (stmt != null)
				stmt.close();
		}
		catch (SQLException sqlexception) { }
		try
		{
			if (conn != null)
				conn.close();
		}
		// Misplaced declaration of an exception variable
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		break MISSING_BLOCK_LABEL_607;
		se;
		com.intellij.notification.Notifications.Bus.notify(new Notification("mybatisDb", "can't connect to db", (new StringBuilder()).append("connect to ").append(datasourceConnectionProperty.getUrl()).append(" with userName ").append(datasourceConnectionProperty.getUserName()).append(" with password").append(datasourceConnectionProperty.getPassword()).append(" fail").toString(), NotificationType.ERROR, null));
		try
		{
			if (stmt != null)
				stmt.close();
		}
		// Misplaced declaration of an exception variable
		catch (SQLException se) { }
		try
		{
			if (conn != null)
				conn.close();
		}
		// Misplaced declaration of an exception variable
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		break MISSING_BLOCK_LABEL_607;
		Exception e;
		e;
		com.intellij.notification.Notifications.Bus.notify(new Notification("mybatisDb", "can't connect to db", (new StringBuilder()).append("connect to ").append(datasourceConnectionProperty.getUrl()).append(" with userName ").append(datasourceConnectionProperty.getUserName()).append(" with password").append(datasourceConnectionProperty.getPassword()).append(" fail").toString(), NotificationType.ERROR, null));
		try
		{
			if (stmt != null)
				stmt.close();
		}
		// Misplaced declaration of an exception variable
		catch (Exception e) { }
		try
		{
			if (conn != null)
				conn.close();
		}
		// Misplaced declaration of an exception variable
		catch (Exception e)
		{
			e.printStackTrace();
		}
		break MISSING_BLOCK_LABEL_607;
		Exception exception;
		exception;
		try
		{
			if (stmt != null)
				stmt.close();
		}
		catch (SQLException sqlexception1) { }
		try
		{
			if (conn != null)
				conn.close();
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		throw exception;
		return databaseInfo;
	}

	private static Connection getConnection(DatasourceConnectionProperty datasourceConnectionProperty)
		throws ClassNotFoundException, SQLException
	{
		DriverManager.setLoginTimeout(1);
		String className = buildDriverClassNameByDatabaseType(datasourceConnectionProperty.getDatabaseType());
		Class.forName(className);
		String realUrl = buildUrl(datasourceConnectionProperty.getDatabaseType(), datasourceConnectionProperty.getUrl(), datasourceConnectionProperty.getDatabase());
		return DriverManager.getConnection(realUrl, datasourceConnectionProperty.getUserName(), datasourceConnectionProperty.getPassword());
	}

	private static String buildDriverClassNameByDatabaseType(String databaseType)
	{
		DBType dbType = DBType.valueOf(databaseType);
		return dbType.getDriverClass();
	}

	public static boolean checkConnection(String databaseType, String url, String userName, String password, String database)
	{
		String realUrl;
		Connection conn;
		realUrl = buildUrl(databaseType, url, database);
		conn = null;
		Statement stmt = null;
		DatabaseInfo databaseInfo = null;
		Class.forName(buildDriverClassNameByDatabaseType(databaseType));
		System.out.println("Connecting to databaseType...");
		conn = DriverManager.getConnection(realUrl, userName, password);
		Exception e;
		if (conn != null)
			try
			{
				conn.close();
			}
			// Misplaced declaration of an exception variable
			catch (Exception e)
			{
				e.printStackTrace();
			}
		break MISSING_BLOCK_LABEL_125;
		e;
		boolean flag = false;
		if (conn != null)
			try
			{
				conn.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		return flag;
		Exception exception;
		exception;
		if (conn != null)
			try
			{
				conn.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		throw exception;
		return true;
	}

	public static String buildUrl(String databaseType, String url, String database)
	{
		if (databaseType.equals("MySql"))
			return (new StringBuilder()).append("jdbc:mysql://").append(url).append("/").append(database).toString();
		else
			return (new StringBuilder()).append("jdbc:mysql://").append(url).append("/").append(database).toString();
	}

	public static List getTableColumnInfo(NewDatabaseInfo info, String tableName)
	{
		Connection conn;
		Statement stmt;
		List tableColumnInfos;
		conn = null;
		stmt = null;
		DatabaseInfo databaseInfo = null;
		tableColumnInfos = Lists.newArrayList();
		DatabaseMetaData metaData;
		String schemaName;
		List tableList;
		java.util.ArrayList arraylist;
		Class.forName(buildDriverClassNameByDatabaseType(info.getDatabaseType()));
		System.out.println("Connecting to databaseType...");
		String realUrl = buildUrl(info.getDatabaseType(), info.getUrl(), info.getDatabase());
		conn = DriverManager.getConnection(realUrl, info.getUserName(), info.getPassword());
		metaData = conn.getMetaData();
		schemaName = info.getDatabase();
		DatabaseInfo databaseInfo = new DatabaseInfo();
		databaseInfo.setDatabaseName(schemaName);
		List tableInfos = Lists.newArrayList();
		ResultSet tableTypes = metaData.getTableTypes();
		List tableTypeList = Lists.newArrayList();
		for (; tableTypes.next(); tableTypeList.add(tableTypes.getString(1)));
		ResultSet tables = metaData.getTables(null, schemaName, tableName, null);
		tableList = Lists.newArrayList();
		for (; tables.next(); tableList.add(tables.getString(3)));
		if (tableList.size() == 1)
			break MISSING_BLOCK_LABEL_250;
		System.out.println("table is not only one");
		arraylist = Lists.newArrayList();
		try
		{
			if (stmt != null)
				stmt.close();
		}
		catch (SQLException sqlexception) { }
		try
		{
			if (conn != null)
				conn.close();
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		return arraylist;
		List list;
		String table = (String)tableList.get(0);
		TableInfo info1 = new TableInfo();
		info1.setTableName(table);
		TableColumnInfo columnInfo;
		for (ResultSet columns = metaData.getColumns(null, schemaName, table, "%"); columns.next(); tableColumnInfos.add(columnInfo))
		{
			String columnName = columns.getString(4);
			columnInfo = new TableColumnInfo();
			String columnType = columns.getString(6);
			columnInfo.setFieldName(columnName);
			columnInfo.setFieldType(columnType);
		}

		conn.close();
		list = tableColumnInfos;
		try
		{
			if (stmt != null)
				stmt.close();
		}
		catch (SQLException sqlexception1) { }
		try
		{
			if (conn != null)
				conn.close();
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		return list;
		SQLException se;
		se;
		com.intellij.notification.Notifications.Bus.notify(new Notification("mybatisDb", "can't connect to db", (new StringBuilder()).append("connect to ").append(info.getUrl()).append(" with userName ").append(info.getUserName()).append(" with password").append(info.getPassword()).append(" fail").toString(), NotificationType.ERROR, null));
		try
		{
			if (stmt != null)
				stmt.close();
		}
		// Misplaced declaration of an exception variable
		catch (SQLException se) { }
		try
		{
			if (conn != null)
				conn.close();
		}
		// Misplaced declaration of an exception variable
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		break MISSING_BLOCK_LABEL_668;
		Exception e;
		e;
		com.intellij.notification.Notifications.Bus.notify(new Notification("mybatisDb", "can't connect to db", (new StringBuilder()).append("connect to ").append(info.getUrl()).append(" with userName ").append(info.getUserName()).append(" with password").append(info.getPassword()).append(" fail").toString(), NotificationType.ERROR, null));
		try
		{
			if (stmt != null)
				stmt.close();
		}
		// Misplaced declaration of an exception variable
		catch (Exception e) { }
		try
		{
			if (conn != null)
				conn.close();
		}
		// Misplaced declaration of an exception variable
		catch (Exception e)
		{
			e.printStackTrace();
		}
		break MISSING_BLOCK_LABEL_668;
		Exception exception;
		exception;
		try
		{
			if (stmt != null)
				stmt.close();
		}
		catch (SQLException sqlexception2) { }
		try
		{
			if (conn != null)
				conn.close();
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		throw exception;
		return tableColumnInfos;
	}
}
