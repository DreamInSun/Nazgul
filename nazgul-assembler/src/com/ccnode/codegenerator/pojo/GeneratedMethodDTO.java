// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GeneratedMethodDTO.java

package com.ccnode.codegenerator.pojo;

import java.util.Set;

public class GeneratedMethodDTO
{
	public static class GeneratedMethodDTOBuilder
	{

		private Set imports;
		private String methodText;

		public GeneratedMethodDTOBuilder imports(Set imports)
		{
			this.imports = imports;
			return this;
		}

		public GeneratedMethodDTOBuilder methodText(String methodText)
		{
			this.methodText = methodText;
			return this;
		}

		public GeneratedMethodDTO build()
		{
			return new GeneratedMethodDTO(imports, methodText);
		}

		public String toString()
		{
			return (new StringBuilder()).append("GeneratedMethodDTO.GeneratedMethodDTOBuilder(imports=").append(imports).append(", methodText=").append(methodText).append(")").toString();
		}

		GeneratedMethodDTOBuilder()
		{
		}
	}


	private Set imports;
	private String methodText;

	GeneratedMethodDTO(Set imports, String methodText)
	{
		this.imports = imports;
		this.methodText = methodText;
	}

	public static GeneratedMethodDTOBuilder builder()
	{
		return new GeneratedMethodDTOBuilder();
	}

	public Set getImports()
	{
		return imports;
	}

	public String getMethodText()
	{
		return methodText;
	}

	public void setImports(Set imports)
	{
		this.imports = imports;
	}

	public void setMethodText(String methodText)
	{
		this.methodText = methodText;
	}
}
