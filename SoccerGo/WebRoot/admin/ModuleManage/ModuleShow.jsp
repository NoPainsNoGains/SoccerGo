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
</head>
	<script>
$(function() {
		$('#MODULE').treegrid({
				iconCls:'icon-save',
				width : $(this).width()*0.975 ,
				height : $(example).height()*1,
				nowrap: false,
				rownumbers: true,
				toolbar : [{
					id : 'btnadd',
					text : '添加一级模块目录',
					iconCls : 'icon-add',
					handler : function() {
						addF();
					}
				}],
				animate:true,
				collapsible:true,
				url:'module/ShowModuleAction.action',
				idField:'id',
				treeField:'name',
				frozenColumns:[[
	                {title:'模块类型名称',field:'name',width:$(this).width()*0.15,
		                formatter:function(value){
		                	return '<span style="color:black">'+value+'</span>';
		                }
	                }
				]],
				columns:[[
					{field:'remark',title:'说明',width:$(this).width() * 0.1,align:'left',
						formatter : function(value, rec) {
                            return value;     
						}
					},
					{field:'code',title:'编码',width:$(this).width() * 0.1,align:'left',
						formatter : function(value, rec) {
                            return value;     
						}
					},
					{field:'status',title:'模块状态',width:$(this).width() * 0.1,align:'center',
						formatter : function(value, rec) {
						if(value){
							return '<span style="color:blue"><a>启用</a></span>';
							}
							else{
							value="停用";
	                        return value; 
	                        } 
						}
					},
					{field:'page',title:'地址路径',width:$(this).width() * 0.1,align:'left',
						formatter : function(value, rec) {
                            return value;     
						}
					},
					{field:'catlog',title:'类别',width:$(this).width() * 0.1,align:'left',
						formatter : function(value, rec) {
                       if(value){
						value="·目录";}
						else{
						value="········页面";}
                            return value;     
						}
					},
					
					{field:'opt',title:'操作',width:$(this).width() * 0.1,align:'left',
						formatter : function(value, rec) {	
							var id1 = rec.id;	
							var name1 = rec.name;
							var status1 = rec.status;
							var page1 = rec.page;
							var fatherid1 = rec.fatherId;
							var remark1 = rec.remark;
							var code1 = rec.code;
							var catlog1 = rec.catlog;
							if(rec.catlog==1){
				            return '<span style="color:blue"><a href="#" id="edit" class="easyui-linkbutton" onclick="javascript:editmodule(\''+id1+'\',\''+name1+'\',\''+status1+'\',\''+fatherid1+'\',\''+page1+'\',\''+remark1+'\',\''+code1+'\',\''+catlog1+'\')">编辑</a></span>&nbsp&nbsp&nbsp&nbsp<span style="color:blue"><a href="#"  id="delete" class="easyui-linkbutton" onclick="javascript:addson(\''+id1+'\',\''+name1+'\',\''+code1+'\')">添加子模块</a></span>';     
							}
							else{
							return '<span style="color:blue"><a href="#" id="edit" class="easyui-linkbutton" onclick="javascript:editmodule(\''+id1+'\',\''+name1+'\',\''+status1+'\',\''+fatherid1+'\',\''+page1+'\',\''+remark1+'\',\''+code1+'\',\''+catlog1+'\')">编辑</a></span>';  
							}
						}
					}
				]],
				onContextMenu: function(e,row){
					e.preventDefault();
					$(this).treegrid('unselectAll');
					$(this).treegrid('select', row.id);
				}
			});
});
	/*****************添加一级模块目录*********************/
function addF(){
		$('#addFdiv').dialog('open').dialog('setTitle', '添加一级模块目录');
		document.getElementById('addFpage').value="/";
		document.getElementById('addnameF').value="";
		document.getElementById('addFstart').checked = true; 
		document.getElementById('addFstop').checked = false;
}

function checkaddF(){
		if(document.getElementById('addnameF').value == ""){
			$.messager.show({
				title : '提示',
				msg : '请输入模块名称'
			});
		}else {
		 var addRemarkF="一级目录";
		 var addFatherIdF=0;
		 var addModuleCodeF="M";
		 var addcatlogF=1;
		 var flag=0;
		 var addmodulePageF = document.getElementById('addFpage').value
		 var addNameF=document.getElementById('addnameF').value;
		 var addStatusF = ($("input[name='st1']:checked").val());
			$.ajax({
				type : "post",
				url : "module/add_First_Module.action",
				data : {"moduleName":addNameF,"moduleStatus":addStatusF,"moduleFatherId":addFatherIdF,"moduleCode":addModuleCodeF
					,"moduleRemark":addRemarkF,"modulePage":addmodulePageF,"isCatalogue":addcatlogF,"FLAG":flag},
				async: false,
				dataType: "json",
	    	});
	    	$('#addFdiv').window('close');
			$('#MODULE').treegrid('reload');
		}
}
		
	/****************添加子模块**********************/
