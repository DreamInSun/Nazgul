// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SendToServerHandler.java

package com.ccnode.codegenerator.log.handler;

import com.ccnode.codegenerator.log.LogEvent;
import com.ccnode.codegenerator.log.LoggerLevel;

// Referenced classes of package com.ccnode.codegenerator.log.handler:
//			LoggerHandler, MybatisLogRequest, MessageBuilder, HttpClient

public class SendToServerHandler extends LoggerHandler
{

	public SendToServerHandler(LoggerHandler next)
	{
		super(next);
	}

	public void handleRequest(LogEvent req)
	{
		sendLogToServer(req);
		super.handleRequest(req);
	}

	public void sendLogToServer(LogEvent req)
	{
		MybatisLogRequest request = new MybatisLogRequest();
		request.setClassName(req.getName());
		request.setLoggerLevel(req.getLevel().name());
		request.setMessages(MessageBuilder.buildMessage(req.getMessage(), req.getE()));
		//HttpClient.sendDataToLog(request);
	}
}
