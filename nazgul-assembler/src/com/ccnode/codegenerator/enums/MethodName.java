// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space
// Source File Name:   MethodName.java

package com.ccnode.codegenerator.enums;


public enum MethodName {

    public static final MethodName insert;
    public static final MethodName insertList;
    public static final MethodName select;
    public static final MethodName update;
    public static final MethodName delete;
    public static final MethodName insertSelective;
    public static final MethodName none;

    private Integer code;
    private String desc;
    private static final MethodName $VALUES[];

    public static MethodName[] values() {
        return (MethodName[]) $VALUES.clone();
    }

    public static MethodName valueOf(String name) {
        return (MethodName) Enum.valueOf(MethodName.class, name);
    }

    private MethodName(String s, int i, Integer code, String desc) {
        super(s, i);
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static MethodName fromName(String name) {
        MethodName amethodname[] = values();
        int i = amethodname.length;
        for (int j = 0; j < i; j++) {
            MethodName e = amethodname[j];
            if (e.name().equals(name))
                return e;
        }

        return none;
    }

    public static MethodName fromCode(Integer code) {
        MethodName amethodname[] = values();
        int i = amethodname.length;
        for (int j = 0; j < i; j++) {
            MethodName e = amethodname[j];
            if (e.getCode().equals(code))
                return e;
        }

        return none;
    }

    public static String codeToName(Integer code) {
        MethodName o = fromCode(code);
        return o.name();
    }

    public static Integer nameToCode(String name) {
        MethodName o = fromName(name);
        return o.getCode();
    }

    public Boolean equalWithName(String name) {
        return Boolean.valueOf(this == fromName(name));
    }

    public Boolean equalWithCode(String name) {
        return Boolean.valueOf(this == fromName(name));
    }

    static {
        insert = new MethodName("insert", 0, Integer.valueOf(0), "����");
        insertList = new MethodName("insertList", 1, Integer.valueOf(1), "����");
        select = new MethodName("select", 2, Integer.valueOf(2), "����");
        update = new MethodName("update", 3, Integer.valueOf(3), "����");
        delete = new MethodName("delete", 4, Integer.valueOf(4), "����");
        insertSelective = new MethodName("insertSelective", 5, Integer.valueOf(5), "����");
        none = new MethodName("none", 6, Integer.valueOf(-1), "none");
        $VALUES = (new MethodName[]{
                insert, insertList, select, update, delete, insertSelective, none
        });
    }
    }
