// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MyBastisDatasourceForm.java

package com.ccnode.codegenerator.view.datasource;

import com.ccnode.codegenerator.datasourceToolWindow.DatasourceState;
import com.ccnode.codegenerator.datasourceToolWindow.MyBatisDatasourceConfigView;
import com.ccnode.codegenerator.datasourceToolWindow.NewDatabaseInfo;
import com.ccnode.codegenerator.datasourceToolWindow.dbInfo.DatabaseConnector;
import com.ccnode.codegenerator.datasourceToolWindow.dbInfo.DatabaseInfo;
import com.ccnode.codegenerator.datasourceToolWindow.dbInfo.DatasourceConnectionProperty;
import com.ccnode.codegenerator.datasourceToolWindow.dbInfo.TableInfo;
import com.ccnode.codegenerator.mybatisGenerator.MybatisGeneratorForm;
import com.ccnode.codegenerator.view.completion.MysqlCompleteCacheInteface;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

// Referenced classes of package com.ccnode.codegenerator.view.datasource:
//			MyBatisDatasourceComponent

public class MyBastisDatasourceForm
{

	private JPanel myDatasourcePanel;
	private JButton addButton;
	private JButton refreshButton;
	private JButton configButton;
	private JButton consoleButton;
	private JTree datasourceTree;
	private Project myProject;
	private DatasourceState myState;
	private DefaultMutableTreeNode selectedNode;
	private Map nodeDatabaseMap;
	private List myNewDatabaseInfos;

