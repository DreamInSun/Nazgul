// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenClassDialog.java

package com.ccnode.codegenerator.database;

import com.ccnode.codegenerator.dialog.MapperUtil;
import com.ccnode.codegenerator.methodnameparser.parsedresult.find.FetchProp;
import com.ccnode.codegenerator.pojo.FieldToColumnRelation;
import com.ccnode.codegenerator.util.GenCodeUtil;
import com.ccnode.codegenerator.util.PsiClassUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import org.apache.commons.lang3.StringUtils;

// Referenced classes of package com.ccnode.codegenerator.database:
//			FieldInfo, GenClassInfo, NewClassFieldInfo, DbUtils

public class GenClassDialog extends DialogWrapper
{

	private Project myProject;
	private java.util.List fetchPropList;
	private Map fieldMap;
	private String methodName;
	private String classPackageName;
	private java.util.List fieldInfoList;
	private java.util.List columnAndFields;
	private FieldToColumnRelation relation;
	private JTable myJtable;
	private JTextField classFolderText;
	private String modulePath;
	private JTextField resultMapText;
	private JTextField classNameText;
	private String classQutifiedName;
	private GenClassInfo genClassInfo;
	private FieldToColumnRelation extractFieldToColumnRelation;

	public GenClassDialog(Project project, java.util.List props, Map fieldMap, String methodName, FieldToColumnRelation relation, PsiClass srcClass)
	{
		super(project, true);
		columnAndFields = new ArrayList();
		myProject = project;
		fetchPropList = props;
		this.fieldMap = fieldMap;
		this.methodName = methodName;
		this.relation = relation;
		fieldInfoList = buildClassInfo(props, fieldMap, relation);
		classFolderText = new JTextField(srcClass.getContainingFile().getVirtualFile().getParent().getPath());
		classNameText = new JTextField(GenCodeUtil.getUpperStart((new StringBuilder()).append(methodName).append("Result").toString()));
		resultMapText = new JTextField((new StringBuilder()).append(methodName).append("Result").toString());
		modulePath = PsiClassUtil.getModuleSrcPathOfClass(srcClass);
		myJtable = new JTable(extractValue(fieldInfoList), new String[] {
			"columnName", "fieldName", "fieldType"
		}) {

			final GenClassDialog this$0;

			public boolean isCellEditable(int row, int column)
			{
				return column == 1;
			}

			
			{
				this.this$0 = GenClassDialog.this;
				super(x0, x1);
			}
		};
		myJtable.getTableHeader().setReorderingAllowed(false);
		setTitle("create new Class for the result");
		init();
	}

	private static Object[][] extractValue(java.util.List fieldInfos)
	{
		Object values[][] = new Object[fieldInfos.size()][];
		for (int i = 0; i < fieldInfos.size(); i++)
		{
			values[i] = new String[3];
			values[i][0] = ((FieldInfo)fieldInfos.get(i)).getColumnName();
			values[i][1] = ((FieldInfo)fieldInfos.get(i)).getFieldName();
			values[i][2] = ((FieldInfo)fieldInfos.get(i)).getFieldType();
		}

		return values;
	}

	private java.util.List buildClassInfo(java.util.List props, Map fieldMap, FieldToColumnRelation relation)
	{
		ArrayList fieldInfos = new ArrayList();
		for (Iterator iterator = props.iterator(); iterator.hasNext();)
		{
			FetchProp prop = (FetchProp)iterator.next();
			if (prop.getFetchFunction() == null)
			{
				FieldInfo fieldInfo = new FieldInfo();
				String columnName = relation.getPropColumn(prop.getFetchProp());
				fieldInfo.setFieldName(prop.getFetchProp());
				fieldInfo.setFieldType((String)fieldMap.get(prop.getFetchProp()));
				fieldInfo.setColumnName(columnName);
				fieldInfos.add(fieldInfo);
			} else
			{
				String column = DbUtils.buildSelectFunctionVal(prop);
				FieldInfo fieldInfo = new FieldInfo();
				fieldInfo.setColumnName(column);
				fieldInfo.setFieldName(column);
				fieldInfo.setFieldType(DbUtils.getReturnClassFromFunction(fieldMap, prop.getFetchFunction(), prop.getFetchProp()));
				fieldInfos.add(fieldInfo);
			}
		}

		return fieldInfos;
	}

