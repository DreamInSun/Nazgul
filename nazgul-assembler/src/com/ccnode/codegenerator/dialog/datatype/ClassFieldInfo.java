// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ClassFieldInfo.java

package com.ccnode.codegenerator.dialog.datatype;

import com.intellij.psi.PsiField;

public class ClassFieldInfo
{

	private String fieldName;
	private String fieldType;
	private PsiField psiField;
	private String comment;

	public ClassFieldInfo()
	{
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public PsiField getPsiField()
	{
		return psiField;
	}

	public void setPsiField(PsiField psiField)
	{
		this.psiField = psiField;
	}

	public String getFieldName()
	{
		return fieldName;
	}

	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	public String getFieldType()
	{
		return fieldType;
	}

	public void setFieldType(String fieldType)
	{
		this.fieldType = fieldType;
	}
}
