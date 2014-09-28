<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	Ext.onReady(function() {
		var tb = new Ext.Toolbar();
		tb.render('toolbar');

		tb.add({
			text : "新建N",
			handler : function() {
				Ext.Msg.alert('提示', '新建');
			}
		}, {
			text : "修改C"
		}, {
			text : "删除"
		}, {
			text : "显示"
		});
	});
</script>
</head>
<body>
<div id="toolbar"></div>
</body>
</html>