// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   XmlParser.java

package com.ccnode.codegenerator.showxmlsql;

import java.util.Map;

// Referenced classes of package com.ccnode.codegenerator.showxmlsql:
//			XmlParseResult, XmlContext

public class XmlParser
{

	public XmlParser()
	{
	}

	public static XmlParseResult parseXmlWithContext(String text, Map myParamMap, XmlContext context)
	{
		XmlParseResult xmlParseResult;
		xmlParseResult = new XmlParseResult();
		StringBuilder sql = new StringBuilder();
		xmlParseResult;
		if (xmlParseResult == null)
			$$$reportNull$$$0(0);
		return;
	}

	private static void $$$reportNull$$$0(int i)
	{
		String.format("@NotNull method %s.%s must not return null", new Object[] {
			"com/ccnode/codegenerator/showxmlsql/XmlParser", "parseXmlWithContext"
		});
		JVM INSTR new #59  <Class IllegalStateException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalStateException();
		throw ;
	}
}
