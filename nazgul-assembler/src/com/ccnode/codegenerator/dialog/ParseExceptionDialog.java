// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ParseExceptionDialog.java

package com.ccnode.codegenerator.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import java.awt.*;
import javax.swing.*;

public class ParseExceptionDialog extends DialogWrapper
{

	private String methodName;
	private Integer start;
	private Integer end;
	private String errorMsg;

	public ParseExceptionDialog(Project project, String methodName, Integer start, Integer end, String errorMsg)
	{
		super(project, true);
		this.methodName = methodName;
		this.start = start;
		this.end = end;
		this.errorMsg = errorMsg;
		setTitle("parse methodname catch exception");
		setOKActionEnabled(false);
		init();
	}

	protected JComponent createCenterPanel()
	{
		JPanel jPanel = new JPanel(new GridBagLayout());
		GridBagConstraints bag = new GridBagConstraints();
		bag.fill = 2;
		bag.anchor = 17;
		bag.gridx = 0;
		bag.gridy = 0;
		bag.insets = new Insets(0, 0, 5, 0);
		if (start != null && end != null)
		{
			jPanel.add(new JLabel((new StringBuilder()).append("methodname: ").append(methodName.substring(0, start.intValue())).toString()), bag);
			bag.gridx = 1;
			JLabel errorPart = new JLabel(methodName.substring(start.intValue(), end.intValue()));
			errorPart.setForeground(Color.RED);
			errorPart.setOpaque(true);
			jPanel.add(errorPart, bag);
			bag.gridx = 2;
			jPanel.add(new JLabel(methodName.substring(end.intValue())), bag);
		} else
		{
			jPanel.add(new JLabel((new StringBuilder()).append("methodname: ").append(methodName).toString()), bag);
		}
		bag.gridx = 0;
		bag.gridy = 1;
		jPanel.add(new JLabel(errorMsg), bag);
		return jPanel;
	}
}
