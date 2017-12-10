// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IncDecDialog.java

package com.ccnode.codegenerator.methodnameparser.parsedresult.update;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import org.apache.commons.lang.StringUtils;

// Referenced classes of package com.ccnode.codegenerator.methodnameparser.parsedresult.update:
//			UpdateField, IncDecUserResult

public class IncDecDialog extends DialogWrapper
{

	private java.util.List myUsePrefixProps;
	private Project myProject;
	private Map resultMap;

	public IncDecDialog(java.util.List usePrefixProps, Project project)
	{
		super(project, true);
		resultMap = new HashMap();
		myUsePrefixProps = usePrefixProps;
		myProject = project;
		init();
	}

	protected JComponent createCenterPanel()
	{
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		final UpdateField myUsePrefixProp;
		final JTextField jTextField;
		final JCheckBox jCheckBox;
		for (Iterator iterator = myUsePrefixProps.iterator(); iterator.hasNext(); jCheckBox.addActionListener(new ActionListener() {

		final JCheckBox val$jCheckBox;
		final JTextField val$jTextField;
		final UpdateField val$myUsePrefixProp;
		final IncDecDialog this$0;

		public void actionPerformed(ActionEvent e)
		{
			if (jCheckBox.isSelected())
			{
				jTextField.setText("");
				IncDecUserResult result = new IncDecUserResult();
				result.setUseParam(Boolean.valueOf(true));
				resultMap.put(myUsePrefixProp.getProp(), result);
			}
		}

			
			{
				this.this$0 = IncDecDialog.this;
				jCheckBox = jcheckbox;
				jTextField = jtextfield;
				myUsePrefixProp = updatefield;
				super();
			}
	}))
		{
			myUsePrefixProp = (UpdateField)iterator.next();
			con.gridy++;
			con.gridx = 0;
			JLabel jLabel = new JLabel((new StringBuilder()).append(myUsePrefixProp.getPrefix()).append(" value for ").append(myUsePrefixProp.getProp()).toString());
			jPanel.add(jLabel, con);
			con.gridx = 1;
			jTextField = new JTextField(20);
			jPanel.add(jTextField, con);
			con.gridx = 2;
			jCheckBox = new JCheckBox("use param");
			jPanel.add(jCheckBox, con);
			jTextField.getDocument().addDocumentListener(new DocumentListener() {

				final JTextField val$jTextField;
				final JCheckBox val$jCheckBox;
				final UpdateField val$myUsePrefixProp;
				final IncDecDialog this$0;

				public void insertUpdate(DocumentEvent e)
				{
					if (StringUtils.isNotBlank(jTextField.getText()))
					{
						jCheckBox.setSelected(false);
						IncDecUserResult result = new IncDecUserResult();
						result.setUseParam(Boolean.valueOf(false));
						result.setValue(jTextField.getText());
						resultMap.put(myUsePrefixProp.getProp(), result);
					}
				}

				public void removeUpdate(DocumentEvent e)
				{
					if (StringUtils.isNotBlank(jTextField.getText()))
					{
						jCheckBox.setSelected(false);
						IncDecUserResult result = new IncDecUserResult();
						result.setUseParam(Boolean.valueOf(false));
						result.setValue(jTextField.getText());
						resultMap.put(myUsePrefixProp.getProp(), result);
					}
				}

				public void changedUpdate(DocumentEvent e)
				{
					if (StringUtils.isNotBlank(jTextField.getText()))
					{
						jCheckBox.setSelected(false);
						IncDecUserResult result = new IncDecUserResult();
						result.setUseParam(Boolean.valueOf(false));
						result.setValue(jTextField.getText());
						resultMap.put(myUsePrefixProp.getProp(), result);
					}
				}

			
			{
				this.this$0 = IncDecDialog.this;
				jTextField = jtextfield;
				jCheckBox = jcheckbox;
				myUsePrefixProp = updatefield;
				super();
			}
			});
		}

		return jPanel;
	}

	protected void doOKAction()
	{
		for (Iterator iterator = myUsePrefixProps.iterator(); iterator.hasNext();)
		{
			UpdateField myUsePrefixProp = (UpdateField)iterator.next();
			if (resultMap.get(myUsePrefixProp.getProp()) == null)
			{
				Messages.showErrorDialog(myProject, (new StringBuilder()).append("please add value for ").append(myUsePrefixProp.getProp()).toString(), "error");
				return;
			}
		}

		super.doOKAction();
	}

	public Map getResultMap()
	{
		return resultMap;
	}

	public void setResultMap(Map resultMap)
	{
		this.resultMap = resultMap;
	}

}
