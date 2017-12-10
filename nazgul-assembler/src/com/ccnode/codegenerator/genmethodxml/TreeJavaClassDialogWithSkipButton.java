// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TreeJavaClassDialogWithSkipButton.java

package com.ccnode.codegenerator.genmethodxml;

import com.intellij.ide.util.ClassFilter;
import com.intellij.ide.util.TreeJavaClassChooserDialog;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.search.GlobalSearchScope;

public class TreeJavaClassDialogWithSkipButton extends TreeJavaClassChooserDialog {
    public TreeJavaClassDialogWithSkipButton(String title, Project project, GlobalSearchScope scope, ClassFilter classFilter, PsiClass initialClass) {
        super(title, project, scope, classFilter, null, initialClass, true);
        setCancelButtonText("skip");
    }


}
