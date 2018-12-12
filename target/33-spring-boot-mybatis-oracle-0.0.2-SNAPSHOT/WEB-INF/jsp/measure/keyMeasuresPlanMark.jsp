<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../../Inc.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>措施计划报表</title>
</head>
<body>
	<form id="form1" method="post">
        <fieldset style="border:solid 1px #aaa;padding:3px;">
            <legend >报表备注信息</legend>
            <div style="padding:5px;">
            	<input id="id" name="id" class="mini-hidden"/>
	           	<table style="table-layout:fixed;">
		            <tr>
		                <td style="width:85px;">时间：</td>
		                <td style="width:150px;">    
		                   <input style="width:120px;" name="stime" type="text" id="stime" class="mini-monthpicker" format="yyyy-MM" allowinput="true"/>
		                </td>
		                <td style="width:85px;">措施类型：</td>
		                <td >    
		                     <input name="well_step_type" id="well_step_type2" class="mini-combobox" textField="text" valueField="id" showClose="true"
								style="width:150px;" url="<%=basePath%>data/csjhType.txt" allowinput="true" shownullitem="false" emptyText="请选择..." 
								oncloseclick="onCloseClick" />
		                </td>
		            </tr>
	                <tr>
	                	<td >备注信息：</td>
	                	<td colspan="3">    
	                    	<input name="note" id="note_id" class="mini-textarea" style="width:100%;height:150px"/>
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
	var stime = mini.get("stime");
	var step_type = mini.get("well_step_type2");
	var note = mini.get("note_id");
	var note_id ;
	//初始函数
	function SetData(obj){
		var data = mini.clone(obj);
		console.log(data);
		action = data.action;
		if(action == "edit"){
			
			stime.setText(data.stime);
			step_type.setText(data.csjh_typeText);
			step_type.setValue(data.csjh_type);
			note.setText(data.msg_note);
			note_id = data.msg_note_id;
			note = data.msg_note;
		}
		//form.setData(data.row);
    	//form.setChanged(false);
	}
	//保存数据
	function saveData(){
		form.validate();
		if (form.isValid() == false){
			return;
		}
		var data = {};
		data.id = note_id;
		data.type = mini.get("well_step_type2").getValue();
		data.stime = mini.get("stime").getText();
		data.note = mini.get("note_id").getText();
		var url="";
		if(action == "edit"){
			url = "<%=basePath%>csjh/updateReportNote";
		}else{
			url = "<%=basePath%>csjh/insertReportNote";
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