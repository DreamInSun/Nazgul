// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MethodExistDialog.java

package com.ccnode.codegenerator.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import java.awt.*;
import javax.swing.*;

public class MethodExistDialog extends DialogWrapper
{

	private final Project myProject;
	private final String existMethodValue;

	public MethodExistDialog(Project project, String existMethodValue)
	{
		super(project, true);
		myProject = project;
		this.existMethodValue = existMethodValue;
		init();
	}

	protected JComponent createCenterPanel()
	{
		JPanel jpanel = new JPanel(new GridBagLayout());
		GridBagConstraints bag = new GridBagConstraints();
		bag.fill = 2;
		bag.anchor = 17;
		bag.gridx = 0;
		bag.gridy = 0;
		bag.insets = new Insets(0, 0, 5, 0);
		jpanel.add(new JLabel("the method sql already exist in mapper,the existed value is:"), bag);
		bag.gridx = 0;
		bag.gridy = 1;
		JTextArea comp = new JTextArea(existMethodValue);
		comp.setEditable(false);
		jpanel.add(comp, bag);
		bag.fill = 2;
		bag.gridy = 2;
		jpanel.add(new JLabel("do you want to override them?"), bag);
		return jpanel;
	}
}
