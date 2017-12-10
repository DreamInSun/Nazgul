// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   JdbcTypeUtils.java

package com.ccnode.codegenerator.database.handler.utils;

import com.ccnode.codegenerator.database.handler.mysql.MysqlHandlerUtils;
import com.ccnode.codegenerator.database.handler.mysql.UnsignedCheckResult;
import java.util.HashMap;
import java.util.Map;

public class JdbcTypeUtils
{

	private static Map fieldToJdbcType = new HashMap() {

			
			{
				put("INT", "INTEGER");
				put("TINYINT", "TINYINT");
				put("SMALLINT", "SMALLINT");
				put("MEDIUMINT", "INTEGER");
				put("BIGINT", "BIGINT");
				put("BIT", "BIT");
				put("FLOAT", "REAL");
				put("DOUBLE", "DOUBLE");
				put("DECIMAL", "DECIMAL");
				put("DATE", "DATE");
				put("DATETIME", "DATE");
				put("TIMESTAMP", "TIMESTAMP");
				put("TIME", "TIME");
				put("CHAR", "CHAR");
				put("VARCHAR", "VARCHAR");
				put("BLOB", "VARBINARY");
				put("TINYBLOB", "VARBINARY");
				put("MEDIUMBLOB", "VARBINARY");
				put("LONGBLOB", "LONGVARCHAR");
				put("TEXT", "VARCHAR");
				put("MEDIUMTEXT", "LONGVARCHAR");
				put("LONGTEXT", "LONGVARCHAR");
				put("TINYTEXT", "VARCHAR");
				put("NUMBER", "NUMERIC");
				put("VARCHAR2", "VARCHAR");
				put("REAL", "REAL");
				put("NUMERIC", "NUMERIC");
			}
	};

	public JdbcTypeUtils()
	{
	}

	public static String extractFromDbType(String filedType)
	{
		UnsignedCheckResult unsignedCheckResult = MysqlHandlerUtils.checkUnsigned(filedType);
		String type = unsignedCheckResult.getType();
		return (String)fieldToJdbcType.get(type);
	}

}
