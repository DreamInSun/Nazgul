// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ShowLearnMoreAction.java

package com.ccnode.codegenerator.view;

import com.ccnode.codegenerator.enums.UrlManager;
import com.intellij.ide.browsers.BrowserLauncher;
import com.intellij.ide.browsers.WebBrowserManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class ShowLearnMoreAction extends AnAction
{

	public ShowLearnMoreAction()
	{
	}

	public void actionPerformed(AnActionEvent event)
	{
		BrowserLauncher.getInstance().browse(UrlManager.getMainPage(), WebBrowserManager.getInstance().getFirstActiveBrowser());
	}
}
