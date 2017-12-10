// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PaidValidator.java

package com.ccnode.codegenerator.validate;

import com.ccnode.codegenerator.util.IpUtil;
import com.ccnode.codegenerator.validate.handler.ProjectRequest;
import com.ccnode.codegenerator.validate.handler.chain.Chain1;
import com.ccnode.codegenerator.validate.handler.chain.Chain10;
import com.ccnode.codegenerator.validate.handler.chain.Chain100;
import com.ccnode.codegenerator.validate.handler.chain.Chain11;
import com.ccnode.codegenerator.validate.handler.chain.Chain12;
import com.ccnode.codegenerator.validate.handler.chain.Chain13;
import com.ccnode.codegenerator.validate.handler.chain.Chain14;
import com.ccnode.codegenerator.validate.handler.chain.Chain15;
import com.ccnode.codegenerator.validate.handler.chain.Chain16;
import com.ccnode.codegenerator.validate.handler.chain.Chain17;
import com.ccnode.codegenerator.validate.handler.chain.Chain18;
import com.ccnode.codegenerator.validate.handler.chain.Chain19;
import com.ccnode.codegenerator.validate.handler.chain.Chain2;
import com.ccnode.codegenerator.validate.handler.chain.Chain20;
import com.ccnode.codegenerator.validate.handler.chain.Chain21;
import com.ccnode.codegenerator.validate.handler.chain.Chain22;
import com.ccnode.codegenerator.validate.handler.chain.Chain23;
import com.ccnode.codegenerator.validate.handler.chain.Chain24;
import com.ccnode.codegenerator.validate.handler.chain.Chain25;
import com.ccnode.codegenerator.validate.handler.chain.Chain26;
import com.ccnode.codegenerator.validate.handler.chain.Chain27;
import com.ccnode.codegenerator.validate.handler.chain.Chain28;
import com.ccnode.codegenerator.validate.handler.chain.Chain29;
import com.ccnode.codegenerator.validate.handler.chain.Chain3;
import com.ccnode.codegenerator.validate.handler.chain.Chain30;
import com.ccnode.codegenerator.validate.handler.chain.Chain31;
import com.ccnode.codegenerator.validate.handler.chain.Chain32;
import com.ccnode.codegenerator.validate.handler.chain.Chain33;
import com.ccnode.codegenerator.validate.handler.chain.Chain34;
import com.ccnode.codegenerator.validate.handler.chain.Chain35;
import com.ccnode.codegenerator.validate.handler.chain.Chain36;
import com.ccnode.codegenerator.validate.handler.chain.Chain37;
import com.ccnode.codegenerator.validate.handler.chain.Chain38;
import com.ccnode.codegenerator.validate.handler.chain.Chain39;
import com.ccnode.codegenerator.validate.handler.chain.Chain4;
import com.ccnode.codegenerator.validate.handler.chain.Chain40;
import com.ccnode.codegenerator.validate.handler.chain.Chain41;
import com.ccnode.codegenerator.validate.handler.chain.Chain42;
import com.ccnode.codegenerator.validate.handler.chain.Chain43;
import com.ccnode.codegenerator.validate.handler.chain.Chain44;
import com.ccnode.codegenerator.validate.handler.chain.Chain45;
import com.ccnode.codegenerator.validate.handler.chain.Chain46;
import com.ccnode.codegenerator.validate.handler.chain.Chain47;
import com.ccnode.codegenerator.validate.handler.chain.Chain48;
import com.ccnode.codegenerator.validate.handler.chain.Chain49;
import com.ccnode.codegenerator.validate.handler.chain.Chain5;
import com.ccnode.codegenerator.validate.handler.chain.Chain50;
import com.ccnode.codegenerator.validate.handler.chain.Chain51;
import com.ccnode.codegenerator.validate.handler.chain.Chain52;
import com.ccnode.codegenerator.validate.handler.chain.Chain53;
import com.ccnode.codegenerator.validate.handler.chain.Chain54;
import com.ccnode.codegenerator.validate.handler.chain.Chain55;
import com.ccnode.codegenerator.validate.handler.chain.Chain56;
import com.ccnode.codegenerator.validate.handler.chain.Chain57;
import com.ccnode.codegenerator.validate.handler.chain.Chain58;
import com.ccnode.codegenerator.validate.handler.chain.Chain59;
import com.ccnode.codegenerator.validate.handler.chain.Chain6;
import com.ccnode.codegenerator.validate.handler.chain.Chain60;
import com.ccnode.codegenerator.validate.handler.chain.Chain61;
import com.ccnode.codegenerator.validate.handler.chain.Chain62;
import com.ccnode.codegenerator.validate.handler.chain.Chain63;
import com.ccnode.codegenerator.validate.handler.chain.Chain64;
import com.ccnode.codegenerator.validate.handler.chain.Chain65;
import com.ccnode.codegenerator.validate.handler.chain.Chain66;
import com.ccnode.codegenerator.validate.handler.chain.Chain67;
import com.ccnode.codegenerator.validate.handler.chain.Chain68;
import com.ccnode.codegenerator.validate.handler.chain.Chain69;
import com.ccnode.codegenerator.validate.handler.chain.Chain7;
import com.ccnode.codegenerator.validate.handler.chain.Chain70;
import com.ccnode.codegenerator.validate.handler.chain.Chain71;
import com.ccnode.codegenerator.validate.handler.chain.Chain72;
import com.ccnode.codegenerator.validate.handler.chain.Chain73;
import com.ccnode.codegenerator.validate.handler.chain.Chain74;
import com.ccnode.codegenerator.validate.handler.chain.Chain75;
import com.ccnode.codegenerator.validate.handler.chain.Chain76;
import com.ccnode.codegenerator.validate.handler.chain.Chain77;
import com.ccnode.codegenerator.validate.handler.chain.Chain78;
import com.ccnode.codegenerator.validate.handler.chain.Chain79;
import com.ccnode.codegenerator.validate.handler.chain.Chain8;
import com.ccnode.codegenerator.validate.handler.chain.Chain80;
import com.ccnode.codegenerator.validate.handler.chain.Chain81;
import com.ccnode.codegenerator.validate.handler.chain.Chain82;
import com.ccnode.codegenerator.validate.handler.chain.Chain83;
import com.ccnode.codegenerator.validate.handler.chain.Chain84;
import com.ccnode.codegenerator.validate.handler.chain.Chain85;
import com.ccnode.codegenerator.validate.handler.chain.Chain86;
import com.ccnode.codegenerator.validate.handler.chain.Chain87;
import com.ccnode.codegenerator.validate.handler.chain.Chain88;
import com.ccnode.codegenerator.validate.handler.chain.Chain89;
import com.ccnode.codegenerator.validate.handler.chain.Chain9;
import com.ccnode.codegenerator.validate.handler.chain.Chain90;
import com.ccnode.codegenerator.validate.handler.chain.Chain91;
import com.ccnode.codegenerator.validate.handler.chain.Chain92;
import com.ccnode.codegenerator.validate.handler.chain.Chain93;
import com.ccnode.codegenerator.validate.handler.chain.Chain94;
import com.ccnode.codegenerator.validate.handler.chain.Chain95;
import com.ccnode.codegenerator.validate.handler.chain.Chain96;
import com.ccnode.codegenerator.validate.handler.chain.Chain97;
import com.ccnode.codegenerator.validate.handler.chain.Chain98;
import com.ccnode.codegenerator.validate.handler.chain.Chain99;
import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.BuildNumber;

