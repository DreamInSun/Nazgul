// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MybatisMethodNotFindInXmlInspection.java

package com.ccnode.codegenerator.view.inspection;

import com.ccnode.codegenerator.util.MyPsiXmlUtils;
import com.ccnode.codegenerator.util.PsiSearchUtils;
import com.intellij.codeInspection.*;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.xml.XmlFile;
import java.util.*;

// Referenced classes of package com.ccnode.codegenerator.view.inspection:
//			MyBatisMethodNotFoundFix

public class MybatisMethodNotFindInXmlInspection extends BaseJavaLocalInspectionTool
{

	private LocalQuickFix fix;
	private static Set mybatisAnnotations = new HashSet() {

			
			{
				add("org.apache.ibatis.annotations.Select");
				add("org.apache.ibatis.annotations.Update");
				add("org.apache.ibatis.annotations.Delete");
				add("org.apache.ibatis.annotations.Insert");
			}
	};

	public MybatisMethodNotFindInXmlInspection()
	{
		fix = new MyBatisMethodNotFoundFix();
	}

	public ProblemDescriptor[] checkMethod(PsiMethod method, InspectionManager manager, boolean isOnTheFly)
	{
		if (method == null)
			$$$reportNull$$$0(0);
		if (manager == null)
			$$$reportNull$$$0(1);
		return (new ProblemDescriptor[] {
			manager.createProblemDescriptor(method, "method not find in xml", fix, ProblemHighlightType.ERROR, true)
		});
	}

	public PsiElementVisitor buildVisitor(final ProblemsHolder holder, boolean isOnTheFly, LocalInspectionToolSession session)
	{
		JavaElementVisitor javaelementvisitor;
		if (holder == null)
			$$$reportNull$$$0(2);
		if (session == null)
			$$$reportNull$$$0(3);
		javaelementvisitor = new JavaElementVisitor() {

			final ProblemsHolder val$holder;
			final MybatisMethodNotFindInXmlInspection this$0;

			public void visitMethod(PsiMethod method)
			{
				super.visitMethod(method);
				PsiClass currentClass = (PsiClass)PsiTreeUtil.getParentOfType(method, com/intellij/psi/PsiClass);
				if (currentClass == null || !currentClass.isInterface())
					return;
				PsiModifierList modifierList;
				if (method.getName().equals("findByHeheAndUserName"))
					modifierList = method.getModifierList();
				modifierList = method.getModifierList();
				if (modifierList != null)
				{
					PsiAnnotation annotations[] = modifierList.getApplicableAnnotations();
					if (annotations.length > 0)
					{
						PsiAnnotation apsiannotation[] = annotations;
						int i = apsiannotation.length;
						for (int j = 0; j < i; j++)
						{
							PsiAnnotation annotation = apsiannotation[j];
							if (MybatisMethodNotFindInXmlInspection.mybatisAnnotations.contains(annotation.getQualifiedName()))
								return;
						}

					}
				}
				String qualifiedName = currentClass.getQualifiedName();
				Module moduleForPsiElement = ModuleUtilCore.findModuleForPsiElement(method);
				List xmlFiles = PsiSearchUtils.searchMapperXml(holder.getProject(), moduleForPsiElement, qualifiedName);
				if (xmlFiles.size() == 0 || xmlFiles.size() > 1)
					return;
				XmlFile xmlFile = (XmlFile)xmlFiles.get(0);
				com.intellij.psi.PsiElement tagForMethodName = MyPsiXmlUtils.findTagForMethodName(xmlFile, method.getName());
				if (tagForMethodName == null)
					holder.registerProblem(method, "mybatis xml not exist", ProblemHighlightType.ERROR, new LocalQuickFix[] {
						fix
					});
			}

			
			{
				this.this$0 = MybatisMethodNotFindInXmlInspection.this;
				holder = problemsholder;
				super();
			}
		};
		javaelementvisitor;
		if (javaelementvisitor == null)
			$$$reportNull$$$0(4);
		return;
	}

