// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   StopContextException.java

package com.ccnode.codegenerator.execute;


// Referenced classes of package com.ccnode.codegenerator.execute:
//			MessageEnum

public class StopContextException extends RuntimeException
{

	private boolean shouldNotifyMessage;
	private String message;
	private MessageEnum messageEnum;

	public StopContextException()
	{
		messageEnum = MessageEnum.INFO;
	}

	public boolean isShouldNotifyMessage()
	{
		return shouldNotifyMessage;
	}

	public String getMessage()
	{
		return message;
	}

	public MessageEnum getMessageEnum()
	{
		return messageEnum;
	}

	public void setShouldNotifyMessage(boolean shouldNotifyMessage)
	{
		this.shouldNotifyMessage = shouldNotifyMessage;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public void setMessageEnum(MessageEnum messageEnum)
	{
		this.messageEnum = messageEnum;
	}
}
