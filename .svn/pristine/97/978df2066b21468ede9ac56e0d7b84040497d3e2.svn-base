<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>资源管理</title>
	<script type="text/javascript">
	$(document).ready(function() {
		zTreeInfo();
		//聚焦第一个输入框
		$("#resName").focus();
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
			onClick: zTreeOnClick,
			onNodeCreated: zTreeOnNodeCreated
        }
	};
	
	function zTreeInfo(){
		$.ajax({
			url:"${ctx}/permission/getMenuAsJosnp", 
			dataType:"jsonp",
			data:{"roleId" : "", "flag" : "1"},
			success:function(data) {
				zNodes = data.menuList;
				menuIds = data.menuIds;
				var t = $("#tree");
				t = $.fn.zTree.init(t, setting, zNodes);
				var zTree = $.fn.zTree.getZTreeObj("tree");
				zTree.expandAll(true);
				var ch = t.getNodeByParam("id", $("#parentId").val(), null);
				if(ch != null){
					t.selectNode(ch);
					$("#parentName").val(ch.resName);
					t.updateNode(ch);
				}
			},
			error:function(){
				$("#tree").html("请求列表失败！！！");
			}
		});
	}
	
	function showIconForTree(treeId, treeNode) {
		return treeNode.level > 5;
	}
	
	function zTreeOnClick(event, treeId, treeNode){
		var id = $("#id").val();
		var pId = treeNode.id;
		if(id != pId){
			$("#parentId").val(treeNode.id);
			$("#parentName").val(treeNode.resName);
		}else{
			alert("父类资源不能是本身!");
		}
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
	</script>
</head>

<body>
	<form id="inputForm" action="${ctx}/resource/${action}" method="post" class="form-horizontal">
		<input type="hidden" id="id" name="id" value="${resource.id}"/>
		<input type="hidden" id="state" name="state" value="${resource.state}"/>
		<fieldset>
			<legend><small>添加资源</small></legend>
			<div class="control-group">
				<label for="resourceName" class="control-label">资源名称:</label>
				<div class="controls">
					<input type="text" id="resName" name="resName"  value="${resource.resName}" class="input-large required" minlength="3"/>
				</div>
			</div>
			<div class="control-group">
				<label for="resourceCode" class="control-label">资源编码:</label>
				<div class="controls">
					<input type="text" id="resCode" name="resCode"  value="${resource.resCode}" class="input-large required" minlength="3"/>
				</div>
			</div>
			<div class="control-group">
				<label for="resourceCode" class="control-label">父类资源:</label>
				<div class="controls dropdown">
					<input type="hidden" id="parentId" name="parentId"  value="${resource.parentId}"/>
					<input type="text" id="parentName" name="parentName" data-toggle="dropdown" class="input-large required dropdown-toggle" readonly="readonly"/>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
					<div style="margin :5px;">
						<ul id="tree" class="ztree"></ul>
					</div>
					</ul>
				</div>
			</div>
			<div class="control-group">
				<label for="resourceCode" class="control-label">访问地址:</label>
				<div class="controls">
					<input type="text" id="resUrl" name="resUrl"  value="${resource.resUrl}"   class="input-large"/>
				</div>
			</div>
			
			<div class="control-group">
				<label for="resourceCode" class="control-label">菜单类型:</label>
				<div class="controls">
      					<input type="radio" name="resType" id="resType" 
         				value="0" <c:if test="${resource.resType=='0' || resource.resType == null}"> checked='checked'</c:if>> 菜单
      					<input type="radio" name="resType" id="resType" 
         				value="1" <c:if test="${resource.resType=='1'}"> checked='checked'</c:if>>按钮
				</div>
			</div>
			
			<div class="control-group">
				<label for="resourceCode" class="control-label">菜单排序:</label>
				<div class="controls">
      				<input type="text" id="resSort" name="resSort"  value="${action=='create'?'9999':resource.resSort}"   class="input-large required"  maxlength="4"/>
				</div>
			</div>
			
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
	
</body>
</html>
