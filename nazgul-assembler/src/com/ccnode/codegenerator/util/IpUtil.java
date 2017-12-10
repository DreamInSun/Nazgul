// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IpUtil.java

package com.ccnode.codegenerator.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import org.apache.commons.lang3.StringUtils;

public class IpUtil
{

	public IpUtil()
	{
	}

	public static String getMacAddress()
	{
		StringBuilder builder;
		InetAddress ip = InetAddress.getLocalHost();
		NetworkInterface network = NetworkInterface.getByInetAddress(ip);
		byte mac[] = network.getHardwareAddress();
		builder = new StringBuilder();
		for (int i = 0; i < mac.length; i++)
			builder.append(String.format("%02X%s", new Object[] {
				Byte.valueOf(mac[i]), i >= mac.length - 1 ? "" : "-"
			}));

		return builder.toString();
		Exception e;
		e;
		return "";
	}
}
