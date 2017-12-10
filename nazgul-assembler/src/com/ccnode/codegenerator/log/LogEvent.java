// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LogEvent.java

package com.ccnode.codegenerator.log;

import org.jetbrains.annotations.NotNull;

public class LogEvent {

    /*========== Properties ==========*/
    private String message;
    private Exception e;
    @NotNull
    private String name;
    @NotNull
    private LoggerLevel level;

    /*========== Constructor ==========*/
    public LogEvent() {
    }

    /*========== Getter & Setter ==========*/
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

    public LoggerLevel getLevel() {
        return this.level;
    }

    public void setLevel(LoggerLevel level) {
        this.level = level;
    }

}
