// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MyConfigurable.java

package com.ccnode.codegenerator.myconfigurable;

import com.google.common.collect.Lists;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComponent;

// Referenced classes of package com.ccnode.codegenerator.myconfigurable:
//			SettingDialog, ConfigChangeListener, PluginState, MyBatisCodeHelperApplicationComponent

public class MyConfigurable
	implements Configurable
{

	public MyBatisCodeHelperApplicationComponent applicationComponent;
	private SettingDialog form;
	private static List configChangeListeners = Lists.newArrayList();

	public MyConfigurable()
	{
		applicationComponent = MyBatisCodeHelperApplicationComponent.getInstance();
	}

	public String getDisplayName()
	{
		return "MyBatisCodeHelper";
	}

	public String getHelpTopic()
	{
		return null;
	}

	public JComponent createComponent()
	{
		if (form == null)
			form = new SettingDialog(applicationComponent.getState().clone());
		return form.getRootComponent();
	}

	public boolean isModified()
	{
		return form != null && form.isSettingModified(applicationComponent.getState());
	}

	public void apply()
		throws ConfigurationException
	{
		PluginState state = applicationComponent.getState();
		PluginState formSettings = form.getSettings();
		ConfigChangeListener configChangeListener;
		for (Iterator iterator = configChangeListeners.iterator(); iterator.hasNext(); configChangeListener.onConfigChange(state, formSettings))
			configChangeListener = (ConfigChangeListener)iterator.next();

		applicationComponent.loadState(formSettings);
	}

	public void reset()
	{
		if (form != null)
			form.importFrom(applicationComponent.getState().clone());
	}

	public void disposeUIResources()
	{
		form = null;
	}

	public static void registerListener(ConfigChangeListener listener)
	{
		configChangeListeners.add(listener);
	}

}