	public MyBastisDatasourceForm(Project project)
	{
		$$$setupUI$$$();
		nodeDatabaseMap = new HashMap();
		myNewDatabaseInfos = new ArrayList();
		myProject = project;
		MyBatisDatasourceComponent component = (MyBatisDatasourceComponent)myProject.getComponent(com/ccnode/codegenerator/view/datasource/MyBatisDatasourceComponent);
		myState = component.getState();
		myNewDatabaseInfos = myState.getDatabaseInfos();
		addButton.addActionListener(new java.awt.event.ActionListener() {

			final MyBastisDatasourceForm this$0;

			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				MyBatisDatasourceConfigView myBatisDatasourceConfigView = new MyBatisDatasourceConfigView(myProject, false, myNewDatabaseInfos);
				boolean b = myBatisDatasourceConfigView.showAndGet();
				if (!b)
				{
					return;
				} else
				{
					NewDatabaseInfo newDatabaseInfo = myBatisDatasourceConfigView.getNewDatabaseInfo();
					myState.setActiveDatabaseInfo(newDatabaseInfo);
					DatabaseInfo dataBaseInfoFromConnection = DatabaseConnector.getDataBaseInfoFromConnection(new DatasourceConnectionProperty(newDatabaseInfo.getDatabaseType(), newDatabaseInfo.getUrl(), newDatabaseInfo.getUserName(), newDatabaseInfo.getPassword(), newDatabaseInfo.getDatabase()));
					DefaultMutableTreeNode top = new DefaultMutableTreeNode(dataBaseInfoFromConnection.getDatabaseName());
					nodeDatabaseMap.put(top, newDatabaseInfo);
					createNodes(top, dataBaseInfoFromConnection.getTableInfoList(), newDatabaseInfo);
					DefaultTreeModel model = (DefaultTreeModel)datasourceTree.getModel();
					DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
					model.insertNodeInto(top, root, root.getChildCount());
					myNewDatabaseInfos.add(newDatabaseInfo);
					return;
				}
			}

			
			{
				this.this$0 = MyBastisDatasourceForm.this;
				super();
			}
		});
	}

	private void createNodes(DefaultMutableTreeNode top, List tableInfos, NewDatabaseInfo info)
	{
		DefaultMutableTreeNode defaultMutableTreeNode;
		for (Iterator iterator = tableInfos.iterator(); iterator.hasNext(); top.add(defaultMutableTreeNode))
		{
			TableInfo tableInfo = (TableInfo)iterator.next();
			defaultMutableTreeNode = new DefaultMutableTreeNode(tableInfo.getTableName());
			nodeDatabaseMap.put(defaultMutableTreeNode, info);
		}

	}

	private void createUIComponents()
	{
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Data sources");
		datasourceTree = new JTree(top);
	}

	public JPanel getMyDatasourcePanel()
	{
		DefaultTreeModel model = (DefaultTreeModel)datasourceTree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
		Iterator iterator = myNewDatabaseInfos.iterator();
		do
		{
			if (!iterator.hasNext())
				break;
			NewDatabaseInfo databaseInfo = (NewDatabaseInfo)iterator.next();
			DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(databaseInfo.getDatabase());
			DatabaseInfo info = DatabaseConnector.getDataBaseInfoFromConnection(new DatasourceConnectionProperty(databaseInfo.getDatabaseType(), databaseInfo.getUrl(), databaseInfo.getUserName(), databaseInfo.getPassword(), databaseInfo.getDatabase()));
			if (info != null)
			{
				createNodes(newChild, info.getTableInfoList(), databaseInfo);
				nodeDatabaseMap.put(newChild, databaseInfo);
				root.add(newChild);
			}
		} while (true);
		datasourceTree.addMouseListener(new java.awt.event.MouseAdapter() {

			final MyBastisDatasourceForm this$0;

			public void mouseClicked(java.awt.event.MouseEvent e)
			{
				if (SwingUtilities.isRightMouseButton(e))
				{
					TreePath pathForLocation = datasourceTree.getPathForLocation(e.getX(), e.getY());
					if (pathForLocation != null)
					{
						selectedNode = (DefaultMutableTreeNode)pathForLocation.getLastPathComponent();
						datasourceTree.getSelectionModel().setSelectionPath(pathForLocation);
						JPopupMenu popupMenu = new JPopupMenu();
						JMenuItem mybatisGeneratorMenu = new JMenuItem("mybatis generator");
						mybatisGeneratorMenu.addActionListener(new java.awt.event.ActionListener() {

							final 2 this$1;

							public void actionPerformed(java.awt.event.ActionEvent e)
							{
								DefaultMutableTreeNode parent = (DefaultMutableTreeNode)selectedNode.getParent();
								Object userObject = selectedNode.getUserObject();
								if (!(userObject instanceof String))
								{
									System.out.println("hehe");
									return;
								}
								String tableName = (String)userObject;
								if (nodeDatabaseMap.containsKey(parent))
								{
									NewDatabaseInfo info = (NewDatabaseInfo)nodeDatabaseMap.get(parent);
									List tableColumnInfo = DatabaseConnector.getTableColumnInfo(info, tableName);
									MybatisGeneratorForm form = new MybatisGeneratorForm(info, tableName, tableColumnInfo, myProject);
									boolean b = form.showAndGet();
									if (!b)
										return;
								}
							}

					
					{
						this.this$1 = 2.this;
						super();
					}
						});
						JMenuItem setAsActiveDatasource = new JMenuItem("set current datasource as active");
						JMenuItem removeDataSource = new JMenuItem("remove database");
						removeDataSource.addActionListener(new java.awt.event.ActionListener() {

							final 2 this$1;

							public void actionPerformed(java.awt.event.ActionEvent e)
							{
								DefaultMutableTreeNode parent = (DefaultMutableTreeNode)selectedNode.getParent();
								if (nodeDatabaseMap.containsKey(parent))
									removeNodeFromTree(parent);
								else
									removeNodeFromTree(selectedNode);
							}

					
					{
						this.this$1 = 2.this;
						super();
					}
						});
						setAsActiveDatasource.addActionListener(new java.awt.event.ActionListener() {

							final 2 this$1;

							public void actionPerformed(java.awt.event.ActionEvent e)
							{
								NewDatabaseInfo info = (NewDatabaseInfo)nodeDatabaseMap.get(selectedNode);
								if (info != null)
								{
									MysqlCompleteCacheInteface service = (MysqlCompleteCacheInteface)ServiceManager.getService(myProject, com/ccnode/codegenerator/view/completion/MysqlCompleteCacheInteface);
									service.cleanAll();
									service.addDatabaseCache(info);
									myState.setActiveDatabaseInfo(info);
									Messages.showMessageDialog("success", "success", null);
								} else
								{
									Messages.showErrorDialog(myProject, "can't find database for it", "fail");
									return;
								}
							}

					
					{
						this.this$1 = 2.this;
						super();
					}
						});
						mybatisGeneratorMenu.addActionListener(new java.awt.event.ActionListener() {

							final 2 this$1;

							public void actionPerformed(java.awt.event.ActionEvent e)
							{
								System.out.println(nodeDatabaseMap.get(selectedNode));
							}

					
					{
						this.this$1 = 2.this;
						super();
					}
						});
						popupMenu.add(mybatisGeneratorMenu);
						popupMenu.add(setAsActiveDatasource);
						if (nodeDatabaseMap.containsKey(selectedNode))
							popupMenu.add(removeDataSource);
						popupMenu.show(datasourceTree, e.getX(), e.getY());
					}
					System.out.println("right mouse");
				}
			}

			
			{
				this.this$0 = MyBastisDatasourceForm.this;
				super();
			}
		});
		return myDatasourcePanel;
	}

	private void removeNodeFromTree(DefaultMutableTreeNode parent)
	{
		NewDatabaseInfo o = (NewDatabaseInfo)nodeDatabaseMap.get(parent);
		myNewDatabaseInfos.remove(o);
		nodeDatabaseMap.remove(parent);
		if (o.equals(myState.getActiveDatabaseInfo()))
			myState.setActiveDatabaseInfo(null);
		((MysqlCompleteCacheInteface)ServiceManager.getService(myProject, com/ccnode/codegenerator/view/completion/MysqlCompleteCacheInteface)).cleanAll();
		((DefaultTreeModel)datasourceTree.getModel()).removeNodeFromParent(parent);
	}

	private void $$$setupUI$$$()
	{
		JPanel jpanel;
		JToolBar jtoolbar;
		JButton jbutton;
		createUIComponents();
		jpanel = new JPanel();
		myDatasourcePanel = jpanel;
		jpanel.setLayout(new GridLayoutManager(2, 2, new java.awt.Insets(0, 0, 0, 0), -1, -1, false, false));
		jpanel.setBackground(new java.awt.Color(-1));
		jtoolbar = new JToolBar();
		jpanel.add(jtoolbar, new GridConstraints(0, 0, 1, 2, 0, 1, 6, 0, null, new java.awt.Dimension(-1, 20), null));
		jbutton = new JButton();
		addButton = jbutton;
		jbutton.setText("add");
		jtoolbar.add(jbutton);
		JButton jbutton1;
		jbutton1 = new JButton();
		refreshButton = jbutton1;
		jbutton1.setText("refresh");
		jtoolbar.add(jbutton1);
		JButton jbutton2;
		jbutton2 = new JButton();
		configButton = jbutton2;
		jbutton2.setText("config");
		jtoolbar.add(jbutton2);
		JButton jbutton3;
		jbutton3 = new JButton();
		consoleButton = jbutton3;
		jbutton3.setText("console");
		jtoolbar.add(jbutton3);
		Spacer spacer = new Spacer();
		jpanel.add(spacer, new GridConstraints(1, 1, 1, 1, 0, 2, 1, 6, null, null, null));
		JTree jtree = datasourceTree;
		jpanel.add(jtree, new GridConstraints(1, 0, 1, 1, 0, 3, 6, 6, null, new java.awt.Dimension(150, 50), null));
		return;
	}

	public JComponent $$$getRootComponent$$$()
	{
		return myDatasourcePanel;
	}









}
