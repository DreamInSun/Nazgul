// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MysqlCompleteCacheInteface.java

package com.ccnode.codegenerator.view.completion;

import com.ccnode.codegenerator.datasourceToolWindow.NewDatabaseInfo;
import java.util.List;

public interface MysqlCompleteCacheInteface
{

	public abstract void addDatabaseCache(NewDatabaseInfo newdatabaseinfo);

	public abstract List getRecommendFromCache(String s, String s1);

	public abstract List getAllTables();

	public abstract List getAllFields();

	public abstract List getAllFieldsWithTable();

	public abstract List getTableAllFields(String s);

	public abstract String getFieldType();

	public abstract void cleanAll();
}
