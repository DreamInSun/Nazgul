// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MysqlAutoCompleteHandler.java

package com.ccnode.codegenerator.database.handler.mysql;

import com.ccnode.codegenerator.database.handler.AutoCompleteHandler;
import com.ccnode.codegenerator.util.PsiClassUtil;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;

// Referenced classes of package com.ccnode.codegenerator.database.handler.mysql:
//			MysqlHandlerUtils

public class MysqlAutoCompleteHandler
        implements AutoCompleteHandler {

    private static volatile MysqlAutoCompleteHandler mInstance;

    private MysqlAutoCompleteHandler() {
    }

    public static MysqlAutoCompleteHandler getInstance() {
        if (mInstance == null)
            synchronized (MysqlAutoCompleteHandler.class) {
                if (mInstance == null)
                    mInstance = new MysqlAutoCompleteHandler();
            }
        return mInstance;
    }

    public boolean isSupportedParam(PsiParameter psiParameter) {
        return MysqlHandlerUtils.getTypePropsByQulifiType(PsiClassUtil.convertToObjectText(psiParameter.getType().getCanonicalText())) != null;
    }
}
