// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ConfigChangeListener.java

package com.ccnode.codegenerator.myconfigurable;


// Referenced classes of package com.ccnode.codegenerator.myconfigurable:
//			PluginState

public interface ConfigChangeListener
{

	public abstract void onConfigChange(PluginState pluginstate, PluginState pluginstate1);
}
