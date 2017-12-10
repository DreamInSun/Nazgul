// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DatabaseFactory.java

package com.ccnode.codegenerator.database.handler;


// Referenced classes of package com.ccnode.codegenerator.database.handler:
//			GenerateFileHandler, GenerateMethodXmlHandler, UpdateFieldHandler, AutoCompleteHandler

public interface DatabaseFactory
{

	public abstract GenerateFileHandler getGenerateFileHandler();

	public abstract GenerateMethodXmlHandler getMethodXmlHandler();

	public abstract UpdateFieldHandler getUpdateFieldHandler();

	public abstract AutoCompleteHandler getAutoCompleteHandler();
}
