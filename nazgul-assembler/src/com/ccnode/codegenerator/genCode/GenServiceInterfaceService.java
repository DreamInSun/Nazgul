// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenServiceInterfaceService.java

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

public class GenServiceInterfaceService
{

	public GenServiceInterfaceService()
	{
	}

	public static void generateServiceIntefaceUsingFtl(InsertFileProp fileProp, ClassInfo srcClass)
	{
		List retList = Lists.newArrayList();
		Map root = Maps.newHashMap();
		root.put("servicePackage", fileProp.getPackageName());
		root.put("pojoFullType", srcClass.getQualifiedName());
		root.put("serviceType", fileProp.getName());
		root.put("pojoType", srcClass.getName());
		root.put("pojoName", GenCodeUtil.getLowerCamel(srcClass.getName()));
		String generateServiceString = TemplateUtil.processToString("gencode/service_interface.ftl", root);
		retList.add(generateServiceString);
		FileUtils.writeFiles(fileProp, retList);
	}
}
