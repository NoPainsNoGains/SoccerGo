//function add(){	//初始化添加窗口
//	var window = $("#windowAdd");
//	var gview = $("#grid").data("kendoGrid");
//	var selectedItem = gview.dataItem(gview.select());   //获取选择的某一行
//	document.getElementById('editmoduleName').value =selectedItem.moduleName;
//	
//	var fathername=selectedItem.moduleName;
//	var fatherid=selectedItem.moduleId;
//	document.getElementById('addmoduleFatherName').readOnly=true;	
//	document.getElementById('addmoduleFatherName').value=selectedItem.moduleName;
//	
//	
//    var selects = document.getElementsByName("Catalog");  
//    	selects[0].checked= true;  
//	document.getElementById('addmoduleName').value = "";
//	document.getElementById('addmodulePage').value = "/";
//	window.data("kendoWindow").open();
//	window.data("kendoWindow").center();
//
//	
//}
//
//function addF(){	//初始化添加窗口
//	var window = $("#windowAddF");
//	document.getElementById('addmoduleNameF').value = "";
//	window.data("kendoWindow").open();
//	window.data("kendoWindow").center();
//
//	
//}
//
//
//
//function onchangRadio(){	//选择目录or页面事件
//	var item = ($("input[name='Catalog']:checked").val());
//}
//
////确定添加模块响应
//function checkaddF(){
//	 var addRemarkF="一级目录";
//	 var addFatherIdF=0;
//	 var addModuleCodeF="M";
//	 var addmodulePageF = "/";
//	 var CorpF=1;
//	 var flag=0;
//	 var addNameF=document.getElementById('addmoduleNameF').value;
//	 var addStatusF=$("#addmoduleStatusF").data("kendoDropDownList").dataItem().value;
//	 var validator = $("#addF").kendoValidator().data("kendoValidator");
//	 if (validator.validate()) {
//			$.ajax({
//				type : "post",
//				url : "module/add_First_Module.action",
//				data : {"moduleName":addNameF,"moduleStatus":addStatusF,"moduleFatherId":addFatherIdF,"moduleCode":addModuleCodeF
//					,"moduleRemark":addRemarkF,"modulePage":addmodulePageF,"isCatalogue":CorpF,"FLAG":flag},
//				async: false,
//				dataType: "json",
//				success: function(data) {
//					if(data == 0){
//						closewindow("AddF");
//						refreshGrid();
//					}else if(data == 1){
//						alert("添加失败");
//					}
//				}
//	    	});
//			closewindow("AddF");
//			 refreshGrid();
//	 }
//	 
//}
//
//
// function checkadd(){
//	 var addRemark ;
//	 var addFatherId;
//	 var addModuleCode;
//	 var flag=0;
//	 var gview = $("#grid").data("kendoGrid");
//	 var selectedItem = gview.dataItem(gview.select());
//	 addFatherId=selectedItem.moduleId;
//	 var CorP = ($("input[name='Catalog']:checked").val());//0 为目录，一为页面
//	 var addName=document.getElementById('addmoduleName').value;
//	 var addStatus=$("#addmoduleStatus").data("kendoDropDownList").dataItem().value;
//	 var validator = $("#add").kendoValidator().data("kendoValidator");
//	 var addmodulePage = document.getElementById('addmodulePage').value;
//	 addModuleCode=selectedItem.moduleCode;
//	 if (CorP==1){
//			 addRemark = "二级目录";
//		 }//添加二级目录
//	 
//	 
//	 
//	 if(CorP==0){
//		 if(selectedItem.moduleRemark=="一级目录"){
//			 addRemark="二级页面";
//		 }
//		 if(selectedItem.moduleRemark=="二级目录"){
//			 addRemark="三级页面";
//		 }
//	 }//添加页面
//	 if (validator.validate()) {
//			$.ajax({
//				type : "post",
//				url : "module/add_First_Module.action",
//				data : {"moduleName":addName,"moduleStatus":addStatus,"moduleFatherId":addFatherId,"moduleCode":addModuleCode
//					,"moduleRemark":addRemark,"modulePage":addmodulePage,"isCatalogue":CorP,"FLAG":flag},
//				async: false,
//				dataType: "json",
//				success: function(data) {
//					if(data == 0){
//						closewindow("Add");
//						refreshGrid();
//					}else if(data == 1){
//						alert("添加失败");
//					}
//				}
//	    	});
//			closewindow("Add");
//			 refreshGrid();
//	 }
//	 
//}
// 
// function closeadd(){
//	 closewindow("Add");
//	 refreshGrid();
// }
// 
// function closeaddF(){
//	 closewindow("AddF");
//	 refreshGrid();
// }
// 
//
// 
///*--------------------编辑窗口--------------------*/
//function edit(){
//	var windowEdit = $("#windowEdit");
//	windowEdit.data("kendoWindow").open();
//	windowEdit.data("kendoWindow").center();
//    
//	var gview = $("#grid").data("kendoGrid");
//	
//	var selectedItem = gview.dataItem(gview.select());   //获取选择的某一行
//	document.getElementById('editmoduleId').value = selectedItem.moduleId;
//	document.getElementById('editmoduleName').value =selectedItem.moduleName;
//	$("#editmoduleStatus").data("kendoDropDownList").value(selectedItem.moduleStatus);
//	document.getElementById('editmoduleFatherId').value =selectedItem.moduleFatherId;
//	document.getElementById('editmoduleWeight').value =selectedItem.moduleWeights;
// 	document.getElementById('editmoduleRemark').value = selectedItem.moduleRemark;    
// 	document.getElementById('editmoduleCode').value = selectedItem.moduleCode;  
// 	document.getElementById('editmodulePage').value = selectedItem.modulePage;   
// 	document.getElementById('editisCatalogue').value = selectedItem.isCatalogue;   
//}
//
//function checkedit(){
//	var validator = $("#edit").kendoValidator().data("kendoValidator");
//	if (validator.validate()) {
//		$.ajax({
//			type : "post",
//			url : "module/ModifyModuleAction.action",
//			data : $('#edit').serialize(),  //把form表单的数据传到后台
//			async: false,
//			dataType: "json",
//			success: function(data) {
//				if(data == 0){
//					closewindow("Edit");
//					$("#grid").data("kendoGrid").clearSelection();  //取消选择的行
//					refreshGrid();
//				}else if(data == 1){
//				}
//			}
//    	});
//	} 	
//	closewindow("Edit");
//}
// 
//
//function closewindow(flag){
//	var window = $("#window" + flag);
//	window.data("kendoWindow").close();
//	$("#grid").data("kendoGrid").clearSelection();  //取消选择的行
//}
//
//function onFocus(id){
//	document.getElementById(id).placeholder = "";
//}
//
//function onBlur(id){
//	document.getElementById(id).placeholder = "必须填写该文本框";
//}
//
//
//
///*--------------------初始化重新加载整个页面---------------------*/
//function refreshGrid(){
//		var grid = $("#grid").kendoGrid( {
//			selectable: "row",  //可以选择行
//			toolbar : [{
//				template: "<a class='k-button' onclick='addF()'><span  class='k-icon k-i-plus'></span>添加一级目录模块</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
//			}],
//					   
//					   dataSource: {
//							transport: {
//								read : {
//				                	type : "post",
//				                	url : "module/ShowModuleAction.action",
//				                    dataType : "json",
//				                }
//							},
//							schema: {
//								data : function(json) {   
//				                	return json.moduleList;
//				                },
//				                total : function(json) {
//				                    return json.total;  
//				               }
//							},
//							batch: true,
//							pageSize: 20,
//							serverPaging: true,
//							serverFiltering: true,
//							serverSorting: true
//						},		
//				height: 650,
//		        scrollable: true,
//		        
//				pageable : {
//					input : true,
//					numeric : false,
//					pageSizes: true,
//					refresh : true,     //刷新
//					messages : {
//						display : "{0} - {1} 共 {2} 条数据",
//						empty : "没有数据",
//						page : "页",
//						of : "/ {0}",
//						itemsPerPage : "条每页",
//						first : "第一页",
//						previous : "前一页",
//						next : "下一页",
//						last : "最后一页",
//						refresh : "刷新"
//					}
//				},
//		
//		columns : [ {
//			field : "moduleId",
//			title : "模块类型ID",
//			hidden: true
//				
//		}, {
//			field : "index",
//			title : "序  号",
//			width : 100
//		},{
//			field : "moduleCode",
//			title : "编  码"
//		},{
//			field : "moduleName",
//			title : "模块类型名称",
////			template:"#if(moduleCode.length==4){#"
////				   +"<a ><a class='button' onClick='showson()'><span class='k-icon k-i-plus'></a>  #= moduleName # </a> "
////		       + "#}else if(moduleCode.length==7&&isCatalogue==1){#"
////		       +"<a>&nbsp&nbsp&nbsp&nbsp&nbsp<a class='button' onClick='showson()'><span class='k-icon k-i-plus'></a>  #= moduleName # </a>"
////		       + "#}else {#"
////		       +"<a>&nbsp&nbsp&nbsp&nbsp&nbsp ◆#= moduleName #</a>"
////		       + "#} #"	
//				
//		},  {
//			field : "moduleRemark",
//			title : "说  明"
//		},  {
//			field : "moduleStatus",
//			title : "模块状态",
////			template : "#=(moduleStatus==0) ? '禁用' : '启用'#" 	
//			template :"#if(moduleStatus==0){#"
//				   +"<a >禁用</a> "
//			       + "#}else {#"
//			       +"<a style='color:red; font-size:14px; font-weight:bold;'>启用 </a>"
//			       + "#} #"		
//		},  {
//			field : "modulePage",
//			width: 300,
//			title : "地址路径"
//		}, {
//			field : "moduleFatherId",
//			title : "父模块ID",
//			hidden: true
//		}, {
//			field : "moduleWeights",
//			title : "权  重",
//			hidden: true				
//		},  {
//			field : "isCatalogue",
//			title : "类  别",
//			template :  "#=(isCatalogue==0) ? '····页面' :'<b>·目录</b>'#"
//		},
//		{
//			title : "操作",
//		    template :"#if(isCatalogue==1){#"
//							   +"<a class='k-button' onClick='edit()'><span class='k-icon k-i-pencil'></span>编辑</a>" +
//							   		"<a class='k-button' onclick='add()'><span class='k-icon k-i-plus'></span>添加子模块</a>"
//						       + "#}else{#"
//						       +"<a class='k-button' onClick='edit()'><span class='k-icon k-i-pencil'></span>编辑</a>"
//						       + "#}#"			
//		}]
//	});
//	
//		var windowAddF = $("#windowAddF");
//		if (!windowAddF.data("kendoWindow")) {
//			windowAddF = windowAddF.kendoWindow({
//				width: "450px",
//				modal: true,           //遮罩效果
//				resizable: false,
//				visible: false,
//				title: "添加一级子目录模块",
//			});
//		}	
//		
//	var windowAdd = $("#windowAdd");
//	if (!windowAdd.data("kendoWindow")) {
//		windowAdd = windowAdd.kendoWindow({
//			width: "450px",
//			modal: true,           //遮罩效果
//			resizable: false,
//			visible: false,
//			title: "添加子模块",
//		});
//	}
//
//	var windowEdit = $("#windowEdit")
//	if (!windowEdit.data("kendoWindow")) {
//		windowEdit = windowEdit.kendoWindow({
//			width: "500px",
//			modal: true,           //遮罩效果
//			resizable: false,
//			close: function(){
//				$("#grid").data("kendoGrid").clearSelection();  //取消选择的行
//			},
//			visible: false,
//			title: "修改模块信息",
//		});
//	}
//}
