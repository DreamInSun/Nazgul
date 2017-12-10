// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MyBatisDatasourceConfigView.java

package com.ccnode.codegenerator.datasourceToolWindow;

import com.ccnode.codegenerator.datasourceToolWindow.dbInfo.DatabaseConnector;
import com.ccnode.codegenerator.view.completion.MysqlCompleteCacheInteface;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.text.JTextComponent;
import org.apache.commons.lang.StringUtils;

// Referenced classes of package com.ccnode.codegenerator.datasourceToolWindow:
//			NewDatabaseInfo

public class MyBatisDatasourceConfigView extends DialogWrapper
{

	private JPanel jpanel;
	private JTextField urlField;
	private JTextField usernamefield;
	private JTextField passwordField;
	private JButton testConnectionButton;
	private JComboBox datasourceCombox;
	private JLabel testConnectionText;
	private JTextField databaseText;
	private JLabel databaseLabel;
	private NewDatabaseInfo newDatabaseInfo;
	private Project myProject;
	private List existingDatabaseInfo;

	public MyBatisDatasourceConfigView(Project project, boolean canBeParent, List existingDatabaseInfo)
	{
		super(project, canBeParent);
		myProject = project;
		this.existingDatabaseInfo = existingDatabaseInfo;
		$$$setupUI$$$();
		setTitle("add database config");
		init();
	}

