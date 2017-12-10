package com.ccnode.codegenerator.database.handler.mysql;

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

import org.jetbrains.annotations.NotNull;

public class MysqlHandlerUtils {
    private static Map<String, List<TypeProps>> mysqlTypeProps = new HashMap() {

        {
            TypeProps integerSqlTypeProps = new TypeProps("INT", "11", "-1");
            TypeProps unsignedIntegerTypeProps = new TypeProps(unsigned("INT"), "11", "0");
            mysqlTypeProps.put("java.lang.Integer", newArrayListWithOrder(new TypeProps[]{integerSqlTypeProps, unsignedIntegerTypeProps}));

            TypeProps bigIntType = new TypeProps("BIGINT", "15", "-1");
            TypeProps unsignedBigIntType = new TypeProps(unsigned("BIGINT"), "15", "0");
            mysqlTypeProps.put("java.lang.Long", newArrayListWithOrder(new TypeProps[]{bigIntType, unsignedBigIntType}));

            TypeProps FLOAT = new TypeProps("FLOAT", "10,2", "-1.0");
            mysqlTypeProps.put("java.lang.Float", newArrayListWithOrder(new TypeProps[]{FLOAT}));

            TypeProps DOUBLE = new TypeProps("DOUBLE", "16,4", "-1.0");
            mysqlTypeProps.put("java.lang.Double", newArrayListWithOrder(new TypeProps[]{DOUBLE}));

            TypeProps TINYINT = new TypeProps("TINYINT", "3", "0");

            TypeProps UNSIGNED_TINYINT = new TypeProps(unsigned("TINYINT"), "3", "0");
            TypeProps BIT = new TypeProps("BIT", "1", "0");
            mysqlTypeProps.put("java.lang.Boolean", newArrayListWithOrder(new TypeProps[]{TINYINT, UNSIGNED_TINYINT, BIT}));

            TypeProps DECIMAL = new TypeProps("DECIMAL", "13,4", "-1");
            mysqlTypeProps.put("java.math.BigDecimal", newArrayListWithOrder(new TypeProps[]{DECIMAL}));

            TypeProps MEDIUMINT = new TypeProps("MEDIUMINT", "7", "-1");
            TypeProps unsignedMediumInt = new TypeProps(unsigned("MEDIUMINT"), "7", "0");

            TypeProps SMALLINT = new TypeProps("SMALLINT", "5", "-1");
            TypeProps unsignedSMALLINT = new TypeProps(unsigned("SMALLINT"), "5", "0");
            mysqlTypeProps.put("java.lang.Short", newArrayListWithOrder(new TypeProps[]{SMALLINT, unsignedSMALLINT, MEDIUMINT, unsignedMediumInt}));

            mysqlTypeProps.put("java.lang.Byte", newArrayListWithOrder(new TypeProps[]{TINYINT, UNSIGNED_TINYINT}));

            TypeProps DATETIME = new TypeProps("DATETIME", "", "'1000-01-01 00:00:00'");

            TypeProps DATE = new TypeProps("DATE", "", "'1000-01-01'");

            TypeProps TIMESTAMP = new TypeProps("TIMESTAMP", null, "CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP");

            TypeProps TIME = new TypeProps("TIME", null, "'12:00'");

            mysqlTypeProps.put("java.util.Date", newArrayListWithOrder(new TypeProps[]{DATETIME, DATE, TIMESTAMP, TIME}));
            mysqlTypeProps.put("java.time.LocalDate", newArrayListWithOrder(new TypeProps[]{DATETIME, DATE, TIMESTAMP, TIME}));
            mysqlTypeProps.put("java.time.LocalDateTime", newArrayListWithOrder(new TypeProps[]{DATETIME, DATE, TIMESTAMP, TIME}));

            mysqlTypeProps.put("java.time.LocalTime", newArrayListWithOrder(new TypeProps[]{DATETIME, DATE, TIMESTAMP, TIME}));
            mysqlTypeProps.put("java.sql.Date", newArrayListWithOrder(new TypeProps[]{DATETIME, DATE, TIMESTAMP, TIME}));
            mysqlTypeProps.put("java.sql.Timestamp", newArrayListWithOrder(new TypeProps[]{TIMESTAMP, DATE, DATETIME, TIME}));
            mysqlTypeProps.put("java.sql.Time", newArrayListWithOrder(new TypeProps[]{TIME, DATE, TIMESTAMP, DATETIME}));
            mysqlTypeProps.put("java.time.LocalDateTime", newArrayListWithOrder(new TypeProps[]{DATETIME, DATE, TIMESTAMP, TIME}));
            mysqlTypeProps.put("java.time.LocalTime", newArrayListWithOrder(new TypeProps[]{TIME, DATETIME, DATE, TIMESTAMP}));
            mysqlTypeProps.put("java.time.LocalDateTime", newArrayListWithOrder(new TypeProps[]{DATETIME, DATE, TIMESTAMP, TIME}));
            mysqlTypeProps.put("java.time.OffsetDateTime", newArrayListWithOrder(new TypeProps[]{DATETIME, DATE, TIMESTAMP, TIME}));
            mysqlTypeProps.put("java.time.OffsetTime", newArrayListWithOrder(new TypeProps[]{TIME, DATETIME, TIMESTAMP, DATETIME}));
            mysqlTypeProps.put("java.time.ZonedDateTime", newArrayListWithOrder(new TypeProps[]{DATETIME, DATE, TIMESTAMP, TIME}));

            TypeProps VARCHAR = new TypeProps("VARCHAR", "50", "''");
            TypeProps TEXT = new TypeProps("TEXT", null, null);
            TypeProps MEDIUMTEXT = new TypeProps("MEDIUMTEXT", null, "''");
            TypeProps LONGTEXT = new TypeProps("LONGTEXT", null, "''");
            TypeProps TINYTEXT = new TypeProps("TINYTEXT", null, "''");
            TypeProps CHAR = new TypeProps("CHAR", "10", null);
            mysqlTypeProps.put("java.lang.String", newArrayListWithOrder(new TypeProps[]{VARCHAR, TEXT, CHAR, MEDIUMTEXT, LONGTEXT, TINYTEXT}));

            TypeProps BLOB = new TypeProps("BLOB", "", "''");
            TypeProps TINYBLOB = new TypeProps("TINYBLOB", "", "''");
            TypeProps MEDIUMBLOB = new TypeProps("MEDIUMBLOB", "", "''");
            TypeProps LONGBLOB = new TypeProps("LONGBLOB", "", "''");
            mysqlTypeProps.put("java.lang.Byte[]", newArrayListWithOrder(new TypeProps[]{BLOB, MEDIUMBLOB, LONGBLOB, TINYBLOB}));
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

    private static String unsigned(String type) {
        return type + "_" + "UNSIGNED";
    }

    @NotNull
    public static List<TypeProps> getRecommendDatabaseTypeOfFieldType(PsiField psiField) {
        String canonicalText = psiField.getType().getCanonicalText();
        List<TypeProps> fromMapTypes = (List) mysqlTypeProps.get(canonicalText);
        List<TypeProps> typePropss = TypePropUtils.generateFromDefaultMap(fromMapTypes);
        if (typePropss != null) {
            if (psiField.getName().equals("id")) {
                ((TypeProps) typePropss.get(0)).setPrimary(Boolean.valueOf(true));
                ((TypeProps) typePropss.get(0)).setHasDefaultValue(Boolean.valueOf(false));
            } else if (psiField.getName().equalsIgnoreCase("updatetime")) {
                for (TypeProps props : typePropss) {
                    if (props.getDefaultType().equals("TIMESTAMP")) {
                        props.setOrder(Integer.valueOf(-1));
                        break;
                    }
                }
                List<TypeProps> tmp156_155 = typePropss;
                return tmp156_155;
            }
            List<TypeProps> tmp166_165 = typePropss;
            return tmp166_165;
        }
        PsiClass psiClass = PsiTypesUtil.getPsiClass(psiField.getType());
        if ((psiClass == null) || (!psiClass.isEnum())) {
            throw new RuntimeException("field type not support, the field is:" + psiField.getName() + " the type is:" + psiField.getType().getCanonicalText());
        }
        List tmp337_334 = newArrayListWithOrder(new TypeProps[]{new TypeProps("VARCHAR", "50", "''"), new TypeProps("INT", "11", "-1"), new TypeProps("SMALLINT", "5", "-1"), new TypeProps("MEDIUMINT", "7", "-1"), new TypeProps("TINYINT", "3", "0")});
        return tmp337_334;
    }

    public static UnsignedCheckResult checkUnsigned(String chooseType) {
        UnsignedCheckResult result = new UnsignedCheckResult();
        String[] split = chooseType.split("_");
        result.setType(split[0]);
        if ((split.length == 2) && (split[1].equals("UNSIGNED"))) {
            result.setUnsigned(true);
            return result;
        }
        result.setUnsigned(false);
        return result;
    }

    public static List<TypeProps> getTypePropsByQulifiType(String type) {
        return (List) mysqlTypeProps.get(type);
    }
}
