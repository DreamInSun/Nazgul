package com.ccnode.codegenerator.database.handler.sqlite;

import com.ccnode.codegenerator.database.handler.utils.TypePropUtils;
import com.ccnode.codegenerator.dialog.datatype.TypeProps;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiType;
import com.intellij.psi.util.PsiTypesUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqliteHandlerUtils {
    private static Map<String, List<TypeProps>> sqliteTypeProps = new HashMap() {
        {
            TypeProps integerSqlTypeProps = new TypeProps("INTEGER", null, "-1");
            sqliteTypeProps.put("java.lang.Integer", newArrayListWithOrder(new TypeProps[]{integerSqlTypeProps}));

            TypeProps LONGNUMBER = new TypeProps("INTEGER", null, "-1");
            sqliteTypeProps.put("java.lang.Long", newArrayListWithOrder(new TypeProps[]{LONGNUMBER}));

            TypeProps FLOATNUMBER = new TypeProps("REAL", null, "-1");
            sqliteTypeProps.put("java.lang.Float", newArrayListWithOrder(new TypeProps[]{FLOATNUMBER}));

            TypeProps DOUBLENUMBER = new TypeProps("REAL", null, "-1");
            sqliteTypeProps.put("java.lang.Double", newArrayListWithOrder(new TypeProps[]{DOUBLENUMBER}));

            TypeProps BOOLEAN = new TypeProps("INTEGER", null, "-1");
            sqliteTypeProps.put("java.lang.Boolean", newArrayListWithOrder(new TypeProps[]{BOOLEAN}));

            TypeProps DECIMAL = new TypeProps("NUMERIC", null, "-1");
            sqliteTypeProps.put("java.math.BigDecimal", newArrayListWithOrder(new TypeProps[]{DECIMAL}));

            TypeProps MEDIUMINT = new TypeProps("INTEGER", null, "-1");

            TypeProps SMALLINT = new TypeProps("INTEGER", null, "-1");
            sqliteTypeProps.put("java.lang.Short", newArrayListWithOrder(new TypeProps[]{SMALLINT, MEDIUMINT}));

            TypeProps DATE = new TypeProps("TEXT", null, "CURRENT_TIMESTAMP");

            TypeProps TIMESTAMP = new TypeProps("INTEGER", null, "CURRENT_TIMESTAMP");

            TypeProps DATE_ASREAL = new TypeProps("REAL", null, "CURRENT_TIMESTAMP");

            sqliteTypeProps.put("java.util.Date", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP, DATE_ASREAL}));
            sqliteTypeProps.put("java.time.LocalDate", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP, DATE_ASREAL}));
            sqliteTypeProps.put("java.time.LocalDateTime", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP, DATE_ASREAL}));

            sqliteTypeProps.put("java.time.LocalTime", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP, DATE_ASREAL}));
            sqliteTypeProps.put("java.sql.Date", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP, DATE_ASREAL}));
            sqliteTypeProps.put("java.sql.Timestamp", newArrayListWithOrder(new TypeProps[]{TIMESTAMP, DATE}));
            sqliteTypeProps.put("java.sql.Time", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP, DATE_ASREAL}));
            sqliteTypeProps.put("java.time.LocalDateTime", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP, DATE_ASREAL}));
            sqliteTypeProps.put("java.time.LocalTime", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP, DATE_ASREAL}));
            sqliteTypeProps.put("java.time.LocalDateTime", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP, DATE_ASREAL}));
            sqliteTypeProps.put("java.time.OffsetDateTime", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP, DATE_ASREAL}));
            sqliteTypeProps.put("java.time.OffsetTime", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP, DATE_ASREAL}));
            sqliteTypeProps.put("java.time.ZonedDateTime", newArrayListWithOrder(new TypeProps[]{DATE, TIMESTAMP, DATE_ASREAL}));

            TypeProps VARCHAR2 = new TypeProps("TEXT", null, "''");
            TypeProps CHAR = new TypeProps("TEXT", null, null);
            sqliteTypeProps.put("java.lang.String", newArrayListWithOrder(new TypeProps[]{VARCHAR2, CHAR}));

            TypeProps BYTE_INTEGER = new TypeProps("INTEGER", null, "-1");
            sqliteTypeProps.put("java.lang.Byte", newArrayListWithOrder(new TypeProps[]{BYTE_INTEGER}));
        }
    };

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
        List<TypeProps> fromMapTypes = (List) sqliteTypeProps.get(canonicalText);
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
        return newArrayListWithOrder(new TypeProps[]{new TypeProps("TEXT", "50", "''"), new TypeProps("NUMERIC", null, null)});
    }

    public static List<TypeProps> getTypePropByQulitifiedName(String name) {
        return (List) sqliteTypeProps.get(name);
    }
}
