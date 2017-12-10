// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenCodeDialog.java

package com.ccnode.codegenerator.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.PsiClass;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

// Referenced classes of package com.ccnode.codegenerator.dialog:
//			GenCodeInsertDialog, GenCodeUpdateDialog, GenCodeType, InsertDialogResult

public class GenCodeDialog extends DialogWrapper
{

	private PsiClass psiClass;
	private Project myProject;
	private GenCodeType type;
	private InsertDialogResult insertDialogResult;
	private java.util.List myValidFields;

	public GenCodeDialog(Project project, PsiClass psiClass, java.util.List validFields)
	{
		super(project, true);
		type = GenCodeType.INSERT;
		myProject = project;
		this.psiClass = psiClass;
		myValidFields = validFields;
		setTitle("choose what you wan't to do");
		setOKButtonText("next");
		init();
	}

	public GenCodeType getType()
	{
		return type;
	}

	public void setType(GenCodeType type)
	{
		this.type = type;
	}

	protected void doOKAction()
	{
		if (type == GenCodeType.INSERT)
		{
			GenCodeInsertDialog genCodeInsertDialog = new GenCodeInsertDialog(myProject, psiClass, myValidFields);
			boolean b = genCodeInsertDialog.showAndGet();
			if (!b)
				return;
			insertDialogResult = genCodeInsertDialog.getInsertDialogResult();
			super.doOKAction();
		} else
		if (type == GenCodeType.UPDATE)
		{
			GenCodeUpdateDialog updateDialog = new GenCodeUpdateDialog(myProject, psiClass, myValidFields);
			boolean b = updateDialog.showAndGet();
			if (!b)
				return;
			super.doOKAction();
		}
	}

	public InsertDialogResult getInsertDialogResult()
	{
		return insertDialogResult;
	}

	protected JComponent createCenterPanel()
	{
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints bag = new GridBagConstraints();
		bag.gridx = 0;
		bag.gridy = 0;
		ButtonGroup group = new ButtonGroup();
		JRadioButton generatenewButton = new JRadioButton("new mybatis file", true);
		generatenewButton.addActionListener(new ActionListener() {

			final GenCodeDialog this$0;

			public void actionPerformed(ActionEvent e)
			{
				type = GenCodeType.INSERT;
			}

			
			{
				this.this$0 = GenCodeDialog.this;
				super();
			}
		});
		group.add(generatenewButton);
		jPanel.add(generatenewButton, bag);
		bag.gridx = 1;
		JRadioButton updateButton = new JRadioButton("update existing mybatis file");
		group.add(updateButton);
		updateButton.addActionListener(new ActionListener() {

			final GenCodeDialog this$0;

			public void actionPerformed(ActionEvent e)
			{
				type = GenCodeType.UPDATE;
			}

			
			{
				this.this$0 = GenCodeDialog.this;
				super();
			}
		});
		jPanel.add(updateButton, bag);
		return jPanel;
	}

}