function addson(fid,fname,fcode){
	$('#addSdiv').dialog('open').dialog('setTitle', '添加子模块');
	document.getElementById('addfatherS').readOnly=true;
	document.getElementById('addSpage').value="/";
	document.getElementById('addnameS').value="";
	document.getElementById('addSstart').checked = true; 
	document.getElementById('addSstop').checked = false;
	document.getElementById('addfid').value=fid;
	document.getElementById('addfatherS').value=fname;
	document.getElementById('addfcode').value=fcode;
	
}

function checkaddS(){
		if(document.getElementById('addnameS').value == ""){
			$.messager.show({
				title : '提示',
				msg : '请输入模块名称'
			});
		}else {
	 var addRemark ;
	 var flag=0;
	 var addFatherId=document.getElementById('addfid').value;
	 var CorP = ($("input[name='cl']:checked").val());//0 为目录，1为页面
	 var addName=document.getElementById('addnameS').value;
	 var addStatus = ($("input[name='st2']:checked").val());
	 var addmodulePage = document.getElementById('addSpage').value;
	 var addModuleCode=document.getElementById('addfcode').value;
	 if (CorP==1){
			 addRemark = "二级目录";
		 }//添加二级目录
	 if(CorP==0){
		 if(addModuleCode.length==4){
			 addRemark="二级页面";
		 }
		 if(addModuleCode.length==7){
			 addRemark="三级页面";
		 }
	 }//添加页面
			$.ajax({
				type : "post",
				url : "module/add_First_Module.action",
				data : {"moduleName":addName,"moduleStatus":addStatus,"moduleFatherId":addFatherId,"moduleCode":addModuleCode
					,"moduleRemark":addRemark,"modulePage":addmodulePage,"isCatalogue":CorP,"FLAG":flag},
				async: false,
				dataType: "json",
	    	});
			$('#addSdiv').window('close');
			$('#MODULE').treegrid('reload');
}
}


	/****************编辑模块信息**********************/
function editmodule(id,name,status,fatherid,page,remark,code,catlog){
		$('#editdiv').dialog('open').dialog('setTitle', '编辑模块信息');
		if(status==1){
		document.getElementById('editstart').checked = true; 
		document.getElementById('editstop').checked = false;
		}
		else{
		document.getElementById('editstart').checked = false; 
		document.getElementById('editstop').checked = true;
		}
		document.getElementById('editname').value = name;
 		document.getElementById('editpage').value = page;
 		document.getElementById('editid').value = id;
 		document.getElementById('editcode').value = code;
 		document.getElementById('editfatherid').value = fatherid;
 		document.getElementById('editremark').value = remark;
 		document.getElementById('editcatlog').value = catlog;
	}
	
		
function checkedit(){
		if(document.getElementById('editname').value == ""){
			$.messager.show({
				title : '提示',
				msg : '请输入模块名称'
			});
		}else {
			 var ids=[];     
			 var id = document.getElementById('editid').value;
			 var name = document.getElementById('editname').value;
			 var remark = document.getElementById('editremark').value;
			 var fatherid = document.getElementById('editfatherid').value;
			 var code = document.getElementById('editcode').value;
			 var page = document.getElementById('editpage').value;
			 var status = ($("input[name='st']:checked").val());
			 var catlog = document.getElementById('editcatlog').value;
			 ids.push(id);
			 ids.push(name);
			 ids.push(remark);
			 ids.push(fatherid);
			 ids.push(code);
			 ids.push(page);
			 ids.push(status);
			 ids.push(catlog);
             $.ajax({
				  url: "module/ModifyModuleAction.action",
				  async: false,
				  dataType: "json",
				  data : {
					 ids : ids.join(',')
			  	  }
	    	 });
			$('#editdiv').window('close');
			$('#MODULE').treegrid('reload');
		}
	
}		
	
	/**************关闭取消重置控件**********************/
function close1(){
	$('#editdiv').window('close');
	$('#addFdiv').window('close');
	$('#addSdiv').window('close');
	document.getElementById('addFstart').checked = true; 
	document.getElementById('addFstop').checked = false;
	document.getElementById('addSstart').checked = true; 
	document.getElementById('addSstop').checked = false;
	document.getElementById('addnameF').value="";
	document.getElementById('addnameS').value="";
	document.getElementById('addSpage').value="/";
	document.getElementById('addFpage').value="/";
}


