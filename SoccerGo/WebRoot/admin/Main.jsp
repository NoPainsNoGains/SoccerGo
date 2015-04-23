<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>
<link rel="stylesheet" type="text/css" href="../plugins/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="../plugins/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="../plugins/easyui/themes/gray/easyui.css">
		<script type="text/javascript" src="../plugins/easyui/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="../plugins/easyui/jquery.easyui.min.js"></script>
<style type="text/css">
body {
	font: 12px/20px "微软雅黑", "宋体", Arial, sans-serif, Verdana, Tahoma;
	padding: 0;
	margin: 0;
}
a:link {
 text-decoration: none;
}
a:visited {
 text-decoration: none;
}
a:hover {
 text-decoration: underline;
}
a:active {
 text-decoration: none;
}
.cs-north {
	height:80px;background:#d4ecff;
}
.cs-north-bg {
	width: 100%;
	height: 100%;
background:url('../images/background.jpg') repeat-x ; 
}
.cs-north-logo {
	height: 40px;
	 margin:0px;
        padding:15px,0px,0px,5px;
	color:#fff;font-size:22px;font-weight:bold;text-decoration:none;

}
.cs-west {
	width:100px;padding:0px;border-left:1px solid #99BBE8;
}
.cs-south {
	height:25px;background:url('themes/gray/images/panel_title.gif') repeat-x;padding:0px;text-align:center;
}
.cs-navi-tab {
	padding: 5px;
}
.cs-tab-menu {
	width:120px;
}
.cs-home-remark {
	padding: 10px;
}
#Layer1 {
	position:fixed;
	width:150px;
	height:25px;
	z-index:1;
   left: 83%;
	top: 8%;
}
</style>
<script type="text/javascript">
	function addTab(title, url){
				if ($('#tabs').tabs('exists', title)){
					$('#tabs').tabs('select', title);//选中并刷新
					var currTab = $('#tabs').tabs('getSelected');
					var url = $(currTab.panel('options').content).attr('src');
					if(url != undefined && currTab.panel('options').title != 'Home') {
						$('#tabs').tabs('update',{
							tab:currTab,
							options:{
								content:createFrame(url)
							}
						});
					}//if
				} else {
					var content = createFrame(url);
					$('#tabs').tabs('add',{
						title:title,
						content:content,
						closable:true
					});
				}//if else
				tabClose();
			}//addTab
			
			function createFrame(url) {
				var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
				return s;
			}//createFrame
			
			function tabClose() {
				/*双击关闭TAB选项卡*/
				$(".tabs-inner").dblclick(function(){
					var subtitle = $(this).children(".tabs-closable").text();
					$('#tabs').tabs('close',subtitle);
				});
				/*为选项卡绑定右键*/
				$(".tabs-inner").bind('contextmenu',function(e){
					$('#mm').menu('show', {
						left: e.pageX,
						top: e.pageY
					});
					var subtitle =$(this).children(".tabs-closable").text();
					$('#mm').data("currtab",subtitle);
					$('#tabs').tabs('select',subtitle);
					return false;
				});
			}//	tabClose
			
		
			function tabCloseEven() {//绑定右键菜单事件
				$('#mm-tabupdate').click(function(){	//刷新
					var currTab = $('#tabs').tabs('getSelected');
					var url = $(currTab.panel('options').content).attr('src');
					if(url != undefined && currTab.panel('options').title != 'Home') {
						$('#tabs').tabs('update',{
							tab:currTab,
							options:{
								content:createFrame(url)
							}
						});
					}
				});
				$('#mm-tabclose').click(function(){	//关闭当前
					var currtab_title = $('#mm').data("currtab");
					$('#tabs').tabs('close',currtab_title);
				});	
				$('#mm-tabcloseall').click(function(){//全部关闭
					$('.tabs-inner span').each(function(i,n){
						var t = $(n).text();
						if(t != 'Home') {
							$('#tabs').tabs('close',t);
						}
					});
				});
				$('#mm-tabcloseother').click(function(){//关闭除当前之外的TAB
					var prevall = $('.tabs-selected').prevAll();
					var nextall = $('.tabs-selected').nextAll();		
					if(prevall.length>0){
						prevall.each(function(i,n){
							var t=$('a:eq(0) span',$(n)).text();
							if(t != 'Home') {
								$('#tabs').tabs('close',t);
							}
						});
					}
					if(nextall.length>0) {
						nextall.each(function(i,n){
							var t=$('a:eq(0) span',$(n)).text();
							if(t != 'Home') {
								$('#tabs').tabs('close',t);
							}
						});
					}
					return false;
				});
				$('#mm-tabcloseright').click(function(){//关闭当前右侧的TAB
					var nextall = $('.tabs-selected').nextAll();
					if(nextall.length==0){
						//msgShow('系统提示','后边没有啦~~','error');
						alert('后边没有啦~~');
						return false;
					}
					nextall.each(function(i,n){
						var t=$('a:eq(0) span',$(n)).text();
						$('#tabs').tabs('close',t);
					});
					return false;
				});
				$('#mm-tabcloseleft').click(function(){	//关闭当前左侧的TAB
					var prevall = $('.tabs-selected').prevAll();
					if(prevall.length==0){
						alert('到头了，前边没有啦~~');
						return false;
					}
					prevall.each(function(i,n){
						var t=$('a:eq(0) span',$(n)).text();
						$('#tabs').tabs('close',t);
					});
					return false;
				});	
				$("#mm-exit").click(function(){//退出
					$('#mm').menu('hide');
				});
			}//tabCloseEven
	
		$(function() {
			tabCloseEven();
			$('.cs-navi-tab').click(function() {
				var $this = $(this);
				var href = $this.attr('src');
				var title = $this.text();
				addTab(title, href);
			});
			$('body').layout();
			$('body').css('visibility', 'visible');
		});
</script>
<script type="text/javascript">
function logout(){
	$.messager.confirm('请确认', '您确定要退出系统吗？', function(r){
		if (r){
			location.href = 'Logout.action';
		}
	});	
}
</script>
</head>
 <body class="easyui-layout" style="visibility: hidden;">
	<div region="north" border="true" class="cs-north">
		<div class="cs-north-bg"><div class="cs-north-logo"><img src="../images/banner.jpg"  border="0" /></div></div>
		 <div id="Layer1" style="text-align:right"><a href="javascript:logout()"><font color="#067dae" >用户退出</font></a></div>
	</div>
	<div region="west" border="true" split="true" title="菜单" class="cs-west">
			<div class="easyui-accordion" fit="true" border="false">
				<div title="系统管理">
					<a href="javascript:void(0);" src="SystemManage/RoleManage.jsp" class="cs-navi-tab">职务管理</a></p>
				</div>
				<div title="课程管理">
					<a href="javascript:void(0);" src="CourseManage/courseManger.jsp" class="cs-navi-tab">课程表</a></p>
				</div>
		</div>
	</div>
	<div id="mainPanle" region="center" border="true" border="false">
		 <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
                <div title="Home">
				<div class="cs-home-remark">
					<h1>后台管理系统 --主页</h1> <br>
					制作：YMH <br>
				</div>
				</div>
        </div>
	</div>
		<div region="south" border="false" class="cs-south"></div>
		<div id="mm" class="easyui-menu cs-tab-menu">
			<div id="mm-tabclose">关闭</div>
			<div id="mm-tabcloseother">关闭其他</div>
			<div id="mm-tabcloseall">关闭全部</div>
		</div>
</body>
</html>
