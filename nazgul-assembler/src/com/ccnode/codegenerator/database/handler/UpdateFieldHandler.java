// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   UpdateFieldHandler.java

package com.ccnode.codegenerator.database.handler;

import java.util.List;

// Referenced classes of package com.ccnode.codegenerator.database.handler:
//			JTableRecommendHandler

public interface UpdateFieldHandler
	extends JTableRecommendHandler
{

	public abstract String generateUpdateSql(List list, String s, List list1);
}
