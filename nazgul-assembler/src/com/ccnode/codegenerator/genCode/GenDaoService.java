// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenDaoService.java

package com.ccnode.codegenerator.genCode;

import com.ccnode.codegenerator.dialog.InsertFileProp;
import com.ccnode.codegenerator.freemarker.TemplateConstants;
import com.ccnode.codegenerator.freemarker.TemplateUtil;
import com.ccnode.codegenerator.myconfigurable.*;
import com.ccnode.codegenerator.pojo.ClassInfo;
import com.ccnode.codegenerator.util.FileUtils;
import com.ccnode.codegenerator.util.GenCodeUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

public class GenDaoService
{

	public GenDaoService()
	{
	}

	public static void generateDaoFileUsingFtl(InsertFileProp daoProp, ClassInfo srcClass)
	{
		Map root = Maps.newHashMap();
		root.put("daoPackageName", daoProp.getPackageName());
		root.put("pojoFullType", srcClass.getQualifiedName());
		root.put("daoType", daoProp.getName());
		root.put("pojoType", srcClass.getName());
		root.put("pojoName", GenCodeUtil.getLowerCamel(srcClass.getName()));
		root.put("addMapperAnnotation", MyBatisCodeHelperApplicationComponent.getInstance().getState().getProfile().getAddMapperAnnotation());
		String genDaoString = TemplateUtil.processToString("gencode/dao.ftl", root);
		List lines = Lists.newArrayList();
		lines.add(genDaoString);
		FileUtils.writeFiles(daoProp, lines);
	}
}
