// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IconUtils.java

package com.ccnode.codegenerator.util;

import com.ccnode.codegenerator.enums.IconEnum;
import com.ccnode.codegenerator.myconfigurable.*;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconUtils
{

	private static ImageIcon methodIcon = new ImageIcon(com/ccnode/codegenerator/util/IconUtils.getClassLoader().getResource("icon/mybatis_new_6.png"));
	private static ImageIcon iconForMenu = new ImageIcon(com/ccnode/codegenerator/util/IconUtils.getClassLoader().getResource("icon/mybatis_new_7.png"));
	private static ImageIcon mapperIcon = null;
	private static ImageIcon mapperXmlIcon = null;
	private static MyBatisCodeHelperApplicationComponent myBatisCodeHelperApplicationComponent = MyBatisCodeHelperApplicationComponent.getInstance();

	public IconUtils()
	{
	}

	public static Icon useMyBatisIcon()
	{
		return methodIcon;
	}

	public static Icon mapperIcon()
	{
		if (mapperIcon == null)
		{
			Profile defaultProfile = myBatisCodeHelperApplicationComponent.getState().getDefaultProfile();
			String iconName = defaultProfile.getIconName();
			IconEnum iconEnum = IconEnum.getIconEnum(iconName);
			if (iconEnum == null)
				mapperIcon = new ImageIcon(com/ccnode/codegenerator/util/IconUtils.getClassLoader().getResource(IconEnum.DEFAULT.getMapperIconPlace()));
			else
				mapperIcon = new ImageIcon(com/ccnode/codegenerator/util/IconUtils.getClassLoader().getResource(iconEnum.getMapperIconPlace()));
		}
		return mapperIcon;
	}

	public static Icon mapperxmlIcon()
	{
		if (mapperXmlIcon == null)
		{
			Profile defaultProfile = myBatisCodeHelperApplicationComponent.getState().getDefaultProfile();
			String iconName = defaultProfile.getIconName();
			IconEnum iconEnum = IconEnum.getIconEnum(iconName);
			if (iconEnum == null)
				mapperIcon = new ImageIcon(com/ccnode/codegenerator/util/IconUtils.getClassLoader().getResource(IconEnum.DEFAULT.getMapperIconPlace()));
			else
				mapperXmlIcon = new ImageIcon(com/ccnode/codegenerator/util/IconUtils.getClassLoader().getResource(iconEnum.getMapperXmlIconPlace()));
		}
		return mapperXmlIcon;
	}

	public static Icon useSmall()
	{
		return iconForMenu;
	}

	public static void setIcon(String name)
	{
		IconEnum iconEnum = IconEnum.getIconEnum(name);
		mapperXmlIcon = new ImageIcon(com/ccnode/codegenerator/util/IconUtils.getClassLoader().getResource(iconEnum.getMapperXmlIconPlace()));
		mapperIcon = new ImageIcon(com/ccnode/codegenerator/util/IconUtils.getClassLoader().getResource(iconEnum.getMapperIconPlace()));
		Project openProjects[] = ProjectManager.getInstance().getOpenProjects();
		Project aproject[] = openProjects;
		int i = aproject.length;
		for (int j = 0; j < i; j++)
		{
			Project openProject = aproject[j];
			FileEditorManager fileEditorManager = FileEditorManager.getInstance(openProject);
			VirtualFile selectedFiles[] = fileEditorManager.getSelectedFiles();
			VirtualFile theFile = null;
			if (selectedFiles != null)
				theFile = selectedFiles[0];
			VirtualFile openFiles[] = fileEditorManager.getOpenFiles();
			VirtualFile avirtualfile[] = openFiles;
			int k = avirtualfile.length;
			for (int l = 0; l < k; l++)
			{
				VirtualFile openFile = avirtualfile[l];
				PsiFile file = PsiManager.getInstance(openProject).findFile(openFile);
				if (file != null)
				{
					fileEditorManager.closeFile(openFile);
					fileEditorManager.openFile(openFile, false);
				}
			}

			if (theFile != null)
				fileEditorManager.openFile(theFile, true);
		}

	}

	static 
	{
		MyConfigurable.registerListener(new ConfigChangeListener() {

			public void onConfigChange(PluginState oldState, PluginState newState)
			{
				String oldIconName = oldState.getDefaultProfile().getIconName();
				String newIconName = newState.getDefaultProfile().getIconName();
				if (!oldIconName.equals(newIconName))
					IconUtils.setIcon(newIconName);
			}

		});
	}
}
