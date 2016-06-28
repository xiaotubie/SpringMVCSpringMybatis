<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>登录并授权</title>
    <style>.error{color:red;}</style>
</head>
<body>
<div class="loginDiv">
	<div class="error">${error}</div>
	<form action="" method="post" class="form-horizontal">
		<div class="control-group">
			<label for="password" class="control-label">用户:</label>
			<div class="controls">
				<input type="text" id="username" name="username" class="input-medium required"/>
			</div>
		</div>
		<div class="control-group">
			<label for="password" class="control-label">密码:</label>
			<div class="controls">
				<input type="password" id="password" name="password" class="input-medium required"/>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="登录并授权"/> 
			</div>
		</div>
	</form>
</div>
</body>
</html>