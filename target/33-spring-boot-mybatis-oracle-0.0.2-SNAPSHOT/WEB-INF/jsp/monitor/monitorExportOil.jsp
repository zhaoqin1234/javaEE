<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../../Inc.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>测试计划模板下载</title>
</head>
<body>
	<fieldset style="border:solid 1px #aaa;padding:3px;">
        <legend>测试计划模板下载</legend>	
		<table>
			<tr>
				<td style="width:50px;">工区:</td>
				<td>
					<input name="site_id" id="site_id" class="mini-combobox" textField="name" valueField="id"  oncloseclick="onCloseClick"  value="HBn4JTnVa2" 
						style="width:150px;" allowinput="true" shownullitem="false" emptyText="请选择..." onvaluechanged="getYcList" showClose="true" />
				</td>
				<td style="width:50px;"></td>
				<td style="width:50px;">油藏:</td>
				<td>
					<input name="yc_id" id="yc_id" class="mini-combobox" textField="name" valueField="id"  oncloseclick="onCloseClick"
						style="width:150px;" allowinput="true" shownullitem="false" emptyText="请选择..." onvaluechanged="getWellList" showClose="true" />
				</td>
			</tr>
			
			
			
			<tr>
				<td>井号:</td>
				<td>
					<input name="well_id" id="well_id" class="mini-combobox" textField="wellCommonName" valueField="wellId" 
						style="width:150px;" allowinput="true" shownullitem="false" emptyText="请选择..."  oncloseclick="onCloseClick" showClose="true" />
				</td>
				<td style="width:50px;"></td>
				<td>日期:</td>
				<td>
					<input name="stime" type="text" id="time" class="mini-monthpicker" format="yyyy-MM" allowinput="true" 
						required="true" />
				</td>
				
			</tr>
			
			<tr>
				 	<td  style="width:50px;">测试类型：</td>
	                    <td > 
	                  <input id="cs_type_id" name="cs_type_id" class="mini-combobox" style="width:150px;" textField="jcjh_name" valueField="jcjh_id" 
   								 showNullItem="true" required="false" url="<%=basePath%>jcjhOption/getJCJHOptionOil"/> 
	                    </td>
	                    <td style="width:50px;"></td>
	                  	<td style="width:50px;">测试要求：</td>
	                    <td > 
	                          <input name="cs_claim" id="cs_claim" class="mini-textbox"  />
	                    </td>
			</tr>
			
		</table>
		<div style="text-align:center;padding:10px;">               
            <a class="mini-button" onclick="download()">模板下载</a>  
        </div>
     </fieldset>
    <form id="uploadForm">
		<fieldset style="border:solid 1px #aaa;padding:3px;">
	        <legend>测试计划模板下载导入</legend>	
			<table>
				<tr>
					<td>选择文件:</td>
					<td><input class="mini-htmlfile" name="fileMult"  id="fileImport" style="width:300px;"limitType="*.xls;*.xlsx" enctype="multipart/form-data" /></td>
				</tr>
			</table>
			<div style="text-align:center;padding:10px;">               
	            <a class="mini-button" onclick="uploadFile()" style="width:60px;margin-right:20px;">上传</a>       
	            <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>       
	        </div>
	     </fieldset>
	</form>
</body>
<script type="text/javascript">
	var time,title,state_id,yc_id;
	time = getTodayMonth();
	mini.parse();
	mini.get("time").setValue(time);
	var siteObj = mini.get("site_id"); //工区
	var ycObj = mini.get("yc_id");  //油藏
	var wellObj = mini.get("well_id"); //井号
	var form = new mini.Form("uploadForm"); //数据导入
	getSiteList();
	getWellList();
	getYcList();
	//工区
	function getSiteList(){
		var url = "<%=basePath%>org/getAllOrg";
		siteObj.setUrl(url);
	}
	//油藏
	function getYcList(){
		state_id = siteObj.getValue();
		var url = "<%=basePath%>block/getBlock?orgId=" + state_id;
		ycObj.setUrl(url);
	}
	//井号
	function getWellList(){
		state_id = siteObj.getValue();
		yc_id = ycObj.getValue();
		var url ="<%=basePath%>well/getWellMsg?orgId=" + state_id + "&siteId=" + yc_id;
		wellObj.setUrl(url);
	}
	//模板下载
	function download(){
		state_id = siteObj.getValue(); //工区Id
		yc_id = ycObj.getValue();
		var well_id = wellObj.getValue();
		time = mini.get("time").getFormValue();
		if(time == ""){
			mini.alert("请选择时间");
			return;
		}
		title = "测试计划模板";
		var url='<%=basePath%>oiljcjh/dwnloadOilJCJHTmp?stime='+time+'&site_id='+state_id+'&fileName='+title+'&yc_id='+yc_id+'&well_id='+well_id;
		window.location.href =encodeURI(url);
	}
	//数据导入
	function uploadFile(){
		form.validate();
		if (form.isValid() == false){
			return;
		}
		var message = "数据导入中，请稍后！";
		var messageBox=mini.loading(message, "信息");
		$.ajaxFileUpload({
			url:'<%=basePath%>oiljcjh/excelUpdateOilJCJH',
			fileElementId : 'fileImport',
			dataType : 'text',
			success : function(data, status) {
				mini.hideMessageBox(messageBox);
	        	//window.Owner.setData(data);
	        	CloseWindow("save"); 
	        	mini.alert("添加成功！请刷新当前页面！");
	        },
	        error : function() {
	        	mini.hideMessageBox(messageBox);
				mini.alert("导入失败");
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
	
	function onCloseClick(e) {
        var obj = e.sender;
        obj.setText("");
        obj.setValue("");
    }
</script>
</html>