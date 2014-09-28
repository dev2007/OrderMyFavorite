<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统登录</title>
</head>
<link rel="stylesheet" type="text/css"
	href="ext-4.2.1/resources/css/ext-all.css" />
<script type="text/javascript" src="ext-4.2.1/ext-all.js"></script>
<script type="text/javascript" src="ext-4.2.1/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
	Ext.onReady(function() {
		//初始化标签中的Ext:Qtip属性。
		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = 'side';
		//登录
		var btnsubmitclick = function() {
			if (form.getForm().isValid()) {
				form.getForm().submit({
					watiMsg:'登录中，请稍候',
                    success: function(form, action) {
						Ext.Msg.alert('提示',action.result.msg,function(){location.href = "Main.jsp";});
                    },
                    failure: function(form, action) {
                        Ext.Msg.alert('提示', action.result.msg);
                    }
                });
			}
		}
		//重置按钮"点击时"处理方法
		var btnresetclick = function() {
			Ext.MessageBox.alert('提示', '你点了重置按钮!');
		}
		//提交按钮
		var btnsubmit = new Ext.Button({
			text : '登录',
			handler : btnsubmitclick
		});
		//重置按钮
		var btnreset = new Ext.Button({
			text : '重置',
			listeners : {
				'click' : btnresetclick
			}
		});

		//用户名input
		var txtusername = new Ext.form.TextField({
			width : 300,
			allowBlank : false,
			maxLength : 20,
			name : 'username',
			fieldLabel : '用户名称',
			blankText : '请输入用户名称',
			maxLengthText : '用户名称不能超过20个字符'
		});
		//密码input
		var txtpassword = new Ext.form.TextField({
			width : 300,
			allowBlank : false,
			maxLength : 20,
			inputType : 'password',
			name : 'password',
			fieldLabel : '登录密码',
			blankText : '请输入密码',
			maxLengthText : '密码不能超过20个字符'
		});

		var form = new Ext.form.FormPanel({
			url:'Validate',
			frame : true,
			style : 'margin:10px',
			labelAlign : 'right',
			labelWidth : 45,
			buttonAlign : 'center',
			bodyStyle : 'padding:6px 0px 0px 15px',
			items : [ txtusername, txtpassword ],
			buttons : [ btnsubmit, btnreset ]
		});

		var win = new Ext.Window({
			title : '系统登录',
			width : 476,
			height : 174,
			modal : true,
			cloasable : false,
			resizable : false,
			maximizable : false,
			minimizable : false,
			items : form
		});
		win.show();
	});
</script>


</body>
</html>