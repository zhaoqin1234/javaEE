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
		           	当前用户：
		            <input id="nickname" class="mini-textbox" style="width: 120px;" onkeyup="k" /> 
		            <span class="separator"></span>             
		            <a class="mini-button" iconCls="icon-save" onclick="userAllotRole()">保存角色</a>                
        		</div>
       			<div class="mini-fit" style="height: 250px;width: 520px" >
	    			<div id="datagrRole" class="mini-datagrid" style="width:100%;height:100%;" 

					 	idField="id" allowResize="true" sortMode="client" 
						multiSelect="true" showReloadButton="true"  allowUnselect="true"
	    				url="<%=basePath%>role/getRoles"   
						>

						<div property="columns">
							<div type="checkcolumn" ></div>
							<div type="indexcolumn" width="50" 	  headeralign="center" headerstyle="font-weight: bold;">序号</div>
							<div field="name" 		headerAlign="center" align="center" headerStyle="font-weight: bold;">角色名称</div>
							<div field="deptName" 	headerAlign="center" align="center" headerStyle="font-weight: bold;">部门名称</div>
			          		<div field="mark" 		headerAlign="center" align="center" headerStyle="font-weight: bold;">备注</div>
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
	
	var grid = mini.get("datagrRole");
	grid.load();
	
	var userId ;
	//初始函数
	function SetData(obj){
		//console.log(arguments);
		var data = mini.clone(obj);
		action = data.action;
		userId = data.row.id;
		if(action == "edit"){
			//mini.get("name").allowInput = true;
		}
		var nickname = mini.get("nickname");
		nickname.setValue(data.row.nickname);
		nickname.allowInput = false;
	}
	
	
	
	function userAllotRole(){
		var grid = mini.get("datagrRole");
		var rows = grid.getSelecteds( );
		
		var url = "<%=basePath%>role/allotRole";
		var data = {};
		var roleIds = "";
		if(rows == null){
			alert(请选择一个角色对象);
		}else{
			for(var i=0;i<rows.length;i++){
				if(rows[i].id!= null && i == 0){
					roleIds +=  rows[i].id  ;
				}else{
					roleIds += ","+rows[i].id + "";
				}
			}
		}
		data.user_id = userId;
		data.role_id = roleIds;
		$.ajax({
            url: url,
	        type: 'post',
	        dataType:'text',
            data: {user_id:userId,role_ids:roleIds},
            cache: false,
            async: false,
            success: function (text) {
                CloseWindow("save");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                mini.alert("保存失败");
            }
        });
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