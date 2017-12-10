// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GenerateDaoXmlFixAction.java

package com.ccnode.codegenerator.view;

import com.intellij.codeInsight.daemon.QuickFixBundle;
import com.intellij.codeInsight.daemon.impl.DaemonCodeAnalyzerEx;
import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.codeInsight.daemon.impl.quickfix.CreateFromUsageBaseFix;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;

/**
 * @deprecated Class GenerateDaoXmlFixAction is deprecated
 */

public class GenerateDaoXmlFixAction extends CreateFromUsageBaseFix {

    public static final String GENERATE_METHOD_SQL = "generate method sql";
    private final SmartPsiElementPointer myMethodCall;

    public GenerateDaoXmlFixAction(PsiMethodCallExpression methodCall) {
        myMethodCall = SmartPointerManager.getInstance(methodCall.getProject()).createSmartPsiElementPointer(methodCall);
    }

    protected boolean isAvailableImpl(int offset) {
        PsiMethodCallExpression call = getMethodCall();
        if (call == null || !call.isValid())
            return false;
        PsiReferenceExpression ref = call.getMethodExpression();
        String name = ref.getReferenceName();
        if (name == null || !PsiNameHelper.getInstance(ref.getProject()).isIdentifier(name))
            return false;
        return !hasErrorInArgumentList(call);
    }

    private boolean hasErrorInArgumentList(PsiMethodCallExpression call) {
        Project project = call.getProject();
        Document document = PsiDocumentManager.getInstance(project).getDocument(call.getContainingFile());
        if (document == null) {
            return true;
        } else {
            PsiExpressionList argumentList = call.getArgumentList();
            final TextRange argRange = argumentList.getTextRange();
            return !DaemonCodeAnalyzerEx.processHighlights(document, project, HighlightSeverity.ERROR, argRange.getStartOffset() + 1, argRange.getEndOffset() - 1,
                    new Processor() {
                        final TextRange argRange = textrange;

                        public boolean process(HighlightInfo highlightInfo) {
                            return highlightInfo.getActualStartOffset() <= argRange.getStartOffset() || highlightInfo.getActualEndOffset() >= argRange.getEndOffset();
                        }

                        public boolean process(Object obj) {
                            return process((HighlightInfo) obj);
                        }

                    });
        }
    }

    protected void invokeImpl(PsiClass targetClass) {
        if (targetClass == null)
            return;
        PsiMethodCallExpression expression = getMethodCall();
        if (expression == null) {
            return;
        } else {
            PsiReferenceExpression ref = expression.getMethodExpression();
            String methodName = ref.getReferenceName();
            return;
        }
    }

    protected boolean isValidElement(PsiElement element) {
        PsiMethodCallExpression callExpression = (PsiMethodCallExpression) element;
        PsiReferenceExpression referenceExpression = callExpression.getMethodExpression();
        return true;
    }

    protected PsiElement getElement() {
        return null;
    }

    @NotNull
    public String getText() {
        return "generate method sql";
    }

    @NotNull
    public String getFamilyName() {
        String s = QuickFixBundle.message("create.method.from.usage.family", new Object[0]);
        return s;
    }

    public PsiMethodCallExpression getMethodCall() {
        return (PsiMethodCallExpression) myMethodCall.getElement();
    }


}
