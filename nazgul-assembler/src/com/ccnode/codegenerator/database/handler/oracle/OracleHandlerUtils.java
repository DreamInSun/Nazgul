package com.ccnode.codegenerator.database.handler.oracle;

import com.ccnode.codegenerator.database.handler.utils.TypePropUtils;
import com.ccnode.codegenerator.dialog.GenCodeProp;
import com.ccnode.codegenerator.dialog.datatype.TypeProps;
import com.google.common.collect.Lists;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiType;
import com.intellij.psi.util.PsiTypesUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OracleHandlerUtils {
    private static Map<String, List<TypeProps>> oracleTypeProps =  new HashMap()
    {
        {
            TypeProps integerSqlTypeProps = new TypeProps("NUMBER", "12", "-1");
            oracleTypeProps.put("java.lang.Integer", newArrayListWithOrder(new TypeProps[]{integerSqlTypeProps}));

            TypeProps LONGNUMBER = new TypeProps("NUMBER", "24", "-1");
            oracleTypeProps.put("java.lang.Long", newArrayListWithOrder(new TypeProps[]{LONGNUMBER}));

            TypeProps FLOATNUMBER = new TypeProps("NUMBER", "10,2", "-1");
            oracleTypeProps.put("java.lang.Float", newArrayListWithOrder(new TypeProps[]{FLOATNUMBER}));

            TypeProps DOUBLENUMBER = new TypeProps("NUMBER", "16.4", "-1");
            oracleTypeProps.put("java.lang.Double", newArrayListWithOrder(new TypeProps[]{DOUBLENUMBER}));

            TypeProps BOOLEAN = new TypeProps("boolean", null, "-1");
            oracleTypeProps.put("java.lang.Boolean", newArrayListWithOrder(new TypeProps[]{BOOLEAN}));

            TypeProps DECIMAL = new TypeProps("NUMBER", "13,4", "-1");
            oracleTypeProps.put("java.math.BigDecimal", newArrayListWithOrder(new TypeProps[]{DECIMAL}));

            TypeProps MEDIUMINT = new TypeProps("NUMBER", "12", "-1");

            TypeProps SMALLINT = new TypeProps("NUMBER", "5", "-1");
            oracleTypeProps.put("java.lang.Short", newArrayListWithOrder(new TypeProps[]{SMALLINT, MEDIUMINT}));

            TypeProps DATE = new TypeProps("DATE", "", "SYSDATE");

            TypeProps TIMESTAMP = new TypeProps("TIMESTAMP", null, "SYSDATE");

            oracleTypeProps.put("java.util.Date", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP}));
            oracleTypeProps.put("java.time.LocalDate", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP}));
            oracleTypeProps.put("java.time.LocalDateTime", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP}));

            oracleTypeProps.put("java.time.LocalTime", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP}));
            oracleTypeProps.put("java.sql.Date", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP}));
            oracleTypeProps.put("java.sql.Timestamp", newArrayListWithOrder(new TypeProps[]{TIMESTAMP, DATE}));
            oracleTypeProps.put("java.sql.Time", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP}));
            oracleTypeProps.put("java.time.LocalDateTime", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP}));
            oracleTypeProps.put("java.time.LocalTime", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP}));
            oracleTypeProps.put("java.time.LocalDateTime", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP}));
            oracleTypeProps.put("java.time.OffsetDateTime", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP}));
            oracleTypeProps.put("java.time.OffsetTime", newArrayListWithOrder(new TypeProps[]{TIMESTAMP}));
            oracleTypeProps.put("java.time.ZonedDateTime", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP}));

            TypeProps VARCHAR2 = new TypeProps("VARCHAR2", "50", "''");
            TypeProps CHAR = new TypeProps("CHAR", "50", null);
            oracleTypeProps.put("java.lang.String", newArrayListWithOrder(new TypeProps[]{VARCHAR2, CHAR}));

            TypeProps BYTE_NUMBER = new TypeProps("NUMBER", "8", "-1");
            oracleTypeProps.put("java.lang.Byte", newArrayListWithOrder(new TypeProps[]{BYTE_NUMBER}));
        }
    }

    ;


    public static List<TypeProps> newArrayListWithOrder(TypeProps... typePropArray) {
        List<TypeProps> typePropslist = Lists.newArrayList();
        for (int i = 0; i < typePropArray.length; i++) {
            typePropArray[i].setOrder(Integer.valueOf(i));
            typePropslist.add(typePropArray[i]);
        }
        return typePropslist;
    }

    public static List<TypeProps> getRecommendDatabaseTypeOfFieldType(PsiField field) {
        String canonicalText = field.getType().getCanonicalText();
        List<TypeProps> fromMapTypes = (List) oracleTypeProps.get(canonicalText);
        List<TypeProps> typePropss = TypePropUtils.generateFromDefaultMap(fromMapTypes);
        if (typePropss != null) {
            if (field.getName().equals("id")) {
                ((TypeProps) typePropss.get(0)).setPrimary(Boolean.valueOf(true));
                ((TypeProps) typePropss.get(0)).setHasDefaultValue(Boolean.valueOf(false));
            }
            return typePropss;
        }
        PsiClass psiClass = PsiTypesUtil.getPsiClass(field.getType());
        if ((psiClass == null) || (!psiClass.isEnum())) {
            throw new RuntimeException("field type not support, the field is:" + field.getName() + " the type is:" + field.getType().getCanonicalText());
        }
        return newArrayListWithOrder(new TypeProps[]{new TypeProps("VARCHAR2", "50", "''"), new TypeProps("CHAR", null, null)});
    }

    public static List<TypeProps> getTypePropByQulitifiedName(String name) {
        return (List) oracleTypeProps.get(name);
    }

    public static String extractJdbcType(GenCodeProp primaryProp) {
        switch (primaryProp.getFiledType()) {
            case "NUMBER":
                return "NUMERIC";
            case "DATE":
                return "DATE";
            case "CHAR":
                return "CHAR";
            case "VARCHAR2":
                return "VARCHAR";
        }
        throw new RuntimeException("the primary key must be string or number");
    }
}
