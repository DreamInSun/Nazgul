// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MyBatisSqlCompleteCache.java

package com.ccnode.codegenerator.view.completion;

import com.ccnode.codegenerator.datasourceToolWindow.NewDatabaseInfo;
import com.ccnode.codegenerator.datasourceToolWindow.dbInfo.*;
import com.ccnode.codegenerator.sqlparse.TableNameAndFieldName;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import java.io.PrintStream;
import java.util.*;

// Referenced classes of package com.ccnode.codegenerator.view.completion:
//			CompleteField, MysqlCompleteCacheInteface

public class MyBatisSqlCompleteCache
	implements MysqlCompleteCacheInteface
{

	private List tables;
	private ArrayListMultimap tableToField;
	private Map fieldMap;

	public MyBatisSqlCompleteCache()
	{
		tables = new ArrayList();
		tableToField = ArrayListMultimap.create();
		fieldMap = new HashMap();
	}

	public void addDatabaseCache(NewDatabaseInfo newDatabaseInfo)
	{
		DatabaseInfo root = DatabaseConnector.getDataBaseInfoFromConnection(new DatasourceConnectionProperty(newDatabaseInfo.getDatabaseType(), newDatabaseInfo.getUrl(), newDatabaseInfo.getUserName(), newDatabaseInfo.getPassword(), newDatabaseInfo.getDatabase()));
		if (root == null)
		{
			System.out.println("can't connect to db");
			return;
		}
		List tableInfoList = root.getTableInfoList();
		if (tableInfoList == null || tableInfoList.size() == 0)
		{
			System.out.println("there is no table exist in db");
			return;
		}
		Iterator iterator = tableInfoList.iterator();
		do
		{
			if (!iterator.hasNext())
				break;
			TableInfo tableInfo = (TableInfo)iterator.next();
			tables.add(tableInfo.getTableName());
			List tableColumnInfos = tableInfo.getTableColumnInfos();
			if (tableColumnInfos != null && tableColumnInfos.size() != 0)
			{
				Iterator iterator1 = tableColumnInfos.iterator();
				while (iterator1.hasNext()) 
				{
					TableColumnInfo tableColumnInfo = (TableColumnInfo)iterator1.next();
					tableToField.put(tableInfo.getTableName(), tableColumnInfo.getFieldName());
					fieldMap.put(new CompleteField(newDatabaseInfo.getDatabaseType(), tableInfo.getTableName(), tableColumnInfo.getFieldName()), tableColumnInfo.getFieldType());
				}
			}
		} while (true);
	}

	public List getRecommendFromCache(String currentText, String allText)
	{
		return new ArrayList();
	}

	public List getAllTables()
	{
		return tables;
	}

	public List getAllFields()
	{
		List fields = Lists.newArrayList();
		java.util.Map.Entry map;
		for (Iterator iterator = tableToField.entries().iterator(); iterator.hasNext(); fields.add(map.getValue()))
			map = (java.util.Map.Entry)iterator.next();

		return fields;
	}

	public List getAllFieldsWithTable()
	{
		List fields = Lists.newArrayList();
		TableNameAndFieldName tableNameAndFieldName;
		for (Iterator iterator = tableToField.entries().iterator(); iterator.hasNext(); fields.add(tableNameAndFieldName))
		{
			java.util.Map.Entry map = (java.util.Map.Entry)iterator.next();
			tableNameAndFieldName = new TableNameAndFieldName();
			tableNameAndFieldName.setTableName((String)map.getKey());
			tableNameAndFieldName.setFieldName((String)map.getValue());
		}

		return fields;
	}

	public List getTableAllFields(String tableName)
	{
		List strings = tableToField.get(tableName);
		List tableNameAndFieldNames = Lists.newArrayList();
		TableNameAndFieldName tableNameAndFieldName;
		for (Iterator iterator = strings.iterator(); iterator.hasNext(); tableNameAndFieldNames.add(tableNameAndFieldName))
		{
			String string = (String)iterator.next();
			tableNameAndFieldName = new TableNameAndFieldName();
			tableNameAndFieldName.setTableName(tableName);
			tableNameAndFieldName.setFieldName(string);
		}

		return tableNameAndFieldNames;
	}

	public String getFieldType()
	{
		return null;
	}

	public void cleanAll()
	{
		fieldMap = new HashMap();
		tables = Lists.newArrayList();
		tableToField = ArrayListMultimap.create();
	}
}
