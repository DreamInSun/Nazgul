// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MyBatisCodeHelperApplicationComponent.java

package com.ccnode.codegenerator.myconfigurable;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.PersistentStateComponent;

// Referenced classes of package com.ccnode.codegenerator.myconfigurable:
//			PluginState, DefaultState

public class MyBatisCodeHelperApplicationComponent
	implements ApplicationComponent, PersistentStateComponent
{

	private PluginState settings;

	public MyBatisCodeHelperApplicationComponent()
	{
	}

	public static MyBatisCodeHelperApplicationComponent getInstance()
	{
		return (MyBatisCodeHelperApplicationComponent)ApplicationManager.getApplication().getComponent(MyBatisCodeHelperApplicationComponent.class);
	}

	public void initComponent()
	{
	}

	public void disposeComponent()
	{
	}

	public String getComponentName()
	{
		return "MyBatisCodeHelper";
	}

	public PluginState getState()
	{
		if (settings == null)
		{
			settings = new PluginState();
			settings.setProfile(DefaultState.createDefault());
		}
		return settings;
	}

	public void loadState(PluginState state)
	{
		settings = state;
	}

	public  void loadState(Object obj)
	{
		loadState((PluginState)obj);
	}


}
