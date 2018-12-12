<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../../Inc.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>用户信息</title>
</head>
<body>
	<div id="div1">
		<div class="mini-splitter" style="width:100%;height:100%;">
			<div size="240" showCollapseButton="true">
		        <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">                
		            <span style="padding-left:5px;">部门名称:</span>
		            <input id="deptName" class="mini-textbox" style="width: 120px;" onkeyup="onSearchClick" />
		            <!-- <a style="margin-left:20px;" class="mini-button" onclick="onClClick">清除</a> -->
		        </div>
		        <div class="mini-fit">
		            <ul id="tree1" class="mini-tree" style="width: 100%; height: 100%;" showtreeicon="true" expandOnLoad="0" 
		            	textfield="name" idfield="id" parentfield="pid" checkOnTextClick="true" expandOnDblClick="false"
                    	resultastree="false" checkRecursive="false" autoCheckParent="false" >        
		            </ul>
		        </div>
    		</div>
    		<div showCollapseButton="true" >
    			<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">                
		            <a class="mini-button" iconCls="icon-add" onclick="add()">新增</a>
		            <span class="separator"></span> 
		        	<a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>    
		            <span class="separator"></span>             
		            <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>  
		            <span class="separator"></span>             
		            <a class="mini-button" iconCls="icon-add" onclick="queryRole()">查询权限</a> 
		            <span class="separator"></span>             
		            <a class="mini-button" iconCls="icon-user" onclick="allotRole()">分配权限</a>                
        		</div>
       			<div class="mini-fit" >
	    			<div id="datagrid" class="mini-datagrid" style="width:100%;height:100%;" url="<%=basePath%>user/getUserByUser" pageSize="20" showPager="true" 
						sizeList="[10,20,30,40,50,100,200,500,1000]" idField="id" allowResize="true" sortMode="client" 
						multiSelect="false" showReloadButton="true" >
						<div property="columns">
							<div type="indexcolumn" width="50" headeralign="center" headerstyle="font-weight: bold;">序号</div>
							<div field="deptName" headerAlign="center" align="center" headerStyle="font-weight: bold;">部门</div>
			          		<div field="name" headerAlign="center" align="center" headerStyle="font-weight: bold;">用户名</div>
			          		<div field="nickname" headerAlign="center" align="center" headerStyle="font-weight: bold;" >姓名</div>
			          		<div field="gender" headerAlign="center" align="center" headerStyle="font-weight: bold;" renderer="genderRender">性别</div>
			          		<div field="age" headerAlign="center" align="center" headerStyle="font-weight: bold;">年龄</div>
			          		<div field="telNumber" headerAlign="center" align="center" headerStyle="font-weight: bold;">电话号码</div>
			          		<div field="address" headerAlign="center" align="center" headerStyle="font-weight: bold;">地址</div>  
			            </div>
	    			</div>
    			</div>
    		</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var HeightEx = document.documentElement.clientHeight;
	document.getElementById("div1").style.height = HeightEx - 50 + "px";
	var time,title,org_id,site_id;
	mini.parse();
	var tree = mini.get("tree1");  //树对象
	var grid = mini.get("datagrid");
	getTreeList();
	//对象输入
    function onSearchClick(e) {
        var key = e.source.getInputText();
        if (key == "") {
            tree.clearFilter();
        } else {
            tree.filter(function (node) {
                var text = node.name ? node.name.toLowerCase() : "";
                if (text.indexOf(key) != -1) {
                    return true;
                }
            });
        }
    }
	function getTreeList(){
		var url='<%=basePath%>deptment/getDeptmentsByDeptment';
	    tree.load(url);
	    grid.load();
	}
	tree.on("nodeselect", function (e){
		grid.load({deptId: e.node.id});
	});
	function genderRender(e){
		if(e.value == "0"){
			return "男";
		}else{
			return "女";
		}
	}
	function search(){
		grid.reload();
	}
	function add(){
		var row = {};
		if(tree.getSelectedNode()){
			row.deptId = tree.getSelectedNode().id;
		}else{
			row.deptId = "";
		}
		mini.open({
            url: "<%=basePath%>user/userEdit",
            title: "新增用户信息", 
            width: 550, 
            height: 350,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {action: "new",row: row};
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
          		search();
            }
        });
	}
	//修改
	function edit(){
		var row = grid.getSelected();
		if(row){
			mini.open({
	            url:"<%=basePath%>user/userEdit",
	            title: "编辑用户信息", 
	            width: 550, 
	            height: 350,
	            onload: function () {
	                var iframe = this.getIFrameEl();
	                var data = { action: "edit", row: row};
	                iframe.contentWindow.SetData(data);
	            },
	            ondestroy: function (action) {
	            	search();
	            }
        	});
		}else{
			alert("请选中一条记录");
		}
	}
	function remove(){
		var row = grid.getSelected();
		if(row){
			if (confirm("确定删除选中记录？")){
				$.ajax({
                    url: "<%=basePath%>user/delUserById",
                    dataType:'text',
                    async: false,
                    data: {
                    	id: row.id
                    },
                    success: function (text) {
                  		search();
                    }
                });
			}
		}else{
			mini.alert("请选中一条记录");
		}
	}
	
	//查询用户的角色
	function queryRole(){
		var row = grid.getSelected();
		if(row){
			mini.open({
	            url:"<%=basePath%>authority/role_Query",
	            title: "查询用户角色", 
	            width: 550, 
	            height: 350,
	            onload: function () {
	                var iframe = this.getIFrameEl();
	                var data = { action: "edit", row: row};
	                iframe.contentWindow.SetData(data);
	            },
	            ondestroy: function (action) {
	            	search();
	            }
        	});
		}else{
			alert("请选中一条记录");
		}
		
	}
	
	//分配角色给用户
	function allotRole(){
		var row = grid.getSelected();
		if(row){
			mini.open({
	            url:"<%=basePath%>authority/role_Allot",
	            title: "给用户分配角色", 
	            width: 550, 
	            height: 350,
	            onload: function () {
	                var iframe = this.getIFrameEl();
	                var data = { action: "allot", row: row};
	                iframe.contentWindow.SetData(data);
	            },
	            ondestroy: function (action) {
	            	search();
	            }
        	});
		}else{
			alert("请选中一条记录");
		}
	}
	function exportData(){
		title = time.replace("-","年") + "月配注数据审核";
		var url='<%=basePath%>monthPZ/dwnloadQueryData?stime='+time+'&sh_starts='+state_id+'&fileName='+title+'&orgId='+org_id+'&siteId='+site_id+'&tj_starts=1';
		window.location.href =encodeURI(url);
	}
	
</script>
</html>