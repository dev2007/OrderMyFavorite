<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.awu.servlet.bl.*"%>
<%@ page import="com.awu.db.utils.EDBMSG"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理页</title>
<script type="text/javascript" src="jquery-easyui-1.4/jquery.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.4/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4/themes/icon.css">
<script type="text/javascript">
function convert(rows){
	function exists(rows, parentId){
		for(var i=0; i<rows.length; i++){
			if (rows[i].id == parentId) return true;
		}
		return false;
	}
	
	var nodes = [];
	// get the top level nodes
	for(var i=0; i<rows.length; i++){
		var row = rows[i];
		if (!exists(rows, row.parentId)){
			nodes.push({
				id:row.id,
				text:row.name
			});
		}
	}
	
	var toDo = [];
	for(var i=0; i<nodes.length; i++){
		toDo.push(nodes[i]);
	}
	while(toDo.length){
		var node = toDo.shift();	// the parent node
		// get the children nodes
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			if (row.parentId == node.id){
				var child = {id:row.id,text:row.name};
				if (node.children){
					node.children.push(child);
				} else {
					node.children = [child];
				}
				toDo.push(child);
			}
		}
	}
	return nodes;
}

$(function(){
	$('#tt').tree({
		onClick:function(node){
			alert(node.text);
			htmlobj=$.ajax({
			type:'POST',
			url:'Menu',
			data:{
				username:
				<%String userName = (String) session.getAttribute("username");
						if (null == userName) {
							out.print("\"\"");
						}
						out.print("\""+userName+"\"");
				%>,
				menuid:
					node.id
			},
			success:function(data){
			 	$('#content').html(htmlobj.responseText);
			},
			error:function(){			
			}
			});
		}
	});
});

<%
		//validate session
		String username = (String) session.getAttribute("username");
		if (null == username) {
			response.sendRedirect("CorpLogin.jsp");
		}
		else{
			ILoadMenuBL loadMenuBL = new CLoadMenuBL();
			EDBMSG result = loadMenuBL.loadMenu(request,username);
				
			String js = "$(function(){$('#tt').tree({"
						+"url: 'menudata/"+username+".json',"
						+"loadFilter: function(rows){"
						+"		return convert(rows);"
						+"	}"
						+"});"
						+"});";
			out.println(js);
		}
%>
	
</script>
</head>
<body>
	<h2>管理系统</h2>
	<p>
		欢迎你，<%
		out.print(session.getAttribute("username"));
	%>
	</p>
	<div id="grid" class="easyui-layout" style="width: 98%; height: 600px;">
		<div data-options="region:'west',split:true" title="菜单"
			style="width: 300px;">
			<ul id="tt"></ul>
		</div>
		<div id="content" region="center" title="功能区" style="padding: 5px;">
			<table id="dg"></table>
		</div>
	</div>
</body>
</html>