// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MethodRecommendCache.java

package com.ccnode.codegenerator.sqlparse;

import com.google.common.collect.Lists;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.*;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;

import java.util.List;

public class MethodRecommendCache {

    private static String sqlMethods[] = {
            "ABS", "COUNT", "DISTINCT", "AVG", "COUNT", "DISTINCT", "FIRST", "FORMAT", "LAST", "LCASE",
            "LEN", "MAX", "MIN", "MID", "NOW", "ROUND", "SUM", "TOP", "UCASE"
    };

    public MethodRecommendCache() {
    }

    public static List getRecommends(String currentWordStart) {
        List methodRecommends = Lists.newArrayList();
        String as[] = sqlMethods;
        int i = as.length;
        for (int j = 0; j < i; j++) {
            String sqlMethod = as[j];
            methodRecommends.add(LookupElementBuilder.create((new StringBuilder()).append(currentWordStart).append(sqlMethod).append("()").toString()).withRenderer(new LookupElementRenderer(sqlMethod) {

                final String val$sqlMethod;

                public void renderElement(LookupElement element, LookupElementPresentation presentation) {
                    presentation.setItemText(sqlMethod);
                    presentation.setTypeText("function");
                }


                {
                    sqlMethod = s;
                    super();

                }
            }).withInsertHandler(new InsertHandler() {

                public void handleInsert(InsertionContext context, LookupElement item) {
                    context.getEditor().getCaretModel().moveToOffset(context.getTailOffset() - 1);
                }

            }));
        }

        return methodRecommends;
    }

}
