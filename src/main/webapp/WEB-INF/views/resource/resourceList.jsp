<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>资源管理</title>
	<script type="text/javascript">
	$(document).ready(function() {
		var message = '${message}';
		if(message != null && "" != message){
			menusLoad();
		}
	});
	function del(id) {
		if(id != 0){
		   if(confirm("确定删除么?")){
			  window.location.href="${ctx}/resource/delete/"+id;
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
				    window.location.href="${ctx}/resource/delete/"+ids;
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
			window.location.href="${ctx}/resource/recover/"+id+"/"+flag;
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
				href="${ctx}/resource/create"
			</shiro:hasRole>
			<shiro:lacksRole name="admin">
				<shiro:hasPermission name="resource_create">href="${ctx}/resource/create"</shiro:hasPermission>
			</shiro:lacksRole>>添加资源</a>
		<a class="btn" 
			<shiro:hasRole name="admin">
				href="javascript:del(0);"
			</shiro:hasRole>
			<shiro:lacksRole name="admin">
				<shiro:hasPermission name="resource_delete">href="javascript:del(0);"</shiro:hasPermission>
			</shiro:lacksRole>>删除资源</a>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" name="checkall" id="checkbox" onclick="checkAll()"/></th>
				<th>资源名称</th>
				<th>资源编码</th>
				<th>父类资源</th>
				<th>访问地址</th>
				<th>菜单类型</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${resources.list}" var="resource">
			<tr>
				<td><input type="checkbox" name="ids" id="checkbox" onchange="checkChange()" value="${resource.id}"/></td>
				<td>
					<c:if test="${resource.state == 1}">
						<a 
						 <shiro:hasRole name="admin"> href="${ctx}/resource/update/${resource.id}"</shiro:hasRole>
						 <shiro:lacksRole name="admin">
						   <shiro:hasPermission name="resource_update">href="${ctx}/resource/update/${resource.id}"</shiro:hasPermission>
						</shiro:lacksRole>>${resource.resName}</a>
					</c:if>
					<c:if test="${resource.state == 0}">
						${resource.resName}
					</c:if>
				</td>
				<td>${resource.resCode}</td>
				<td>
					<c:if test="${resource.parentName == null}">功能菜单</c:if>
					<c:if test="${resource.parentName != null}">${resource.parentName}</c:if>
				</td>
				<td>${resource.resUrl}</td>
				<td>
					<c:if test="${resource.resType == 0}">
						菜单
					</c:if>
					<c:if test="${resource.resType == 1}">
						按钮
					</c:if>
				</td>
				<td>${resource.createName}</td>
				<td>
					<fmt:formatDate value="${resource.createDate}" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td>
					<c:if test="${resource.state == 1}">
						<a 
						 <shiro:hasRole name="admin"> href="javascript:rec(${resource.id}, 0);"</shiro:hasRole>
						 <shiro:lacksRole name="admin">
						   <shiro:hasPermission name="resource_frozen">href="javascript:rec(${resource.id}, 0);"</shiro:hasPermission>
						</shiro:lacksRole>
						>冻结</a>
					</c:if>
					<c:if test="${resource.state == 0}">
						<a 
						 <shiro:hasRole name="admin"> href="javascript:rec(${resource.id}, 1);"</shiro:hasRole>
						 <shiro:lacksRole name="admin">
						   <shiro:hasPermission name="resource_frozen">href="javascript:rec(${resource.id}, 1);"</shiro:hasPermission>
						</shiro:lacksRole>>解冻</a>
					</c:if>|
					<a  <shiro:hasRole name="admin"> href="javascript:del(${resource.id});"</shiro:hasRole>
						<shiro:lacksRole name="admin">
							<shiro:hasPermission name="resource_delete">href="javascript:del(${resource.id});"</shiro:hasPermission>
						</shiro:lacksRole>>删除</a>
				</td>
			</tr>
			
		</c:forEach>
		</tbody>
	</table>
	<tags:pagination page="${resources}" paginationSize="5"/>
</body>
</html>
