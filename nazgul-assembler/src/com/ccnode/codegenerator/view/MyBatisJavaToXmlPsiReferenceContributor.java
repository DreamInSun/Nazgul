// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MyBatisJavaToXmlPsiReferenceContributor.java

package com.ccnode.codegenerator.view;

import com.ccnode.codegenerator.dialog.MapperUtil;
import com.ccnode.codegenerator.reference.PsiJavaMethodReference;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.patterns.XmlPatterns;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.psi.xml.*;
import com.intellij.util.ProcessingContext;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

public class MyBatisJavaToXmlPsiReferenceContributor extends PsiReferenceContributor
{

	private static Set tagNames = new HashSet() {

			
			{
				add("update");
				add("insert");
				add("select");
				add("delete");
			}
	};

	public MyBatisJavaToXmlPsiReferenceContributor()
	{
	}

	public void registerReferenceProviders(PsiReferenceRegistrar registrar)
	{
		if (registrar == null)
			$$$reportNull$$$0(0);
		registrar.registerReferenceProvider(XmlPatterns.xmlAttributeValue(), new PsiReferenceProvider() {

			final MyBatisJavaToXmlPsiReferenceContributor this$0;

			public PsiReference[] getReferencesByElement(PsiElement element, ProcessingContext context)
			{
				PsiElement parent1;
				if (element == null)
					$$$reportNull$$$0(0);
				if (context == null)
					$$$reportNull$$$0(1);
				parent1 = element.getParent();
				if (parent1 instanceof XmlAttribute) goto _L2; else goto _L1
_L1:
				PsiReference.EMPTY_ARRAY;
				if (PsiReference.EMPTY_ARRAY == null)
					$$$reportNull$$$0(2);
				return;
_L2:
				XmlAttribute parent;
				String name;
				parent = (XmlAttribute)parent1;
				name = parent.getName();
				if (name.equals("id")) goto _L4; else goto _L3
_L3:
				PsiReference.EMPTY_ARRAY;
				if (PsiReference.EMPTY_ARRAY == null)
					$$$reportNull$$$0(3);
				return;
_L4:
				XmlTag selectTag = parent.getParent();
				if (selectTag != null) goto _L6; else goto _L5
_L5:
				PsiReference.EMPTY_ARRAY;
				if (PsiReference.EMPTY_ARRAY == null)
					$$$reportNull$$$0(4);
				return;
_L6:
				if (MyBatisJavaToXmlPsiReferenceContributor.tagNames.contains(selectTag.getName())) goto _L8; else goto _L7
_L7:
				PsiReference.EMPTY_ARRAY;
				if (PsiReference.EMPTY_ARRAY == null)
					$$$reportNull$$$0(5);
				return;
_L8:
				if (selectTag.getParent() instanceof XmlTag) goto _L10; else goto _L9
_L9:
				PsiReference.EMPTY_ARRAY;
				if (PsiReference.EMPTY_ARRAY == null)
					$$$reportNull$$$0(6);
				return;
_L10:
				XmlAttribute namespaceAttribute;
				XmlTag rootParent = (XmlTag)selectTag.getParent();
				namespaceAttribute = rootParent.getAttribute("namespace");
				if (namespaceAttribute != null) goto _L12; else goto _L11
_L11:
				PsiReference.EMPTY_ARRAY;
				if (PsiReference.EMPTY_ARRAY == null)
					$$$reportNull$$$0(7);
				return;
_L12:
				String namespace = namespaceAttribute.getValue();
				if (!StringUtils.isEmpty(namespace)) goto _L14; else goto _L13
_L13:
				PsiReference.EMPTY_ARRAY;
				if (PsiReference.EMPTY_ARRAY == null)
					$$$reportNull$$$0(8);
				return;
_L14:
				PsiShortNamesCache shortNamesCache;
				Module moduleForPsiElement;
				shortNamesCache = PsiShortNamesCache.getInstance(element.getProject());
				moduleForPsiElement = ModuleUtilCore.findModuleForPsiElement(element);
				if (moduleForPsiElement != null) goto _L16; else goto _L15
_L15:
				PsiReference.EMPTY_ARRAY;
				if (PsiReference.EMPTY_ARRAY == null)
					$$$reportNull$$$0(9);
				return;
_L16:
				PsiClass findedClass;
				PsiClass classesByName[] = shortNamesCache.getClassesByName(MapperUtil.extractClassShortName(namespace), GlobalSearchScope.moduleScope(moduleForPsiElement));
				findedClass = null;
				PsiClass apsiclass[] = classesByName;
				int i = apsiclass.length;
				int j = 0;
				do
				{
					if (j >= i)
						break;
					PsiClass psiClass = apsiclass[j];
					if (psiClass.getQualifiedName().equals(namespace))
					{
						findedClass = psiClass;
						break;
					}
					j++;
				} while (true);
				if (findedClass != null) goto _L18; else goto _L17
_L17:
				PsiReference.EMPTY_ARRAY;
				if (PsiReference.EMPTY_ARRAY == null)
					$$$reportNull$$$0(10);
				return;
_L18:
				PsiMethod findMethod;
				String value = parent.getValue();
				findMethod = null;
				PsiMethod methods[] = findedClass.getMethods();
				PsiMethod apsimethod[] = methods;
				int k = apsimethod.length;
				for (int l = 0; l < k; l++)
				{
					PsiMethod method = apsimethod[l];
					if (method.getName().equals(value))
						findMethod = method;
				}

				if (findMethod == null) goto _L20; else goto _L19
_L19:
				PsiReference apsireference[] = {
					new PsiJavaMethodReference((XmlAttributeValue)element, findMethod.getNameIdentifier())
				};
				apsireference;
				if (apsireference == null)
					$$$reportNull$$$0(11);
				return;
_L20:
				PsiReference.EMPTY_ARRAY;
				if (PsiReference.EMPTY_ARRAY == null)
					$$$reportNull$$$0(12);
				return;
			}

			private static void $$$reportNull$$$0(int i)
			{
				String s;
				switch (i)
				{
				case 0: // '\0'
				case 1: // '\001'
				default:
					s = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
					break;

				case 2: // '\002'
				case 3: // '\003'
				case 4: // '\004'
				case 5: // '\005'
				case 6: // '\006'
				case 7: // '\007'
				case 8: // '\b'
				case 9: // '\t'
				case 10: // '\n'
				case 11: // '\013'
				case 12: // '\f'
					s = "@NotNull method %s.%s must not return null";
					break;
				}
				int j;
				s;
				switch (i)
				{
				case 0: // '\0'
				case 1: // '\001'
				default:
					j = 3;
					break;

				case 2: // '\002'
				case 3: // '\003'
				case 4: // '\004'
				case 5: // '\005'
				case 6: // '\006'
				case 7: // '\007'
				case 8: // '\b'
				case 9: // '\t'
				case 10: // '\n'
				case 11: // '\013'
				case 12: // '\f'
					j = 2;
					break;
				}
				new Object[j];
				i;
				JVM INSTR tableswitch 0 12: default 224
			//			               0 224
			//			               1 232
			//			               2 240
			//			               3 240
			//			               4 240
			//			               5 240
			//			               6 240
			//			               7 240
			//			               8 240
			//			               9 240
			//			               10 240
			//			               11 240
			//			               12 240;
				   goto _L1 _L1 _L2 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3
_L1:
				JVM INSTR dup ;
				0;
				"element";
				JVM INSTR aastore ;
				  goto _L4
_L2:
				JVM INSTR dup ;
				0;
				"context";
				JVM INSTR aastore ;
				  goto _L4
_L3:
				JVM INSTR dup ;
				0;
				"com/ccnode/codegenerator/view/MyBatisJavaToXmlPsiReferenceContributor$2";
				JVM INSTR aastore ;
_L4:
				i;
				JVM INSTR tableswitch 0 12: default 316
			//			               0 316
			//			               1 316
			//			               2 324
			//			               3 324
			//			               4 324
			//			               5 324
			//			               6 324
			//			               7 324
			//			               8 324
			//			               9 324
			//			               10 324
			//			               11 324
			//			               12 324;
				   goto _L5 _L5 _L5 _L6 _L6 _L6 _L6 _L6 _L6 _L6 _L6 _L6 _L6 _L6
_L5:
				JVM INSTR dup ;
				1;
				"com/ccnode/codegenerator/view/MyBatisJavaToXmlPsiReferenceContributor$2";
				JVM INSTR aastore ;
				  goto _L7
_L6:
				JVM INSTR dup ;
				1;
				"getReferencesByElement";
				JVM INSTR aastore ;
_L7:
				i;
				JVM INSTR tableswitch 0 12: default 400
			//			               0 400
			//			               1 400
			//			               2 408
			//			               3 408
			//			               4 408
			//			               5 408
			//			               6 408
			//			               7 408
			//			               8 408
			//			               9 408
			//			               10 408
			//			               11 408
			//			               12 408;
				   goto _L8 _L8 _L8 _L9 _L9 _L9 _L9 _L9 _L9 _L9 _L9 _L9 _L9 _L9
_L8:
				JVM INSTR dup ;
				2;
				"getReferencesByElement";
				JVM INSTR aastore ;
_L9:
				String.format();
				i;
				JVM INSTR tableswitch 0 12: default 480
			//			               0 480
			//			               1 480
			//			               2 491
			//			               3 491
			//			               4 491
			//			               5 491
			//			               6 491
			//			               7 491
			//			               8 491
			//			               9 491
			//			               10 491
			//			               11 491
			//			               12 491;
				   goto _L10 _L10 _L10 _L11 _L11 _L11 _L11 _L11 _L11 _L11 _L11 _L11 _L11 _L11
_L10:
				JVM INSTR new #208 <Class IllegalArgumentException>;
				JVM INSTR dup_x1 ;
				JVM INSTR swap ;
				IllegalArgumentException();
				  goto _L12
_L11:
				JVM INSTR new #213 <Class IllegalStateException>;
				JVM INSTR dup_x1 ;
				JVM INSTR swap ;
				IllegalStateException();
_L12:
				throw ;
			}

			
			{
				this.this$0 = MyBatisJavaToXmlPsiReferenceContributor.this;
				super();
			}
		});
	}

	private static void $$$reportNull$$$0(int i)
	{
		String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", new Object[] {
			"registrar", "com/ccnode/codegenerator/view/MyBatisJavaToXmlPsiReferenceContributor", "registerReferenceProviders"
		});
		JVM INSTR new #70  <Class IllegalArgumentException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalArgumentException();
		throw ;
	}


}
