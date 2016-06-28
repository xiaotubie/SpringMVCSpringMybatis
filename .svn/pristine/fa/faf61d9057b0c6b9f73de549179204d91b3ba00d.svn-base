<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<script type="text/javascript">
$(document).ready(function() {
	menusLoad();
});
function menusLoad(){
	$.ajax({
		type:"post",
		url:"${ctx}/permission/menusLoad",
		dataType:"json",
		success: function(data) {
			var content = "";
			var menusList = data.menusList;
			if(menusList != null && menusList.length > 0){
				for(var i=0; i<menusList.length; i++){
					var menu = menusList[i];
					if(menu.parentId == 0){
						if(menu.resUrl != null && "" != menu.resUrl){
							content += "<li><a href='${ctx}/"+menu.resUrl+"'>"+menu.resName+"</a></li>";
						}else{
							content += "<li class='dropdown'>";
							content += "<a data-toggle='dropdown' class='dropdown-toggle' href='#'>";
							content += menu.resName;
							content += "<b class='caret'></b></a>";
							content += "<ul class='dropdown-menu'>";
							var childMenus = menu.childMenu;
							if(childMenus != null && childMenus.length > 0){
								for(var j=0; j<childMenus.length; j++){
									var menu2 = childMenus[j];
									var childMenus2 = menu2.childMenu;
									if(childMenus2 != null && childMenus2.lenght > 0){
										content += "<li class='dropdown-submenu'><a href='#'>"+menu2.resName+"</a>";
									}else{
										content += "<li><a href='${ctx}/"+menu2.resUrl+"'>"+menu2.resName+"</a><li>";
									}
								}
							} 
							content += "</ul>";
							content += "</li>";
						}
					}
				}
			}
			$("#menus").html(content);
		}
	});
}
</script>
</head>
</html>
<body>
<div id="header">
	<div class="head">
		<div class="wind">
		 	<div class="logo"><a href="${ctx}"><img src="${ctx}/static/images/logo.jpg" /></a></div>
		</div>
		<shiro:user>
	    	<div class="btn-group pull-right" style="margin-top: 50px; margin-right: 20px;">
				<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
					<i class="icon-user"></i> <shiro:principal property="name"/>
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="${ctx}/profile">修改资料</a></li>
					<li><a href="${ctx}/login/logout">退出登录</a></li>
				</ul>
			</div>
		</shiro:user>
	</div>
	<div id="title" style="margin-top: 10px;">
    	<shiro:user>
			<div class="navbar">
				<div class="navbar-inner">
					<div class="nav-collapse pl50">
						<ul id="menus" class="nav">
						</ul>
					</div>
				</div>
			</div>
		</shiro:user>
	</div>
</div>
</body>
