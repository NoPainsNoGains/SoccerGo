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

		<link
			href="../../plugins/kendoui/examples/content/shared/styles/examples-offline.css"
			rel="stylesheet">
		<link href="../../plugins/kendoui/styles/kendo.common.min.css"
			rel="stylesheet">
		<link href="../../plugins/kendoui/styles/kendo.default.min.css"
			rel="stylesheet">

		<script src="../../plugins/kendoui/js/jquery.min.js">
</script>
		<script src="../../plugins/kendoui/js/kendo.web.min.js">
</script>
		<script src="../../plugins/kendoui/js/kendo.messages.zh-CN.js">
</script>
		<script
			src="../../plugins/kendoui/examples/content/shared/js/console.js">
</script>
	</head>
	<body>
		<div id="grid"></div>
		<script>
var record = 0;
$(document).ready(function () {	
            	var dataSource = new kendo.data.DataSource({
                    transport : {
                        read : {
                            type : "post",
                            url : "../../admin/systemManage/user_list.action",
                            dataType : "json"
                        },
                        update : {
                        	type : "post",
                        	url : "../../admin/systemManage/user_update.action",
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
                             id: "userId",
                             fields: {
                                userName: { validation: { required: true } },
                                userPassword: { validation: { required: true } },
                                createDate: { validation: { required: true } },
                                academy: { validation: { required: true } },
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
                            of : "of {0}", // {0} is total amount of pages
                            itemsPerPage : "条每页",
                            first : "首页",
                            previous : "前一页",
                            next : "下一页",
                            last : "最后一页",
                            refresh : "刷新"
                        }
                    },
                    height: 430,
                    toolbar: [{
						template: "<a class='k-button' onclick='createUser()'><span  class='k-icon k-i-plus'></span>创建用户</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
					}],
                    selectable:"row",
                    columns: [
                    		  {title: "序号",template: "#= ++record #",width: 50}, 
                              { field: "userName", title: "用户名"},
                              { field: "userPassWord", title: "用户密码"},
                              { field: "userCreateDate", title: "创建时间"},
                              { field: "academy.academyName", title: "所在学院" },
                              { command: [{
							                name: "edit",
							                text: { 
							                    edit: "编辑",
							                    update: "更新",
							                    cancel: "取消"
							                }
							             }
							        ],
							        title: "操作",
							        width: "160px"
							   }
							],
           			 dataBinding: function() {
   						 record = (this.dataSource.page() -1) * this.dataSource.pageSize();
 					 },
           			 editable: "popup"
          	  });
				var windowAdd = $("#windowAdd");
					if (!windowAdd.data("kendoWindow")) {
						windowAdd = windowAdd.kendoWindow({	
							draggable: true,
							modal: true,          
							resizable: true,
							visible: false,
							title: "创建用户"
					});
				}
            });	 
		   function dataSource_requestEnd(e) {
			    
				if (e.type == "create" || e.type == "update") {
			        if (e.response == null || e.response.Errors == null) {
			            $('#grid').data('kendoGrid').dataSource.read();
						$('#grid').data('kendoGrid').refresh();
			        }
  				 }
		    } 
		    function createUser(){
		    	var windowAdd = $("#windowAdd");
           		windowAdd.data("kendoWindow").open();
           		windowAdd.data("kendoWindow").center();
		    }
		    function createSave(){
		    	$.ajax({
			        cache: true,
			        type: "POST",
			        url:"../../admin/systemManage/user_add.action",
			        data:$('#add_user').serialize(),
			        async: false,
			        dataType: "json",
			        success: function(flag) {
			         	if(flag==0){
			         		alert("插入成功!");
			         		var windows = $("#windowAdd");
					    	windows.data("kendoWindow").close();
					    	var gview = $("#grid").data("kendoGrid");
							gview.dataSource.read();
			         	}
			         	if(flag==1){
			         		alert("该用户名已存在");
			         	}
			        }
			    }); 
		    }
		    function createCancel(){
		    
		    }
          </script>
		<div id="windowAdd" style="display: none; width: 300px;">
			<form id="add_user">
				<p>
					<label style="margin-left: 40px;">
						用户名:
					</label>
					<input name="userName" style="margin-left: 38px; width: 120px;" />
				</p>
				<br>
				<p>
					<label style="margin-left: 40px;">
						用户密码:
					</label>
					<input name="userPassWord"
						style="margin-left: 28px; width: 120px;" />
				</p>

				<br>
				<p>
					<label style="margin-left: 40px;">
						所在学院:
					</label>
					<input name="academy.academyName" style="margin-left: 28px; width: 120px;" />
				</p>
				<br>
		
				<div style="float: right;">
					<a class='k-button' onclick='createSave()'>确定</a>&nbsp&nbsp
					<a class='k-button' onclick='createCancel()'>取消</a>
				</div>
			</form>
		</div>
	</body>
</html>
