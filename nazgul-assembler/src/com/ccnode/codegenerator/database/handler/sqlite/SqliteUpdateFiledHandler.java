// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SqliteUpdateFiledHandler.java

package com.ccnode.codegenerator.database.handler.sqlite;

import com.ccnode.codegenerator.database.handler.UpdateFieldHandler;
import com.intellij.psi.PsiField;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// Referenced classes of package com.ccnode.codegenerator.database.handler.sqlite:
//			SqliteHandlerUtils

public class SqliteUpdateFiledHandler
        implements UpdateFieldHandler {

    private static volatile SqliteUpdateFiledHandler mInstance;

    private SqliteUpdateFiledHandler() {
    }

    public static SqliteUpdateFiledHandler getInstance() {
        if (mInstance == null)
            synchronized (SqliteUpdateFiledHandler.class) {
                if (mInstance == null)
                    mInstance = new SqliteUpdateFiledHandler();
            }
        return mInstance;
    }

    public String generateUpdateSql(List newAddedProps, String tableName, List deletedFields) {
        return null;
    }

    @NotNull
    public List getRecommendDatabaseTypeOfFieldType(PsiField field) {
        List list = SqliteHandlerUtils.getRecommendDatabaseTypeOfFieldType(field);
        return list;
    }


}
