// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   FileUtils.java

package com.ccnode.codegenerator.util;

import com.ccnode.codegenerator.dialog.InsertFileProp;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FileUtils
{

	public FileUtils()
	{
	}

	public static void writeFiles(InsertFileProp prop, List retList)
	{
		try
		{
			File file = new File(prop.getFullPath());
			if (!file.exists())
				file.getParentFile().mkdirs();
			Files.write(Paths.get(prop.getFullPath(), new String[0]), retList, new OpenOption[] {
				StandardOpenOption.CREATE, StandardOpenOption.APPEND
			});
		}
		catch (IOException e)
		{
			throw new RuntimeException((new StringBuilder()).append("can't write file ").append(prop.getName()).append(" to path ").append(prop.getFullPath()).toString(), e);
		}
	}

	public static void writeFiles(String fullPath, List retList, String fileName)
	{
		try
		{
			File file = new File(fullPath);
			if (!file.exists())
				file.getParentFile().mkdirs();
			Files.write(Paths.get(fullPath, new String[0]), retList, new OpenOption[] {
				StandardOpenOption.CREATE, StandardOpenOption.APPEND
			});
		}
		catch (IOException e)
		{
			throw new RuntimeException((new StringBuilder()).append("can't write file ").append(fileName).append(" to path ").append(fullPath).toString(), e);
		}
	}
}
