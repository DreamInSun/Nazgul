// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ParsedUpdate.java

package com.ccnode.codegenerator.methodnameparser.parsedresult.update;

import com.ccnode.codegenerator.methodnameparser.parsedresult.base.ParsedBase;
import com.rits.cloning.Cloner;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.ccnode.codegenerator.methodnameparser.parsedresult.update:
//			UpdateField

public class ParsedUpdate extends ParsedBase
{

	private List updateProps;

	public ParsedUpdate()
	{
	}

	public void addUpdateProps(String prop)
	{
		if (updateProps == null)
			updateProps = new ArrayList();
		if (updateProps.size() == 0)
		{
			UpdateField field = new UpdateField();
			field.setProp(prop);
			updateProps.add(field);
		} else
		{
			UpdateField field = (UpdateField)updateProps.get(updateProps.size() - 1);
			if (field.getProp() == null)
			{
				field.setProp(prop);
			} else
			{
				UpdateField newField = new UpdateField();
				newField.setProp(prop);
				updateProps.add(newField);
			}
		}
	}

	public void addPrefix(String prefix)
	{
		if (updateProps == null)
			updateProps = new ArrayList();
		UpdateField field = new UpdateField();
		field.setPrefix(prefix);
		updateProps.add(field);
	}

	public ParsedUpdate clone()
	{
		return (ParsedUpdate)Cloner.standard().deepClone(this);
	}

	public List getUpdateProps()
	{
		return updateProps;
	}

	public volatile Object clone()
		throws CloneNotSupportedException
	{
		return clone();
	}
}
