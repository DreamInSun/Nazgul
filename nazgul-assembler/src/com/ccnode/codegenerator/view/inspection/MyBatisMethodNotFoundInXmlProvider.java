// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MyBatisMethodNotFoundInXmlProvider.java

package com.ccnode.codegenerator.view.inspection;

import com.intellij.codeInspection.InspectionToolProvider;

// Referenced classes of package com.ccnode.codegenerator.view.inspection:
//			MybatisMethodNotFindInXmlInspection

public class MyBatisMethodNotFoundInXmlProvider
	implements InspectionToolProvider
{

	public MyBatisMethodNotFoundInXmlProvider()
	{
	}

	public Class[] getInspectionClasses()
	{
		return (new Class[] {
			com/ccnode/codegenerator/view/inspection/MybatisMethodNotFindInXmlInspection
		});
	}
}
