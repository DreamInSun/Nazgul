// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PsiJavaMethodReference.java

package com.ccnode.codegenerator.reference;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public class PsiJavaMethodReference
        implements PsiReference {

    private PsiIdentifier myIdentifier;
    private XmlAttributeValue psiElement;

    public PsiJavaMethodReference(XmlAttributeValue element, PsiIdentifier identifier) {
        psiElement = element;
        myIdentifier = identifier;
    }

    public PsiElement getElement() {
        return psiElement;
    }

    public TextRange getRangeInElement() {
        String text = psiElement.getText();
        if (text.startsWith("\"") && text.endsWith("\"") && text.length() > 2)
            return new TextRange(1, psiElement.getTextLength() - 1);
        else
            return new TextRange(0, psiElement.getTextLength());
    }

    public PsiElement resolve() {
        PsiElement parent = myIdentifier.getParent();
        if (parent != null && (parent instanceof PsiMethod))
            return parent;
        else
            return myIdentifier;
    }

    @NotNull
    public String getCanonicalText() {
        String s = myIdentifier.getText();
        return s;
    }

    public PsiElement handleElementRename(String newElementName)
            throws IncorrectOperationException {
        PsiElement parent = psiElement.getParent();
        if (!(parent instanceof XmlAttribute)) {
            return psiElement;
        } else {
            XmlAttribute attribute = (XmlAttribute) parent;
            attribute.setValue(newElementName);
            return attribute.getValueElement();
        }
    }

    public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
        this.psiElement.add(element);
        return this.psiElement;
    }

    public boolean isReferenceTo(PsiElement element) {
        return element == resolve();
    }

    @NotNull
    public Object[] getVariants() {
        Object aobj[] = new Object[0];
        return aobj;
    }

    public boolean isSoft() {
        return false;
    }

}
