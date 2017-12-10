// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenCodeAction.java

package com.ccnode.codegenerator.view;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class GenCodeAction extends AnAction
{

	public GenCodeAction()
	{
		super("Text _Boxes");
	}

	public void actionPerformed(AnActionEvent event)
	{
		Project project = (Project)event.getData(PlatformDataKeys.PROJECT);
		Messages.showMessageDialog(project, "please use alt+insert (generate mybatis files) on domain Class instead\n(ctrl+N on mac. same key shortcut for generate getter setter method)", "action not supported", null);
	}
}
