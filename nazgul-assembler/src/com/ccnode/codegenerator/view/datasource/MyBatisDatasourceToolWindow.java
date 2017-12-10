// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MyBatisDatasourceToolWindow.java

package com.ccnode.codegenerator.view.datasource;

import com.ccnode.codegenerator.util.IconUtils;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.*;
import javax.swing.JPanel;

// Referenced classes of package com.ccnode.codegenerator.view.datasource:
//			MyBastisDatasourceForm

public class MyBatisDatasourceToolWindow
	implements ToolWindowFactory, DumbAware
{

	private JPanel myToolWindowContent;
	private ToolWindow myToolWindow;

	public MyBatisDatasourceToolWindow()
	{
	}

	public void createToolWindowContent(Project project, ToolWindow toolWindow)
	{
		if (project == null)
			$$$reportNull$$$0(0);
		if (toolWindow == null)
			$$$reportNull$$$0(1);
		myToolWindowContent = (new MyBastisDatasourceForm(project)).getMyDatasourcePanel();
		myToolWindow = toolWindow;
		ContentFactory instance = com.intellij.ui.content.ContentFactory.SERVICE.getInstance();
		Content content = instance.createContent(myToolWindowContent, "", false);
		content.setIcon(IconUtils.useMyBatisIcon());
		toolWindow.getContentManager().addContent(content);
	}

	public void init(ToolWindow toolwindow)
	{
	}

	public boolean shouldBeAvailable(Project project)
	{
		if (project == null)
			$$$reportNull$$$0(2);
		return true;
	}

	public boolean isDoNotActivateOnStart()
	{
		return false;
	}

	private static void $$$reportNull$$$0(int i)
	{
		"Argument for @NotNull parameter '%s' of %s.%s must not be null";
		new Object[3];
		i;
		JVM INSTR tableswitch 0 2: default 32
	//	               0 32
	//	               1 40
	//	               2 32;
		   goto _L1 _L1 _L2 _L1
_L1:
		JVM INSTR dup ;
		0;
		"project";
		JVM INSTR aastore ;
		  goto _L3
_L2:
		JVM INSTR dup ;
		0;
		"toolWindow";
		JVM INSTR aastore ;
_L3:
		JVM INSTR dup ;
		1;
		"com/ccnode/codegenerator/view/datasource/MyBatisDatasourceToolWindow";
		JVM INSTR aastore ;
		i;
		JVM INSTR tableswitch 0 2: default 80
	//	               0 80
	//	               1 80
	//	               2 88;
		   goto _L4 _L4 _L4 _L5
_L4:
		JVM INSTR dup ;
		2;
		"createToolWindowContent";
		JVM INSTR aastore ;
		  goto _L6
_L5:
		JVM INSTR dup ;
		2;
		"shouldBeAvailable";
		JVM INSTR aastore ;
_L6:
		String.format();
		JVM INSTR new #113 <Class IllegalArgumentException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalArgumentException();
		throw ;
	}
}
