<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>角色管理</title>
	<script type="text/javascript">
	function del(id) {
		if(id != 0){
		   if(confirm("确定删除么?")){
			  window.location.href="${ctx}/role/delete/"+id;
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
				    window.location.href="${ctx}/role/delete/"+ids;
				}	
			}
	  	}
	}	
	  
	function rec(id, flag) {
		var str = "";
		if(flag == 0){
			str = "确定冻结么?";
		}
		if(flag == 1){
			str = "确定解冻么?";
		}
		if(confirm(str)){
			window.location.href="${ctx}/role/recover/"+id+"/"+flag;
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
				<label>名称：</label> <input type="text" name="search_LIKE_roleName" class="input-medium"
					 value="${param.search_LIKE_roleName}"> 
					<button type="submit" class="btn" id="search_btn">Search</button>
		    </form>
	    </div>
	    <tags:sort/>
	</div>
	<div class="pagination">
		<a class="btn"
		 <shiro:hasRole name="admin">
				href="${ctx}/role/create"</shiro:hasRole>
		<shiro:lacksRole name="admin">
			<shiro:hasPermission name="role_create">href="${ctx}/role/create"</shiro:hasPermission>
		</shiro:lacksRole>>添加角色</a>
		<a class="btn" 
		<shiro:hasRole name="admin">
				href="javascript:del(0);"
			</shiro:hasRole>
			<shiro:lacksRole name="admin">
				<shiro:hasPermission name="role_delete">href="javascript:del(0);"</shiro:hasPermission>
			</shiro:lacksRole>>删除角色</a>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" name="checkall" id="checkbox" onclick="checkAll()"/></th>
				<th>角色名称</th>
				<th>角色编码</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>更新人</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${roles.list}" var="role">
			<tr>
				<td><input type="checkbox" name="ids" id="checkbox" onchange="checkChange()" value="${role.id}"/></td>
				<td>
					<c:if test="${role.state == 1}">
						<a 
						 <shiro:hasRole name="admin"> href="${ctx}/role/update/${role.id}"</shiro:hasRole>
						 <shiro:lacksRole name="admin">
						   <shiro:hasPermission name="role_update">href="${ctx}/role/update/${role.id}"</shiro:hasPermission>
						</shiro:lacksRole>
						>${role.roleName}</a>
					</c:if>
					<c:if test="${role.state == 0}">
						${role.roleName}
					</c:if>
				</td>
				<td>${role.roleCode}</td>
				<td>${role.createName}</td>
				<td>
					<fmt:formatDate value="${role.createDate}" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td>${role.updateName}</td>
				<td>
					<fmt:formatDate value="${role.updateDate}" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td>
					<c:if test="${role.state == 1}">
						<a 
						 <shiro:hasRole name="admin"> href="javascript:rec(${role.id}, 0);"</shiro:hasRole>
						 <shiro:lacksRole name="admin">
						   <shiro:hasPermission name="role_frozen">href="javascript:rec(${role.id}, 0);"</shiro:hasPermission>
						</shiro:lacksRole>
						>冻结</a>
					</c:if>
					<c:if test="${role.state == 0}">
						<a <shiro:hasRole name="admin"> href="javascript:rec(${role.id}, 1);"</shiro:hasRole>
						 <shiro:lacksRole name="admin">
						   <shiro:hasPermission name="role_frozen">href="javascript:rec(${role.id}, 1);"</shiro:hasPermission>
						</shiro:lacksRole>>解冻</a>
					</c:if>|
					<a <shiro:hasRole name="admin">href="javascript:del(${role.id});"</shiro:hasRole>
					<shiro:lacksRole name="admin">
						<shiro:hasPermission name="role_delete">href="javascript:del(${role.id});"</shiro:hasPermission>
					</shiro:lacksRole>
					>删除</a>
				</td>
			</tr>
			
		</c:forEach>
		</tbody>
	</table>
	<tags:pagination page="${roles}" paginationSize="5"/>
</body>
</html>
