// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TypeProps.java

package com.ccnode.codegenerator.dialog.datatype;


public class TypeProps
{

	private String defaultType;
	private String size;
	private Boolean canBeNull;
	private Boolean unique;
	private String defaultValue;
	private Boolean primary;
	private Boolean index;
	private Boolean hasDefaultValue;
	private Integer order;

	public Integer getOrder()
	{
		return order;
	}

	public void setOrder(Integer order)
	{
		this.order = order;
	}

	public Boolean getIndex()
	{
		return index;
	}

	public void setIndex(Boolean index)
	{
		this.index = index;
	}

	public Boolean getHasDefaultValue()
	{
		return hasDefaultValue;
	}

	public void setHasDefaultValue(Boolean hasDefaultValue)
	{
		this.hasDefaultValue = hasDefaultValue;
	}

	public Boolean getPrimary()
	{
		return primary;
	}

	public void setPrimary(Boolean primary)
	{
		this.primary = primary;
	}

	public String getDefaultType()
	{
		return defaultType;
	}

	public void setDefaultType(String defaultType)
	{
		this.defaultType = defaultType;
	}

	public String getSize()
	{
		return size;
	}

	public void setSize(String size)
	{
		this.size = size;
	}

	public Boolean getCanBeNull()
	{
		return canBeNull;
	}

	public void setCanBeNull(Boolean canBeNull)
	{
		this.canBeNull = canBeNull;
	}

	public Boolean getUnique()
	{
		return unique;
	}

	public void setUnique(Boolean unique)
	{
		this.unique = unique;
	}

	public String getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	public TypeProps(String defaultType, String size, String defaultValue)
	{
		this.defaultType = defaultType;
		this.size = size;
		this.defaultValue = defaultValue;
		canBeNull = Boolean.valueOf(false);
		unique = Boolean.valueOf(false);
		primary = Boolean.valueOf(false);
		index = Boolean.valueOf(false);
		hasDefaultValue = Boolean.valueOf(true);
	}

	public TypeProps()
	{
	}
}
