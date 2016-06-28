<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>员工信息管理</title>
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<div class="row">
		<div class="span4 offset7">
			<form class="form-search" action="#">
				<label>名称：</label> <input type="text" name="search_LIKE_title" class="input-medium"
					 value="${param.search_LIKE_title}"> 
				<button type="submit" class="btn" id="search_btn">Search</button>
		    </form>
	    </div>
	    <tags:sort/>
	</div>
	<div class="pagination">
		<a class="btn" href="${ctx}/people/create">创建员工</a>
		<a class="btn" href="#">删除员工</a>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>姓名</th><th>工号</th><th>职位</th></tr></thead>
		<tbody>
		<c:forEach items="${peoples.list}" var="people">
			<tr>
				<td><a href="${ctx}/people/update/${people.id}">${people.name}</a></td>
				<td>${people.numberCard}</td>
				<td>${people.position}</td>
				<td><a href="${ctx}/people/delete/${people.id}">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<tags:pagination page="${peoples}" paginationSize="5"/>

</body>
</html>
