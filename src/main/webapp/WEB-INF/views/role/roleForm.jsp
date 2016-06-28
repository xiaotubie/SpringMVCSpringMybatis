<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>角色管理</title>
	<script type="text/javascript">
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#roleName").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate();
	});
	</script>
</head>

<body>
	<form id="inputForm" action="${ctx}/role/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${role.id}"/>
		<input type="hidden" name="state" value="${role.state}"/>
		<fieldset>
			<legend><small>添加角色</small></legend>
			<div class="control-group">
				<label for="roleName" class="control-label">角色名称:</label>
				<div class="controls">
					<input type="text" id="roleName" name="roleName"  value="${role.roleName}" class="input-large required" minlength="3"/>
				</div>
			</div>
			<div class="control-group">
				<label for="roleCode" class="control-label">角色编码:</label>
				<div class="controls">
					<input type="text" id="roleCode" name="roleCode"  value="${role.roleCode}" class="input-large required" minlength="3"/>
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
</body>
</html>
