<#assign myParentDir="entity">
<#assign className = table.className>
<#assign classNameLower = className?lower_case>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>
<#assign clasNameWithoutPrefix = classNameLower?replace(rootPagefloder,'')>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<title>${table.remarks}</title>
	<jsp:include page="/common/common.jsp"/>
</head>
<body style="background:#f9f9f9;" data-spy="scroll" data-target=".left-navbar">
<div class="top-title">
	<div class="title-box">
		<div class="content-head-title">
			<i class="mark-img"></i><span class="label">当前位置：${table.remarks}</span>
		</div>
	</div>
</div>
<div class="main-content">
	<div class="right-content">
		<form id="myForm" action="${r'${contextPath}'}/${rootDomainfloder}/${classLowerNameWithoutPrefix}/save" method="post">
			<#list table.pkColumns as column>
				<input type="hidden" name="${column.camelName}" id="${column.camelName}" value="${r'${'}data.${column.camelName}${r'}'}"/>
			</#list>
			<div class="panel">
				<div  id="configInfo" class="inner-panel">
					<div class="panel-heading">
						<span class="label">应用基本信息</span>
						<i class="fold"></i>
					</div>
					<div class="panel-bodying">
						<div class="information-body">
							<ul class="information">
								<#list table.columns as column><#if !column.isPk()>
								<li class="row-li">
								<label><i>*</i>${column.columnAlias}：</label>
								<#if (column.simpleJavaType =="Timestamp" || column.simpleJavaType =="Date")>
									<input class="easyui-validatebox text lg-input"  name="${column.camelName}" id="${column.camelName}" value="${r'${'}data.${column.camelName}${r'}'}"/>" />
								<#elseif (column.simpleJavaType =="Integer" || column.simpleJavaType =="Long" || column.simpleJavaType =="Short") >
									<input class="easyui-validatebox text lg-input"  name="${column.camelName}" id="${column.camelName}" value="${r'${'}data.${column.camelName}${r'}'}" data-options="validType:'integer'"/>
								<#elseif (column.simpleJavaType =="BigDecimal" || column.simpleJavaType =="Double" || column.simpleJavaType =="Float") >
									<input class="easyui-validatebox text lg-input"  name="${column.camelName}" id="${column.camelName}" value="${r'${'}data.${column.camelName}${r'}'}" />
								<#elseif (column.simpleJavaType =="Boolean" ) >
									<input class="easyui-validatebox text lg-input"  name="${column.camelName}" id="${column.camelName}" value="${r'${'}data.${column.camelName}${r'}'}" />
								<#else>
									<input class="easyui-validatebox text lg-input"  name="${column.camelName}" id="${column.camelName}" value="${r'${'}data.${column.camelName}${r'}'}" data-options="validType:'maxLength[255]'"/>
								</#if>
								</li>
								</#if></#list>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="left-content">
		<div class="left-navbar">
			<ul class="left-nav nav">
				<li class="active"><a href="#configInfo"><i class="icons-arrow"></i>基本信息</a></li>
			</ul>
			<button class="big-btn big-save radius5" type="button" onclick="save();"><i class="custom-save"></i>保存</button>
			<button class="big-btn big-return radius5" type="button" onclick="back();"><i class="custom-return"></i>返回</button>
		</div>
	</div>
</div>
<script type="text/javascript" src="${r'${contextPath}'}/pages/${rootDomainfloder}/${classLowerNameWithoutPrefix}/controller.js?v= ${r'${cssVersion}'}"></script>

</body>
</html>
