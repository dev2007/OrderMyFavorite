<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="../ext-4.2.1/resources/css/ext-all.css" />
<script type="text/javascript" src="../ext-4.2.1/ext-all.js"></script>
<script type="text/javascript" src="../ext-4.2.1/ext-lang-zh_CN.js"></script>

<script type="text/javascript">
	Ext.onReady(function() {
		Ext.define('User',{
			extend:'Ext.data.Model',
			fields:[
			       {name:'fullname',type:'string'},
			       {name:'age',type:'int'},
			       {name:'sex',type:'string'},
			       {name:'phonenumber',type:'string'}
			       ]
		});
		
		var store = new Ext.data.Store({
			model:'User',
			proxy :{
				type:'ajax',
				url : '../MenuData',
				reader:{
					type:'json',
					root:'data'
				}
			},
			autoLoad:true
		});
		

		
		new Ext.grid.Panel({
			title : '测试',
			store : store,
			columns : [ {
				text : '姓名',
				dataIndex : 'fullname'
			}, {
				text : '年龄',
				dataIndex : 'age',
				flex : 1
			}, {
				text : '性别',
				dataIndex : 'sex'
			}, {
				text : '电话',
				dataIndex : 'phonenumber'
			} ],
			height : 200,
			width : 400,
			renderTo : 'div_grid'
		});
	});
</script>
</head>
<body>
<div id="div_grid"></div>
</body>
</html>