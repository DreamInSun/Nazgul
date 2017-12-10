// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ClassValidateResult.java

package com.ccnode.codegenerator.database;

import java.util.List;

public class ClassValidateResult
{

	private List validFields;
	private Boolean valid;
	private String invalidMessages;

	public ClassValidateResult()
	{
	}

	public String getInvalidMessages()
	{
		return invalidMessages;
	}

	public void setInvalidMessages(String invalidMessages)
	{
		this.invalidMessages = invalidMessages;
	}

	public List getValidFields()
	{
		return validFields;
	}

	public void setValidFields(List validFields)
	{
		this.validFields = validFields;
	}

	public Boolean getValid()
	{
		return valid;
	}

	public void setValid(Boolean valid)
	{
		this.valid = valid;
	}
}
