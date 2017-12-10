// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DatasourceState.java

package com.ccnode.codegenerator.datasourceToolWindow;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.ccnode.codegenerator.datasourceToolWindow:
//			NewDatabaseInfo

public class DatasourceState
{

	private List databaseInfos;
	private NewDatabaseInfo activeDatabaseInfo;

	public DatasourceState()
	{
		databaseInfos = new ArrayList();
	}

	public List getDatabaseInfos()
	{
		return databaseInfos;
	}

	public void setDatabaseInfos(List databaseInfos)
	{
		this.databaseInfos = databaseInfos;
	}

	public NewDatabaseInfo getActiveDatabaseInfo()
	{
		return activeDatabaseInfo;
	}

	public void setActiveDatabaseInfo(NewDatabaseInfo activeDatabaseInfo)
	{
		this.activeDatabaseInfo = activeDatabaseInfo;
	}
}
