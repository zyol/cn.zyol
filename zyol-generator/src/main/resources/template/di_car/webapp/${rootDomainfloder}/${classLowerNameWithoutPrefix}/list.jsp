<#assign myParentDir="entity">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerFull = className?lower_case>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>
<#assign clasNameWithoutPrefix = classNameLowerFull?replace(rootPagefloder,'')>
<%@ taglib prefix="text-align" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>${table.remarks}管理</title>
	<jsp:include page="/common/common.jsp"/>
</head>
<body class="easyui-layout">
<div data-options="region:'center',border:false" id="myBody">
	<div class="main-table">
		<div class="toolbar" style="padding:6px 0px 4px;">
			<div style="float:left;padding-left:35px;">
				<div class="content-head-title">
					<i class="mark-img"></i>
					<span class="label" id="myNav">
				  当前位置：${table.remarks}
				</span>
				</div>
			</div>
			<div style="float:right;">
				<table cellspacing="0" cellpadding="0">
					<tbody>
					<tr>
						<td>
							<a href="javascript:add();" class="middle-btn"><em class="pm-plus"></em>新增</a>
						</td>
						<td>
							<a href="javascript:reloadPage();" class="middle-btn"><em class="pm-refresh"></em>刷新</a>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
		</div>
		<table id="mygrid" data-options="fit:true,fitColumns:true,striped:true,toolbar:'.toolbar'"></table>
	</div>
</div>
</body>

<script type="text/javascript" src="${r'${contextPath}'}/pages/${rootDomainfloder}/${classLowerNameWithoutPrefix}/controller.js?v= ${r'${cssVersion}'}"></script>

</html>

