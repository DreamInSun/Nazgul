// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MyComboBoxRender.java

package com.ccnode.codegenerator.dialog;

import com.ccnode.codegenerator.dialog.datatype.TypeProps;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class MyComboBoxRender
	implements TableCellRenderer
{

	private Map jComboBoxMap;
	private Map fieldTypeMap;

	public MyComboBoxRender(Map fieldTypeMap)
	{
		jComboBoxMap = new HashMap();
		this.fieldTypeMap = fieldTypeMap;
	}

	public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		JComboBox jComboBox;
		if (jComboBoxMap.get(Integer.valueOf(row)) == null)
		{
			jComboBox = new JComboBox();
			Object fieldName = table.getValueAt(row, 0);
			List typePropsList = (List)fieldTypeMap.get(fieldName);
			TypeProps getType;
			for (Iterator iterator = typePropsList.iterator(); iterator.hasNext(); jComboBox.addItem(getType.getDefaultType()))
				getType = (TypeProps)iterator.next();

			jComboBoxMap.put(Integer.valueOf(row), jComboBox);
		}
		jComboBox = (JComboBox)jComboBoxMap.get(Integer.valueOf(row));
		if (value != null)
			jComboBox.setSelectedItem(value);
		return jComboBox;
	}
}
