// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   HandlerValidator.java

package com.ccnode.codegenerator.database.handler;

import com.ccnode.codegenerator.database.ClassValidateResult;
import com.ccnode.codegenerator.util.PsiClassUtil;
import com.google.common.collect.Lists;
import com.intellij.psi.*;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.ccnode.codegenerator.database.handler:
//			FieldValidator

public class HandlerValidator
{

	private FieldValidator fieldValidator;

	public HandlerValidator(FieldValidator fieldValidator)
	{
		this.fieldValidator = fieldValidator;
	}

	public ClassValidateResult validateResult(PsiClass psiClass)
	{
		ClassValidateResult result = new ClassValidateResult();
		PsiField allFields[] = psiClass.getAllFields();
		if (allFields.length == 0)
		{
			result.setValid(Boolean.valueOf(false));
			result.setInvalidMessages("there is no available field in current class");
			return result;
		}
		List validFields = Lists.newArrayList();
		List errorMessages = Lists.newArrayList();
		PsiField apsifield[] = allFields;
		int i = apsifield.length;
		for (int j = 0; j < i; j++)
		{
			PsiField psiField = apsifield[j];
			if (psiField.hasModifierProperty("transient"))
			{
				errorMessages.add(buildErrorMessage(psiField, " ignore transient field type"));
				continue;
			}
			if (PsiClassUtil.isSupprtedModifier(psiField))
			{
				String fieldType = psiField.getType().getCanonicalText();
				if (!fieldValidator.isValidField(psiField))
				{
					if (PsiClassUtil.isPrimitiveType(fieldType))
						errorMessages.add(buildErrorMessage(psiField, " please use with object type"));
					else
						errorMessages.add(buildErrorMessage(psiField, " unsupported field type"));
				} else
				{
					validFields.add(psiField);
				}
			} else
			{
				errorMessages.add(buildErrorMessage(psiField, " please use not public and not static"));
			}
		}

		result.setValidFields(validFields);
		if (errorMessages.size() > 0)
		{
			result.setValid(Boolean.valueOf(false));
			StringBuilder builder = new StringBuilder();
			String errorMessage;
			for (Iterator iterator = errorMessages.iterator(); iterator.hasNext(); builder.append((new StringBuilder()).append(errorMessage).append("\n").toString()))
				errorMessage = (String)iterator.next();

			builder.deleteCharAt(builder.length() - 1);
			result.setInvalidMessages(builder.toString());
			return result;
		} else
		{
			result.setValid(Boolean.valueOf(true));
			return result;
		}
	}

	private static String buildErrorMessage(PsiField psiField, String reason)
	{
		return (new StringBuilder()).append("field name is:").append(psiField.getName()).append("  field type is:").append(psiField.getType().getCanonicalText()).append("  invalid reason is:").append(reason).toString();
	}
}
