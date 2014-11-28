<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.awu.app.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理页</title>
<link rel="stylesheet" type="text/css"
	href="ext-4.2.1/resources/css/ext-all.css" />
<script type="text/javascript" src="ext-4.2.1/ext-all.js"></script>
<script type="text/javascript" src="ext-4.2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
	//按菜单结点判断相应的加载页面
	var id = 0;
	
	function Link(){
		var url = "menupage/";
		if(id == 1){
			url += "";
		}else if(id == 2){
			url += "operator.jsp";
		}else if(id == 3){
			url += "";
		}else if(id == 4){
			url += "";
		}
		return url;
	}
	
	Ext.onReady(function() {
				//获取菜单数据
				var store = Ext.create('Ext.data.TreeStore', {
					id:'tree_store',
					proxy:{
						type:'ajax',
						url:'MenuData',
						reader:{type:'json'}
					},
					autoLoad:true
				});
				
				
				//菜单树 
				var menu_Tree = new Ext.tree.TreePanel({
					region : 'west',
					width : 300,
					title : '菜单',
					collapsible : true,
					split : true,
					//-----
					containerScroll : true,
					animate : true,
					store : store,
					rootVisible : false,
					listeners: {
		                itemclick: function (view, record, item, index, e) {
		                	if(record.get('leaf')){
		                		id = record.raw.id;
		                		var n = contentPanel.getComponent(record.raw.id);
		                        if (!n) { // 判断是否已经打开该面板
		                            n = contentPanel.add({
		                                'id' : record.raw.id,
		                                'title' : record.raw.text,
		                                closable : true,
		                                autoLoad:{
		                                	url:Link(),//'Menu?menuid='+record.raw.id,//TODO:Tab页面
		                                	scripts:true}
		                            });
		                        }
		                        contentPanel.setActiveTab(n);
		                	}else{
		                		alert("not leaf");
		                	}
		                }
		            }

				});

				//功能区
				var contentPanel = new Ext.TabPanel(
						{
							region : 'center',
							enableTabScroll : true,
							activeTab : 0,
							items : [ {
								id : 'homePage',
								title : '首页',
								autoScroll : true,
								html : '<div style="position:absolute;color:#ff0000;top:40%;left:40%;">欢迎登录综合管理系统</div>'
							} ]
						});
							
				//布局
				new Ext.Viewport({
					title : "Viewport",
					layout : "border",
					defaults : {
						bodyStyle : "background-color: #FFFFFF;",
						frame : true
					},
					items : [ menu_Tree, {
						region : "north",
						title : '综合管理系统',
						collapsible : false
					}, contentPanel ]
				});

			});
</script>
</head>
<body>
</body>
</html>