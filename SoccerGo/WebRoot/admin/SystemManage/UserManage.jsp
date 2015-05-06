<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<title>用户管理</title>
		<link href="../../plugins/kendo/examples/content/shared/styles/examples-offline.css" rel="stylesheet">
		<link href="../../plugins/kendo/styles/kendo.common.min.css" rel="stylesheet">
		<link href="../../plugins/kendo/styles/kendo.default.min.css" rel="stylesheet">
		<script src="../../plugins/kendo/js/jquery.min.js"></script>
		<script src="../../plugins/kendo/js/kendo.web.min.js"></script>
		<script src="../../plugins/kendo/js/kendo.messages.zh-CN.js"></script>
		<script src="../../plugins/kendo/examples/content/shared/js/console.js"></script>
	</head>
	<body>
		<div id="grid"></div>
		<script>
			var record = 0;
			var selectedItem=undefined;   
			$(document).ready(function () {	
            	var dataSource = new kendo.data.DataSource({
                    transport : {
                        read : {
                            type : "post",
                            url : "/SoccerGo/admin/SystemManage/UserManager/list.action",
                            dataType : "json"
                        },
                        parameterMap : function(options, operation) {   
                        	if (operation == "read"){
                        		return{
                           		 	page:options.page,
                                    pageSize:options.pageSize
                           		};
                    		}else{            			 
                    			return {models: kendo.stringify(options.models)};	
                    		}                    	
                        }
                    },
                    batch:true,
                    pageSize:5,
                    schema : {
                    	 model: {
                             id: "objectId",
                             fields: {
                                username: { validation: { required: true } },
                                nickname: { validation: { required: false } },
                                preferedRole: { validation: { required: false }},
                                points: { validation: { required: false } },
                                level: { validation: { required: false } }
                             }
                        },
                        data : function(json) {
                            return json.rows; 
                        },
                        total : function(json) {
                           return json.total;  
                        }                  
                    },
                    requestEnd:dataSource_requestEnd,
                    serverPaging : true,
                    serverFiltering : true,
                    serverSorting : true
                });
                $("#grid").kendoGrid({
                    dataSource: dataSource,
                    pageable : {
                    	refresh: true,
        				pageSizes: true,
        				buttonCount: 5,
        				page: 1,
        				pageSize: 5,
        				pageSizes: [5, 10, 15],
                        messages : {
                            display : "{0} - {1} 共 {2} 条数据",
                            empty : "没有数据",
                            page : "Page",
                            of : "of {0}",
                            itemsPerPage : "条每页",
                            first : "首页",
                            previous : "前一页",
                            next : "下一页",
                            last : "最后一页",
                            refresh : "刷新"
                        }
                    },
                    height: 750,
                    selectable:"row",
                    columns: [
                    		  {title: "序号",template: "#= ++record #",width: 100}, 
                    		  { field: "mobilePhoneNumber", title: "手机号"},
                              { field: "username", title: "用户名"},     
                              { field: "nickname", title: "昵称"},
                          /*     { field: "preferedRole", title: "擅长位置"},
                              { field: "points", title: "积分"},
                              { field: "level", title: "用户等级"}, */
                              { field: "createdAt", title: "创建时间"},
                              { field: "updatedAt", title: "更新时间"},
                              { field: "roleName", title: "角色名"},
                              { command:[{text:"编辑",click:userRoleEditOpen}],title: "角色编辑"},
							],
					  edit: function (e) {        	
	                     var editWindow = e.container.data("kendoWindow");  
		                if (e.model.isNew()) {  
		                    editWindow.title('创建用户');
		                     $("a.k-grid-update")[0].innerHTML="保存";
	                    	 $("a.k-grid-cancel")[0].innerHTML="取消";
	                    	 e.container.find('.k-grid-update,.k-grid-cancel').css('float','right');
	                    	 e.container.find(".k-grid-update").insertAfter(".k-grid-cancel");
		                       
		                } 
           			 },
           			 dataBinding: function() {
   						 record = (this.dataSource.page() -1) * this.dataSource.pageSize();
 					 },
 					 change:function(e){
           			 		var grid = $("#grid").data("kendoGrid");
							selectedItem = grid.dataItem(grid.select());	
           			 },
           			 cancel:function(){
           			 	selectedItem=undefined;
						$('#grid').data('kendoGrid').dataSource.read();
		   	  			$('#grid').data('kendoGrid').refresh();	
           			 }
           			 
          	  });
				var winEditUserRole = $("#winEditUserRole");
				if (!winEditUserRole.data("kendoWindow")) {
					winEditUserRole = winEditUserRole.kendoWindow({
						width: "360px",
						draggable: true,
						modal: true,          
						resizable: true,
						visible: false,
						title: "编辑角色",
					});
				}
				/*左右用户列表*/
				$("#roleNameId").kendoDropDownList({
					dataTextField: "name",
					dataValueField: "objectId",		
					optionLabel: "请选择角色" ,
					dataSource: {
						transport: {
						  read: {
							async: false,
							type: "post",
							dataType: "json",
						    url: "/SoccerGo/admin/SystemManage/RoleManager/listAllRole.action"
						   }
						 },	 
						 schema : {
						    data : function(json) { 
						       return json; 
						    }                      
						 }
					}
			    });
            });	 
		   function dataSource_requestEnd(e) {
				if (e.type == "create" || e.type == "destory") {
			        if (e.response == null || e.response.Errors == null) {
			            $('#grid').data('kendoGrid').dataSource.read();
						$('#grid').data('kendoGrid').refresh();
			        }
  				 }
		    }
			function userRoleEditOpen(){
				if(selectedItem != undefined){
					document.getElementById("mobilePhoneNumberId").value = selectedItem.mobilePhoneNumber;
					document.getElementById("usernameId").value=selectedItem.username;
					document.getElementById("nicknameId").value=selectedItem.nickname;
				}
			 	var winEditUserRole = $("#winEditUserRole");
           		winEditUserRole.data("kendoWindow").open();
           		winEditUserRole.data("kendoWindow").center(); 
			}
			function UserRoleAddSave(){
			  var data = $("#roleNameId").data("kendoDropDownList").dataItem();
		   	     if(data.objectId == "" || data== undefined)
		   	     	alert("请选择角色再保存");
		    	 else{
		    	 	  if(selectedItem != undefined ){
				        //添加角色用户列表     	
				    	$.ajax({
					        cache: true,
					        type: "POST",
					        url:"/SoccerGo/admin/SystemManage/RoleManager/addUserRole.action",
					        data:{"roleId":data.objectId,"userId":selectedItem.objectId},
					        async: false,
					        dataType: "json",
					        success: function(flag) {
				         		var winEditUserRole = $("#winEditUserRole");
						    	winEditUserRole.data("kendoWindow").close();
						    	var gview = $("#grid").data("kendoGrid");
								gview.dataSource.read();
					        }
					    }); 
			   		 }
		    	 } 
			
			}
          </script>
		<div  id="winEditUserRole" style="display:none;">
			 <label style="margin-left:30px;font-size:15px;">手机号:
				 <input id="mobilePhoneNumberId"  style="width: 200px;background-color: transparent; border: 1px" />
			 </label>
			 </br>
			 <label style="margin-left:30px;font-size:15px;">用户名:
				 <input id="usernameId"  style="width: 200px;background-color: transparent; border: 1px" />
			 </label>
			  </br>
			  <label style="margin-left:42px;font-size:15px;">昵称:
				 <input id="nicknameId"  style="width: 200px;background-color: transparent; border: 1px" />
			 </label>
			  </br>
			 <label style="margin-left:42px;font-size:15px;">角色:
				 <input id="roleNameId"  style="width: 200px;background-color: transparent; border: 1px" />
			 </label>
			  </br>
              <div style="float:right;">
	 			  	 <a class='k-button' onclick='UserRoleAddSave()' >确定</a>
	 		  </div>
        </div>
	</body>
</html>
