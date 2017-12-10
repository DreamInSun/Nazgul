// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MybatiXmlShowSqlAction.java

package com.ccnode.codegenerator.view;

import com.ccnode.codegenerator.constants.MyBatisXmlConstants;
import com.ccnode.codegenerator.showxmlsql.ShowSqlDialog;
import com.ccnode.codegenerator.util.*;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import java.util.Map;
import java.util.Set;

public class MybatiXmlShowSqlAction extends AnAction
{

	public MybatiXmlShowSqlAction()
	{
		super(null, null, IconUtils.useSmall());
	}

	public void actionPerformed(AnActionEvent e)
	{
		XmlTag theTag = extractXmlTag(e);
		if (theTag == null)
			return;
		PsiMethod methodOfXmlTag = MyPsiXmlUtils.findMethodOfXmlTag(theTag);
		if (methodOfXmlTag == null)
		{
			Messages.showErrorDialog(e.getProject(), "please provide method for current xml", "can't find method in class of current xml");
			return;
		}
		Map paramMap = PsiClassUtil.extractMybatisParamForXmlSql(methodOfXmlTag);
		ShowSqlDialog dialog = new ShowSqlDialog(e.getProject(), paramMap, theTag);
		boolean b = dialog.showAndGet();
		if (b)
			return;
		else
			return;
	}

	public void update(AnActionEvent e)
	{
		if (extractXmlTag(e) == null)
		{
			e.getPresentation().setVisible(false);
			return;
		} else
		{
			return;
		}
	}

	private XmlTag extractXmlTag(AnActionEvent e)
	{
		PsiFile data = (PsiFile)e.getData(PlatformDataKeys.PSI_FILE);
		if (data == null || !data.isWritable() || !(data instanceof XmlFile))
			return null;
		XmlFile theFile = (XmlFile)data;
		XmlTag rootTag = theFile.getRootTag();
		if (rootTag == null || !rootTag.getName().equals("mapper"))
			return null;
		XmlTag subTags[] = rootTag.getSubTags();
		Caret data2 = (Caret)e.getData(CommonDataKeys.CARET);
		int selectionStart = data2.getSelectionStart();
		XmlTag axmltag[] = subTags;
		int i = axmltag.length;
		for (int j = 0; j < i; j++)
		{
			XmlTag subTag = axmltag[j];
			if (subTag.getTextRange().getStartOffset() <= selectionStart && subTag.getTextRange().getEndOffset() >= selectionStart && MyBatisXmlConstants.mapperMethodSet.contains(subTag.getName()))
				return subTag;
		}

		return null;
	}
}
