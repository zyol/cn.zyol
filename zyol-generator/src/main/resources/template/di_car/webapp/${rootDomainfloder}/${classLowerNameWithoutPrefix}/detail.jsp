<#assign myParentDir="entity">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerFull = className?lower_case>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>
<#assign clasNameWithoutPrefix = classNameLowerFull?replace(rootPagefloder,'')>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<title>${table.remarks}详细详细</title>
	<jsp:include page="/common/common.jsp"/>
</head>
<body style="background:#f9f9f9;" data-spy="scroll" data-target=".left-navbar">
<div class="top-title">
	<div class="title-box">
		<div class="content-head-title">
			<i class="mark-img"></i><span class="label">当前位置：功能模块详细信息查看</span>
		</div>
	</div>
</div>
<div class="main-content">
	<div class="right-content">
		<form id="myView">
			<#list table.pkColumns as column>
				<input type="hidden" name="${column.camelName}" id="${column.camelName}" value="${r'${'}data.${column.camelName}${r'}'}"/>
			</#list>
			<div class="panel">
				<div  id="configInfo" class="inner-panel">
					<div class="panel-heading">
						<span class="label app-label">${table.remarks}详细详细</span>
						<i class="fold"></i>
					</div>
					<div class="panel-bodying">
						<div class="information-body">
							<ul class="information">
								<#list table.columns as column><#if !column.isPk()>
								<li>
									<label> ${column.columnAlias}：</label>
									<div class="list-info">
								 		${r'${'}data.${column.camelName}${r'}'}
									</div>
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
				<li class="active"><a href="#configInfo"><i></i>基本信息</a></li>
			</ul>
			<button class="big-btn big-change radius5" type="button" onclick="change('${r'${'}data.${table.idColumn.camelName}${r'}'}');"><i class="custom-change"></i>变更</button>
			<button class="big-btn big-return radius5" type="button" onclick="back();"><i class="custom-return"></i>返回</button>
		</div>
	</div>
</div>
</body>

<script type="text/javascript" src="${r'${contextPath}'}/pages/${rootDomainfloder}/${classLowerNameWithoutPrefix}/controller.js?v= ${r'${cssVersion}'}"></script>

</html>
