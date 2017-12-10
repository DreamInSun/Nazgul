// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PsiResultMapSqlReference.java

package com.ccnode.codegenerator.reference;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.util.IncorrectOperationException;
import java.util.HashSet;
import java.util.Set;

public class PsiResultMapSqlReference
	implements PsiReference
{

	private static Set tagNames = new HashSet() {

			
			{
				add("update");
				add("insert");
				add("select");
				add("delete");
			}
	};
	private XmlAttributeValue psiReference;
	private XmlAttributeValue psiElement;

	public PsiResultMapSqlReference(XmlAttributeValue element, XmlAttributeValue psiReference)
	{
		psiElement = element;
		this.psiReference = psiReference;
	}

	public PsiElement getElement()
	{
		return psiElement;
	}

	public TextRange getRangeInElement()
	{
		return new TextRange(0, psiElement.getTextLength());
	}

	public PsiElement resolve()
	{
		return psiReference;
	}

	public String getCanonicalText()
	{
		String s = psiReference.getText();
		s;
		if (s == null)
			$$$reportNull$$$0(0);
		return;
	}

	public PsiElement handleElementRename(String newElementName)
		throws IncorrectOperationException
	{
		PsiElement parent = psiElement.getParent();
		if (!(parent instanceof XmlAttribute))
		{
			return psiElement;
		} else
		{
			XmlAttribute attribute = (XmlAttribute)parent;
			attribute.setValue(newElementName);
			return attribute.getValueElement();
		}
	}

	public PsiElement bindToElement(PsiElement element)
		throws IncorrectOperationException
	{
		if (element == null)
			$$$reportNull$$$0(1);
		return null;
	}

	public boolean isReferenceTo(PsiElement element)
	{
		return element == resolve();
	}

	public Object[] getVariants()
	{
		Object aobj[] = new Object[0];
		aobj;
		if (aobj == null)
			$$$reportNull$$$0(2);
		return;
	}

	public boolean isSoft()
	{
		return false;
	}

	private static void $$$reportNull$$$0(int i)
	{
		String s;
		switch (i)
		{
		case 0: // '\0'
		case 2: // '\002'
		default:
			s = "@NotNull method %s.%s must not return null";
			break;

		case 1: // '\001'
			s = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
			break;
		}
		int j;
		s;
		switch (i)
		{
		case 0: // '\0'
		case 2: // '\002'
		default:
			j = 2;
			break;

		case 1: // '\001'
			j = 3;
			break;
		}
		new Object[j];
		i;
		JVM INSTR tableswitch 0 2: default 104
	//	               0 104
	//	               1 112
	//	               2 104;
		   goto _L1 _L1 _L2 _L1
_L1:
		JVM INSTR dup ;
		0;
		"com/ccnode/codegenerator/reference/PsiResultMapSqlReference";
		JVM INSTR aastore ;
		  goto _L3
_L2:
		JVM INSTR dup ;
		0;
		"element";
		JVM INSTR aastore ;
_L3:
		i;
		JVM INSTR tableswitch 0 2: default 148
	//	               0 148
	//	               1 156
	//	               2 164;
		   goto _L4 _L4 _L5 _L6
_L4:
		JVM INSTR dup ;
		1;
		"getCanonicalText";
		JVM INSTR aastore ;
		  goto _L7
_L5:
		JVM INSTR dup ;
		1;
		"com/ccnode/codegenerator/reference/PsiResultMapSqlReference";
		JVM INSTR aastore ;
		  goto _L7
_L6:
		JVM INSTR dup ;
		1;
		"getVariants";
		JVM INSTR aastore ;
_L7:
		i;
		JVM INSTR tableswitch 0 2: default 200
	//	               0 200
	//	               1 203
	//	               2 200;
		   goto _L8 _L8 _L9 _L8
_L9:
		JVM INSTR dup ;
		2;
		"bindToElement";
		JVM INSTR aastore ;
_L8:
		String.format();
		i;
		JVM INSTR tableswitch 0 2: default 240
	//	               0 240
	//	               1 251
	//	               2 240;
		   goto _L10 _L10 _L11 _L10
_L10:
		JVM INSTR new #122 <Class IllegalStateException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalStateException();
		  goto _L12
_L11:
		JVM INSTR new #126 <Class IllegalArgumentException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalArgumentException();
_L12:
		throw ;
	}

}
