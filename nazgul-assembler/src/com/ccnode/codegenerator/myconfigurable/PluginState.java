// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PluginState.java

package com.ccnode.codegenerator.myconfigurable;

import com.ccnode.codegenerator.util.MyCloner;
import com.rits.cloning.Cloner;

// Referenced classes of package com.ccnode.codegenerator.myconfigurable:
//			DomainObject, Profile

public class PluginState extends DomainObject
	implements Cloneable
{

	private Profile profile;

	public PluginState()
	{
	}

	public Profile getProfile()
	{
		return profile;
	}

	public void setProfile(Profile profile)
	{
		this.profile = profile;
	}

	protected PluginState clone()
	{
		return (PluginState)MyCloner.getCloner().deepClone(this);
	}

	public Profile getDefaultProfile()
	{
		if (profile != null)
		{
			return profile;
		} else
		{
			profile = new Profile();
			return profile;
		}
	}

}
