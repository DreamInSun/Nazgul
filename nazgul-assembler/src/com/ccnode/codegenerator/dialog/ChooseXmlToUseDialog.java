// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ChooseXmlToUseDialog.java

package com.ccnode.codegenerator.dialog;

import com.ccnode.codegenerator.methodnameparser.tag.XmlTagAndInfo;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.xml.XmlTag;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class ChooseXmlToUseDialog extends DialogWrapper
{

	private java.util.List xmlTagAndInfos;
	private Integer choosedIndex;

	public Integer getChoosedIndex()
	{
		return choosedIndex;
	}

	public ChooseXmlToUseDialog(Project project, java.util.List tags)
	{
		super(project, true);
		choosedIndex = Integer.valueOf(0);
		xmlTagAndInfos = tags;
		setTitle("exist mutiple parse result");
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
		jpanel.add(new JLabel("please choose one"), bag);
		ButtonGroup buttonGroup = new ButtonGroup();
		for (int i = 0; i < xmlTagAndInfos.size(); i++)
		{
			bag.gridx = 0;
			bag.gridy = bag.gridy + 1;
			JTextArea comp = new JTextArea(((XmlTagAndInfo)xmlTagAndInfos.get(i)).getXmlTag().getText());
			comp.setEditable(false);
			jpanel.add(comp, bag);
			bag.gridx = 1;
			JRadioButton jRadioButton = new JRadioButton("", true);
			jRadioButton.setActionCommand(String.valueOf(i));
			jRadioButton.addActionListener(new ActionListener() {

				final ChooseXmlToUseDialog this$0;

				public void actionPerformed(ActionEvent e)
				{
					String actionCommand = e.getActionCommand();
					choosedIndex = Integer.valueOf(Integer.parseInt(actionCommand));
				}

			
			{
				this.this$0 = ChooseXmlToUseDialog.this;
				super();
			}
			});
			buttonGroup.add(jRadioButton);
			jpanel.add(jRadioButton, bag);
		}

		bag.fill = 2;
		bag.gridy = 2;
		return jpanel;
	}

}
