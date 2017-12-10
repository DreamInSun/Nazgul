// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MessageBuilder.java

package com.ccnode.codegenerator.log.handler;

import com.ccnode.codegenerator.common.VersionManager;
import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.util.BuildNumber;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.commons.lang.StringUtils;

public class MessageBuilder
{

	public MessageBuilder()
	{
	}

	static String buildMessage(String message, Exception e)
	{
		StringBuilder builder = new StringBuilder();
		if (StringUtils.isEmpty(message))
			builder.append("the message is empty");
		else
			builder.append((new StringBuilder()).append("the message is ").append(message).toString());
		if (e != null)
		{
			builder.append(" the exception cause:");
			StringWriter writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			printWriter.flush();
			builder.append(writer.toString());
		}
		builder.append((new StringBuilder()).append(" the system os is:").append(System.getProperty("os.name")).toString());
		builder.append((new StringBuilder()).append(" the intellij version is:").append(ApplicationInfo.getInstance().getBuild().asString()).toString());
		builder.append((new StringBuilder()).append(" the currentVersion are:").append(VersionManager.getCurrentVersion()).toString());
		return builder.toString();
	}
}
