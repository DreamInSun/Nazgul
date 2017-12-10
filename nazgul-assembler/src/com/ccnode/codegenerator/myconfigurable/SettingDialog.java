// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SettingDialog.java

package com.ccnode.codegenerator.myconfigurable;

import com.ccnode.codegenerator.enums.IconEnum;
import java.awt.*;
import javax.swing.*;

// Referenced classes of package com.ccnode.codegenerator.myconfigurable:
//			DataBaseConstants, PluginState, Profile

public class SettingDialog
{

	private JPanel rootComponent;
	private JLabel databaseLabel;
	private JComboBox databaseCombox;
	private JCheckBox addMapperAnnotationCheckBox;
	private JCheckBox useGeneratedKeysCheckBox;
	private JCheckBox mysqlUsingWithDashCheckBox;
	private JCheckBox useWithIfTestCheckBox;
	private JCheckBox generateMethodXmlInServiceCheckBox;
	private JCheckBox generateMethodXmlInServiceInterfaceCheckBOx;
	private JTextField mapperPrefixTextField;
	private JComboBox iconBox;
	private PluginState settings;

	public SettingDialog(PluginState state)
	{
		init(state);
	}

	private void init(PluginState state)
	{
		settings = state;
		Profile profile = state.getProfile();
		rootComponent = new JPanel(new GridBagLayout());
		GridBagConstraints bag = new GridBagConstraints();
		bag.anchor = 18;
		bag.gridx = 0;
		bag.gridy = 0;
		bag.weightx = 0.10000000000000001D;
		bag.weighty = 1.0D;
		databaseLabel = new JLabel("database:");
		Font font = new Font("Monospaced", 0, 15);
		databaseLabel.setFont(font);
		rootComponent.add(databaseLabel, bag);
		databaseCombox = new JComboBox();
		databaseCombox.addItem("MySql");
		databaseCombox.addItem("Oracle");
		databaseCombox.addItem("Sqlite");
		int i = 0;
		do
		{
			if (i >= databaseCombox.getItemCount())
				break;
			Object itemAt = databaseCombox.getItemAt(i);
			if (itemAt.equals(profile.getDatabase()))
			{
				databaseCombox.setSelectedIndex(i);
				break;
			}
			i++;
		} while (true);
		bag.gridx = 1;
		bag.weightx = 0.5D;
		rootComponent.add(databaseCombox, bag);
		bag.gridy = 1;
		bag.gridx = 0;
		bag.weighty = 1.0D;
		addMapperAnnotationCheckBox = new JCheckBox("add @Mapper to mybatis interface", profile.getAddMapperAnnotation().booleanValue());
		rootComponent.add(addMapperAnnotationCheckBox, bag);
		bag.gridy = 2;
		bag.gridx = 0;
		useGeneratedKeysCheckBox = new JCheckBox("use generated keys", profile.getUseGeneratedKeys().booleanValue());
		rootComponent.add(useGeneratedKeysCheckBox, bag);
		bag.gridy = 3;
		bag.gridx = 0;
		mysqlUsingWithDashCheckBox = new JCheckBox("mysql use with `", profile.getMysqlUseWithDash().booleanValue());
		rootComponent.add(mysqlUsingWithDashCheckBox, bag);
		bag.gridy = 4;
		bag.gridx = 0;
		useWithIfTestCheckBox = new JCheckBox("generate with if test", profile.getGenerateWithIfTest().booleanValue());
		rootComponent.add(useWithIfTestCheckBox, bag);
		bag.gridy = 5;
		bag.gridx = 0;
		generateMethodXmlInServiceInterfaceCheckBOx = new JCheckBox("generate method xml in service interface", profile.getGenerateMethodInServiceInterface().booleanValue());
		rootComponent.add(generateMethodXmlInServiceInterfaceCheckBOx, bag);
		bag.gridy = 6;
		bag.gridx = 0;
		generateMethodXmlInServiceCheckBox = new JCheckBox("generate method xml in service", profile.getGenerateMethodInService().booleanValue());
		rootComponent.add(generateMethodXmlInServiceCheckBox, bag);
		bag.gridy = 7;
		bag.gridx = 0;
		rootComponent.add(new JLabel("mapper suffix"), bag);
		bag.gridx = 1;
		mapperPrefixTextField = new JTextField(profile.getMapperPrefix());
		rootComponent.add(mapperPrefixTextField, bag);
		bag.gridy = 8;
		bag.gridx = 0;
		rootComponent.add(new JLabel("mapper icon"), bag);
		bag.gridx = 1;
		iconBox = new JComboBox();
		IconEnum values[] = IconEnum.values();
		IconEnum aiconenum[] = values;
		int j = aiconenum.length;
		for (int k = 0; k < j; k++)
		{
			IconEnum value = aiconenum[k];
			iconBox.addItem(value.getIconName());
		}

		iconBox.setSelectedItem(profile.getIconName());
		rootComponent.add(iconBox, bag);
		bag.gridy = 9;
		bag.gridx = 0;
		bag.weighty = 10000D;
		rootComponent.add(new JPanel(), bag);
	}

	public JPanel getRootComponent()
	{
		return rootComponent;
	}

	public void importFrom(PluginState setting)
	{
		settings = setting;
		setData(setting.getDefaultProfile());
	}

	public void setData(Profile data)
	{
		databaseCombox.setSelectedItem(data.getDatabase());
		addMapperAnnotationCheckBox.setSelected(data.getAddMapperAnnotation().booleanValue());
		useGeneratedKeysCheckBox.setSelected(data.getUseGeneratedKeys().booleanValue());
		mysqlUsingWithDashCheckBox.setSelected(data.getMysqlUseWithDash().booleanValue());
		useWithIfTestCheckBox.setSelected(data.getGenerateWithIfTest().booleanValue());
		generateMethodXmlInServiceInterfaceCheckBOx.setSelected(data.getGenerateMethodInServiceInterface().booleanValue());
		generateMethodXmlInServiceCheckBox.setSelected(data.getGenerateMethodInService().booleanValue());
		mapperPrefixTextField.setText(data.getMapperPrefix());
		iconBox.setSelectedItem(data.getIconName());
	}

	public boolean isSettingModified(PluginState state)
	{
		getData(settings.getDefaultProfile());
		return !settings.equals(state);
	}

	public PluginState getSettings()
	{
		getData(settings.getDefaultProfile());
		return settings;
	}

	private void getData(Profile defaultProfile)
	{
		defaultProfile.setAddMapperAnnotation(Boolean.valueOf(addMapperAnnotationCheckBox.isSelected()));
		defaultProfile.setDatabase((String)databaseCombox.getSelectedItem());
		defaultProfile.setUseGeneratedKeys(Boolean.valueOf(useGeneratedKeysCheckBox.isSelected()));
		defaultProfile.setMysqlUseWithDash(Boolean.valueOf(mysqlUsingWithDashCheckBox.isSelected()));
		defaultProfile.setGenerateWithIfTest(Boolean.valueOf(useWithIfTestCheckBox.isSelected()));
		defaultProfile.setGenerateMethodInServiceInterface(Boolean.valueOf(generateMethodXmlInServiceInterfaceCheckBOx.isSelected()));
		defaultProfile.setGenerateMethodInService(Boolean.valueOf(generateMethodXmlInServiceCheckBox.isSelected()));
		defaultProfile.setMapperPrefix(mapperPrefixTextField.getText());
		String iconName = (String)iconBox.getSelectedItem();
		defaultProfile.setIconName(iconName);
	}
}
