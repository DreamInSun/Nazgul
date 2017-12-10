// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenCodeActionUsingAlt.java

package com.ccnode.codegenerator.view;

import com.intellij.codeInsight.generation.actions.BaseGenerateAction;

// Referenced classes of package com.ccnode.codegenerator.view:
//			GenCodeUsingAltHandler

public class GenCodeActionUsingAlt extends BaseGenerateAction
{

	public GenCodeActionUsingAlt()
	{
		super(new GenCodeUsingAltHandler());
	}
}