	public boolean isEnabledByDefault()
	{
		return true;
	}

	public String getDisplayName()
	{
		"check mybatis xml";
		if ("check mybatis xml" == null)
			$$$reportNull$$$0(5);
		return;
	}

	private static void $$$reportNull$$$0(int i)
	{
		String s;
		switch (i)
		{
		case 0: // '\0'
		case 1: // '\001'
		case 2: // '\002'
		case 3: // '\003'
		default:
			s = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
			break;

		case 4: // '\004'
		case 5: // '\005'
			s = "@NotNull method %s.%s must not return null";
			break;
		}
		int j;
		s;
		switch (i)
		{
		case 0: // '\0'
		case 1: // '\001'
		case 2: // '\002'
		case 3: // '\003'
		default:
			j = 3;
			break;

		case 4: // '\004'
		case 5: // '\005'
			j = 2;
			break;
		}
		new Object[j];
		i;
		JVM INSTR tableswitch 0 5: default 140
	//	               0 140
	//	               1 148
	//	               2 156
	//	               3 164
	//	               4 172
	//	               5 172;
		   goto _L1 _L1 _L2 _L3 _L4 _L5 _L5
_L1:
		JVM INSTR dup ;
		0;
		"method";
		JVM INSTR aastore ;
		  goto _L6
_L2:
		JVM INSTR dup ;
		0;
		"manager";
		JVM INSTR aastore ;
		  goto _L6
_L3:
		JVM INSTR dup ;
		0;
		"holder";
		JVM INSTR aastore ;
		  goto _L6
_L4:
		JVM INSTR dup ;
		0;
		"session";
		JVM INSTR aastore ;
		  goto _L6
_L5:
		JVM INSTR dup ;
		0;
		"com/ccnode/codegenerator/view/inspection/MybatisMethodNotFindInXmlInspection";
		JVM INSTR aastore ;
_L6:
		i;
		JVM INSTR tableswitch 0 5: default 220
	//	               0 220
	//	               1 220
	//	               2 220
	//	               3 220
	//	               4 228
	//	               5 236;
		   goto _L7 _L7 _L7 _L7 _L7 _L8 _L9
_L7:
		JVM INSTR dup ;
		1;
		"com/ccnode/codegenerator/view/inspection/MybatisMethodNotFindInXmlInspection";
		JVM INSTR aastore ;
		  goto _L10
_L8:
		JVM INSTR dup ;
		1;
		"buildVisitor";
		JVM INSTR aastore ;
		  goto _L10
_L9:
		JVM INSTR dup ;
		1;
		"getDisplayName";
		JVM INSTR aastore ;
_L10:
		i;
		JVM INSTR tableswitch 0 5: default 284
	//	               0 284
	//	               1 284
	//	               2 292
	//	               3 292
	//	               4 300
	//	               5 300;
		   goto _L11 _L11 _L11 _L12 _L12 _L13 _L13
_L11:
		JVM INSTR dup ;
		2;
		"checkMethod";
		JVM INSTR aastore ;
		  goto _L13
_L12:
		JVM INSTR dup ;
		2;
		"buildVisitor";
		JVM INSTR aastore ;
_L13:
		String.format();
		i;
		JVM INSTR tableswitch 0 5: default 344
	//	               0 344
	//	               1 344
	//	               2 344
	//	               3 344
	//	               4 355
	//	               5 355;
		   goto _L14 _L14 _L14 _L14 _L14 _L15 _L15
_L14:
		JVM INSTR new #110 <Class IllegalArgumentException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalArgumentException();
		  goto _L16
_L15:
		JVM INSTR new #115 <Class IllegalStateException>;
		JVM INSTR dup_x1 ;
		JVM INSTR swap ;
		IllegalStateException();
_L16:
		throw ;
	}



}
