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
	<script type="text/javascript">
		function addTeacher(){
			var form = document.forms[0];
			form.action="/mvc03/Teacher/add.action";
			form.method="post";
			form.submit();
		}
	</script>
  </head>
  <body>
       <br>教师list页面 
       <form id="userForm">
       		姓名 <input type="text" name="teacherName">
       		年龄 <input type="text" name="teacherId">
       		<input type="button" value="添加" onclick="addTeacher()"></input>
       </form>
  </body>
</html>
