// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MybatisGeneratorAction.java

package com.ccnode.codegenerator.view;

import com.ccnode.codegenerator.util.CollectionUtils;
import com.ccnode.codegenerator.util.IconUtils;
import com.ccnode.codegenerator.validate.PaidValidator;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.xml.XmlFile;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

public class MybatisGeneratorAction extends AnAction
	implements DumbAware
{

	public MybatisGeneratorAction()
	{
		super(IconUtils.useSmall());
	}

	public void actionPerformed(AnActionEvent e)
	{
		boolean validate = PaidValidator.validate(e.getProject());
		if (!validate)
			return;
		PsiFile data = (PsiFile)e.getData(CommonDataKeys.PSI_FILE);
		if (data == null || !(data instanceof XmlFile))
			return;
		XmlFile s = (XmlFile)data;
		s.getVirtualFile().refresh(false, false);
		String path = s.getVirtualFile().getPath();
		List warnings = new ArrayList();
		boolean overwrite = true;
		File configFile = new File(path);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = null;
		try
		{
			config = cp.parseConfiguration(configFile);
		}
		catch (IOException e1)
		{
			Messages.showErrorDialog(e.getProject(), printStack(e1), "parse config file error");
			return;
		}
		catch (XMLParserException e1)
		{
			Messages.showErrorDialog(e.getProject(), printStack(e1), "parse config file error");
			return;
		}
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = null;
		try
		{
			myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		}
		catch (InvalidConfigurationException e1)
		{
			Messages.showErrorDialog(e.getProject(), printStack(e1), "config error");
			return;
		}
		try
		{
			myBatisGenerator.generate(null);
		}
		catch (SQLException e1)
		{
			Messages.showErrorDialog(e.getProject(), printStack(e1), "error");
			return;
		}
		catch (IOException e1)
		{
			Messages.showErrorDialog(e.getProject(), printStack(e1), "error");
			return;
		}
		catch (InterruptedException e1)
		{
			Messages.showErrorDialog(e.getProject(), printStack(e1), "error");
			return;
		}
		VirtualFileManager.getInstance().syncRefresh();
		if (CollectionUtils.isEmpty(warnings))
		{
			Messages.showInfoMessage(e.getProject(), "generate success", "success");
		} else
		{
			String m = "";
			for (Iterator iterator = warnings.iterator(); iterator.hasNext();)
			{
				String warning = (String)iterator.next();
				m = (new StringBuilder()).append(m).append(warning).append("\n").toString();
			}

			Messages.showInfoMessage(e.getProject(), m, "warning");
		}
	}

	public void update(AnActionEvent e)
	{
		PsiFile data = (PsiFile)e.getData(CommonDataKeys.PSI_FILE);
		if (data == null || !(data instanceof XmlFile))
			e.getPresentation().setVisible(false);
	}

	public static String printStack(Exception e)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
}
