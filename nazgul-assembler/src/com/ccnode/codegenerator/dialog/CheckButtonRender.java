// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CheckButtonRender.java

package com.ccnode.codegenerator.dialog;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class CheckButtonRender extends JCheckBox
	implements TableCellRenderer
{

	CheckButtonRender()
	{
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		setSelected(value != null && ((Boolean)value).booleanValue());
		return this;
	}
}
