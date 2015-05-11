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
		<title>职务管理</title>
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
			var strId = undefined;	
			var record = 0;
			var inrecord = 0;
			var selectedItem = undefined;
			var inObjectId = undefined;
		    $(document).ready(function() {
                    var element = $("#grid").kendoGrid({
                        dataSource: {
                            transport: {
                              read : {
		                            type : "post",
		                            url : "/SoccerGo/admin/SystemManage/RoleManager/list.action",
		                            dataType : "json"
		                      },
		                      create: {
		                        	 type : "post",
		                             url : "/SoccerGo/admin/SystemManage/RoleManager/add.action",
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
                            pageSize: 5,
                            schema : {
		                    	 model: {
		                             id: "objectId",
		                             fields: {
		                                name: { validation: { required: true } }
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
                            serverPaging: true,
                            serverSorting: true
                        },
                        height: 750,
                        toolbar: [{ name: "create", text: "创建角色" }],
                        selectable:"row",
                        sortable: true,
                        pageable: true,
                        detailInit: detailInit,
                        dataBound: function() {
                            this.expandRow(this.tbody.find("tr.k-master-row").first());
                        },
                        columns: [
                           {title: "序号",template: "#= ++record #",width: 100}, 
                              { field: "name", title: "职务"},
                              { field: "createdAt", title: "创建时间"},
                              { field: "updatedAt", title: "更新时间"},
                              { command:[{text:"编辑",click:roleAuthorityEdit}],title: "角色权限编辑"},
                              { command: [{
							                 name: "start",
							                 text: "删除",
							                 click:deleteRole
							              }],
							      title: "操作",
							      width: "200px"   
							 }   
                        ],
                        edit: function (e) {        	
		                     var editWindow = e.container.data("kendoWindow");  
			                if (e.model.isNew()) {  
			                    editWindow.title('创建角色');
			                     $("a.k-grid-update")[0].innerHTML="保存";
		                    	 $("a.k-grid-cancel")[0].innerHTML="取消";
		                    	 e.container.find('.k-grid-update,.k-grid-cancel').css('float','right');
		                    	 e.container.find(".k-grid-update").insertAfter(".k-grid-cancel");
			                       
			                } 
	           			 },
	 					 change:function(e){
	           			 		var grid = $("#grid").data("kendoGrid");
								selectedItem = grid.dataItem(grid.select());	
	           			 },
	           			 dataBinding: function() {
	   						 record = (this.dataSource.page() -1) * this.dataSource.pageSize();
	 					 },
	           			 editable: {
		                    mode: "popup",
		                	template: kendo.template($("#popup-editor").html())
	  					}
                    });
                });

                function detailInit(e) {    
                    $("<div id=\"ingrid\"/>").appendTo(e.detailCell).kendoGrid({
                     	dataSource: {
                            transport: {
                                read : {
		                            type : "post",
		                            url : "/SoccerGo/admin/SystemManage/RoleManager/listRoleUser.action",
		                            dataType : "json"
		                        },
		                        parameterMap : function(options, operation) {   
		                        	inObjectId = e.data.objectId;
		                        	if (operation == "read"){
		                        		return{
			                        			roleObjectId:e.data.objectId,
			                           		 	pageIn:options.page,
			                                    pageSizeIn:options.pageSize
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
                            serverPaging: true,
                            serverSorting: true,
                            serverFiltering: true,
                            pageSize: 5
                        },
                        scrollable: false,
                        sortable: true,
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
                        columns: [
                    		  {title: "序号",template: "#= ++inrecord #",width: 100}, 
                    		  { field: "mobilePhoneNumber", title: "手机号"},     
                              { field: "username", title: "用户名"},     
                              { field: "nickname", title: "昵称"},
                              { field: "createdAt", title: "创建时间"},
                              { field: "updatedAt", title: "更新时间"} 
							],
						  edit: function (e) {        	
		                     var editWindow = e.container.data("kendoWindow");  
			                 if (e.model.isNew()) {  
			                     editWindow.title('增加角色用户');
			                     $("a.k-grid-update")[0].innerHTML="保存";
		                    	 $("a.k-grid-cancel")[0].innerHTML="取消";
		                    	 e.container.find('.k-grid-update,.k-grid-cancel').css('float','right');
		                    	 e.container.find(".k-grid-update").insertAfter(".k-grid-cancel");
			                       
			                } 
	           			  },
	           			  dataBinding: function() {
	   						 inrecord = (this.dataSource.page() -1) * this.dataSource.pageSize();
	 					 }
                    });
                   	
                }
                 function dataSource_requestEnd(e) {
					if (e.type == "create" || e.type == "destory") {
				        if (e.response == null || e.response.Errors == null) {
				            $('#grid').data('kendoGrid').dataSource.read();
							$('#grid').data('kendoGrid').refresh();
				        }
	  				 }
			    }
			    function deleteRole(){
				    if(selectedItem!=undefined){
				   		var objectId = selectedItem.objectId;
			    		alert(objectId);
				    }
			    }
			   
			    function roleAuthorityEdit(){			    
			    	var data = undefined;//获取权限树	
				    $.ajax({
					     url: "/SoccerGo/admin/ModuleAuthority/list.action",
					     async: false,
					     dataType: "json",
					     data:{"roleId":selectedItem.objectId},
					     success: function(d){
							data = d;
					    }
				    });
				    var dataSource = new kendo.data.HierarchicalDataSource({     
						data: data,
						schema: {
				        	model: {
				        		id:"id",
				        		hasChildren : "hasChildren",
				                children: "items",
				                checked:"checked"
				            }
				        }	
					 });
					 var treeview = $("#treeviewEdit").kendoTreeView({
						checkboxes: {			
	            			checkChildren: true
	        			},
		                dataSource: dataSource,
		                dataValueField: "id",
				        dataTextField: "text"
		             }).data("kendoTreeView");   
		             treeview.expand(".k-item");//展开一级子树
		             treeview.expand(".k-item");//展开二级子树
		             treeview.collapse(".k-item");
		             treeview.collapse(".k-item");
		             /*打开窗口*/
					 var windowEdit = $("#windows");
						if (!windowEdit.data("kendoWindow")) {
						windowEdit = windowEdit.kendoWindow({
							width: "250px",
							draggable: true,
							modal: true,
							resizable: true,
							visible: true,
							title: "角色权限选择",
						});
					}
					windowEdit.data("kendoWindow").open();
					windowEdit.data("kendoWindow").center();
					$("#treeviewEdit").data("kendoTreeView").dataSource.bind("change", function() {
			            var checkedNodes = [],treeView = $("#treeviewEdit").data("kendoTreeView"),message;
			            checkedNodeIds(treeView.dataSource.view(), checkedNodes);
			  			strId =checkedNodes.join(",");
			       }); 
	          		function checkedNodeIds(nodes, checkedNodes) {
			            for (var i = 0; i < nodes.length; i++) {
			               	if (nodes[i].checked) {
			                    checkedNodes.push(nodes[i].id);
			                }
			                if (nodes[i].hasChildren) {
			                    checkedNodeIds(nodes[i].children.view(), checkedNodes);
			                }
			            }
          			}      
			   }//roleEdit
			   function setSave(){
				 	var grid = $("#grid").data("kendoGrid");
					selectedItem = grid.dataItem(grid.select());//获取选择行对象	
					if(strId!=undefined&&strId!=""){//用户没有选择
						strId=strId+","+selectedItem.objectId;
						if (strId.length > 1) {	         
				           	 $.ajax({
							     url: "/SoccerGo/admin/ModuleAuthority/update.action",
							     async: false,
							     dataType: "json",
							     data:{"authoritySet":strId},
							     success: function(flag) {
					         		var windows = $("#windows");
							    	windows.data("kendoWindow").close();
							    	window.location.reload();		
						        }
					    	});
				        } 
					}				
				}
		</script>
		<script id="popup-editor" type="text/x-kendo-template"> 
  				<p>
   					<label style="margin-left:40px;">角色名: <input data-bind="value:name" style="width: 200px;" class="k-textbox" placeholder="必须填写角色名" required validationMessage="不能为空"/></label>
 				</p>
		</script>
		 <div  id="windows" type="text/x-kendo-template" style="display:none;">
              <div id="treeviewEdit" style="margin-left:10px;"></div> 
              <div style="float:right;">
	 			  	 <a class='k-button' onclick='setSave()'>确定</a>
	 		  </div> 
          </div>
	</body>
</html>
