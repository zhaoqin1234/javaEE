<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../../Inc.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>措施计划</title>
</head>
<body>
	<form id="form1" method="post">
        <fieldset style="border:solid 1px #aaa;padding:3px;">
            <legend >措施计划</legend>
            <div style="padding:5px;">
            	<input name="seq" id="seq" class="mini-hidden"/>
            	<input name="yc_name" id="yc_id" class="mini-hidden">
            	<input name="step_type" id="step_type" class="mini-hidden">
	           	<table style="table-layout:fixed;">
		            <tr>
		            	<td style="width:85px;">工区：</td>
	                    <td > 
							<input name="site_id" id="site_id" class="mini-combobox" textField="name" valueField="id" showClose="true" value="HBn4JTnVa2"   url="<%=basePath%>org/getAllOrg"
								style="width:150px;" allowinput="true" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick" 
								onvaluechanged="getWellList" required="true" />
	                    </td>
		                <td style="width:85px;">措施：</td>
		                <td >    
		                    <input name="well_step_name" id="well_step_name2" class="mini-combobox" textField="cs_name" valueField="ce_id" showClose="true"
								style="width:150px;" url="<%=basePath%>/csjh/getCsjhOption?cs_type=1" allowinput="true" shownullitem="false" emptyText="请选择..." 
								oncloseclick="onCloseClick" />
		                </td>
		            </tr>
	                <tr>
	                	<td >井号：</td>
	                	<td >    
	                        <input name="well_id" id="well_id" class="mini-combobox" textField="wellCommonName" valueField="wellId" showClose="true" required="true" 
								style="width:150px;" allowinput="true" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick" onvaluechanged="setYc"/>
	                    </td>
	                	<td >井别：</td>
	                    <td >    
	                        <input name="well_type" id="well_type" class="mini-combobox"  showClose="true" url="<%=basePath%>/data/wellType.txt" 
								style="width:150px;" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick" required="true" />
	                    </td>
	                </tr> 
	                <tr>
	                	<td >井状态：</td>
	                    <td >    
	                        <input name="well_status" id="well_status" class="mini-combobox"  showClose="true" url="<%=basePath%>/data/wellStatus.txt" 
								style="width:150px;" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick" required="true" />
	                    </td>
	                	<td >数据日期：</td>
	                    <td>
	                    	<input name="stime" id="stime" class="mini-hidden">
	                    	<input name="time" type="text" id="time" style="width:150px;" class="mini-monthpicker" 
	                    		format="yyyy-MM" allowinput="true" required="true" onvaluechanged="setTime" />
	                    </td>
	                </tr>
	                <tr>
	                	<td >日增油：</td>
	                    <td >    
	                        <input name="prod_daily" class="mini-textbox" vtype="float;required" floatErrorText="请输入正确的金额!"/>
	                    </td>
	                </tr>
	                <tr>
	                	<td >备注：</td>
	                	<td colspan="3">    
	                    	<input name="mark" class="mini-textarea" style="width:100%;"/>
	                	</td>
	                </tr>
            	</table>
        	</div>
        </fieldset>
    </form>
    <div style="text-align:center;padding:10px;">               
    	<a class="mini-button" onclick="saveData" style="width:60px;margin-right:20px;">提交</a>       
    	<a class="mini-button" onclick="onCancel" style="width:60px;">返回</a>       
    </div> 
</body>
<script type="text/javascript">
	var time,title,site_id,well_step_id,state_id,well_type,data_type;
	var site_id,action;
	time = getTodayMonth();
	mini.parse();
	var siteObj = mini.get("site_id");   //工区
	var stepObj = mini.get("well_step_name2");   //措施
	var wellObj = mini.get("well_id");   //井号
	var typeObj = mini.get("well_type");  //井别
	var statusObj = mini.get("well_status");   //井状态
	var timeObj = mini.get("time");  //日期
	var form = new mini.Form("form1");
	//初始函数
	function SetData(obj){
		var data = mini.clone(obj);
		action = data.action;
		var step_type = data.step_type;
		if(step_type == "1"){  //重点措施
			$.ajax({
	            url: "<%=basePath%>/csjh/getCsjhOption?cs_type=1",
		        type: 'post',
		        dataType:'text',
	            data: data,
	            cache: false,
	            success: function (text) {
	            	var well_step_id =  mini.get("well_step_name");
	            	well_step_id.setData(text);
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                mini.alert("保存失败");
	            }
	        });
		}else{
			$.ajax({
	            url: "<%=basePath%>/csjh/getCsjhOption?cs_type=0",
		        type: 'post',
		        dataType:'text',
	            data: {},
	            cache: false,
	            success: function (text) {
	            	var well_step_id =  mini.get("well_step_name");
	            	well_step_id.setData(text);
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                //mini.alert("保存失败");
	            }
	        });
		}
		
		if(action == "edit"){
			form.setData(data.row);
			timeObj.setValue(data.row.stime);
			console.log(data.row.well_step_name);
			console.log(stepObj);
			stepObj.setText(data.row.well_step_name);
        	form.setChanged(true);
        	getWellList();
		}else{
			mini.get("step_type").setValue(data.step_type);
			timeObj.setValue(time);
			mini.get("stime").setValue(time);
			getWellList();
		}
	}
	function getWellList(){
		site_id = siteObj.getValue();
		var url ="<%=basePath%>well/getWellMsg?orgId=" + site_id;
		wellObj.setUrl(url);
	}
	//设置油藏值
	function setYc(e){
		mini.get("yc_id").setValue(e.selected.siteName);
	}
	//设置时间
	function setTime(e){
		mini.get("stime").setValue(timeObj.getFormValue());
	}
	//保存数据
	function saveData(){
		form.validate();
		if (form.isValid() == false){
			return;
		}
		var data = form.getData();
		data.well_name = wellObj.getText();   //井名称
		data.site_name = siteObj.getText();   //工区名称
		data.well_type_name = typeObj.getText();   //井别
		data.well_step_name = stepObj.getText();   //措施名称
		data.well_status = statusObj.getText();    //井状态
		data.sh_status = "0";
		data.tj_status = "0";
		var url="<%=basePath%>csjh/insert";
		$.ajax({
            url: url,
	        type: 'post',
	        dataType:'text',
            data: data,
            cache: false,
            success: function (text) {
                CloseWindow("save");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                mini.alert("保存失败");
            }
        });
	}
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