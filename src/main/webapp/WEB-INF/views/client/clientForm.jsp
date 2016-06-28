<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>客户端管理</title>
	<script type="text/javascript">
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#clientName").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate();
	});
	</script>
</head>

<body>
	<form id="inputForm" action="${ctx}/client/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${client.id}"/>
		<fieldset>
			<legend><small>客户端管理</small></legend>
			<div class="control-group">
				<label for="roleName" class="control-label">客户端名称:</label>
				<div class="controls">
					<input type="text" id="clientName" name="clientName"  value="${client.clientName}" class="input-large required" minlength="3"/>
				</div>
			</div>
			<c:if test="${action == 'update'}">
			<div class="control-group">
				<label for="roleCode" class="control-label">客户端编码:</label>
				<div class="controls">
					<input type="text" id="clientId" name="clientId"  value="${client.clientId}" class="input-large required" readonly="readonly" minlength="3"/>
				</div>
			</div>
			<div class="control-group">
				<label for="roleCode" class="control-label">客户端密钥:</label>
				<div class="controls">
					<input type="text" id="clientSecret" name="clientSecret"  value="${client.clientSecret}" class="input-large required" readonly="readonly" minlength="3"/>
				</div>
			</div>
			</c:if>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
</body>
</html>
