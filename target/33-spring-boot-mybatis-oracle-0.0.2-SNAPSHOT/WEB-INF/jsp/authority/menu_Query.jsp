<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="/Inc.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>用户角色编辑</title>
</head>
<body>
	<div id="div1">
			
    		<div showCollapseButton="true" >
    			<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">                
		           	当前角色：
		            <input id="name" class="mini-textbox" style="width: 120px;" onkeyup="k" /> 
		            <span class="separator"></span>             
		                           
        		</div>
       			<div class="mini-fit" style="height: 450px;width: 680px" >
	    			<div id="datagrMenu" class="mini-treegrid" style="width:100%;height:100%;" 
						treeColumn="name" idField="id" parentField="pid" resultAsTree="false"
					 	allowResize="true" sortMode="client" 
						multiSelect="true" showReloadButton="true"  allowUnselect="true"
	    				url="<%=basePath%>menu/getMenuByRole"   
						>

						<div property="columns">
							
							<div type="indexcolumn" width="20" 	  headeralign="center" headerstyle="font-weight: bold;">序号</div>
							<div name="name" field="name" width="150"	headerAlign="center" align="left" headerStyle="font-weight: bold;">菜单名称</div>
							<div field="url" 	 width="150"	headerAlign="center" align="left" headerStyle="font-weight: bold;">部门名称</div>
			          		<div field="mark" 	 width="150"	headerAlign="center" align="left" headerStyle="font-weight: bold;">备注</div>
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
	
	var grid = mini.get("datagrMenu");
	
	
	var userId ;
	//初始函数
	function SetData(obj){
		var data = mini.clone(obj);
		action = data.action;
		if(action == "edit"){
			//mini.get("name").allowInput = true;
		}
		var name = mini.get("name");
		name.setValue(data.row.name);
		userId = JSON.stringify(data.row.id);
		name.allowInput = false;
		grid.load({id:data.row.id});
	}
	
	
	

	function CloseWindow(action) {            
       
        if (window.CloseOwnerWindow){
        	return window.CloseOwnerWindow(action);
        }else{
        	window.close();            
        }
    }
	
	
</script>
</html>