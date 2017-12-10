// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ParamInfo.java

package com.ccnode.codegenerator.methodnameparser.buidler;


public class ParamInfo
{
	public static class ParamInfoBuilder
	{

		private String paramType;
		private String paramAnno;
		private String paramValue;
		private String paramFullType;

		public ParamInfoBuilder paramType(String paramType)
		{
			this.paramType = paramType;
			return this;
		}

		public ParamInfoBuilder paramAnno(String paramAnno)
		{
			this.paramAnno = paramAnno;
			return this;
		}

		public ParamInfoBuilder paramValue(String paramValue)
		{
			this.paramValue = paramValue;
			return this;
		}

		public ParamInfoBuilder paramFullType(String paramFullType)
		{
			this.paramFullType = paramFullType;
			return this;
		}

		public ParamInfo build()
		{
			return new ParamInfo(paramType, paramAnno, paramValue, paramFullType);
		}

		public String toString()
		{
			return (new StringBuilder()).append("ParamInfo.ParamInfoBuilder(paramType=").append(paramType).append(", paramAnno=").append(paramAnno).append(", paramValue=").append(paramValue).append(", paramFullType=").append(paramFullType).append(")").toString();
		}

		ParamInfoBuilder()
		{
		}
	}


	private String paramType;
	private String paramAnno;
	private String paramValue;
	private String paramFullType;

	ParamInfo(String paramType, String paramAnno, String paramValue, String paramFullType)
	{
		this.paramType = paramType;
		this.paramAnno = paramAnno;
		this.paramValue = paramValue;
		this.paramFullType = paramFullType;
	}

	public static ParamInfoBuilder builder()
	{
		return new ParamInfoBuilder();
	}

	public String getParamType()
	{
		return paramType;
	}

	public String getParamAnno()
	{
		return paramAnno;
	}

	public String getParamValue()
	{
		return paramValue;
	}

	public String getParamFullType()
	{
		return paramFullType;
	}

	public void setParamType(String paramType)
	{
		this.paramType = paramType;
	}

	public void setParamAnno(String paramAnno)
	{
		this.paramAnno = paramAnno;
	}

	public void setParamValue(String paramValue)
	{
		this.paramValue = paramValue;
	}

	public void setParamFullType(String paramFullType)
	{
		this.paramFullType = paramFullType;
	}
}