// Referenced classes of package com.ccnode.codegenerator.validate:
//			RequestHandler

public class PaidValidator
{

	public PaidValidator()
	{
	}

	public static boolean validate(Project project)
	{
		ProjectRequest request = new ProjectRequest();
		validateTheProject(request);
		Boolean valid = request.getValid();
		if (!valid.booleanValue())
		{
			ProjectRequest request1;
			for (; !valid.booleanValue(); valid = request1.getValid())
			{
				String userKey = Messages.showInputDialog("the plugin is invalid  you can go to https://github.com/gejun123456/MyBatisCodeHelper-Pro to register", "the plugin is not available", null);
				if (userKey == null)
					return false;
				request1 = new ProjectRequest();
				request1.setKey(userKey);
				request1.setUserMac(IpUtil.getMacAddress());
				request1.setUserOs(System.getProperty("os.name"));
				request1.setUserPluginVersion(ApplicationInfo.getInstance().getBuild().asString());
				request1.setUserOsVersion("");
				validateTheProject(request1);
			}

		}
		return valid.booleanValue();
	}

	private static void validateTheProject(ProjectRequest request)
	{
		Chain1.requestHandler.handleRequest(request);
		Chain2.requestHandler.handleRequest(request);
		Chain3.requestHandler.handleRequest(request);
		Chain4.requestHandler.handleRequest(request);
		Chain5.requestHandler.handleRequest(request);
		Chain6.requestHandler.handleRequest(request);
		Chain7.requestHandler.handleRequest(request);
		Chain8.requestHandler.handleRequest(request);
		Chain9.requestHandler.handleRequest(request);
		Chain10.requestHandler.handleRequest(request);
		Chain11.requestHandler.handleRequest(request);
		Chain12.requestHandler.handleRequest(request);
		Chain13.requestHandler.handleRequest(request);
		Chain14.requestHandler.handleRequest(request);
		Chain15.requestHandler.handleRequest(request);
		Chain16.requestHandler.handleRequest(request);
		Chain17.requestHandler.handleRequest(request);
		Chain18.requestHandler.handleRequest(request);
		Chain19.requestHandler.handleRequest(request);
		Chain20.requestHandler.handleRequest(request);
		Chain21.requestHandler.handleRequest(request);
		Chain22.requestHandler.handleRequest(request);
		Chain23.requestHandler.handleRequest(request);
		Chain24.requestHandler.handleRequest(request);
		Chain25.requestHandler.handleRequest(request);
		Chain26.requestHandler.handleRequest(request);
		Chain27.requestHandler.handleRequest(request);
		Chain28.requestHandler.handleRequest(request);
		Chain29.requestHandler.handleRequest(request);
		Chain30.requestHandler.handleRequest(request);
		Chain31.requestHandler.handleRequest(request);
		Chain32.requestHandler.handleRequest(request);
		Chain33.requestHandler.handleRequest(request);
		Chain34.requestHandler.handleRequest(request);
		Chain35.requestHandler.handleRequest(request);
		Chain36.requestHandler.handleRequest(request);
		Chain37.requestHandler.handleRequest(request);
		Chain38.requestHandler.handleRequest(request);
		Chain39.requestHandler.handleRequest(request);
		Chain40.requestHandler.handleRequest(request);
		Chain41.requestHandler.handleRequest(request);
		Chain42.requestHandler.handleRequest(request);
		Chain43.requestHandler.handleRequest(request);
		Chain44.requestHandler.handleRequest(request);
		Chain45.requestHandler.handleRequest(request);
		Chain46.requestHandler.handleRequest(request);
		Chain47.requestHandler.handleRequest(request);
		Chain48.requestHandler.handleRequest(request);
		Chain49.requestHandler.handleRequest(request);
		Chain50.requestHandler.handleRequest(request);
		Chain51.requestHandler.handleRequest(request);
		Chain52.requestHandler.handleRequest(request);
		Chain53.requestHandler.handleRequest(request);
		Chain54.requestHandler.handleRequest(request);
		Chain55.requestHandler.handleRequest(request);
		Chain56.requestHandler.handleRequest(request);
		Chain57.requestHandler.handleRequest(request);
		Chain58.requestHandler.handleRequest(request);
		Chain59.requestHandler.handleRequest(request);
		Chain60.requestHandler.handleRequest(request);
		Chain61.requestHandler.handleRequest(request);
		Chain62.requestHandler.handleRequest(request);
		Chain63.requestHandler.handleRequest(request);
		Chain64.requestHandler.handleRequest(request);
		Chain65.requestHandler.handleRequest(request);
		Chain66.requestHandler.handleRequest(request);
		Chain67.requestHandler.handleRequest(request);
		Chain68.requestHandler.handleRequest(request);
		Chain69.requestHandler.handleRequest(request);
		Chain70.requestHandler.handleRequest(request);
		Chain71.requestHandler.handleRequest(request);
		Chain72.requestHandler.handleRequest(request);
		Chain73.requestHandler.handleRequest(request);
		Chain74.requestHandler.handleRequest(request);
		Chain75.requestHandler.handleRequest(request);
		Chain76.requestHandler.handleRequest(request);
		Chain77.requestHandler.handleRequest(request);
		Chain78.requestHandler.handleRequest(request);
		Chain79.requestHandler.handleRequest(request);
		Chain80.requestHandler.handleRequest(request);
		Chain81.requestHandler.handleRequest(request);
		Chain82.requestHandler.handleRequest(request);
		Chain83.requestHandler.handleRequest(request);
		Chain84.requestHandler.handleRequest(request);
		Chain85.requestHandler.handleRequest(request);
		Chain86.requestHandler.handleRequest(request);
		Chain87.requestHandler.handleRequest(request);
		Chain88.requestHandler.handleRequest(request);
		Chain89.requestHandler.handleRequest(request);
		Chain90.requestHandler.handleRequest(request);
		Chain91.requestHandler.handleRequest(request);
		Chain92.requestHandler.handleRequest(request);
		Chain93.requestHandler.handleRequest(request);
		Chain94.requestHandler.handleRequest(request);
		Chain95.requestHandler.handleRequest(request);
		Chain96.requestHandler.handleRequest(request);
		Chain97.requestHandler.handleRequest(request);
		Chain98.requestHandler.handleRequest(request);
		Chain99.requestHandler.handleRequest(request);
		Chain100.requestHandler.handleRequest(request);
	}

	public static boolean isValid()
	{
		ProjectRequest request = new ProjectRequest();
		validateTheProject(request);
		return request.getValid().booleanValue();
	}
}
