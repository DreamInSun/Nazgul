// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenerateResultMapDialog.java

package com.ccnode.codegenerator.dialog;

import com.ccnode.codegenerator.constants.MapperConstants;
import com.ccnode.codegenerator.pojo.FieldToColumnRelation;
import com.ccnode.codegenerator.util.GenCodeUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import org.apache.commons.lang.StringUtils;

// Referenced classes of package com.ccnode.codegenerator.dialog:
//			PropAndField

public class GenerateResultMapDialog extends DialogWrapper
{

	private java.util.List props;
	private java.util.List jTextFields;
	private String className;
	private JTextField columMapText;
	private FieldToColumnRelation relation;

	public GenerateResultMapDialog(Project project, java.util.List prop, String className)
	{
		super(project, true);
		props = prop;
		this.className = className;
		jTextFields = new ArrayList();
		setTitle((new StringBuilder()).append("generate the resultMap for type:").append(className).toString());
		initRelation();
		init();
	}

	public FieldToColumnRelation getRelation()
	{
		return relation;
	}

	public void setRelation(FieldToColumnRelation relation)
	{
		this.relation = relation;
	}

	protected void doOKAction()
	{
		Map fieldToColumnMap = new LinkedHashMap();
		if (StringUtils.isBlank(columMapText.getText()))
		{
			Messages.showErrorDialog("resultMapId is empty, please write things to it", "resultMapId is empty");
			return;
		}
		for (Iterator iterator = jTextFields.iterator(); iterator.hasNext();)
		{
			PropAndField field = (PropAndField)iterator.next();
			if (StringUtils.isNotBlank(field.getField().getText()))
			{
				fieldToColumnMap.put(field.getProp(), field.getField().getText().trim());
			} else
			{
				Messages.showErrorDialog((new StringBuilder()).append("please write text to ").append(field.getProp()).toString(), "column is empty");
				return;
			}
		}

		relation.setResultMapId(columMapText.getText());
		relation.setFiledToColumnMap(fieldToColumnMap);
		super.doOKAction();
	}

	private void initRelation()
	{
		relation = new FieldToColumnRelation();
		Map fieldToMap = new HashMap();
		String prop;
		for (Iterator iterator = props.iterator(); iterator.hasNext(); fieldToMap.put(prop, prop))
			prop = (String)iterator.next();

		relation.setFiledToColumnMap(fieldToMap);
		relation.setResultMapId("BaseResultMap");
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
		bag.gridwidth = 5;
		JLabel comp = new JLabel((new StringBuilder()).append("can't find right resultMap for type:").append(className).append(" let generate new resultMap to it").toString());
		comp.setForeground(Color.RED);
		comp.setOpaque(true);
		jpanel.add(comp, bag);
		bag.gridwidth = 1;
		bag.gridy = 1;
		ButtonGroup group = new ButtonGroup();
		JRadioButton rawRadio = new JRadioButton("raw", true);
		jpanel.add(rawRadio, bag);
		group.add(rawRadio);
		bag.gridx = 2;
		JRadioButton snakeRadio = new JRadioButton("snake");
		jpanel.add(snakeRadio, bag);
		group.add(snakeRadio);
		bag.gridx = 3;
		JRadioButton lowerRadio = new JRadioButton("lowercase");
		jpanel.add(lowerRadio, bag);
		group.add(lowerRadio);
		bag.gridy++;
		bag.gridx = 0;
		jpanel.add(new JLabel("resultMapId"), bag);
		bag.gridx = 1;
		columMapText = new JTextField("BaseResultMap");
		jpanel.add(columMapText, bag);
		bag.gridy++;
		bag.gridx = 0;
		jpanel.add(new JLabel("property"), bag);
		bag.gridx = 1;
		jpanel.add(new JLabel("column"), bag);
		JTextField field;
		for (Iterator iterator = props.iterator(); iterator.hasNext(); jpanel.add(field, bag))
		{
			String prop = (String)iterator.next();
			bag.gridy++;
			bag.gridx = 0;
			JLabel jLabel = new JLabel(prop);
			jpanel.add(jLabel, bag);
			field = new JTextField(prop);
			PropAndField e = new PropAndField();
			e.setProp(prop);
			e.setField(field);
			jTextFields.add(e);
			bag.gridx = 1;
		}

		rawRadio.addActionListener(new ActionListener() {

			final GenerateResultMapDialog this$0;

			public void actionPerformed(ActionEvent e)
			{
				PropAndField field;
				for (Iterator iterator1 = jTextFields.iterator(); iterator1.hasNext(); field.getField().setText(field.getProp()))
					field = (PropAndField)iterator1.next();

			}

			
			{
				this.this$0 = GenerateResultMapDialog.this;
				super();
			}
		});
		snakeRadio.addActionListener(new ActionListener() {

			final GenerateResultMapDialog this$0;

			public void actionPerformed(ActionEvent e)
			{
				PropAndField field;
				for (Iterator iterator1 = jTextFields.iterator(); iterator1.hasNext(); field.getField().setText(GenCodeUtil.getUnderScore(field.getProp())))
					field = (PropAndField)iterator1.next();

			}

			
			{
				this.this$0 = GenerateResultMapDialog.this;
				super();
			}
		});
		lowerRadio.addActionListener(new ActionListener() {

			final GenerateResultMapDialog this$0;

			public void actionPerformed(ActionEvent e)
			{
				PropAndField field;
				for (Iterator iterator1 = jTextFields.iterator(); iterator1.hasNext(); field.getField().setText(field.getProp().toLowerCase()))
					field = (PropAndField)iterator1.next();

			}

			
			{
				this.this$0 = GenerateResultMapDialog.this;
				super();
			}
		});
		return jpanel;
	}

	private void createUIComponents()
	{
	}

}
