// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SqliteGenerateFilesHandler.java

package com.ccnode.codegenerator.database.handler.sqlite;

import com.ccnode.codegenerator.database.ClassValidateResult;
import com.ccnode.codegenerator.database.handler.*;
import com.ccnode.codegenerator.dialog.GenCodeProp;
import com.ccnode.codegenerator.util.DateUtil;
import com.ccnode.codegenerator.util.GenCodeUtil;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTypesUtil;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

// Referenced classes of package com.ccnode.codegenerator.database.handler.sqlite:
//			SqliteHandlerUtils

public class SqliteGenerateFilesHandler implements GenerateFileHandler {

    class SqliteFieldValidator implements FieldValidator {

        final SqliteGenerateFilesHandler this$0;

        public boolean isValidField(PsiField psiField) {
            String canonicalText = psiField.getType().getCanonicalText();
            List typePropss = SqliteHandlerUtils.getTypePropByQulitifiedName(canonicalText);
            if (typePropss != null)
                return true;
            PsiClass psiClass = PsiTypesUtil.getPsiClass(psiField.getType());
            return psiClass != null && psiClass.isEnum();
        }

        SqliteFieldValidator() {
            super();
            this.this$0 = SqliteGenerateFilesHandler.this;

        }
    }


    private static volatile SqliteGenerateFilesHandler instance;
    private static volatile HandlerValidator handlerValidator;

    private SqliteGenerateFilesHandler() {
    }

    public static SqliteGenerateFilesHandler getInstance() {
        if (instance == null)
            synchronized (SqliteGenerateFilesHandler.class) {
                if (instance == null)
                    instance = new SqliteGenerateFilesHandler();
            }
        return instance;
    }

    @NotNull
    public List getRecommendDatabaseTypeOfFieldType(PsiField field) {
        List list = SqliteHandlerUtils.getRecommendDatabaseTypeOfFieldType(field);
        return list;
    }

    @NotNull
    public String generateSql(List propList, GenCodeProp primaryKey, String tableName, List multipleColumnIndex, List multipleColumnUnique) {
        String s;
        List retList = Lists.newArrayList();
        retList.add(String.format("-- auto Generated on %s ", new Object[]{
                DateUtil.formatLong(new Date())
        }));
        retList.add((new StringBuilder()).append("-- DROP TABLE IF EXISTS ").append(tableName).append("; ").toString());
        retList.add((new StringBuilder()).append("CREATE TABLE ").append(tableName).append("(").toString());
        List indexText = Lists.newArrayList();
        List uniques = Lists.newArrayList();
        for (int i = 0; i < propList.size(); i++) {
            GenCodeProp field = (GenCodeProp) propList.get(i);
            String fieldSql = genfieldSql(field);
            if (i == propList.size() - 1)
                fieldSql = fieldSql.substring(0, fieldSql.length() - 1);
            retList.add(fieldSql);
            if (field.getIndex().booleanValue())
                indexText.add((new StringBuilder()).append("CREATE INDEX ix_").append(field.getColumnName()).append(" ON ").append(tableName).append(" (").append(field.getColumnName()).append(");").toString());
            if (field.getUnique().booleanValue())
                uniques.add((new StringBuilder()).append("\tCREATE UNIQUE INDEX ux_").append(field.getColumnName()).append(" ON ").append(tableName).append(" (").append(field.getColumnName()).append(");").toString());
        }

        retList.add(");");
        String text;
        for (Iterator iterator = multipleColumnIndex.iterator(); iterator.hasNext(); indexText.add(text)) {
            List columnIndex = (List) iterator.next();
            text = "CREATE INDEX ix_";
            for (Iterator iterator2 = columnIndex.iterator(); iterator2.hasNext(); ) {
                String index = (String) iterator2.next();
                text = (new StringBuilder()).append(text).append(index).append("_").toString();
            }

            text = text.substring(0, text.length() - 1);
            text = (new StringBuilder()).append(text).append(" ON ").append(tableName).append(" (").toString();
            for (Iterator iterator3 = columnIndex.iterator(); iterator3.hasNext(); ) {
                String index = (String) iterator3.next();
                text = (new StringBuilder()).append(text).append(index).append(",").toString();
            }

            text = text.substring(0, text.length() - 1);
            text = (new StringBuilder()).append(text).append(");").toString();
        }

        for (Iterator iterator1 = multipleColumnUnique.iterator(); iterator1.hasNext(); uniques.add(text)) {
            List columnUnique = (List) iterator1.next();
            text = "\tCREATE UNIQUE INDEX ux_";
            for (Iterator iterator4 = columnUnique.iterator(); iterator4.hasNext(); ) {
                String index = (String) iterator4.next();
                text = (new StringBuilder()).append(text).append(index).append("_").toString();
            }

            text = text.substring(0, text.length() - 1);
            text = (new StringBuilder()).append(text).append(" ON ").append(tableName).append(" (").toString();
            for (Iterator iterator5 = columnUnique.iterator(); iterator5.hasNext(); ) {
                String index = (String) iterator5.next();
                text = (new StringBuilder()).append(text).append(index).append(",").toString();
            }

            text = text.substring(0, text.length() - 1);
            text = (new StringBuilder()).append(text).append(");").toString();
        }

        retList.addAll(uniques);
        retList.addAll(indexText);
        s = Joiner.on("\n").join(retList);
        return s;
    }

    @NotNull
    public ClassValidateResult validateCurrentClass(PsiClass psiClass) {
        ClassValidateResult classvalidateresult;
        if (handlerValidator == null)
            synchronized (SqliteGenerateFilesHandler.class) {
                if (handlerValidator == null)
                    handlerValidator = new HandlerValidator(new SqliteFieldValidator());
            }
        classvalidateresult = handlerValidator.validateResult(psiClass);
        return classvalidateresult;

    }

    private String genfieldSql(GenCodeProp field) {
        StringBuilder ret = new StringBuilder();
        ret.append("\t").append(field.getColumnName()).append(" ").append(field.getFiledType());
        if (StringUtils.isNotBlank(field.getSize()))
            ret.append((new StringBuilder()).append("(").append(field.getSize()).append(")").toString());
        if (field.getPrimaryKey().booleanValue())
            ret.append(" PRIMARY KEY");
        if (!field.getCanBeNull().booleanValue())
            ret.append(" NOT NULL");
        if (!field.getPrimaryKey().booleanValue() && field.getHasDefaultValue().booleanValue() && StringUtils.isNotBlank(field.getDefaultValue()))
            ret.append((new StringBuilder()).append(" DEFAULT ").append(field.getDefaultValue()).toString());
        ret.append(",");
        return ret.toString();
    }

}
