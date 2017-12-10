package com.ccnode.codegenerator.database.handler;

import com.ccnode.codegenerator.constants.SqlTypeEnum;
import com.ccnode.codegenerator.database.DbUtils;
import com.ccnode.codegenerator.database.GenClassDialog;
import com.ccnode.codegenerator.database.GenClassInfo;
import com.ccnode.codegenerator.execute.StopContextException;
import com.ccnode.codegenerator.genCode.GenClassService;
import com.ccnode.codegenerator.methodnameparser.buidler.MethodNameParsedResult;
import com.ccnode.codegenerator.methodnameparser.buidler.ParamInfo;
import com.ccnode.codegenerator.methodnameparser.buidler.ParamInfo.ParamInfoBuilder;
import com.ccnode.codegenerator.methodnameparser.buidler.QueryInfo;
import com.ccnode.codegenerator.methodnameparser.parsedresult.base.QueryRule;
import com.ccnode.codegenerator.methodnameparser.parsedresult.count.ParsedCount;
import com.ccnode.codegenerator.methodnameparser.parsedresult.delete.ParsedDelete;
import com.ccnode.codegenerator.methodnameparser.parsedresult.find.FetchProp;
import com.ccnode.codegenerator.methodnameparser.parsedresult.find.ParsedFind;
import com.ccnode.codegenerator.methodnameparser.parsedresult.update.IncDecDialog;
import com.ccnode.codegenerator.methodnameparser.parsedresult.update.IncDecOperation;
import com.ccnode.codegenerator.methodnameparser.parsedresult.update.IncDecUserResult;
import com.ccnode.codegenerator.methodnameparser.parsedresult.update.ParsedUpdate;
import com.ccnode.codegenerator.methodnameparser.parsedresult.update.UpdateField;
import com.ccnode.codegenerator.pojo.FieldToColumnRelation;
import com.ccnode.codegenerator.util.MyPsiXmlUtils;
import com.ccnode.codegenerator.util.PsiClassUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.xml.XmlFile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseQueryBuilder implements QueryBuilder
{
	private QueryBuilderHandler queryBuilderHandler;

	public BaseQueryBuilder(QueryBuilderHandler handler)
	{
		this.queryBuilderHandler = handler;
	}

	public QueryInfo buildQueryInfoByMethodNameParsedResult(MethodNameParsedResult result)
	{
		switch (result.getParsedType())
		{
			case FIND:
				return buildWithFind(result);
			case UPDATE:
				return buildWithUpdate(result);
			case DELETE:
				return buildWithDelete(result);
			case COUNT:
				return buildWithCount(result);
		}
		return null;
	}

	private QueryInfo buildWithCount(MethodNameParsedResult result)
	{
		QueryInfo info = new QueryInfo();
		info.setParseQuery(result.getParsedCount());
		info.setImportList(new HashSet());
		ParsedCount count = result.getParsedCount();
		Map<String, String> fieldMap = result.getFieldMap();
		FieldToColumnRelation relation = result.getRelation();
		String tableName = result.getTableName();
		info.setType(SqlTypeEnum.SELECT);
		String idType = (String)fieldMap.get("id");
		if (idType != null)
		{
			info.setReturnClass(idType);
			String returnType = PsiClassUtil.extractLast(idType);
			info.getImportList().add(idType);
			info.setMethodReturnType(returnType);
		}
		else
		{
			info.setReturnClass("java.lang.Integer");
			info.setMethodReturnType("Integer");
		}
		StringBuilder builder = new StringBuilder();
		builder.append("\n\tselect count(");
		if (count.isDistinct())
		{
			builder.append("distinct(");
			for (int i = 0; i < count.getFetchProps().size(); i++)
			{
				builder.append(relation
						.getPropFormatColumn((String)count.getFetchProps().get(i)));
				if (i != count.getFetchProps().size() - 1) {
					builder.append(",");
				}
			}
			builder.append(")");
		}
		else if (count.getFetchProps() == null)
		{
			builder.append("1");
		}
		else
		{
			for (int i = 0; i < count.getFetchProps().size(); i++)
			{
				builder.append(relation
						.getPropFormatColumn((String)count.getFetchProps().get(i)));
				if (i != count.getFetchProps().size() - 1) {
					builder.append(",");
				}
			}
		}
		builder.append(")");
		builder.append("\n\tfrom " + tableName);
		info.setParamInfos(new ArrayList());
		info.setSql(builder.toString());
		if (count.getQueryRules() != null) {
			buildQuerySqlAndParam(count.getQueryRules(), info, fieldMap, relation);
		}
		return info;
	}

	private QueryInfo buildWithDelete(MethodNameParsedResult result)
	{
		QueryInfo info = new QueryInfo();
		info.setParseQuery(result.getParsedDelete());
		info.setImportList(new HashSet());
		ParsedDelete delete = result.getParsedDelete();
		String tableName = result.getTableName();
		FieldToColumnRelation relation = result.getRelation();
		Map<String, String> fieldMap = result.getFieldMap();
		info.setType(SqlTypeEnum.DELETE);
		info.setMethodReturnType("int");
		StringBuilder builder = new StringBuilder();
		builder.append("\n\tdelete from  " + tableName);

		info.setParamInfos(new ArrayList());
		info.setSql(builder.toString());
		if (delete.getQueryRules() != null) {
			buildQuerySqlAndParam(delete.getQueryRules(), info, fieldMap, relation);
		}
		return info;
	}

	private QueryInfo buildWithUpdate(MethodNameParsedResult result)
	{
		QueryInfo info = new QueryInfo();
		info.setParseQuery(result.getParsedUpdate());
		info.setImportList(new HashSet());
		ParsedUpdate update = result.getParsedUpdate();
		String tableName = result.getTableName();
		Map<String, String> fieldMap = result.getFieldMap();
		FieldToColumnRelation relation = result.getRelation();
		info.setType(SqlTypeEnum.UPDATE);
		info.setMethodReturnType("int");
		StringBuilder builder = new StringBuilder();
		builder.append("\n\tupdate " + tableName + "\n" + "\t" + "set");

		info.setParamInfos(new ArrayList());
		Map<String, IncDecUserResult> incDecUserResultMap = buildMapFromUpdateProps(update
				.getUpdateProps(), result.getProject());
		for (int i = 0; i < update.getUpdateProps().size(); i++)
		{
			UpdateField prop = (UpdateField)update.getUpdateProps().get(i);
			String jdbcType = result.getRelation().getJdbcType(prop.getProp());
			String realProp = prop.getProp();
			if (prop.getPrefix() == null)
			{
				String updateQualifyType = (String)fieldMap.get(realProp);
				info.getImportList().add(updateQualifyType);

				ParamInfo paramInfo = ParamInfo.builder().paramAnno("updated" + firstCharUpper(prop.getProp())).paramType(PsiClassUtil.extractLast(updateQualifyType)).paramFullType(updateQualifyType).paramValue("updated" + firstCharUpper(prop.getProp())).build();
				info.getParamInfos().add(paramInfo);
				builder.append(" " + relation
						.getPropFormatColumn(prop.getProp()) + "=#{" + paramInfo
						.getParamAnno() + jdbcType + "}");
			}
			else
			{
				IncDecUserResult result1 = (IncDecUserResult)incDecUserResultMap.get(prop.getProp());
				IncDecOperation operation = new IncDecOperation();
				if (prop.getPrefix().equals("inc"))
				{
					operation.setBeforeAnno("added");
					operation.setOperator("+");
				}
				else
				{
					operation.setBeforeAnno("deducted");
					operation.setOperator("-");
				}
				String propFormatColumn = relation.getPropFormatColumn(prop.getProp());
				if (!result1.getUseParam().booleanValue())
				{
					builder.append(" " + propFormatColumn + " = " + propFormatColumn + operation
							.getOperator() + result1
							.getValue());
				}
				else
				{
					String updateQualifyType = (String)fieldMap.get(realProp);
					info.getImportList().add(updateQualifyType);

					ParamInfo paramInfo = ParamInfo.builder().paramAnno(operation.getBeforeAnno() + firstCharUpper(prop.getProp())).paramType(PsiClassUtil.extractLast(updateQualifyType)).paramFullType(updateQualifyType).paramValue(operation.getBeforeAnno() + firstCharUpper(prop.getProp())).build();
					info.getParamInfos().add(paramInfo);

					String propFormatColumn1 = relation.getPropFormatColumn(prop.getProp());
					builder.append(" " + propFormatColumn1 + "=" + propFormatColumn1 + operation
							.getOperator() + "#{" + paramInfo
							.getParamAnno() + jdbcType + "}");
				}
			}
			if (i != update.getUpdateProps().size() - 1) {
				builder.append(",");
			}
		}
		info.setSql(builder.toString());
		if (update.getQueryRules() != null) {
			buildQuerySqlAndParam(update.getQueryRules(), info, fieldMap, relation);
		}
		return info;
	}

	private Map<String, IncDecUserResult> buildMapFromUpdateProps(List<UpdateField> updateProps, Project project)
	{
		List<UpdateField> usePrefixProps = Lists.newArrayList();
		for (UpdateField updateProp : updateProps) {
			if (updateProp.getPrefix() != null) {
				usePrefixProps.add(updateProp);
			}
		}
		if (usePrefixProps.size() == 0) {
			return Maps.newHashMap();
		}
		IncDecDialog dialog = new IncDecDialog(usePrefixProps, project);
		boolean b = dialog.showAndGet();
		if (b) {
			return dialog.getResultMap();
		}
		StopContextException stopContextException = new StopContextException();
		stopContextException.setShouldNotifyMessage(false);
		throw stopContextException;
	}

	public static String cdata(String s)
	{
		return "<![CDATA[" + s + "]]>";
	}

	private static String firstCharUpper(String prop)
	{
		return prop.substring(0, 1).toUpperCase() + prop.substring(1);
	}

	public void buildQuerySqlAndParam(List<QueryRule> queryRules, QueryInfo info, Map<String, String> fieldMap, FieldToColumnRelation relation)
	{
		info.setSql(info.getSql() + "\n" + "\t" + "where");
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < queryRules.size(); i++)
		{
			QueryRule rule = (QueryRule)queryRules.get(i);
			String prop = rule.getProp();
			String operator = rule.getOperator();
			String propColumn = relation.getPropFormatColumn(prop);
			String jdbcType = relation.getJdbcType(prop);
			String paramQualtifyType = (String)fieldMap.get(prop);
			info.getImportList().add(paramQualtifyType);
			String paramShortType = PsiClassUtil.extractLast(paramQualtifyType);
			if (rule.isUseIfTest())
			{
				if (i == 0) {
					builder.append(" 1=1");
				}
				if (isArrayOperator(operator)) {
					builder.append("\n<if test=\"").append(prop).append("!=null and ").append(prop).append(".size()>0\">\n");
				} else {
					builder.append("\n<if test=\"").append(prop).append("!=null\">\n");
				}
				if (i == 0) {
					builder.append(" and");
				}
			}
			if (i >= 1)
			{
				QueryRule rule1 = (QueryRule)queryRules.get(i - 1);
				??? = rule1.getConnector();
				if (??? != null) {
				builder.append(" " + ???);
			}
			}
			if (operator == null)
			{
				??? = ParamInfo.builder().paramAnno(prop).paramType(paramShortType).paramFullType(paramQualtifyType).paramValue(prop).build();
				info.getParamInfos().add(???);
				builder.append(" " + propColumn + "=#{" + ???
				.getParamAnno() + jdbcType + "}");
			}
			else
			{
				switch (operator)
				{
					case "after":
					case "greaterthan":
						ParamInfo paramInfo = ParamInfo.builder().paramAnno("min" + firstCharUpper(prop)).paramType(paramShortType).paramFullType(paramQualtifyType).paramValue("min" + firstCharUpper(prop)).build();
						info.getParamInfos().add(paramInfo);
						builder.append(" " + propColumn + cdata(">") + " #{" + paramInfo
								.getParamAnno() + jdbcType + "}");
						break;
					case "greaterthanorequalto":
						ParamInfo paramInfo = ParamInfo.builder().paramAnno("min" + firstCharUpper(prop)).paramType(paramShortType).paramFullType(paramQualtifyType).paramValue("min" + firstCharUpper(prop)).build();
						info.getParamInfos().add(paramInfo);
						builder.append(" " + propColumn + cdata(">=") + " #{" + paramInfo
								.getParamAnno() + jdbcType + "}");
						break;
					case "before":
					case "lessthan":
						ParamInfo paramInfo = ParamInfo.builder().paramAnno("max" + firstCharUpper(prop)).paramType(paramShortType).paramFullType(paramQualtifyType).paramValue("max" + firstCharUpper(prop)).build();
						info.getParamInfos().add(paramInfo);
						builder.append(" " + propColumn + cdata("<") + " #{" + paramInfo
								.getParamAnno() + jdbcType + "}");
						break;
					case "lessthanorequalto":
						ParamInfo paramInfo = ParamInfo.builder().paramAnno("max" + firstCharUpper(prop)).paramType(paramShortType).paramFullType(paramQualtifyType).paramValue("max" + firstCharUpper(prop)).build();
						info.getParamInfos().add(paramInfo);
						builder.append(" " + propColumn + cdata("<=") + " #{" + paramInfo
								.getParamAnno() + jdbcType + "}");
						break;
					case "between":
						ParamInfo min = ParamInfo.builder().paramAnno("min" + firstCharUpper(prop)).paramType(paramShortType).paramFullType(paramQualtifyType).paramValue("min" + firstCharUpper(prop)).build();

						ParamInfo max = ParamInfo.builder().paramAnno("max" + firstCharUpper(prop)).paramType(paramShortType).paramFullType(paramQualtifyType).paramValue("max" + firstCharUpper(prop)).build();
						info.getParamInfos().add(min);
						info.getParamInfos().add(max);
						builder.append(" " + propColumn + cdata(">") + " #{" + min
								.getParamAnno() + jdbcType + "} and " + propColumn + " " +
								cdata("<") + " #{" + max
								.getParamAnno() + jdbcType + "}");
						break;
					case "betweenorequalto":
						ParamInfo min = ParamInfo.builder().paramAnno("min" + firstCharUpper(prop)).paramType(paramShortType).paramFullType(paramQualtifyType).paramValue("min" + firstCharUpper(prop)).build();

						ParamInfo max = ParamInfo.builder().paramAnno("max" + firstCharUpper(prop)).paramType(paramShortType).paramFullType(paramQualtifyType).paramValue("max" + firstCharUpper(prop)).build();
						info.getParamInfos().add(min);
						info.getParamInfos().add(max);
						builder.append(" " + propColumn + cdata(">=") + " #{" + min
								.getParamAnno() + jdbcType + "} and " + propColumn + " " +
								cdata("<=") + " #{" + max
								.getParamAnno() + jdbcType + "}");
						break;
					case "isnotnull":
						builder.append(" " + prop + " is not null");
						break;
					case "isnull":
						builder.append(" " + prop + " is null");
						break;
					case "not":
						ParamInfo paramInfo = ParamInfo.builder().paramAnno("not" + firstCharUpper(prop)).paramType(paramShortType).paramFullType(paramQualtifyType).paramValue("not" + firstCharUpper(prop)).build();
						info.getParamInfos().add(paramInfo);
						builder.append(" " + propColumn + "<> #{" + paramInfo
								.getParamAnno() + jdbcType + "}");
						break;
					case "notin":
						ParamInfo paramInfo = ParamInfo.builder().paramAnno(prop + "List").paramType("List<" + paramShortType + ">").paramValue(prop + "List").build();
						info.getImportList().add("java.util.List");
						info.getParamInfos().add(paramInfo);
						builder.append(" " + propColumn + " not in \n" + "\t" + "<foreach item=\"item\" index=\"index\" collection=\"" + paramInfo

								.getParamAnno() + "\"\n" + "\t" + "open=\"(\" separator=\",\" close=\")\">\n" + "\t" + "#{item" + jdbcType + "}\n" + "\t" + "</foreach>\n");

						break;
					case "in":
						ParamInfo paramInfo = ParamInfo.builder().paramAnno(prop + "List").paramType("List<" + paramShortType + ">").paramValue(prop + "List").build();
						info.getImportList().add("java.util.List");
						info.getParamInfos().add(paramInfo);
						builder.append(" " + propColumn + " in \n" + "\t" + "<foreach item=\"item\" index=\"index\" collection=\"" + paramInfo

								.getParamAnno() + "\"\n" + "\t" + "open=\"(\" separator=\",\" close=\")\">\n" + "\t" + "#{item" + jdbcType + "}\n" + "\t" + "</foreach>\n");

						break;
					case "notlike":
						ParamInfo paramInfo = ParamInfo.builder().paramAnno("notlike" + firstCharUpper(prop)).paramType(paramShortType).paramFullType(paramQualtifyType).paramValue("notlike" + firstCharUpper(prop)).build();
						info.getParamInfos().add(paramInfo);
						builder.append(" " + propColumn + "not like #{" + paramInfo
								.getParamAnno() + jdbcType + "}");
						break;
					case "like":
						ParamInfo paramInfo = ParamInfo.builder().paramAnno("like" + firstCharUpper(prop)).paramType(paramShortType).paramFullType(paramQualtifyType).paramValue("like" + firstCharUpper(prop)).build();
						info.getParamInfos().add(paramInfo);
						builder.append(" " + propColumn + "like #{" + paramInfo
								.getParamAnno() + jdbcType + "}");
						break;
					case "startingwith":
						ParamInfo paramInfo = ParamInfo.builder().paramAnno(prop + "Prefix").paramType(paramShortType).paramFullType(paramQualtifyType).paramValue(prop + "Prefix").build();
						info.getParamInfos().add(paramInfo);
						builder.append(" " + propColumn + "like #{" + paramInfo
								.getParamAnno() + jdbcType + "}%");
						break;
					case "endingwith":
						ParamInfo paramInfo = ParamInfo.builder().paramAnno(prop + "Suffix").paramType(paramShortType).paramFullType(paramQualtifyType).paramValue(prop + "Suffix").build();
						info.getParamInfos().add(paramInfo);
						builder.append(" " + propColumn + "like %#{" + paramInfo
								.getParamAnno() + jdbcType + "}");
						break;
					case "containing":
						ParamInfo paramInfo = ParamInfo.builder().paramAnno("containing" + firstCharUpper(prop)).paramType(paramShortType).paramFullType(paramQualtifyType).paramValue("containing" + firstCharUpper(prop)).build();
						info.getParamInfos().add(paramInfo);
						builder.append(" " + propColumn + "like %#{" + paramInfo
								.getParamAnno() + jdbcType + "}%");
						break;
				}
			}
			if (rule.isUseIfTest()) {
				builder.append("</if>");
			}
		}
		info.setSql(info.getSql() + builder.toString());
	}

	private boolean isArrayOperator(String operator)
	{
		if (operator == null) {
			return false;
		}
		if ((operator.equals("in")) ||
				(operator.equals("notin"))) {
			return true;
		}
		return false;
	}

	private QueryInfo buildWithFind(MethodNameParsedResult result)
	{
		QueryInfo info = new QueryInfo();
		info.setParseQuery(result.getParsedFind());
		info.setImportList(new HashSet());
		ParsedFind find = result.getParsedFind();

		FieldToColumnRelation relation = result.getRelation();
		Map<String, String> fieldMap = result.getFieldMap();
		info.setType(SqlTypeEnum.SELECT);
		boolean queryAllTable = false;
		String fetchFunction;
		if ((find.getFetchProps() != null) && (find.getFetchProps().size() > 0))
		{
			if (find.getFetchProps().size() > 1)
			{
				GenClassDialog genClassDialog = new GenClassDialog(result.getProject(), find.getFetchProps(), fieldMap, result.getMethodName(), relation, result.getSrcClass());
				boolean b = genClassDialog.showAndGet();
				if (!b) {
					return null;
				}
				GenClassInfo genClassInfo = genClassDialog.getGenClassInfo();
				GenClassService.generateClassFileUsingFtl(genClassInfo);
				VirtualFileManager.getInstance().syncRefresh();

				PsiDocumentManager manager = PsiDocumentManager.getInstance(result.getProject());

				XmlFile mybatisXmlFile = result.getMybatisXmlFile();
				MyPsiXmlUtils.buildAllColumnMap(result.getProject(), manager
						.getDocument(mybatisXmlFile), mybatisXmlFile
						.getRootTag(), manager, genClassDialog
						.getExtractFieldToColumnRelation(), genClassDialog
						.getClassQutifiedName());
				info.setReturnClass(genClassDialog.getClassQutifiedName());
				info.setReturnMap(genClassDialog
						.getExtractFieldToColumnRelation()
						.getResultMapId());
			}
			else
			{
				FetchProp prop = (FetchProp)find.getFetchProps().get(0);
				if (prop.getFetchFunction() == null)
				{
					String returnClass = (String)fieldMap.get(prop.getFetchProp());
					info.setReturnClass(returnClass);
				}
				else
				{
					fetchFunction = prop.getFetchFunction();
					String returnClass = DbUtils.getReturnClassFromFunction(fieldMap, fetchFunction, prop
							.getFetchProp());
					info.setReturnClass(returnClass);
				}
			}
		}
		else
		{
			queryAllTable = true;
			info.setReturnMap(relation.getResultMapId());
		}
		Boolean returnList = find.getReturnList();
		if (find.getQueryRules() != null) {
			for (QueryRule rule : find.getQueryRules())
			{
				String prop = rule.getProp();
				if ((prop.toLowerCase().equals("id")) &&
						(rule.getOperator() == null)) {
					returnList = Boolean.valueOf(false);
				}
			}
		}
		if (info.getReturnClass() == null) {
			info.setReturnClass(result.getPsiClassFullName());
		}
		if (returnList.booleanValue())
		{
			info.setMethodReturnType("List<" +
					PsiClassUtil.extractLast(info.getReturnClass()) + ">");
			info.getImportList().add("java.util.List");
		}
		else
		{
			info.setMethodReturnType(
					PsiClassUtil.extractLast(info.getReturnClass()));
		}
		info.getImportList().add(info.getReturnClass());
		info.setParamInfos(new ArrayList());
		this.queryBuilderHandler.handleFindBeforeFromTable(info, result, queryAllTable);

		info.setSql(info.getSql() + "\n" + "\t" + " from " + result
				.getTableName());
		this.queryBuilderHandler.handleFindAfterFromTable(info, result);
		return info;
	}

	private static boolean checkHasFunction(ParsedFind find)
	{
		List<FetchProp> fetchProps = find.getFetchProps();
		if ((fetchProps == null) || (fetchProps.size() == 0)) {
			return false;
		}
		for (FetchProp fetchProp : fetchProps) {
			if (fetchProp.getFetchFunction() != null) {
				return true;
			}
		}
		return false;
	}
}