	protected JComponent createCenterPanel()
	{
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints bag = new GridBagConstraints();
		int mygridy = 0;
		bag.anchor = 18;
		bag.fill = 1;
		bag.gridwidth = 1;
		bag.weightx = 1.0D;
		bag.weighty = 1.0D;
		bag.gridy = mygridy++;
		bag.gridx = 0;
		jPanel.add(new JLabel("className"), bag);
		bag.gridx = 1;
		jPanel.add(classNameText, bag);
		bag.gridy = 1;
		bag.gridx = 0;
		jPanel.add(new JLabel("resultMapId:"), bag);
		bag.gridx = 1;
		jPanel.add(resultMapText, bag);
		bag.gridy = 3;
		bag.gridx = 0;
		jPanel.add(new JLabel(" class location:"), bag);
		bag.gridx = 1;
		jPanel.add(classFolderText, bag);
		bag.gridy = 4;
		bag.gridx = 1;
		JScrollPane jScrollPane = new JScrollPane(myJtable);
		jPanel.add(jScrollPane, bag);
		return jPanel;
	}

	protected void doOKAction()
	{
		String className = classNameText.getText();
		if (StringUtils.isBlank(className))
		{
			Messages.showErrorDialog(myProject, "the className is empty, please reinput", "validefail");
			return;
		}
		String folder = classFolderText.getText();
		String packageToModule = PsiClassUtil.getPackageToModule(folder, modulePath);
		int rowCount = myJtable.getRowCount();
		Set importList = Sets.newHashSet();
		GenClassInfo info = new GenClassInfo();
		java.util.List fieldInfos = Lists.newArrayList();
		extractFieldToColumnRelation = new FieldToColumnRelation();
		extractFieldToColumnRelation.setResultMapId(resultMapText.getText());
		Map fieldToColumnMap = new LinkedHashMap();
		extractFieldToColumnRelation.setFiledToColumnMap(fieldToColumnMap);
		for (int i = 0; i < rowCount; i++)
		{
			String columnName = (String)myJtable.getValueAt(i, 0);
			String fieldName = (String)myJtable.getValueAt(i, 1);
			String fieldType = (String)myJtable.getValueAt(i, 2);
			fieldToColumnMap.put(fieldName, columnName);
			NewClassFieldInfo e = new NewClassFieldInfo();
			e.setFieldName(fieldName);
			e.setFieldShortType(MapperUtil.extractClassShortName(fieldType));
			if (checkIsNeedImport(fieldType))
				importList.add(fieldType);
			fieldInfos.add(e);
		}

		info.setClassFullPath((new StringBuilder()).append(folder).append("/").append(className).append(".java").toString());
		info.setClassFieldInfos(fieldInfos);
		info.setClassName(className);
		info.setClassPackageName(packageToModule);
		info.setImportList(importList);
		genClassInfo = info;
		classQutifiedName = (new StringBuilder()).append(packageToModule).append(".").append(className).toString();
		super.doOKAction();
	}

	private boolean checkIsNeedImport(String s)
	{
		return s != null && !s.startsWith("java.lang");
	}

	public java.util.List getColumnAndFields()
	{
		return columnAndFields;
	}

	public String getClassQutifiedName()
	{
		return classQutifiedName;
	}

	public FieldToColumnRelation getExtractFieldToColumnRelation()
	{
		return extractFieldToColumnRelation;
	}

	public GenClassInfo getGenClassInfo()
	{
		return genClassInfo;
	}
}
