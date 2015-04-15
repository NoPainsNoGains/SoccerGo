<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		 function addUser(){
		  $.ajax({
                    type: "POST",
                    dataType: "json",
                    url:"/mvc03/user/addUser",
                    data: $('#userForm').serialize(),
                    success: function (result) {
                        alert("名字: "+result.userName+"   年龄: "+result.age);
                    }
              });
		 } 
	</script>
  </head>
  <body>
       <br>添加用户页面 
       <form id="userForm">
       		姓名 <input type="text" name="userName">
       		年龄 <input type="text" name="age">
       		<input type="button" value="添加" onclick="addUser()"></input>
       </form>
  </body>
</html>
