// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenClassService.java

package com.ccnode.codegenerator.genCode;

import com.ccnode.codegenerator.database.GenClassInfo;
import com.ccnode.codegenerator.freemarker.TemplateConstants;
import com.ccnode.codegenerator.freemarker.TemplateUtil;
import com.ccnode.codegenerator.util.FileUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

public class GenClassService
{

	public GenClassService()
	{
	}

	public static void generateClassFileUsingFtl(GenClassInfo classInfo)
	{
		Map root = Maps.newHashMap();
		root.put("className", classInfo.getClassName());
		root.put("classPackage", classInfo.getClassPackageName());
		root.put("fields", classInfo.getClassFieldInfos());
		root.put("importList", classInfo.getImportList());
		String genClassString = TemplateUtil.processToString("newclass.ftl", root);
		List lines = Lists.newArrayList();
		lines.add(genClassString);
		FileUtils.writeFiles(classInfo.getClassFullPath(), lines, classInfo.getClassName());
	}
}
