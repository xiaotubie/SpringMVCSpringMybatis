<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>客户端管理</title>
	<script type="text/javascript">
	function del(id) {
		if(id != 0){
		   if(confirm("确定删除么?")){
			  window.location.href="${ctx}/client/delete/"+id;
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
				    window.location.href="${ctx}/client/delete/"+ids;
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
				<label>名称：</label> <input type="text" name="search_LIKE_loginName" class="input-medium"
					 value="${param.search_LIKE_loginName}"> 
				<button type="submit" class="btn" id="search_btn">Search</button>
				
		    </form>
	    </div>
	    <tags:sort/>
	</div>
	<div class="pagination">
		<a class="btn" 
			<shiro:hasRole name="admin">
				href="${ctx}/client/create"
			</shiro:hasRole>
			<shiro:lacksRole name="admin">
				<shiro:hasPermission name="client_create">href="${ctx}/client/create"</shiro:hasPermission>
			</shiro:lacksRole>>添加客户端</a>
		<a class="btn" 
			<shiro:hasRole name="admin">
				href="javascript:del(0);"
			</shiro:hasRole>
			<shiro:lacksRole name="admin">
				<shiro:hasPermission name="client_delete">href="javascript:del(0);"</shiro:hasPermission>
			</shiro:lacksRole>>删除客户端</a>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" name="checkall" id="checkbox" onclick="checkAll()"/></th>
				<th>客户端名称</th>
				<th>客户端编码</th>
				<th>客户端密钥</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${clients.list}" var="client">
			<tr>
				<td><input type="checkbox" name="ids" id="checkbox" onchange="checkChange()" value="${client.id}"/></td>
				<td>
					<a 
					 <shiro:hasRole name="admin"> href="${ctx}/client/update/${client.id}"</shiro:hasRole>
					 <shiro:lacksRole name="admin">
					   <shiro:hasPermission name="client_update">href="${ctx}/client/update/${client.id}"</shiro:hasPermission>
					</shiro:lacksRole>>${client.clientName}</a>
				</td>
				<td>${client.clientId}</td>
				<td>${client.clientSecret}</td>
				<td>
					<a  <shiro:hasRole name="admin"> href="javascript:del(${client.id});"</shiro:hasRole>
						<shiro:lacksRole name="admin">
							<shiro:hasPermission name="client_delete">href="javascript:del(${client.id});"</shiro:hasPermission>
						</shiro:lacksRole>>删除</a>
				</td>
			</tr>
			
		</c:forEach>
		</tbody>
	</table>
	<tags:pagination page="${clients}" paginationSize="5"/>
</body>
</html>
