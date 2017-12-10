// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TypePropUtils.java

package com.ccnode.codegenerator.database.handler.utils;

import com.ccnode.codegenerator.dialog.datatype.TypeProps;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public class TypePropUtils
{

	public TypePropUtils()
	{
	}

	public static List generateFromDefaultMap(List fromMapTypes)
	{
		if (fromMapTypes == null)
			return null;
		List typePropslist = Lists.newArrayList();
		TypeProps fromMapType;
		for (Iterator iterator = fromMapTypes.iterator(); iterator.hasNext(); typePropslist.add(convert(fromMapType)))
			fromMapType = (TypeProps)iterator.next();

		return typePropslist;
	}

	private static TypeProps convert(TypeProps fromMapType)
	{
		TypeProps typeProps = new TypeProps();
		typeProps.setOrder(fromMapType.getOrder());
		typeProps.setIndex(fromMapType.getIndex());
		typeProps.setHasDefaultValue(fromMapType.getHasDefaultValue());
		typeProps.setPrimary(fromMapType.getPrimary());
		typeProps.setDefaultType(fromMapType.getDefaultType());
		typeProps.setSize(fromMapType.getSize());
		typeProps.setCanBeNull(fromMapType.getCanBeNull());
		typeProps.setUnique(fromMapType.getUnique());
		typeProps.setDefaultValue(fromMapType.getDefaultValue());
		return typeProps;
	}
}
