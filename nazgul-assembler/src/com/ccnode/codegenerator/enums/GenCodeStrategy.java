// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space
// Source File Name:   GenCodeStrategy.java

package com.ccnode.codegenerator.enums;


public final class GenCodeStrategy extends Enum {

    public static final GenCodeStrategy APPEND_NEW;
    public static final GenCodeStrategy REPLACE_TOTALLY;
    public static final GenCodeStrategy NONE;

    private Integer code;
    private String desc;
    private static final GenCodeStrategy $VALUES[];

    public static GenCodeStrategy[] values() {
        return (GenCodeStrategy[]) $VALUES.clone();
    }

    public static GenCodeStrategy valueOf(String name) {
        return (GenCodeStrategy) Enum.valueOf(GenCodeStrategy.class, name);
    }

    private GenCodeStrategy(String s, int i, Integer code, String desc) {
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

    public static GenCodeStrategy fromName(String name) {
        GenCodeStrategy agencodestrategy[] = values();
        int i = agencodestrategy.length;
        for (int j = 0; j < i; j++) {
            GenCodeStrategy e = agencodestrategy[j];
            if (e.name().equals(name))
                return e;
        }

        return NONE;
    }

    public static GenCodeStrategy fromCode(Integer code) {
        GenCodeStrategy agencodestrategy[] = values();
        int i = agencodestrategy.length;
        for (int j = 0; j < i; j++) {
            GenCodeStrategy e = agencodestrategy[j];
            if (e.getCode().equals(code))
                return e;
        }

        return NONE;
    }

    public static String codeToName(Integer code) {
        GenCodeStrategy o = fromCode(code);
        return o.name();
    }

    public static Integer nameToCode(String name) {
        GenCodeStrategy o = fromName(name);
        return o.getCode();
    }

    static {
        APPEND_NEW = new GenCodeStrategy("APPEND_NEW", 0, Integer.valueOf(0), "");
        REPLACE_TOTALLY = new GenCodeStrategy("REPLACE_TOTALLY", 1, Integer.valueOf(1), "");
        NONE = new GenCodeStrategy("NONE", 2, Integer.valueOf(-1), "none");
        $VALUES = (new GenCodeStrategy[]{
                APPEND_NEW, REPLACE_TOTALLY, NONE
        });
    }
}
