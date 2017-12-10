// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenServiceService.java

package com.ccnode.codegenerator.genCode;

import com.ccnode.codegenerator.dialog.InsertFileProp;
import com.ccnode.codegenerator.freemarker.TemplateConstants;
import com.ccnode.codegenerator.freemarker.TemplateUtil;
import com.ccnode.codegenerator.pojo.ClassInfo;
import com.ccnode.codegenerator.util.FileUtils;
import com.ccnode.codegenerator.util.GenCodeUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

public class GenServiceService
{

	public GenServiceService()
	{
	}

	public static void generateServiceUsingFtl(InsertFileProp fileProp, ClassInfo srcClass, InsertFileProp daoProp, InsertFileProp serviceInterface)
	{
		String daoName = GenCodeUtil.getLowerCamel(daoProp.getName());
		List retList = Lists.newArrayList();
		Map root = Maps.newHashMap();
		root.put("servicePackage", fileProp.getPackageName());
		root.put("pojoFullType", srcClass.getQualifiedName());
		root.put("daoFullType", daoProp.getQutifiedName());
		root.put("serviceType", fileProp.getName());
		root.put("daoType", daoProp.getName());
		root.put("daoName", GenCodeUtil.getLowerCamel(daoProp.getName()));
		root.put("pojoType", srcClass.getName());
		root.put("pojoName", GenCodeUtil.getLowerCamel(srcClass.getName()));
		if (serviceInterface != null)
		{
			root.put("serviceInterfaceName", serviceInterface.getName());
			root.put("serviceInterfaceFullType", serviceInterface.getQutifiedName());
		}
		String generateServiceString = TemplateUtil.processToString("gencode/service.ftl", root);
		retList.add(generateServiceString);
		FileUtils.writeFiles(fileProp, retList);
	}
}
