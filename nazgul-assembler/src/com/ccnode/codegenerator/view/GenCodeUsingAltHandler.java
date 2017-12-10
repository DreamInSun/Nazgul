// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenCodeUsingAltHandler.java

package com.ccnode.codegenerator.view;

import com.ccnode.codegenerator.database.ClassValidateResult;
import com.ccnode.codegenerator.database.DatabaseComponenent;
import com.ccnode.codegenerator.database.handler.DatabaseFactory;
import com.ccnode.codegenerator.database.handler.GenerateFileHandler;
import com.ccnode.codegenerator.dialog.*;
import com.ccnode.codegenerator.pojo.ClassInfo;
import com.ccnode.codegenerator.service.pojo.GenerateInsertCodeService;
import com.ccnode.codegenerator.validate.PaidValidator;
import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.CodeInsightUtilBase;
import com.intellij.codeInsight.generation.OverrideImplementUtil;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.*;
import java.util.Iterator;
import java.util.List;

public class GenCodeUsingAltHandler
	implements CodeInsightActionHandler
{

	public GenCodeUsingAltHandler()
	{
	}

	public void invoke(Project project, Editor editor, PsiFile file)
	{
		if (project == null)
			$$$reportNull$$$0(0);
		if (editor == null)
			$$$reportNull$$$0(1);
		if (file == null)
			$$$reportNull$$$0(2);
		boolean validate = PaidValidator.validate(project);
		if (!validate)
			return;
		if (!CodeInsightUtilBase.prepareEditorForWrite(editor))
			return;
		if (!FileDocumentManager.getInstance().requestWriting(editor.getDocument(), project))
			return;
		PsiClass currentClass = OverrideImplementUtil.getContextClass(project, editor, file, false);
		ClassValidateResult classValidateResult = DatabaseComponenent.currentHandler().getGenerateFileHandler().validateCurrentClass(currentClass);
		if (!classValidateResult.getValid().booleanValue())
			if (classValidateResult.getValid() == null || classValidateResult.getValidFields().size() == 0)
			{
				Messages.showErrorDialog(project, classValidateResult.getInvalidMessages(), "validate fail");
			} else
			{
				List validFields1 = classValidateResult.getValidFields();
				StringBuilder validBuilder = new StringBuilder();
				validBuilder.append("\n the following are valid fields: ");
				PsiField psiField;
				for (Iterator iterator = validFields1.iterator(); iterator.hasNext(); validBuilder.append((new StringBuilder()).append(psiField.getName()).append(",").toString()))
					psiField = (PsiField)iterator.next();

				int i = Messages.showOkCancelDialog(project, (new StringBuilder()).append(classValidateResult.getInvalidMessages()).append(validBuilder.toString()).append("\n\n do you want just use with valid fields?").toString(), "some field not valid", null);
				if (i == 2)
					return;
			}
		VirtualFileManager.getInstance().syncRefresh();
		ApplicationManager.getApplication().saveAll();
		GenCodeDialog genCodeDialog = new GenCodeDialog(project, currentClass, classValidateResult.getValidFields());
		boolean b = genCodeDialog.showAndGet();
		if (!b)
			return;
		ClassInfo info = new ClassInfo();
		info.setQualifiedName(currentClass.getQualifiedName());
		info.setName(currentClass.getName());
		static class 1
		{

			static final int $SwitchMap$com$ccnode$codegenerator$dialog$GenCodeType[];

			static 
			{
				$SwitchMap$com$ccnode$codegenerator$dialog$GenCodeType = new int[GenCodeType.values().length];
				try
				{
					$SwitchMap$com$ccnode$codegenerator$dialog$GenCodeType[GenCodeType.INSERT.ordinal()] = 1;
				}
				catch (NoSuchFieldError nosuchfielderror) { }
				try
				{
					$SwitchMap$com$ccnode$codegenerator$dialog$GenCodeType[GenCodeType.UPDATE.ordinal()] = 2;
				}
				catch (NoSuchFieldError nosuchfielderror1) { }
			}
		}

		switch (1..SwitchMap.com.ccnode.codegenerator.dialog.GenCodeType[genCodeDialog.getType().ordinal()])
		{
		case 1: // '\001'
			InsertDialogResult insertDialogResult = genCodeDialog.getInsertDialogResult();
			insertDialogResult.setSrcClass(info);
			GenerateInsertCodeService.generateInsert(insertDialogResult);
			VirtualFileManager.getInstance().syncRefresh();
			Messages.showMessageDialog(project, "generate files success", "success", Messages.getInformationIcon());
			return;

		case 2: // '\002'
		default:
			return;
		}
	}

	public boolean startInWriteAction()
	{
		return false;
	}

	private static void $$$reportNull$$$0(int i)
	{
		"Argument for @NotNull parameter '%s' of %s.%s must not be null";
		new Object[3];
		i;
		JVM INSTR tableswitch 0 2: default 36
	//	               0 36
	//	               1 45
	//	               2 54;
		   goto _L1 _L1 _L2 _L3
_L1:
		JVM INSTR dup ;
		0;
		"project";
		JVM INSTR aastore ;
		  goto _L4
_L2:
		JVM INSTR dup ;
		0;
		"editor";
		JVM INSTR aastore ;
		  goto _L4
_L3:
		JVM INSTR dup ;
		0;
		"file";
		JVM INSTR aastore ;
_L4:
		JVM INSTR dup ;
		1;
		"com/ccnode/codegenerator/view/GenCodeUsingAltHandler";
		JVM INSTR aastore ;
		JVM INSTR dup ;
		2;
		"invoke";
		JVM INSTR aastore ;
		String.format();
		JVM INSTR new #291 <Class IllegalArgumentException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalArgumentException();
		throw ;
	}
}
