<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>权限分配</title>
	<script type="text/javascript">
	function del(id) {
		if(id != 0){
		   if(confirm("确定删除么?")){
			  window.location.href="${ctx}/permission/delete/"+id;
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
				    window.location.href="${ctx}/permission/delete/"+ids;
				}	
			}
	  	}
	}
	
	var setting = {
			view: {
				showLine: true,
				showIcon:false
				
			},
			data: {
				simpleData: {
					enable: true,
					idKey:"resId",
					pIdKey:"resPid"
				},
				key:{
					name:"resName"
				}
			}
		};
	
	
	$(document).ready(function(){
	   var el =$(".pertree");
  	   el.each(function(index){
  		  var perHid = $(this).next(".perValue"); 
  		  var perVal = perHid.val();
  		  $.fn.zTree.init($(this), setting, $.parseJSON(perVal));
        });
	});
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
			<shiro:hasRole name="admin"> href="${ctx}/permission/create" </shiro:hasRole>
			<shiro:lacksRole name="admin">
				<shiro:hasPermission name="permission_create">href="${ctx}/permission/create" </shiro:hasPermission>
			</shiro:lacksRole>>添加权限</a>
		<a class="btn"
		 <shiro:hasRole name="admin">href="javascript:del(0);"</shiro:hasRole>
			<shiro:lacksRole name="admin">
				<shiro:hasPermission name="permission_delete">href="javascript:del(0);"</shiro:hasPermission>
			</shiro:lacksRole>>删除权限</a>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" name="checkall" id="checkbox" onclick="checkAll()"/></th>
				<th>角色名称</th>
				<th>角色编码</th>
				<th>权限功能</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${permissions.list}" var="permission" varStatus="s">
			<tr>
				<td><input type="checkbox" name="ids" id="checkbox" onchange="checkChange()" value="${permission.roleId}"/></td>
				<td>${permission.roleName}</td>
				<td>${permission.roleCode}</td>
				<td style="width: 500px;">
					<div class="zTreeDemoBackground left">
						<ul id="tree${s.index}" class="ztree pertree"></ul>
						<input class="perValue" type="hidden" value='${permission.resName}'/>
					</div>
				</td>
				<td><a 
				 <shiro:hasRole name="admin">href="${ctx}/permission/update/${permission.roleId}" </shiro:hasRole>
			<shiro:lacksRole name="admin">
				<shiro:hasPermission name="permission_update">href="${ctx}/permission/update/${permission.roleId}" </shiro:hasPermission>
			</shiro:lacksRole>
				>修改权限</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<tags:pagination page="${permissions}" paginationSize="5"/>
</body>
</html>
