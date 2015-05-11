<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<link href="../../plugins/kendo/examples/content/shared/styles/examples-offline.css" rel="stylesheet">
    <link href="../../plugins/kendo/styles/kendo.common.min.css" rel="stylesheet">
    <link href="../../plugins/kendo/styles/kendo.default.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="../../plugins/easyui/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="../../plugins/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../../plugins/easyui/demo.css">
	<script type="text/javascript" src="../../plugins/easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../../plugins/easyui/jquery.easyui.min.js"></script>
    <script src="../../plugins/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
</head>
	<script>
		
		$(function(){
			$('#moduleMent').treegrid({
				iconCls:'icon-save',
				width : $(this).width()*0.9,
				height : $(this).height()*0.9,
				nowrap: false,
				rownumbers: true,
				toolbar : [{
					id : 'btnadd',
					text : '添加模块',
					iconCls : 'icon-add',
					handler : function() {
						append();
					}
				}],
				animate:true,
				collapsible:true,
				url:'/SoccerGo/admin/ModuleManage/ModuleManager/list.action',
				idField:'id',
				treeField:'text',
				frozenColumns:[[
	                {title:'模块名',field:'text',width:$(this).width()*0.1,
		                formatter:function(value){
		                	return '<span style="color:black">'+value+'</span>';
		                }
	                }
				]],
				columns:[[
					{field:'module_code',title:'编码',width:$(this).width() * 0.1,align:'center'},
					{field:'isCatalogue',title:'页面',width:$(this).width() * 0.05,align:'center',
						formatter : function(value, rec) {
							/* if(value==0){
								 return '<span style="color:black"">'+是+'</span>';
							}else{
								 return '<span style="color:black">'+否+'</span>';
							} */
							 return '开启';  
						}
					},
					{field:'state',title:'状态',width:$(this).width() * 0.05,align:'center',
						formatter : function(value, rec) {
							/* if(value==0){
								 return '<span style="color:red">'+关闭+'</span>';
							}else{
								 return '<span style="color:black">'+开启+'</span>';
							}
							*/
							  return '开启';     						
						}
					},
					{field:'remark',title:'备注',width:$(this).width() * 0.3,align:'center'},
					{field:'opt',title:'操作',width:$(this).width() * 0.2,align:'center',
						formatter : function(value, rec) {
				            return '编辑';     
						}
					}
				]]
			});	
		});
</script>
<body >
	<!-- ************************show********************************** -->
	 <div id="example" class="k-content" style="margin:0; padding:0;">
		<table id="moduleMent"></table>
	</div>
</body>
</html>
