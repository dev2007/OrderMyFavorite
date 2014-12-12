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
			}, {
				name : 'rolename',
				type : 'string'
			} ]
		});

		//query page number
		var pageSize = 20;

		//list store
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
		
		//limit listitems store.
		var limitStore = new Ext.data.Store({
			fields : ['idrole','fullname'],
			proxy : {
				type : 'ajax',
				url : 'operator',
				reader : {
					type : 'json',
					root : 'limit'
				},
				extraParams : {
					type : 'limit'
				},
				autoLoad:true
			}
		});
		
		//limit comboBox
		var limitComboBox = new Ext.form.ComboBox({
			name : 'roleid',
			fieldLabel : '权限组',
			store : limitStore,
			displayField : 'fullname',
			valueField : 'idrole',
			mode : 'remote',
			triggerAction:"all",
			editable:false,
			emptyText : '请选择权限组'
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

		
		//operator form.
		var formPanel = Ext.create('Ext.form.Panel', {
			layout : 'form',
			width : 300,
			bodyPadding: 5,
			url : 'operator',
			defaultType : 'textfield',
			border : false,
			fieldDefaults : {
				allowBlank : true
			},
			items : [ {
				fieldLabel : '用户名',
				maxLength : 45,
				minLength : 2,
				name : 'username'
			}, {
				fieldLabel : '密码',
				inputType : "password",
				name : 'password',
				id : 'pass'
			}, {
				fieldLabel : '确认密码',
				inputType : "password",
				vtype : "password",//check type
				vtypeText : "两次密码不一致！",
				confirmTo : "pass"//need another id
			}, 
			limitComboBox, 
			{
				fieldLabel : '姓名',
				maxLength : 45,
				minLength : 2,
				name : 'fullname'
			}, {
				fieldLabel : '年龄',
				xtype : 'numberfield',
				minValue : 0,
				maxValue : 100,
				name : 'age'
			}, {
				fieldLabel : '性别',
				xtype : 'radiogroup',
				columns : 2,
				vertical : false,
				items : [
				         {boxLabel : '男', name : 'sex', inputValue : '1', checked : true},
				         {boxLabel : '女', name : 'sex', inputValue : '0'}]
			}, {
				fieldLabel : '电话',
				xtype : 'numberfield',
				maxLength : 12,
				minLength : 7,
				name : 'phonenumber'
			} ]
		});
		
		//operation type.
		//1.add		add opeator.
		//2.delete	delete operator.
		//3.edit		edit operator.
		var opType = "add";
		
		//add operator window
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
						opType = "add";
						
						formPanel.getForm().submit({
							waitTitle:"请稍候",
		                    waitMsg:"正在提交数据，请稍候",
						    url: "operator",
						    params : {
						    	'type': opType
						    },
						    success: function (form, action) {
						        Ext.Msg.alert('提示', action.result.msg);
						        formPanel.getForm().reset();
						        win.close();
						       store.load({start : 0,limit : pageSize});
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
		var grid = new Ext.grid.Panel({
			title : '',
			store : store,
			columns : [ new Ext.grid.RowNumberer(), {
				text : '姓名',
				dataIndex : 'fullname'
			}, {
				text : '用户名',
				dataIndex : 'username'
			}, {
				text : '权限组',
				dataIndex : 'rolename'
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
						alert("Edit " + rec.get('username'));
					}
				}, '->', {
					icon : 'icons/fam/user_delete.png',
					tooltip : '删 除',
					handler : function(grid, rowIndex, colIndex) {
						var rec = grid.getStore().getAt(rowIndex);
						Ext.MessageBox.confirm('提示','确定删除用户' + rec.get('username') + '？',
								function(id){
									if(id == 'yes'){
										Ext.Ajax.request({
										    url: 'operator',
										    params: {
										    	type : 'delete',
										        username : rec.get('username')
										    },
										    success: function(response){
										        var text = response.responseText;
										        Ext.MessageBox.alert('提示',text,function(id){
										        	store.load({start : 0,limit : pageSize});
										        });
										    }
										});
									}
								});
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