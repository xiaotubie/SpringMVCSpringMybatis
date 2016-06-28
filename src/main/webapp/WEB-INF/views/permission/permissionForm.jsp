<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>权限分配</title>
	<script type="text/javascript">
	$(document).ready(function() {
		zTreeInfo();
		//聚焦第一个输入框
		$("#roleId").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate();
	});
	
	var zNodes;
	var menuIds;
	
	var setting = {
		view: {
			showLine: true,
			showIcon: showIconForTree
		},
		check: {
			enable: true,
			chkStyle: "checkbox"
		},
		data: {
			key: {
				name: "resName"
			},
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "parentId"
			}
		},
		callback : {
			onNodeCreated: zTreeOnNodeCreated
        }
	};
	
	function zTreeInfo(){
		var roleId = $("#roleId").val();
		$.ajax({
			url:"${ctx}/permission/getMenuAsJosnp", 
			dataType:"jsonp",
			data:{"roleId" : roleId, "flag" : "0"},
			success:function(data) {
				zNodes = data.menuList;
				menuIds = data.menuIds;
				var t = $("#tree");
				t = $.fn.zTree.init(t, setting, zNodes);
				var zTree = $.fn.zTree.getZTreeObj("tree");
				zTree.expandAll(true);
			},
			error:function(){
				$("#tree").html("请求列表失败！！！");
			}
		});
	}
	
	function showIconForTree(treeId, treeNode) {
		return treeNode.level > 5;
	}
	
	function zTreeOnNodeCreated(event, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("tree");
		if(menuIds != null && menuIds.length > 0){
			for(var i = 0; i < menuIds.length; i++){
				if(menuIds[i] == treeNode.id){
					treeNode.checked = true;
				}
			}
		}
		zTree.updateNode(treeNode);
	}
	
	function savepe(){
		var roleId = $("#roleId").val();
		if(roleId != null && "" != roleId){
			var selectMenuIds = [];
			var zTree = $.fn.zTree.getZTreeObj("tree");
			var checkedNodes = zTree.getCheckedNodes();
			if(checkedNodes != null && checkedNodes.length > 0){
				for(var i = 0; i < checkedNodes.length; i++){
					selectMenuIds.push(checkedNodes[i].id);
				}
			}
			$("#selectIds").val(selectMenuIds);
			$("#inputForm").submit();
		}else{
			$("#roleId").focus();
			alert("请选择角色!");
		}
	}
	</script>
</head>

<body>
	<form id="inputForm" action="${ctx}/permission/${action}" method="post" class="form-horizontal">
		<input type="hidden" id="selectIds" name="selectIds"/>
		<input type="hidden" name="id" value="${permission.id}"/>
		<fieldset>
			<legend><small>权限分配</small></legend>
			<div class="control-group">
				<label for="roleId" class="control-label">角色名称:</label>
				<div class="controls">
					<select id="roleId" name="roleId" onchange="zTreeInfo();">
						<option value="">-----请选择-----</option>  
						<c:forEach items="${roles}" var="role">	
							<option value="${role.id}" <c:if test="${role.id == permission.roleId}">selected="selected"</c:if>>${role.roleName}</option>
						</c:forEach>  
					</select>
				</div>
			</div>
			<div class="control-group">
				<label for="tree" class="control-label">功能菜单:</label>
				<div class="controls">
					<ul id="tree" class="ztree" style="width: 300px; max-height:350px; overflow:auto;"></ul>
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="button" value="提交" onclick="savepe();"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
</body>
</html>
