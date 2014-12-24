<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜品管理</title>
<script type="text/javascript">
Ext.onReady(function() {
	Ext.define('Dish',{
		extend : 'Ext.data.Model',
		fields : [{
			name : 'id',
			type : 'int'
		},{
			name : 'typename',
			type : 'string'
		},{
			name : 'dishname',
			type : 'string'
		},{
			name : 'description',
			type : 'string'
		},{
			name : 'unitprice',
			type : 'float'
		},{
			name : 'imageurl',
			type : 'string'
		}
		]
	});
	
	//query page number
	var pageSize = 20;
	
	//list store
	var dishStore = new Ext.data.Store({
		model : 'Dish',
		pageSize : pageSize,
		proxy : {
			type : 'ajax',
			url : 'dish',
			reader : {
				type : 'json',
				root : 'dish',
				totalProperty : 'totalRecord'
			},
			extraParams : {
				type : 'list'//query type,list data
			}
		},
		autoLoad : {
			start : 0,
			limit : pageSize
		}
	});
	
	//type listitems store.
	var typeStore = new Ext.data.Store({
		fields : ['iddishtype','fullname'],
		proxy : {
			type : 'ajax',
			url : 'dish',
			reader : {
				type : 'json',
				root : 'type'
			},
			extraParams : {
				type : 'type'
			},
			autoLoad:true
		}
	});
	
	//type comboBox
	var typeComboBox = new Ext.form.ComboBox({
		name : 'iddishtype',
		fieldLabel : '权限组',
		store : typeStore,
		displayField : 'fullname',
		valueField : 'iddishtype',
		mode : 'remote',
		triggerAction:"all",
		editable:false,
		emptyText : '请选择类别'
	});
	
	//operator form.
	var formPanel = Ext.create('Ext.form.Panel', {
		layout : 'form',
		width : 300,
		bodyPadding: 5,
		url : 'dish',
		defaultType : 'textfield',
		border : false,
		fieldDefaults : {
			allowBlank : true
		},
		items : [ typeComboBox,{
			fieldLabel : '菜名',
			maxLength : 45,
			minLength : 2,
			name : 'fullname'
		},{
			fieldLabel : '描述',
			name : 'description'
		},{
			fieldLabel : '单价',
			name : 'unitprice'
		},{
			fieldLabel : '图片链接',
			name : 'imageurl'
		}
	]
	});
	
	//add dish window
	var win = Ext.create('Ext.window.Window', {
		title : '添加人员',
		closeAction : 'hide',
		width : 500,
		height : 300,
		minWidth : 300,
		minHeight : 200,
		layout : 'fit',
		plain : true,
		modal : true,
		items : formPanel,
		buttons : [ {
			text : '添加',
			listeners:{
				'click':function(){					
					formPanel.getForm().submit({
						waitTitle:"请稍候",
	                    waitMsg:"正在提交数据，请稍候",
					    url: "dish",
					    params : {
					    	'type': 'add'
					    },
					    success: function (form, action) {
					        Ext.Msg.alert('提示', action.result.msg);
					        formPanel.getForm().reset();
					        win.close();
					       dishStore.load({start : 0,limit : pageSize});
					    },
					    failure: function(form, action) {
	                        Ext.Msg.alert('提示', action.result.msg);
	                    }
					});
				}
			}
		}, {
			text : '取消',
			listeners : {
				'click' : function() {
					this.up("window").close();
				}
			}
		} ]
	});
	
	//grid
	var dishGrid = new Ext.grid.Panel({
		title : '',
		store : dishStore,
		columns : [ new Ext.grid.RowNumberer(), {
			text : '编号',
			dataIndex : 'id'
		},{
			text : '分类',
			dataIndex : 'typename'
		},{
			text : '菜名',
			dataIndex : 'dishname'
		},{
			text : '描述',
			dataIndex : 'description'
		},{
			text : '单价',
			dataIndex : 'unitprice'
		},{
			text : '图片链接',
			dataIndex : 'imageurl'
		}
		],
		tbar : [ '->', {
			xtype : 'button',
			id : "btnAdd_List",
			text : '添 加',
			scale : 'medium',
			icon : 'icons/fam/add.png',
			border : 1,
			style : {
				borderColor : 'gray',
				borderStyle : 'solid'
			},
			//add button event.
			listeners : {
				'click' : function() {
					win.show();
				}
			}
		} ],
		bbar : new Ext.PagingToolbar({
			store : dishStore,
			displayInfo : true,
			displayMsg : '第{0} 至 {1}条记录 合计 {2} 条',
			emptyMsg : "没有记录"
		}),
		height : 600,
		width : 800,
		renderTo : 'div_dishgrid'
	});
	
});
</script>
</head>
<body>
	<div id="div_dishgrid"></div>
</body>
</html>