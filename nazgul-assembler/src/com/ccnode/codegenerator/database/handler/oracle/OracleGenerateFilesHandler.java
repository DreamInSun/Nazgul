// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   OracleGenerateFilesHandler.java

package com.ccnode.codegenerator.database.handler.oracle;

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

// Referenced classes of package com.ccnode.codegenerator.database.handler.oracle:
//			OracleHandlerUtils

public class OracleGenerateFilesHandler
        implements GenerateFileHandler {
    class OracleFieldValidator
            implements FieldValidator {

        final OracleGenerateFilesHandler this$0;

        public boolean isValidField(PsiField psiField) {
            String canonicalText = psiField.getType().getCanonicalText();
            List typePropss = OracleHandlerUtils.getTypePropByQulitifiedName(canonicalText);
            if (typePropss != null)
                return true;
            PsiClass psiClass = PsiTypesUtil.getPsiClass(psiField.getType());
            return psiClass != null && psiClass.isEnum();
        }

        OracleFieldValidator() {
            super();
            this.this$0 = OracleGenerateFilesHandler.this;
        }
    }


    private static volatile OracleGenerateFilesHandler instance;
    private static volatile HandlerValidator handlerValidator;

    private OracleGenerateFilesHandler() {
    }

    public static OracleGenerateFilesHandler getInstance() {
        if (instance == null)
            synchronized (OracleGenerateFilesHandler.class) {
                if (instance == null)
                    instance = new OracleGenerateFilesHandler();
            }
        return instance;
    }

    @NotNull
    public List getRecommendDatabaseTypeOfFieldType(PsiField field) {
        List list = OracleHandlerUtils.getRecommendDatabaseTypeOfFieldType(field);
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
        Iterator iterator = propList.iterator();
        do {
            if (!iterator.hasNext())
                break;
            GenCodeProp field = (GenCodeProp) iterator.next();
            String fieldSql = genfieldSql(field);
            retList.add(fieldSql);
            if (field.getIndex().booleanValue())
                indexText.add((new StringBuilder()).append("CREATE INDEX ix_").append(field.getColumnName()).append(" ON ").append(tableName).append(" (").append(field.getColumnName()).append(");").toString());
            if (field.getUnique().booleanValue())
                uniques.add((new StringBuilder()).append("\tCONSTRAINT ux_").append(field.getColumnName()).append(" UNIQUE(").append(field.getColumnName()).append("),").toString());
        } while (true);
        String text;
        for (Iterator iterator1 = multipleColumnIndex.iterator(); iterator1.hasNext(); indexText.add(text)) {
            List columnIndex = (List) iterator1.next();
            text = "CREATE INDEX ix_";
            for (Iterator iterator3 = columnIndex.iterator(); iterator3.hasNext(); ) {
                String index = (String) iterator3.next();
                text = (new StringBuilder()).append(text).append(index).append("_").toString();
            }

            text = text.substring(0, text.length() - 1);
            text = (new StringBuilder()).append(text).append(" ON ").append(tableName).append(" (").toString();
            for (Iterator iterator4 = columnIndex.iterator(); iterator4.hasNext(); ) {
                String index = (String) iterator4.next();
                text = (new StringBuilder()).append(text).append(index).append(",").toString();
            }

            text = text.substring(0, text.length() - 1);
            text = (new StringBuilder()).append(text).append(");").toString();
        }

        for (Iterator iterator2 = multipleColumnUnique.iterator(); iterator2.hasNext(); uniques.add(text)) {
            List columnUnique = (List) iterator2.next();
            text = "\tCONSTRAINT ux_";
            for (Iterator iterator5 = columnUnique.iterator(); iterator5.hasNext(); ) {
                String index = (String) iterator5.next();
                text = (new StringBuilder()).append(text).append(index).append("_").toString();
            }

            text = text.substring(0, text.length() - 1);
            text = (new StringBuilder()).append(text).append(" UNIQUE(").toString();
            for (Iterator iterator6 = columnUnique.iterator(); iterator6.hasNext(); ) {
                String index = (String) iterator6.next();
                text = (new StringBuilder()).append(text).append(index).append(",").toString();
            }

            text = text.substring(0, text.length() - 1);
            text = (new StringBuilder()).append(text).append("),").toString();
        }

        retList.addAll(uniques);
        retList.add((new StringBuilder()).append("\tCONSTRAINT ").append(tableName).append("_pk PRIMARY KEY (").append(primaryKey.getColumnName()).append("));").toString());
        retList.add(generateAutoIncrementAndTrigger(tableName, primaryKey.getColumnName()));
        retList.addAll(indexText);
        s = Joiner.on("\n").join(retList);
        return s;
    }

    @NotNull
    public ClassValidateResult validateCurrentClass(PsiClass psiClass) {
        ClassValidateResult classvalidateresult;
        if (handlerValidator == null)
            synchronized (OracleGenerateFilesHandler.class) {
                if (handlerValidator == null)
                    handlerValidator = new HandlerValidator(new OracleFieldValidator());
            }
        classvalidateresult = handlerValidator.validateResult(psiClass);
        return classvalidateresult;
    }

    private String genfieldSql(GenCodeProp field) {
        StringBuilder ret = new StringBuilder();
        ret.append("\t").append(field.getColumnName()).append(" ").append(field.getFiledType());
        if (StringUtils.isNotBlank(field.getSize()))
            ret.append((new StringBuilder()).append("(").append(field.getSize()).append(")").toString());
        if (!field.getPrimaryKey().booleanValue() && field.getHasDefaultValue().booleanValue() && StringUtils.isNotBlank(field.getDefaultValue()))
            ret.append((new StringBuilder()).append(" DEFAULT ").append(field.getDefaultValue()).toString());
        if (!field.getCanBeNull().booleanValue())
            ret.append(" NOT NULL");
        ret.append(",");
        return ret.toString();
    }

    private static String generateAutoIncrementAndTrigger(String tableName, String primaryColumnName) {
        return (new StringBuilder()).append("create sequence ").append(tableName).append("_seq start with 1 increment by 1 nomaxvalue;\ncreate trigger ").append(tableName).append("_trigger\nbefore insert on ").append(tableName).append("\nfor each row\n   begin\n     select ").append(tableName).append("_seq.nextval into :new.").append(primaryColumnName).append(" from dual;\n   end;").toString();
    }

}
