// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MultipleMethodGenerateAction.java

package com.ccnode.codegenerator.view;

import com.ccnode.codegenerator.database.DatabaseComponenent;
import com.ccnode.codegenerator.genmethodxml.GenMethodResult;
import com.ccnode.codegenerator.genmethodxml.GenMethodXmlInvoker;
import com.ccnode.codegenerator.log.Log;
import com.ccnode.codegenerator.log.LogFactory;
import com.ccnode.codegenerator.util.IconUtils;
import com.ccnode.codegenerator.validate.PaidValidator;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.intellij.codeInsight.CodeInsightUtil;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;

public class MultipleMethodGenerateAction extends AnAction
{
	private static Log log = LogFactory.getLogger(MultipleMethodGenerateAction.class);

	public MultipleMethodGenerateAction()
	{
		super(null, null, IconUtils.useSmall());
	}

	public void actionPerformed(AnActionEvent e)
	{
		boolean validate = PaidValidator.validate(e.getProject());
		if (!validate)
			return;
		Stopwatch started = Stopwatch.createStarted();
		Editor editor = (Editor)e.getData(PlatformDataKeys.EDITOR);
		Caret currentCaret = editor.getCaretModel().getCurrentCaret();
		if (currentCaret.getSelectionStart() == currentCaret.getSelectionEnd())
			return;
		Document document = editor.getDocument();
		String selectedText = currentCaret.getSelectedText();
		List methodNames = extractMethodNames(selectedText);
		if (methodNames.isEmpty())
			return;
		PsiFile data = (PsiFile)e.getData(PlatformDataKeys.PSI_FILE);
		GenMethodXmlInvoker genMethodXmlInvoker = new GenMethodXmlInvoker(methodNames, editor.getProject(), new TextRange(currentCaret.getSelectionStart(), currentCaret.getSelectionEnd()), data);
		GenMethodResult result = genMethodXmlInvoker.invoke();
		if (result == null)
			return;
		if (result.isHasCursor())
			CodeInsightUtil.positionCursor(editor.getProject(), result.getCursorFile(), result.getCursorElement());
		StringBuilder methodNameBuilder = new StringBuilder();
		String s;
		for (Iterator iterator = methodNames.iterator(); iterator.hasNext(); methodNameBuilder.append((new StringBuilder()).append(s).append(" ,").toString()))
			s = (String)iterator.next();

		methodNameBuilder.deleteCharAt(methodNameBuilder.length() - 1);
		long elapsed = started.elapsed(TimeUnit.MILLISECONDS);
		log.info((new StringBuilder()).append("generate dao multiple xml use with time in mill second is:").append(elapsed).append(" and the method name is:").append(methodNameBuilder.toString()).append(" used database is:").append(DatabaseComponenent.currentDatabase()).toString());
	}

	private static List extractMethodNames(String selectedText)
	{
		List methodList;
		methodList = Lists.newArrayList();
		String mm = "";
		for (int i = 0; i < selectedText.length(); i++)
		{
			char c = selectedText.charAt(i);
			if (Character.isJavaIdentifierPart(c))
			{
				mm = (new StringBuilder()).append(mm).append(c).toString();
				continue;
			}
			if (mm.length() > 0)
				methodList.add(mm);
			mm = "";
		}

		if (StringUtils.isNotBlank(mm))
			methodList.add(mm);
		methodList;
		if (methodList == null)
			$$$reportNull$$$0(0);
		return;
	}

	public void update(AnActionEvent e)
	{
		PsiFile data = (PsiFile)e.getData(PlatformDataKeys.PSI_FILE);
		if (data == null || !data.isWritable() || !(data instanceof PsiJavaFile))
		{
			e.getPresentation().setVisible(false);
			return;
		}
		Editor editor = (Editor)e.getData(PlatformDataKeys.EDITOR);
		String selectedText = editor.getCaretModel().getCurrentCaret().getSelectedText();
		if (StringUtils.isBlank(selectedText))
			e.getPresentation().setVisible(false);
	}

	private static void $$$reportNull$$$0(int i)
	{
		String.format("@NotNull method %s.%s must not return null", new Object[] {
			"com/ccnode/codegenerator/view/MultipleMethodGenerateAction", "extractMethodNames"
		});
		JVM INSTR new #325 <Class IllegalStateException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalStateException();
		throw ;
	}

}
