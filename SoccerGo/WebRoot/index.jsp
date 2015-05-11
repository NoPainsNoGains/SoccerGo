<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>SoccerGo后台管理系统</title>
<link rel="stylesheet" type="text/css" href="plugins/easyui/themes/gray/easyui.css">
<script type="text/javascript" src="plugins/easyui/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="plugins/easyui/jquery.easyui.min.js"></script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow: hidden;
}

#Layer1 {
	position: absolute;
	width: 293px;
	height: 169px;
	z-index: 1;
	left: 22%;
	top: 285px;
}
</style>
<script type="text/javascript">
	$(function() {
		//使用 Ajax 的方式 判断登录  
		$("#btn_login").click(function() {
			if ($("#inputname").val() == "") {
				$.messager.show({						
						title : '提示',
						msg : '用户名不能为空'
				}); 
				return;
			}
			if ($("#inputpassword").val() == "") {
				$.messager.show({						
						title : '提示',
						msg : '密码不能为空'
				}); 
				return;
			}
			$.post('/SoccerGo/admin/login.action', {
				//参数一
				userName : $("#inputname").val(),
				//参数二
				passWord : $("#inputpassword").val()
			}, function(user)//回调函数
			{
			
				if(user == null){//不能统一认证
					$.messager.show({						
						title : '提示',
						msg : '请确认用户名和密码'
					}); 
				}else{
					  document.location.href="admin/Main.jsp";
				}
			}, "json"//返回类型
			);
		});
	});
	function check() {
		document.getElementById('inputpassword').value = '';
		document.getElementById('inputname').value = '';
	}
</script>
<script language="javaScript">
	if (window != top)
		top.location.href = location.href;
</script>
<script>
	function tab() {
		if (event.keyCode == 13) {
			document.getElementById("btn_login").click();
		}
	}
</script>
<style type="text/css">
.style1 {
	font-size: 12px;
	background: url(images/su.png);
	border: 0px 0;
	width: 55px;
	height: 23px;
	cursor: hand;
}

.s2 {
	font-size: 12px;
	background: url(images/re.png);
	border: 0px 0;
	width: 55px;
	height: 23px;
	cursor: hand;
}

.STYLE9 {
	font-size: 12px;
	font-family: "宋体";
}
</style>
</head>
<body style="height: 100%">
	<table width="100%" height="100%" border="0" cellpadding="0"
		cellspacing="0">

		<tr>
			<td height="608" background="images/login_03.jpg"><table
					width="961" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="266" background="images/login_04.jpg"
							bgcolor="#33FFFF">&nbsp;</td>
					</tr>
					<tr>
						<td height="95"><table id="logintable" width="100%"
								border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="476" height="95" background="images/login_06.jpg"><div>
											<table width="412" height="84" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="96" height="12" align="right"></td>

												</tr>
												<tr>
													<td>&nbsp;</td>
													<td width="77" height="32" align="center"><span
														class="STYLE9">用&nbsp;户&nbsp;名：</span></td>
													<td width="239" colspan="4" align="left"><input
														name="userName" type="text" id="inputname"
														style="height: 20px; width: 170px; border: solid 1px #b5c1c1; font-size: 12px; color: #81b432;"
														size="80"></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td width="77" height="40" align="center"><span
														class="STYLE9">密&nbsp;&nbsp;&nbsp;&nbsp;码：</span></td>
													<td colspan="4" width="239" align="left"><input
														name="passWord" type="password" id="inputpassword"
														onKeyDown="tab()"
														style="height: 20px; width: 170px; border: solid 1px #b5c1c1; font-size: 12px; color: #81b432;"
														size="40"></td>
												</tr>

											</table>
										</div></td>
									<td width="183" background="images/login_07.jpg">&nbsp;</td>
									<td width="302" background="images/login_08.jpg">&nbsp;</td>
								</tr>
							</table></td>
					</tr>
					<tr>
						<td height="247" valign="top" background="images/login_09.jpg"><table
								width="413" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="176" height="26">&nbsp;</td>
									<td width="237"><input type="submit" name="btn_login"
										value="" id="btn_login" class="style1" /> &nbsp;&nbsp; <input
										type="reset" onClick="check();" value="" id="btn_reset"
										class="s2" /></td>

								</tr>

							</table></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td bgcolor="#ffffff">&nbsp;</td>
		</tr>
	</table>
</body>
</html>
