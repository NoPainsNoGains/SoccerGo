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
                              { field: "username", title: "用户名"},     
                              { field: "nickname", title: "昵称"},
                              { field: "preferedRole", title: "擅长位置"},
                              { field: "points", title: "积分"},
                              { field: "level", title: "用户等级"},
                              { field: "createdAt", title: "创建时间"},
                              { field: "updatedAt", title: "更新时间"}
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
						rowIndex=undefined;
						$('#grid').data('kendoGrid').dataSource.read();
		   	  			$('#grid').data('kendoGrid').refresh();	
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
		
          </script>
	
	</body>
</html>
