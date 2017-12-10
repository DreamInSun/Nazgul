// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MybatisGeneratorForm.java

package com.ccnode.codegenerator.mybatisGenerator;

import com.ccnode.codegenerator.datasourceToolWindow.NewDatabaseInfo;
import com.ccnode.codegenerator.datasourceToolWindow.dbInfo.DatabaseConnector;
import com.ccnode.codegenerator.myconfigurable.DataBaseConstants;
import com.ccnode.codegenerator.myconfigurable.MyBatisCodeHelperApplicationComponent;
import com.ccnode.codegenerator.myconfigurable.PluginState;
import com.ccnode.codegenerator.myconfigurable.Profile;
import com.ccnode.codegenerator.util.GenCodeUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFileManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.DefaultShellCallback;

public class MybatisGeneratorForm extends DialogWrapper
{

	private NewDatabaseInfo myInfo;
	private String myTableName;
	private java.util.List myColumnInfo;
	private JTextField javaModelPackage;
	private JTextField javaModelName;
	private JTextField javaMapperPackgeTextField;
	private JTextField xmlMapperPackageTextField;
	private JTextField javaModelPath;
	private JTextField javaMapperPath;
	private JTextField xmlMapperPath;
	private Project myProject;

	public MybatisGeneratorForm(NewDatabaseInfo info, String tableName, java.util.List tableColumnInfo, Project myProject)
	{
		super(myProject, true);
		setTitle((new StringBuilder()).append("run mybatis generator for ").append(tableName).toString());
		myInfo = info;
		myTableName = tableName;
		myColumnInfo = tableColumnInfo;
		this.myProject = myProject;
		init();
	}

	protected JComponent createCenterPanel()
	{
		JPanel jPanel = new JPanel();
		Border border = jPanel.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		GridBagLayout panelGridBagLayout = new GridBagLayout();
		panelGridBagLayout.columnWidths = (new int[] {
			86, 86, 0
		});
		panelGridBagLayout.rowHeights = (new int[] {
			20, 20, 20, 20, 20, 0
		});
		panelGridBagLayout.columnWeights = (new double[] {
			0.0D, 1.0D, 4.9406564584124654E-324D
		});
		panelGridBagLayout.rowWeights = (new double[] {
			0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9406564584124654E-324D
		});
		jPanel.setLayout(panelGridBagLayout);
		javaModelName = new JTextField(GenCodeUtil.getUpperCamelFromUnderScore(myTableName));
		Profile profile = MyBatisCodeHelperApplicationComponent.getInstance().getState().getProfile();
		javaModelPackage = new JTextField(profile.getJavaModelPackage() != null ? profile.getJavaModelPackage() : "com.aa.model");
		javaMapperPackgeTextField = new JTextField(profile.getJavaMapperPackage() != null ? profile.getJavaMapperPackage() : "com.aa.mapper");
		xmlMapperPackageTextField = new JTextField(profile.getXmlMapperPackage() != null ? profile.getXmlMapperPackage() : "com.aa.mapper");
		String projectFilePath = myProject.getBasePath();
		javaModelPath = new JTextField(profile.getJavaModelPath() != null ? profile.getJavaModelPath() : (new StringBuilder()).append(projectFilePath).append("/src/main/java").toString());
		javaMapperPath = new JTextField(profile.getJavaMapperPath() != null ? profile.getJavaMapperPath() : (new StringBuilder()).append(projectFilePath).append("/src/main/java").toString());
		xmlMapperPath = new JTextField(profile.getXmlMapperPath() != null ? profile.getXmlMapperPath() : (new StringBuilder()).append(projectFilePath).append("/src/main/resources").toString());
		addLabelAndTextField("java model name", 0, jPanel, javaModelName);
		addLabelAndTextField("java model package", 1, jPanel, javaModelPackage);
		addLabelAndTextField("java mapper pakcage:", 2, jPanel, javaMapperPackgeTextField);
		addLabelAndTextField("xml mapper package:", 3, jPanel, xmlMapperPackageTextField);
		addLabelAndTextField("java model path", 4, jPanel, javaModelPath);
		addLabelAndTextField("java mapper path", 5, jPanel, javaMapperPath);
		addLabelAndTextField("xml mapper path", 6, jPanel, xmlMapperPath);
		return jPanel;
	}

