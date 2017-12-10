// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   QueryBuilderHandler.java

package com.ccnode.codegenerator.database.handler;

import com.ccnode.codegenerator.methodnameparser.buidler.MethodNameParsedResult;
import com.ccnode.codegenerator.methodnameparser.buidler.QueryInfo;

public interface QueryBuilderHandler
{

	public abstract void handleFindBeforeFromTable(QueryInfo queryinfo, MethodNameParsedResult methodnameparsedresult, boolean flag);

	public abstract void handleFindAfterFromTable(QueryInfo queryinfo, MethodNameParsedResult methodnameparsedresult);

	public abstract void handleUpdate(QueryInfo queryinfo, MethodNameParsedResult methodnameparsedresult);

	public abstract void handleDelete(QueryInfo queryinfo, MethodNameParsedResult methodnameparsedresult);

	public abstract void handleCount(QueryInfo queryinfo, MethodNameParsedResult methodnameparsedresult);
}