	protected JComponent createCenterPanel()
	{
		testConnectionButton.addActionListener(new java.awt.event.ActionListener() {

			final MyBatisDatasourceConfigView this$0;

			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				boolean b = DatabaseConnector.checkConnection((String)datasourceCombox.getSelectedItem(), urlField.getText(), usernamefield.getText(), passwordField.getText(), databaseText.getText());
				if (!b)
					testConnectionText.setText("failed");
				else
					testConnectionText.setText("success");
				Timer t = new Timer(4000, new java.awt.event.ActionListener() {

					final 1 this$1;

					public void actionPerformed(java.awt.event.ActionEvent e)
					{
						testConnectionText.setText(null);
					}

					
					{
						this.this$1 = 1.this;
						super();
					}
				});
				t.setRepeats(false);
				t.start();
			}

			
			{
				this.this$0 = MyBatisDatasourceConfigView.this;
				super();
			}
		});
		return jpanel;
	}

	protected void doOKAction()
	{
		if (StringUtils.isEmpty(databaseText.getText()))
		{
			Messages.showErrorDialog("the database should not empyt", "database is empty");
			return;
		}
		boolean b = DatabaseConnector.checkConnection((String)datasourceCombox.getSelectedItem(), urlField.getText(), usernamefield.getText(), passwordField.getText(), databaseText.getText());
		if (b)
		{
			newDatabaseInfo = new NewDatabaseInfo();
			newDatabaseInfo.setDatabaseType((String)datasourceCombox.getSelectedItem());
			newDatabaseInfo.setUrl(urlField.getText());
			newDatabaseInfo.setUserName(usernamefield.getText());
			newDatabaseInfo.setPassword(passwordField.getText());
			newDatabaseInfo.setDatabase(databaseText.getText());
			if (existingDatabaseInfo.contains(newDatabaseInfo))
			{
				Messages.showErrorDialog(myProject, "database already exist", "validate fail");
				return;
			}
			MysqlCompleteCacheInteface service = (MysqlCompleteCacheInteface)ServiceManager.getService(myProject, com/ccnode/codegenerator/view/completion/MysqlCompleteCacheInteface);
			service.cleanAll();
			service.addDatabaseCache(newDatabaseInfo);
			super.doOKAction();
		} else
		{
			Messages.showErrorDialog(myProject, "make sure you can connect to the database", "database connect fail");
		}
	}

	public NewDatabaseInfo getNewDatabaseInfo()
	{
		return newDatabaseInfo;
	}

	private void $$$setupUI$$$()
	{
		JPanel jpanel1 = new JPanel();
		jpanel = jpanel1;
		jpanel1.setLayout(new java.awt.BorderLayout(0, 0));
		JPanel jpanel2 = new JPanel();
		jpanel2.setLayout(new GridLayoutManager(4, 2, new java.awt.Insets(0, 0, 0, 0), -1, -1, false, false));
		jpanel1.add(jpanel2, "West");
		Spacer spacer = new Spacer();
		jpanel2.add(spacer, new GridConstraints(1, 1, 2, 1, 0, 1, 6, 1, null, null, null));
		Spacer spacer1 = new Spacer();
		jpanel2.add(spacer1, new GridConstraints(3, 0, 1, 1, 0, 2, 1, 6, null, null, null));
		JPanel jpanel3 = new JPanel();
		jpanel3.setLayout(new GridLayoutManager(9, 3, new java.awt.Insets(0, 0, 0, 0), -1, -1, false, false));
		jpanel1.add(jpanel3, "Center");
		JLabel jlabel = new JLabel();
		jlabel.setText("url");
		jpanel3.add(jlabel, new GridConstraints(1, 0, 1, 1, 8, 0, 0, 0, null, null, null));
		JTextField jtextfield = new JTextField();
		urlField = jtextfield;
		jtextfield.setText("localhost:3306");
		jpanel3.add(jtextfield, new GridConstraints(1, 1, 1, 1, 8, 1, 6, 0, null, new java.awt.Dimension(150, -1), null));
		Spacer spacer2 = new Spacer();
		jpanel3.add(spacer2, new GridConstraints(8, 1, 1, 1, 0, 2, 1, 6, null, null, null));
		Spacer spacer3 = new Spacer();
		jpanel3.add(spacer3, new GridConstraints(1, 2, 1, 1, 0, 1, 6, 1, null, null, null));
		Spacer spacer4 = new Spacer();
		jpanel3.add(spacer4, new GridConstraints(6, 1, 1, 1, 0, 2, 1, 6, null, null, null));
		JButton jbutton = new JButton();
		testConnectionButton = jbutton;
		jbutton.setText("testConnection");
		jpanel3.add(jbutton, new GridConstraints(5, 0, 1, 1, 0, 1, 3, 0, null, null, null));
		JLabel jlabel1 = new JLabel();
		jlabel1.setText("userName");
		jpanel3.add(jlabel1, new GridConstraints(2, 0, 1, 1, 8, 0, 0, 0, null, null, null));
		JTextField jtextfield1 = new JTextField();
		usernamefield = jtextfield1;
		jtextfield1.setText("root");
		jpanel3.add(jtextfield1, new GridConstraints(2, 1, 1, 1, 8, 1, 6, 0, null, new java.awt.Dimension(150, -1), null));
		JLabel jlabel2 = new JLabel();
		jlabel2.setText("datasource");
		jpanel3.add(jlabel2, new GridConstraints(0, 0, 1, 1, 8, 0, 0, 0, null, null, null));
		JComboBox jcombobox = new JComboBox();
		datasourceCombox = jcombobox;
		DefaultComboBoxModel defaultcomboboxmodel = new DefaultComboBoxModel();
		defaultcomboboxmodel.addElement("MySql");
		jcombobox.setModel(defaultcomboboxmodel);
		jpanel3.add(jcombobox, new GridConstraints(0, 1, 1, 1, 8, 1, 2, 0, null, null, null));
		JTextField jtextfield2 = new JTextField();
		passwordField = jtextfield2;
		jtextfield2.setText("");
		jpanel3.add(jtextfield2, new GridConstraints(3, 1, 1, 1, 8, 1, 6, 0, null, new java.awt.Dimension(150, -1), null));
		JLabel jlabel3 = new JLabel();
		jlabel3.setText("password");
		jpanel3.add(jlabel3, new GridConstraints(3, 0, 1, 1, 8, 0, 0, 0, null, null, null));
		JLabel jlabel4 = new JLabel();
		testConnectionText = jlabel4;
		jlabel4.setText("");
		jpanel3.add(jlabel4, new GridConstraints(5, 1, 1, 1, 8, 0, 0, 0, null, null, null));
		JLabel jlabel5 = new JLabel();
		databaseLabel = jlabel5;
		jlabel5.setText("database");
		jpanel3.add(jlabel5, new GridConstraints(4, 0, 1, 1, 8, 0, 0, 0, null, null, null));
		JTextField jtextfield3 = new JTextField();
		databaseText = jtextfield3;
		jpanel3.add(jtextfield3, new GridConstraints(4, 1, 1, 1, 8, 1, 6, 0, null, new java.awt.Dimension(150, -1), null));
	}

	public JComponent $$$getRootComponent$$$()
	{
		return jpanel;
	}






}