//height:700px;
</script>

	</head>
	
	<body>
		<!-- ************************show********************************** -->
	 <div id="example" class="k-content" style="margin:0; padding:0;height:650px">
		<table id="MODULE"  ></table>
	</div>
		<!--编辑-->
	<div id="editdiv" class="easyui-dialog" style="width:430px;height:210px;left:650px;top:260px;padding:4px;" closed="true" buttons="#editbutton">
		<form id="edit">
				<ul>
					<li>
						<label class="required">
							设置模块名称：
						</label>
						<input type="text" id="editname"    class="k-textbox" placeholder="必须填写模块名称"  style="width: 200px;" />
					</li>
					<li style="padding-top:8px;">
						<label for="editstatus" class="required">
							设置模块状态：
						</label>
						启   用：
							<input id="editstart" name=st  value="1" type="radio" checked="checked"   "/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						禁   用：
							<input id="editstop"  name=st value="0" type="radio" checked="checked"  "/><br/>
					</li>
 						
					<li style="padding-top:8px;" >
						<label style="padding-top:200px; " >
							编辑地址路径：
						</label>
						<input type="text" id="editpage"    class="k-textbox"   style="width: 200px;" />
					</li>
					
		                   		<input id="editid" type="hidden" style="width: 200px;"/> 
		            		
							  
		                   		<input id="editremark" type="hidden" style="width: 200px;"/> 
		            		
		            		 
		                   		<input id="editcode" type="hidden" style="width: 200px;"/> 
		            		
		            		
		                   		<input id="editfatherid" type="hidden" style="width: 200px;"/> 
		            		 
		            		 
		                   		<input id="editcatlog"  type="hidden" style="width: 200px;"/> 
		            		
		            		
				</ul>		
		</form>
	</div>
	<div id="editbutton" align="right">
		<right>
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="checkedit()">保存</a> 
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="close1()">取消</a>
		</right>		
	</div>
	
						<!-- 	添加一级目录 -->
	<div id="add1" style="display: none;">								
	<div id="addFdiv" class="easyui-dialog" style="width:430px;height:200px;left:650px;top:260px;padding:4px;" closed="true" buttons="#addFbutton">
		<form id="add1">
						<ul>
					<li>
						<label class="required">
							设置模块名称：
						</label>
						<input type="text" id="addnameF"    class="k-textbox" placeholder="必须填写模块名称"  style="width: 200px;" />
					</li>
					<li style="padding-top:8px;">
						<label  class="required">
							设置模块状态：
						</label>
						启   用：
							<input id="addFstart" name=st1  value="1" type="radio" checked="checked"   "/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						禁   用：
							<input id="addFstop"  name=st1 value="0" type="radio"   "/>
					</li>
 
					<li style="padding-top:8px;">
						<label  >
							编辑地址路径：
						</label>
						<input type="text" id="addFpage"    class="k-textbox"   style="width: 200px;" />
					</li>
				</ul>		
		</form>
		</div>
	<div id="addFbutton" align="right">
		<right>
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="checkaddF()">保存</a> 
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="close1()">取消</a>
		</right>		
	</div>
			
						<!-- 	添加子模块          -->
	<div id="addSdiv" class="easyui-dialog" style="width:430px;height:250px;left:650px;top:260px;padding:4px;" closed="true" buttons="#addSbutton">
		<form id="add2">
						<ul>
					<li >
						<label >
							父 目 录 名 称：
						</label>
						<input type="text" id="addfatherS"  style="background-color: transparent; border: 11px ;padding-left:0px; readOnly:true"  class="k-textbox"  />
					</li>	
					<li >
						<label  class="required">
							设置模块类别：
						</label>
						目   录：
							<input id="cata" name=cl  value="1" type="radio" checked="checked"   "/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						页   面：
							<input id="page"  name=cl value="0" type="radio"   "/>
					</li>
					<li style="padding-top:7px;">
						<label class="required">
							设置模块名称：
						</label>
						<input type="text" id="addnameS"    class="k-textbox" placeholder="必须填写模块名称"  style="width: 200px;" />
					</li>
					<li style="padding-top:4px;">
						<label  class="required">
							设置模块状态：
						</label>
						启   用：
							<input id="addSstart" name=st2  value="1" type="radio" checked="checked"   "/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						禁   用：
							<input id="addSstop"  name=st2 value="0" type="radio"   "/>
					</li>
					<li style="padding-top:7px;">
						<label  >
							编辑地址路径：
						</label>
						<input type="text" id="addSpage"    class="k-textbox"   style="width: 200px;" />
					</li>
							    <input id="addfid" type="hidden" style="width: 200px;"/> 
		                   		<input id="addfcode"  type="hidden" style="width: 200px;"/> 
				</ul>		
		</form>
		</div>
	<div id="addSbutton" align="right">
		<right>
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="checkaddS()">保存</a> 
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="close1()">取消</a>
		</right>		
	</div>
	
	
<!-- 				<div id="grid" style="height: 450px"></div> -->
	</body>
</html>
