<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="/Inc.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>菜单编辑</title>
</head>
<body>
	<form id="form1" method="post">
        <fieldset style="border:solid 1px #aaa;padding:3px;">
            <legend >菜单信息</legend>
            <div style="padding:5px;">
            	<input id="id" name="id" class="mini-hidden"/>
	           	<table style="table-layout:fixed;">
		            <tr>
		                <td style="width:85px;">菜单名称：</td>
		                <td style="width:150px;">    
		                    <input id="name" name="name" class="mini-textbox" required="true" />
		                </td>
		                <td style="width:85px;">上级菜单：</td>
		                <td >    
		                    <input name="pid" id="pid" class="mini-treeselect" textField="name" valueField="id" url="<%=basePath%>menu/getMenuByMenu"
								parentField="pid" style="width:150px;" allowinput="true" shownullitem="false" emptyText="请选择..."  required="false" />
		                </td>
		            </tr>
	                <tr>
	                	<td >URL：</td>
	                    <td colspan="3"> 
	                        <input name="url" class="mini-textbox"style="width:100%" required="false" />
	                    </td>
	                </tr> 
	              	<tr>
		                <td style="width:85px;">排序：</td>
		                <td style="width:150px;">    
		                    <input id="orderNumber" name="orderNumber" class="mini-textbox" required="false" />
		                </td>
		               
		            </tr>
	                <tr>
	                	<td >备注信息：</td>
	                	<td colspan="3">    
	                    	<input name="mark" class="mini-textarea" style="width:100%"/>
	                	</td>
	                </tr>
            	</table>
        	</div>
        </fieldset>
    </form>
    <div style="text-align:center;padding:10px;">               
    	<a class="mini-button" onclick="saveData()" style="width:60px;margin-right:20px;">提交</a>       
    	<a class="mini-button" onclick="onCancel" style="width:60px;">返回</a>       
    </div> 
</body>
<script type="text/javascript">
	var action;
	mini.parse();
	var form = new mini.Form("form1");
	//初始函数
	function SetData(obj){
		var data = mini.clone(obj);
		action = data.action;
		if(action == "edit"){
			mini.get("name").allowInput = true;
		}
		form.setData(data.row);
    	form.setChanged(false);
	}
	//保存数据
	function saveData(){
		form.validate();
		if (form.isValid() == false){
			return;
		}
		var data = form.getData();
		data.deptName = mini.get("pid").getText();
		var url="";
		if(action == "edit"){
			url = "<%=basePath%>menu/updMenu";
		}else{
			url = "<%=basePath%>menu/addMenu";
			
		}
		$.ajax({
            url: url,
	        type: 'post',
	        dataType:'text',
            data: data,
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
	//判断重名
	function isRepeat(name){
		var flag = false;
		$.ajax({
            url: "<%=basePath%>user/getUserByName",
	        type: 'post',
	        dataType: 'text',
	        cache: false,
            async: false,
            data: {
            	name: name
            },
            success: function (data) {
                if(data > 0){
                	flag = true;
                }
            }
        });
		return flag;
	}
	//取消
	function onCancel(){
		CloseWindow("cancel");
	}
	function CloseWindow(action) {            
        if (action == "close" && form.isChanged()) {
            if (confirm("数据被修改了，是否先保存？")) {
                return false;
            }
        }
        if (window.CloseOwnerWindow){
        	return window.CloseOwnerWindow(action);
        }else{
        	window.close();            
        }
    }
</script>
</html>