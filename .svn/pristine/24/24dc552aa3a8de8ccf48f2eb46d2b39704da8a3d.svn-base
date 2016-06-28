<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>员工信息管理</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/people/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${people.id}"/>
		<fieldset>
			<legend><small>员工信息</small></legend>
			<div class="control-group">
				<label for="task_title" class="control-label">姓名:</label>
				<div class="controls">
					<input type="text" id="people_name" name="name"  value="${people.name}" class="input-large required" minlength="3"/>
				</div>
			</div>	
			<div class="control-group">
				<label for="description" class="control-label">工号:</label>
				<div class="controls">
					<input type="text" id="numberCard" name="numberCard"  value="${people.numberCard}" class="input-large required" minlength="3"/>
				</div>
			</div>
			<div class="control-group">
				<label for="description" class="control-label">职位:</label>
				<div class="controls">
				<input type="text" id="position" name="position"  value="${people.position}" class="input-large required" minlength="3"/>
				</div>
			</div>		
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#people_name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</body>
</html>
