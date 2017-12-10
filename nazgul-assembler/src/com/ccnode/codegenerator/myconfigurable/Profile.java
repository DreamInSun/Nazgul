// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Profile.java

package com.ccnode.codegenerator.myconfigurable;

import com.ccnode.codegenerator.enums.IconEnum;

// Referenced classes of package com.ccnode.codegenerator.myconfigurable:
//			DomainObject, DataBaseConstants

public class Profile extends DomainObject
{

	private String database;
	private Boolean addMapperAnnotation;
	private Boolean useGeneratedKeys;
	private Boolean mysqlUseWithDash;
	private Boolean generateWithIfTest;
	private Boolean generateMethodInService;
	private Boolean generateMethodInServiceInterface;
	private String mapperPrefix;
	private String javaModelPackage;
	private String javaMapperPackage;
	private String xmlMapperPackage;
	private String javaModelPath;
	private String javaMapperPath;
	private String xmlMapperPath;
	private String iconName;

	public Profile()
	{
		database = "MySql";
		addMapperAnnotation = Boolean.valueOf(true);
		useGeneratedKeys = Boolean.valueOf(true);
		mysqlUseWithDash = Boolean.valueOf(false);
		generateWithIfTest = Boolean.valueOf(false);
		generateMethodInService = Boolean.valueOf(false);
		generateMethodInServiceInterface = Boolean.valueOf(false);
		mapperPrefix = "Dao";
		iconName = IconEnum.DEFAULT.getIconName();
	}

	public String getDatabase()
	{
		return database;
	}

	public Boolean getAddMapperAnnotation()
	{
		return addMapperAnnotation;
	}

	public Boolean getUseGeneratedKeys()
	{
		return useGeneratedKeys;
	}

	public Boolean getMysqlUseWithDash()
	{
		return mysqlUseWithDash;
	}

	public Boolean getGenerateWithIfTest()
	{
		return generateWithIfTest;
	}

	public Boolean getGenerateMethodInService()
	{
		return generateMethodInService;
	}

	public Boolean getGenerateMethodInServiceInterface()
	{
		return generateMethodInServiceInterface;
	}

	public String getMapperPrefix()
	{
		return mapperPrefix;
	}

	public String getJavaModelPackage()
	{
		return javaModelPackage;
	}

	public String getJavaMapperPackage()
	{
		return javaMapperPackage;
	}

	public String getXmlMapperPackage()
	{
		return xmlMapperPackage;
	}

	public String getJavaModelPath()
	{
		return javaModelPath;
	}

	public String getJavaMapperPath()
	{
		return javaMapperPath;
	}

	public String getXmlMapperPath()
	{
		return xmlMapperPath;
	}

	public String getIconName()
	{
		return iconName;
	}

	public void setDatabase(String database)
	{
		this.database = database;
	}

	public void setAddMapperAnnotation(Boolean addMapperAnnotation)
	{
		this.addMapperAnnotation = addMapperAnnotation;
	}

	public void setUseGeneratedKeys(Boolean useGeneratedKeys)
	{
		this.useGeneratedKeys = useGeneratedKeys;
	}

	public void setMysqlUseWithDash(Boolean mysqlUseWithDash)
	{
		this.mysqlUseWithDash = mysqlUseWithDash;
	}

	public void setGenerateWithIfTest(Boolean generateWithIfTest)
	{
		this.generateWithIfTest = generateWithIfTest;
	}

	public void setGenerateMethodInService(Boolean generateMethodInService)
	{
		this.generateMethodInService = generateMethodInService;
	}

	public void setGenerateMethodInServiceInterface(Boolean generateMethodInServiceInterface)
	{
		this.generateMethodInServiceInterface = generateMethodInServiceInterface;
	}

	public void setMapperPrefix(String mapperPrefix)
	{
		this.mapperPrefix = mapperPrefix;
	}

	public void setJavaModelPackage(String javaModelPackage)
	{
		this.javaModelPackage = javaModelPackage;
	}

	public void setJavaMapperPackage(String javaMapperPackage)
	{
		this.javaMapperPackage = javaMapperPackage;
	}

	public void setXmlMapperPackage(String xmlMapperPackage)
	{
		this.xmlMapperPackage = xmlMapperPackage;
	}

	public void setJavaModelPath(String javaModelPath)
	{
		this.javaModelPath = javaModelPath;
	}

	public void setJavaMapperPath(String javaMapperPath)
	{
		this.javaMapperPath = javaMapperPath;
	}

	public void setXmlMapperPath(String xmlMapperPath)
	{
		this.xmlMapperPath = xmlMapperPath;
	}

	public void setIconName(String iconName)
	{
		this.iconName = iconName;
	}
}