	private void addLabelAndTextField(String labelText, int yPos, JPanel jPanel, JTextField javaModelTextField)
	{
		JLabel faxLabel = new JLabel(labelText);
		GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
		gridBagConstraintForLabel.fill = 1;
		gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
		gridBagConstraintForLabel.gridx = 0;
		gridBagConstraintForLabel.gridy = yPos;
		jPanel.add(faxLabel, gridBagConstraintForLabel);
		GridBagConstraints gridBagConstraintForTextField = new GridBagConstraints();
		gridBagConstraintForTextField.fill = 1;
		gridBagConstraintForTextField.insets = new Insets(0, 0, 5, 0);
		gridBagConstraintForTextField.gridx = 1;
		gridBagConstraintForTextField.gridy = yPos;
		jPanel.add(javaModelTextField, gridBagConstraintForTextField);
		javaModelTextField.setColumns(25);
	}

	protected void doOKAction()
	{
		generateFiles();
		VirtualFileManager.getInstance().syncRefresh();
		Messages.showMessageDialog(myProject, "generate files success", "success", Messages.getInformationIcon());
		super.doOKAction();
	}

	private void generateFiles()
	{
		java.util.List warnings = new ArrayList();
		boolean overwrite = true;
		Configuration config = new Configuration();
		Context context = new Context(ModelType.FLAT);
		context.setId("mySql");
		context.setTargetRuntime("MyBatis3");
		JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
		jdbcConnectionConfiguration.setDriverClass("com.mysql.jdbc.Driver");
		jdbcConnectionConfiguration.setConnectionURL(DatabaseConnector.buildUrl("MySql", myInfo.getUrl(), myInfo.getDatabase()));
		jdbcConnectionConfiguration.setUserId(myInfo.getUserName());
		jdbcConnectionConfiguration.setPassword(myInfo.getPassword());
		context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
		Profile profile = MyBatisCodeHelperApplicationComponent.getInstance().getState().getProfile();
		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
		javaModelGeneratorConfiguration.setTargetPackage(javaModelPackage.getText());
		profile.setJavaModelPackage(javaModelPackage.getText());
		javaModelGeneratorConfiguration.setTargetProject(javaModelPath.getText());
		profile.setJavaModelPath(javaModelPath.getText());
		context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
		SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
		sqlMapGeneratorConfiguration.setTargetPackage(xmlMapperPackageTextField.getText());
		profile.setXmlMapperPackage(xmlMapperPackageTextField.getText());
		sqlMapGeneratorConfiguration.setTargetProject(xmlMapperPath.getText());
		profile.setXmlMapperPath(xmlMapperPath.getText());
		context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
		JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
		javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
		javaClientGeneratorConfiguration.setTargetPackage(javaMapperPackgeTextField.getText());
		profile.setJavaMapperPackage(javaMapperPackgeTextField.getText());
		javaClientGeneratorConfiguration.setTargetProject(javaMapperPath.getText());
		profile.setJavaMapperPath(javaMapperPath.getText());
		context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
		TableConfiguration tc = new TableConfiguration(context);
		tc.setTableName(myTableName);
		tc.setSchema(null);
		tc.setDomainObjectName(javaModelName.getText());
		tc.setCountByExampleStatementEnabled(false);
		tc.setSelectByExampleStatementEnabled(false);
		tc.setUpdateByExampleStatementEnabled(false);
		tc.setDeleteByExampleStatementEnabled(false);
		context.addTableConfiguration(tc);
		config.addContext(context);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = null;
		try
		{
			myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			myBatisGenerator.generate(null);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		String warning;
		for (Iterator iterator = warnings.iterator(); iterator.hasNext(); System.out.println(warning))
			warning = (String)iterator.next();

	}
}
