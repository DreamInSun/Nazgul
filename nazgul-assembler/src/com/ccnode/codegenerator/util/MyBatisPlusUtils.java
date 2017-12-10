// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MyBatisPlusUtils.java

package com.ccnode.codegenerator.util;

import com.ccnode.codegenerator.pojo.DomainClassInfo;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTypesUtil;
import java.util.Objects;
import org.apache.commons.lang.StringUtils;

// Referenced classes of package com.ccnode.codegenerator.util:
//			PsiClassUtil

public class MyBatisPlusUtils
{

	public MyBatisPlusUtils()
	{
	}

	public static String extractTableNameByAnnotation(DomainClassInfo domainClassInfo)
	{
		String tableName = null;
		PsiModifierList modifierList = domainClassInfo.getDomainClass().getModifierList();
		if (modifierList != null)
		{
			PsiAnnotation annotations[] = modifierList.getApplicableAnnotations();
			if (annotations.length > 0)
			{
				PsiAnnotation apsiannotation[] = annotations;
				int i = apsiannotation.length;
				for (int k = 0; k < i; k++)
				{
					PsiAnnotation annotation = apsiannotation[k];
					if (!annotation.getQualifiedName().equals("com.baomidou.mybatisplus.annotations.TableName"))
						continue;
					PsiNameValuePair attributes[] = annotation.getParameterList().getAttributes();
					PsiNameValuePair apsinamevaluepair[] = attributes;
					int i1 = apsinamevaluepair.length;
					for (int k1 = 0; k1 < i1; k1++)
					{
						PsiNameValuePair attribute = apsinamevaluepair[k1];
						if (attribute.getName() != null && !attribute.getName().equals("value"))
							continue;
						PsiAnnotationMemberValue value = attribute.getValue();
						if (value != null && StringUtils.isNotBlank(value.getText()) && value.getText().startsWith("\"") && value.getText().endsWith("\""))
							return value.getText().substring(1, value.getText().length() - 1);
					}

				}

			}
		}
		if (modifierList != null)
		{
			PsiAnnotation annotations[] = modifierList.getApplicableAnnotations();
			if (annotations.length > 0)
			{
				PsiAnnotation apsiannotation1[] = annotations;
				int j = apsiannotation1.length;
				for (int l = 0; l < j; l++)
				{
					PsiAnnotation annotation = apsiannotation1[l];
					if (!annotation.getQualifiedName().equals("javax.persistence.Table"))
						continue;
					PsiNameValuePair attributes[] = annotation.getParameterList().getAttributes();
					PsiNameValuePair apsinamevaluepair1[] = attributes;
					int j1 = apsinamevaluepair1.length;
					for (int l1 = 0; l1 < j1; l1++)
					{
						PsiNameValuePair attribute = apsinamevaluepair1[l1];
						if (attribute.getName() != null && !attribute.getName().equals("name"))
							continue;
						PsiAnnotationMemberValue value = attribute.getValue();
						if (value != null && StringUtils.isNotBlank(value.getText()) && value.getText().startsWith("\"") && value.getText().endsWith("\""))
							return value.getText().substring(1, value.getText().length() - 1);
					}

				}

			}
		}
		return tableName;
	}

	static PsiClass getClassFromMyBatisPlus(PsiClass srcClass)
	{
		PsiClassType extendsListTypes[] = srcClass.getExtendsListTypes();
		PsiClassType apsiclasstype[] = extendsListTypes;
		int i = apsiclasstype.length;
		for (int j = 0; j < i; j++)
		{
			PsiClassType extendsListType = apsiclasstype[j];
			if (!Objects.equals(extendsListType.getClassName(), "BaseMapper") && extendsListType.getParameterCount() != 1)
				continue;
			PsiClass psiClass = PsiTypesUtil.getPsiClass(extendsListType.getParameters()[0]);
			if (PsiClassUtil.canBeDomainClass(psiClass))
				return psiClass;
		}

		return null;
	}
}
