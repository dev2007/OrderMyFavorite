<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员管理</title>

<script type="text/javascript">
	Ext.onReady(function() {
		Ext.define('User', {
			extend : 'Ext.data.Model',
			fields : [ {
				name : 'fullname',
				type : 'string'
			}, {
				name : 'username',
				type : 'string'
			}, {
				name : 'age',
				type : 'int'
			}, {
				name : 'sex',
				type : 'string'
			}, {
				name : 'phonenumber',
				type : 'string'
			} ]
		});

		//query page number
		var pageSize = 20;

		//store
		var store = new Ext.data.Store({
			model : 'User',
			pageSize : pageSize,
			proxy : {
				type : 'ajax',
				url : 'operator',
				reader : {
					type : 'json',
					root : 'users',
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

		//check password twice
		Ext.apply(Ext.form.VTypes, {
			password : function(val, field) {
				if (field.confirmTo) {
					var pwd = field.up('form').down('#' +field.confirmTo);
					return (val == pwd.getValue());
				}
				return true;
			}
		});

		//default field y offset value.
		var yOffset = 30;
		
		//operator form.
		var form = Ext.create('Ext.form.Panel', {
			layout : 'absolute',
			url : 'operator',
			defaultType : 'textfield',
			border : false,
			fieldDefaults : {
				labelWidth : 60,
				labelAlign : "left",
				fieldWidth : 60,
				x : 5,
				anchor : '-5',
				allowBlank : true
			},
			items : [ {
				fieldLabel : '用户名',
				y : 2.5,
				maxLength : 45,
				minLength : 6,
				name : 'username'
			}, {
				fieldLabel : '密码',
				inputType : "password",
				y : 2.5 + yOffset,
				name : 'password',
				id : 'pass'
			}, {
				fieldLabel : '确认密码',
				inputType : "password",
				vtype : "password",//自定义的验证类型 
				vtypeText : "两次密码不一致！",
				confirmTo : "pass",//要比较的另外一个的组件的id 
				y : 2 * yOffset
			}, {
				fieldLabel : '权限组',
				y : 3 * yOffset,
				name : 'roleid'
			}, {
				fieldLabel : '姓名',
				maxLength : 45,
				minLength : 2,
				y : 4 * yOffset,
				name : 'fullname'
			}, {
				fieldLabel : '年龄',
				xtype : 'numberfield',
				minValue : 0,
				maxValue : 100,
				y : 5 * yOffset,
				name : 'age'
			}, {
				fieldLabel : '性别',
				y : 6 * yOffset,
				name : 'sex'
			}, {
				fieldLabel : '电话',
				xtype : 'numberfield',
				maxLength : 12,
				minLength : 7,
				y : 7 * yOffset,
				name : 'phonenumber'
			} ]
		});
		
		//operation type.
		//1.add		add opeator.
		//2.delete	delete operator.
		//3.edit		edit operator.
		var opType = "add";

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
			items : form,
			buttons : [ {
				text : '添加',
				listeners:{
					'click':function(){
						opType = "add";
						
						form.getForm().submit({
						    url: "operator",
						    params : {
						    	'type': opType
						    },
						    success: function (form, action) {
						        Ext.Msg.alert('Success', action.result.msg);
						    },
						    failure: function(form, action) {
		                        Ext.Msg.alert('Failed', action.result.msg);
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
		new Ext.grid.Panel({
			title : '',
			store : store,
			columns : [ new Ext.grid.RowNumberer(), {
				text : '姓名',
				dataIndex : 'fullname'
			}, {
				text : '年龄',
				dataIndex : 'age'
			}, {
				text : '性别',
				dataIndex : 'sex'
			}, {
				text : '电话',
				dataIndex : 'phonenumber'
			}, {
				xtype : 'actioncolumn',
				width : 100,
				align : 'center',
				items : [ {
					icon : 'icons/fam/user_edit.png',
					tooltip : '编 辑',
					handler : function(grid, rowIndex, colIndex) {
						var rec = grid.getStore().getAt(rowIndex);
						alert("Edit " + rec.get('fullname'));
					}
				}, '->', {
					icon : 'icons/fam/user_delete.png',
					tooltip : '删 除',
					handler : function(grid, rowIndex, colIndex) {
						var rec = grid.getStore().getAt(rowIndex);
						alert("Terminate " + rec.get('fullname'));
					}
				} ]
			} ],
			tbar : [ '->', {
				xtype : 'button',
				id : "btnAdd",
				text : '添 加',
				scale : 'medium',
				icon : 'icons/fam/user_add.gif',
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
				store : store,
				displayInfo : true,
				displayMsg : '第{0} 至 {1}条记录 合计 {2} 条',
				emptyMsg : "没有记录"
			}),
			height : 600,
			width : 800,
			renderTo : 'div_grid'
		});
	});
</script>
</head>
<body>
	<div id="div_grid"></div>
</body>
</html>