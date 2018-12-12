<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="/Inc.jsp"></jsp:include>
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
		            <span style="padding-left:5px;">菜单目录:</span>
		            <input id="deptName" class="mini-textbox" style="width: 120px;" onkeyup="onSearchClick" />
		            <!-- <a style="margin-left:20px;" class="mini-button" onclick="onClClick">清除</a> -->
		            <a class="mini-button" iconCls="icon-reload" onclick="reloadTree()"></a> 
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
        		</div>
       			<div class="mini-fit" >
	    			<div id="datagrid" class="mini-datagrid" style="width:100%;height:100%;" url="<%=basePath%>button/getButtonsByButton" pageSize="20" showPager="true" 
						sizeList="[10,20,30,40,50,100,200,500,1000]" idField="id" allowResize="true" sortMode="client" 
						multiSelect="false" showReloadButton="true" >
						<div property="columns">
							<div type="indexcolumn" width="50" headeralign="center" headerstyle="font-weight: bold;">序号</div>
							<div field="key" headerAlign="center" align="center" headerStyle="font-weight: bold;">key</div>
							<!--  
			          		<div field="urlType" headerAlign="center" align="center" headerStyle="font-weight: bold;">类别</div>
			          		<div field="hostName" class="mini-hidden" headerAlign="center" align="center" headerStyle="font-weight: bold;" >hostName</div>
			          		<div field="appName" headerAlign="center" align="center" headerStyle="font-weight: bold;" >appName</div>
			          		-->
			          		<div field="name" headerAlign="center" align="center" headerStyle="font-weight: bold;">名称</div>
			          		<div field="menuName" headerAlign="center" align="center" headerStyle="font-weight: bold;">菜单名称</div>
			          		<div field="status" headerAlign="center" align="center" headerStyle="font-weight: bold;">状态</div>
			          		<div field="mark" headerAlign="center" align="center" headerStyle="font-weight: bold;">备注</div>
			          		<!--    
			          		<div field="imgName" class="mini-hidden" headerAlign="center" align="center" headerStyle="font-weight: bold;">imgName</div>
			          		-->  
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
		var url='<%=basePath%>menu/getMenuByMenu';
	    tree.load(url);
	    grid.load();
	}
	tree.on("nodeselect", function (e){
		grid.load({menuId: e.node.id});
	});
	function reloadTree(){
		tree.load();
	}
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
            url: "<%=basePath%>authority/button_Edit",
            title: "新增按钮信息", 
            width: 550, 
            height: 350,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {action: "new",row: row};
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
          		search();
          		tree.load();
            }
        });
		
	}
	//修改
	function edit(){
		var row = grid.getSelected();
		if(row){
			mini.open({
	            url:"<%=basePath%>authority/button_Edit",
	            title: "编辑按钮信息", 
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
                    url: "<%=basePath%>button/delButtonByButton",
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

	
</script>
</html>