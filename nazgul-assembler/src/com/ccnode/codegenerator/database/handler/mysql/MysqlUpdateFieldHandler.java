// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MysqlUpdateFieldHandler.java

package com.ccnode.codegenerator.database.handler.mysql;

import com.ccnode.codegenerator.database.handler.UpdateFieldHandler;
import com.ccnode.codegenerator.dialog.GenCodeProp;
import com.ccnode.codegenerator.dialog.dto.mybatis.ColumnAndField;
import com.intellij.psi.PsiField;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

// Referenced classes of package com.ccnode.codegenerator.database.handler.mysql:
//			UnsignedCheckResult, MysqlHandlerUtils

public class MysqlUpdateFieldHandler
	implements UpdateFieldHandler
{

	private static volatile MysqlUpdateFieldHandler mInstance;

	private MysqlUpdateFieldHandler()
	{
	}

	public static MysqlUpdateFieldHandler getInstance()
	{
		if (mInstance == null)
			synchronized ( MysqlUpdateFieldHandler.class)
			{
				if (mInstance == null)
					mInstance = new MysqlUpdateFieldHandler();
			}
		return mInstance;
	}

	public String generateUpdateSql(List newAddedProps, String tableName, List deletedFields)
	{
		StringBuilder ret = new StringBuilder();
		for (Iterator iterator = newAddedProps.iterator(); iterator.hasNext(); ret.append(";"))
		{
			GenCodeProp field = (GenCodeProp)iterator.next();
			ret.append((new StringBuilder()).append("ALTER TABLE ").append(tableName).append("\n\tADD ").append(field.getColumnName()).toString());
			UnsignedCheckResult result = MysqlHandlerUtils.checkUnsigned(field.getFiledType());
			ret.append((new StringBuilder()).append(" ").append(result.getType()).toString());
			if (StringUtils.isNotBlank(field.getSize()))
				ret.append((new StringBuilder()).append("(").append(field.getSize()).append(")").toString());
			if (result.isUnsigned())
				ret.append(" UNSIGNED");
			if (field.getUnique().booleanValue())
				ret.append(" UNIQUE");
			if (!field.getCanBeNull().booleanValue())
				ret.append(" NOT NULL");
			if (field.getHasDefaultValue().booleanValue() && StringUtils.isNotBlank(field.getDefaultValue()))
				ret.append((new StringBuilder()).append(" DEFAULT ").append(field.getDefaultValue()).toString());
			if (field.getPrimaryKey().booleanValue())
				ret.append(" AUTO_INCREMENT");
			ret.append((new StringBuilder()).append(" COMMENT '").append(field.getFieldName()).append("'").toString());
			if (field.getIndex().booleanValue())
				ret.append((new StringBuilder()).append(",\n\tADD INDEX (").append(field.getColumnName()).append(")").toString());
		}

		ColumnAndField deletedField;
		for (Iterator iterator1 = deletedFields.iterator(); iterator1.hasNext(); ret.append((new StringBuilder()).append(deletedField.getColumn()).append(";").toString()))
		{
			deletedField = (ColumnAndField)iterator1.next();
			ret.append((new StringBuilder()).append("ALTER TABLE ").append(tableName).append(" DROP COLUMN ").toString());
		}

		return ret.toString();
	}

	@NotNull
	public List getRecommendDatabaseTypeOfFieldType(PsiField field)
	{
		return MysqlHandlerUtils.getRecommendDatabaseTypeOfFieldType(field);
	}

}
