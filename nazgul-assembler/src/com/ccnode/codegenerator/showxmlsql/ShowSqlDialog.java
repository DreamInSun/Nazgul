// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ShowSqlDialog.java

package com.ccnode.codegenerator.showxmlsql;

import com.ccnode.codegenerator.constants.MyBatisXmlConstants;
import com.ccnode.codegenerator.pojo.TextFieldAndCheckBox;
import com.google.common.collect.Maps;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlTagValue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.PrintStream;
import java.util.*;
import javax.swing.*;

// Referenced classes of package com.ccnode.codegenerator.showxmlsql:
//			XmlContext, XmlParseResult, XmlParser

public class ShowSqlDialog extends DialogWrapper
{
	protected class CopySqlAction extends com.intellij.openapi.ui.DialogWrapper.DialogWrapperAction
	{

		final ShowSqlDialog this$0;

		protected void doAction(ActionEvent e)
		{
			extractValues();
			XmlContext context = extractContextFromXmlTag(myXmlTag);
			XmlParseResult parseResult = XmlParser.parseXmlWithContext(myXmlTag.getValue().getText(), myParamMap, context);
			if (parseResult.getIsValid().booleanValue())
				System.out.println(parseResult.getSql());
			else
				System.out.println(parseResult.getErrorText());
		}

		private static void $$$reportNull$$$0(int i)
		{
			String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", new Object[] {
				"name", "com/ccnode/codegenerator/showxmlsql/ShowSqlDialog$CopySqlAction", "<init>"
			});
			JVM INSTR new #126 <Class IllegalArgumentException>;
			JVM INSTR dup_x1 ;
			JVM INSTR swap ;
			IllegalArgumentException();
			throw ;
		}

		protected CopySqlAction(String name)
		{
			if (name == null)
				$$$reportNull$$$0(0);
			this.this$0 = ShowSqlDialog.this;
			super(ShowSqlDialog.this, name);
		}
	}


	private Map myParamMap;
	private Map textFieldMap;
	private static Map fullTypeDefaultValues = new HashMap() {

			
			{
				put("java.lang.Integer", "0");
				put("java.lang.String", "'hehe'");
				put("java.math.BigDecimal", "100");
				put("java.lang.Long", "1222113");
				put("java.lang.Byte", "0");
				put("java.lang.Short", "2334");
				put("java.lang.Boolean", "0");
			}
	};
	private static Map userChooseValues = Maps.newHashMap();
	private XmlTag myXmlTag;

	public ShowSqlDialog(Project project, Map paramMap, XmlTag tag)
	{
		super(project, true);
		textFieldMap = Maps.newHashMap();
		myParamMap = paramMap;
		myXmlTag = tag;
		setTitle("show sql of the current tag");
		init();
	}

	protected JComponent createCenterPanel()
	{
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints bag = new GridBagConstraints();
		bag.gridx = 0;
		bag.gridy = 0;
		bag.fill = 2;
		for (Iterator iterator = myParamMap.keySet().iterator(); iterator.hasNext();)
		{
			String oneParam = (String)iterator.next();
			jPanel.add(new JLabel(oneParam), bag);
			bag.gridx = 1;
			JTextField comp = new JTextField(getDefaultValue((String)myParamMap.get(oneParam)));
			comp.setColumns(20);
			jPanel.add(comp, bag);
			bag.gridx = 2;
			JCheckBox jCheckBox = new JCheckBox("null value", false);
			jPanel.add(jCheckBox, bag);
			TextFieldAndCheckBox textFieldAndCheckBox = new TextFieldAndCheckBox();
			textFieldAndCheckBox.setJTextField(comp);
			textFieldAndCheckBox.setJCheckBox(jCheckBox);
			textFieldMap.put(oneParam, textFieldAndCheckBox);
			bag.gridx = 0;
			bag.gridy++;
		}

		return jPanel;
	}

	private static String getDefaultValue(String fullType)
	{
		String s = (String)fullTypeDefaultValues.get(fullType);
		if (s != null)
			return s;
		else
			return "";
	}

	protected Action[] createActions()
	{
		Action allAction[];
		Action copySqlAction = new CopySqlAction("copy sql");
		Action actions[] = super.createActions();
		allAction = new Action[actions.length + 1];
		allAction[0] = copySqlAction;
		for (int i = 1; i < allAction.length; i++)
			allAction[i] = actions[i - 1];

		allAction;
		if (allAction == null)
			$$$reportNull$$$0(0);
		return;
	}

	private XmlContext extractContextFromXmlTag(XmlTag myXmlTag)
	{
		XmlContext xmlContext = new XmlContext();
		Map sqlIdMaps = Maps.newHashMap();
		XmlTag parentTag = myXmlTag.getParentTag();
		XmlTag subTags[] = parentTag.getSubTags();
		XmlTag axmltag[] = subTags;
		int i = axmltag.length;
		for (int j = 0; j < i; j++)
		{
			XmlTag subTag = axmltag[j];
			if (subTag.getName().equals("sql"))
				sqlIdMaps.put(subTag.getAttributeValue("id"), subTag.getValue().getText());
		}

		xmlContext.setSqlIdMap(sqlIdMaps);
		return xmlContext;
	}

	protected void doOKAction()
	{
		extractValues();
		super.doOKAction();
	}

	private void extractValues()
	{
		for (Iterator iterator = textFieldMap.keySet().iterator(); iterator.hasNext();)
		{
			String key = (String)iterator.next();
			TextFieldAndCheckBox textFieldAndCheckBox = (TextFieldAndCheckBox)textFieldMap.get(key);
			JCheckBox jCheckBox = textFieldAndCheckBox.getJCheckBox();
			if (jCheckBox.isSelected())
				userChooseValues.put(key, null);
			else
				userChooseValues.put(key, textFieldAndCheckBox.getJTextField().getText());
		}

	}

	public static Map getUserChooseValues()
	{
		return userChooseValues;
	}

	public static void setUserChooseValues(Map userChooseValues)
	{
		userChooseValues = userChooseValues;
	}

	private static void $$$reportNull$$$0(int i)
	{
		String.format("@NotNull method %s.%s must not return null", new Object[] {
			"com/ccnode/codegenerator/showxmlsql/ShowSqlDialog", "createActions"
		});
		JVM INSTR new #316 <Class IllegalStateException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalStateException();
		throw ;
	}





}
