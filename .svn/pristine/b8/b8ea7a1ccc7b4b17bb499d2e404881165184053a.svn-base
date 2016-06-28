<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>用户管理</title>
	<script type="text/javascript">
	function del(id) {
		if(id != 0){
		   if(confirm("确定删除么?")){
			  window.location.href="${ctx}/admin/user/delete/"+id;
		   }
	  	}else{
	  		var count=getCheckCount();
			if(count<=0)
				alert("至少选中一条记录!");
			if(count>0)
			{
				if(confirm("确定删除记录么?"))
				{
				    var index1 = 0;
				    var ids = new Array();
				    var arr=document.getElementsByName("ids");
				    for(var i=0;i<arr.length;i++){
					    if(arr[i].checked){ 
					    	ids[index1] = arr[i].value;
					    	index1++;
					    }
				    }
				    window.location.href="${ctx}/admin/user/delete/"+ids;
				}	
			}
	  	}
	}	
	</script>
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<div class="row">
		<div class="span4 offset7">
			<form class="form-search" action="#">
				<label>名称：</label> <input type="text" name="search_LIKE_resName" class="input-medium"
					 value="${param.search_LIKE_resName}"> 
				<button type="submit" class="btn" id="search_btn">Search</button>
				
		    </form>
	    </div>
	    <tags:sort/>
	</div>
	<div class="pagination">
		<a class="btn" 
			<shiro:hasRole name="admin">
				href="${ctx}/register"
			</shiro:hasRole>
			<shiro:lacksRole name="admin">
				<shiro:hasPermission name="user_create">href="${ctx}/register"</shiro:hasPermission>
			</shiro:lacksRole>>添加用户</a>
		<a class="btn" 
			<shiro:hasRole name="admin">
				href="javascript:del(0);"
			</shiro:hasRole>
			<shiro:lacksRole name="admin">
				<shiro:hasPermission name="user_delete">href="javascript:del(0);"</shiro:hasPermission>
			</shiro:lacksRole>>删除用户</a>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<th><input type="checkbox" name="checkall" id="checkbox" onclick="checkAll()"/></th>
			<th>登录名</th>
			<th>用户名</th>
			<th>注册时间</th>
			<th>管理</th></tr>
		</thead>
		<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><input type="checkbox" name="ids" id="checkbox" onchange="checkChange()" value="${user.id}"/></td>
				<td><a href="${ctx}/admin/user/update/${user.id}">${user.loginName}</a></td>
				<td>${user.name}</td>
				<td>
					<fmt:formatDate value="${user.registerDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
				</td>
				<td><a href="${ctx}/admin/user/delete/${user.id}">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
