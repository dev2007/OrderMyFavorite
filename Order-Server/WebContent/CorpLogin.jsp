<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统登录</title>
</head>
<script type="text/javascript" src="jquery-easyui-1.4/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4/themes/default/easyui.css"> 
 <link rel="stylesheet" type="text/css" href="jquery-easyui-1.4/themes/icon.css">
 <script type="text/javascript">
 //real login function
 function login(){
	 var user = $('#txtUserName');
	 var pwd = $('#txtPassword');
	 
	 if(user.val() == ""){
		 alert("请输入用户名！");
		 user.focus();
		 return false;
	 }else if(pwd.val() == ""){
		 alert("请输入密码！");
		 pwd.focus();
		 return false;
	 }else{
		 $.post("Validate",
				 {
			 		username:user.val(),
			 		password:pwd.val()
				 },
				 function(data,status){
					    if(status == "success"){
					    	var infoArray = data.split(";");
					    	if(infoArray.length == 1){
					    		if(infoArray[0] == "OK"){
					    			alert("请求成功！");
					    		}else if(infoArray[0] == "ERROR"){
					    			alert("请求失败！");
					    		}
					    	}else if(infoArray.length == 3){
					    		if(infoArray[1] == "MSG"){
					    			alert(infoArray[2]);
					    		}else if(infoArray[1] == "URL"){
					    			self.location = infoArray[2];
					    		}
					    	}
					    }else{
					    	alert("请重试！");
					    }
				 }
				);
	 }
	 
 }
 //click for login
 $(function(){
	 $('#btnLogin').click(function(){login(); return false;});
 })
 </script>
<body>

<div id="win" class="easyui-window" title="登录" style="width:400px;height:180px;">
    <form style="padding:10px 20px 10px 40px;">
		<input class="easyui-textbox" data-options="iconCls:'icon-man'" style="width:300px;height:30px"   id="txtUserName">
		<p/>
		<input class="easyui-textbox" type="password" data-options="iconCls:'icon-lock'" style="width:300px;height:30px"  id="txtPassword">
        <div style="padding:5px;text-align:center;">
            <a href="#" class="easyui-linkbutton" icon="icon-ok" id="btnLogin">登录</a>
            <a href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
        </div>
    </form>
</div>


</body>
</html>