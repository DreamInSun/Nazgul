// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MysqlGenerateFileHandler.java

package com.ccnode.codegenerator.database.handler.mysql;

import com.ccnode.codegenerator.database.ClassValidateResult;
import com.ccnode.codegenerator.database.DatabaseComponenent;
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

// Referenced classes of package com.ccnode.codegenerator.database.handler.mysql:
//			UnsignedCheckResult, MysqlHandlerUtils

public class MysqlGenerateFileHandler implements GenerateFileHandler {

    class MysqlFieldValidator implements FieldValidator {
        final MysqlGenerateFileHandler this$0;

        MysqlFieldValidator() {
            super();
            this.this$0 = MysqlGenerateFileHandler.this;
        }

        public boolean isValidField(PsiField psiField) {
            String canonicalText = psiField.getType().getCanonicalText();
            List typePropss = MysqlHandlerUtils.getTypePropsByQulifiType(canonicalText);
            if (typePropss != null)
                return true;
            PsiClass psiClass = PsiTypesUtil.getPsiClass(psiField.getType());
            return psiClass != null && psiClass.isEnum();
        }


    }


    private static volatile MysqlGenerateFileHandler instance;
    private static volatile HandlerValidator handlerValidator;

    @NotNull
    public List getRecommendDatabaseTypeOfFieldType(PsiField field) {
        return MysqlHandlerUtils.getRecommendDatabaseTypeOfFieldType(field);
    }

    private MysqlGenerateFileHandler() {
        if (instance != null)
            throw new IllegalStateException("Already initialized.");
        else
            return;
    }

    public static MysqlGenerateFileHandler getInstance() {
        MysqlGenerateFileHandler result = instance;
        if (result == null)
            synchronized (MysqlGenerateFileHandler.class) {
                result = instance;
                if (result == null)
                    instance = result = new MysqlGenerateFileHandler();
            }
        return result;
    }

    @NotNull
    public String generateSql(List propList, GenCodeProp primaryKey, String tableName, List multipleColumnIndex, List multipleColumnUnique) {
        String s;
        List retList = Lists.newArrayList();
        String newTableName = DatabaseComponenent.formatColumn(tableName);
        retList.add(String.format("-- auto Generated on %s ", new Object[]{
                DateUtil.formatLong(new Date())
        }));
        retList.add((new StringBuilder()).append("-- DROP TABLE IF EXISTS ").append(newTableName).append("; ").toString());
        retList.add((new StringBuilder()).append("CREATE TABLE ").append(newTableName).append("(").toString());
        List indexText = Lists.newArrayList();
        Iterator iterator = propList.iterator();
        do {
            if (!iterator.hasNext())
                break;
            GenCodeProp field = (GenCodeProp) iterator.next();
            String fieldSql = genfieldSql(field);
            retList.add(fieldSql);
            if (field.getIndex().booleanValue())
                indexText.add((new StringBuilder()).append("\tINDEX(").append(field.getColumnName()).append("),").toString());
        } while (true);
        retList.addAll(indexText);
        if (!multipleColumnIndex.isEmpty()) {
            String m;
            for (Iterator iterator1 = multipleColumnIndex.iterator(); iterator1.hasNext(); retList.add(m)) {
                List strings = (List) iterator1.next();
                m = "INDEX `ix_";
                for (Iterator iterator3 = strings.iterator(); iterator3.hasNext(); ) {
                    String string = (String) iterator3.next();
                    m = (new StringBuilder()).append(m).append(string).append("_").toString();
                }

                m = m.substring(0, m.length() - 1);
                m = (new StringBuilder()).append(m).append("`(").toString();
                for (Iterator iterator4 = strings.iterator(); iterator4.hasNext(); ) {
                    String string = (String) iterator4.next();
                    m = (new StringBuilder()).append(m).append(string).append(",").toString();
                }

                m = m.substring(0, m.length() - 1);
                m = (new StringBuilder()).append(m).append("),").toString();
            }

        }
        if (!multipleColumnUnique.isEmpty()) {
            String m;
            for (Iterator iterator2 = multipleColumnUnique.iterator(); iterator2.hasNext(); retList.add(m)) {
                List strings = (List) iterator2.next();
                m = "UNIQUE `ux_";
                for (Iterator iterator5 = strings.iterator(); iterator5.hasNext(); ) {
                    String string = (String) iterator5.next();
                    m = (new StringBuilder()).append(m).append(string).append("_").toString();
                }

                m = m.substring(0, m.length() - 1);
                m = (new StringBuilder()).append(m).append("`(").toString();
                for (Iterator iterator6 = strings.iterator(); iterator6.hasNext(); ) {
                    String string = (String) iterator6.next();
                    m = (new StringBuilder()).append(m).append(string).append(",").toString();
                }

                m = m.substring(0, m.length() - 1);
                m = (new StringBuilder()).append(m).append("),").toString();
            }

        }
        retList.add((new StringBuilder()).append("\tPRIMARY KEY (").append(DatabaseComponenent.formatColumn(primaryKey.getColumnName())).append(")").toString());
        retList.add(String.format(")ENGINE=%s DEFAULT CHARSET=%s COMMENT '%s';", new Object[]{
                "InnoDB", "utf8", newTableName
        }));
        return Joiner.on("\n").join(retList);
    }

    private static String genfieldSql(GenCodeProp field) {
        StringBuilder ret = new StringBuilder();
        UnsignedCheckResult result = MysqlHandlerUtils.checkUnsigned(field.getFiledType());
        ret.append("\t").append(DatabaseComponenent.formatColumn(field.getColumnName())).append(" ").append(result.getType());
        if (StringUtils.isNotBlank(field.getSize()))
            ret.append((new StringBuilder()).append(" (").append(field.getSize()).append(")").toString());
        if (result.isUnsigned())
            ret.append(" UNSIGNED");
        if (field.getUnique().booleanValue())
            ret.append(" UNIQUE");
        if (!field.getCanBeNull().booleanValue())
            ret.append(" NOT NULL");
        if (!field.getPrimaryKey().booleanValue() && field.getHasDefaultValue().booleanValue() && StringUtils.isNotBlank(field.getDefaultValue()))
            ret.append((new StringBuilder()).append(" DEFAULT ").append(field.getDefaultValue()).toString());
        if (field.getPrimaryKey().booleanValue())
            ret.append(" AUTO_INCREMENT");
        ret.append((new StringBuilder()).append(" COMMENT '").append(field.getComment()).append("',").toString());
        return ret.toString();
    }

    @NotNull
    public ClassValidateResult validateCurrentClass(PsiClass psiClass) {
        ClassValidateResult classvalidateresult;
        if (handlerValidator == null)
            synchronized (MysqlGenerateFileHandler.class) {
                if (handlerValidator == null)
                    handlerValidator = new HandlerValidator(new MysqlFieldValidator());
            }
        return handlerValidator.validateResult(psiClass);

    }
}
