// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenerateFileHandler.java

package com.ccnode.codegenerator.database.handler;

import com.ccnode.codegenerator.database.ClassValidateResult;
import com.ccnode.codegenerator.dialog.GenCodeProp;
import com.intellij.psi.PsiClass;
import java.util.List;

// Referenced classes of package com.ccnode.codegenerator.database.handler:
//			JTableRecommendHandler

public interface GenerateFileHandler
	extends JTableRecommendHandler
{

	public abstract String generateSql(List list, GenCodeProp gencodeprop, String s, List list1, List list2);

	public abstract ClassValidateResult validateCurrentClass(PsiClass psiclass);
}
