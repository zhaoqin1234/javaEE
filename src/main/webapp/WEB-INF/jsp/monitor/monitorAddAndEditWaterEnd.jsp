<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../../Inc.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>测试计划井号</title>
</head>
<body>
	<form id="form1" method="post">
        <fieldset style="border:solid 1px #aaa;padding:3px;">
            <legend >测试计划井号</legend>
            <div style="padding:5px;">
            	<input id="seq" name="seq" class="mini-hidden"/>
            	<input id="site_name" name="site_name" class="mini-hidden"/>
            	<input id="yc_id"   name="yc_id" class="mini-hidden"/>
            	<input  id="well_name"  name="well_name" class="mini-hidden"/>
            	<input  id="cs_type_name"  name="cs_type_name" class="mini-hidden"/>
	           	<table style="table-layout:fixed;">
		            <tr height="35px">
		                <td style="width:200px;"align="right">工区：</td>
		                <td style="width:150px;">    
		                    <input name="site_id" id="site_id" class="mini-combobox" textField="name" valueField="id"   value="HBn4JTnVa2" 
								style="width:150px;" allowinput="true" shownullitem="false" emptyText="请选择..." onvaluechanged="getWellListBySiteAndGetYcBySite()"
								required="true"/>
		                </td>
		                 <td style="width:200px;" align="right">油藏：</td>
		                <td style="width:150px;">    
		                    <input name="yc_id" id="yc_id" class="mini-combobox" textField="name" valueField="id"  
								style="width:150px;" allowinput="true" shownullitem="false" emptyText="请选择..." onvaluechanged="getWellListByYc()"
								required="true" />
		                </td>
		            </tr>
	            <tr height="35px">
	                   <td style="width:150px;" align="right">井号：</td>
		                <td >    
		                    <input name="well_id" id="well_id" class="mini-combobox" textField="wellCommonName" valueField="wellId" 
								style="width:150px;" allowinput="true" shownullitem="false" emptyText="请选择..."
								required="true" requiredErrorText="井号不能为空" onvaluechanged="setYc"
								/> 
		                </td>
		                	<td align="right">监测计划月份：</td>
	                    <td > 
	                      <input id="stime" name="stime" valueType="string" class="mini-monthpicker" format="yyyy-MM" style="width:150px;"  showTime="false" />
	                    </td>
	                </tr> 
	             <tr height="35px">
	             			   
	                	<td align="right">测试类型：</td>
	                    <td> 
	                  <input id="cs_type_id" name="cs_type_id" class="mini-combobox" style="width:150px;" textField="jcjh_name" valueField="jcjh_id"  allowInput="true"
   								 showNullItem="true" required="true" url="<%=basePath%>jcjhOption/getJCJHOptionWater"/> 
	                    </td>
		                
		                
		                    <td style="width:150px;"align="right">提出日期：</td>
		                <td > 
		                    <input id="tc_date" name="tc_date" valueType="string" class="mini-datepicker" style="width:150px;" format="yyyy-MM-dd" showTime="false" allowInput="false"/>
		                </td>
		                
		                
		                
		               
	                </tr> 
	               <tr height="35px">
	                	<td align="right">测试要求：</td>
	                    <td > 
	                          <input name="cs_claim" id="cs_claim" class="mini-textbox"  />
	                    </td>
	                 	<td align="right">生产井段（m）：</td>
	                    <td >    
	                        <input name="well_section" id="well_section" class="mini-textbox"  floatErrorText="请输入数字"/>
	                    </td>
	                </tr> 
	              <tr height="35px">
	              
	               	<td align="right">厚度（m）：</td>
	                    <td > 
	                        <input name="thickness" id="thickness" class="mini-textbox"  required="true" floatErrorText="请输入数字" />
	                    </td>
	              	<td align="right">层数：</td>
	                    <td >    
	                        <input name="plies_num" id="plies_num" class="mini-textbox" floatErrorText="请输入数字"/>
	                    </td>
	                </tr> 
	                <tr height="35px">
	                     
	                	<td align="right">需求程度：</td>
	                    <td>    
<!-- 	                        <input name="level_demand" id="level_demand" class="mini-textbox" value="正常"/> -->
	                   
	                     <input id="level_demand" name="level_demand" class="mini-combobox" style="width:150px;" textField="text" valueField="id"  allowInput="false"
   								 showNullItem="true" required="true" value="0"  url="<%=basePath%>/data/xuqiuchengdu.txt" /> 
	                   
	                    </td>
	                   <td style="width:85px;" align="right">上次测成时间：</td>
		                <td >    
		                   <input id="last_success_date" name="last_success_date" valueType="string" class="mini-datepicker" style="width:150px;" format="yyyy-MM-dd" showTime="false" allowInput="false"/>
		                </td>
	                </tr> 
	            <tr height="35px">
	                	<td align="right">（最近一次）未测成时间：</td>
	                    <td > 
	                      <input id="recently_fail_date" name="recently_fail_date" valueType="string" class="mini-datepicker" style="width:150px;" format="yyyy-MM-dd" showTime="false" allowInput="false"/>
	                    </td>
	            
	                   <td style="width:150px;"align="right">（最近一次）整改情况：</td>
		                <td >   
		                     <input name="recently_revise_mesg" id="recently_revise_mesg" class="mini-textbox" allowInput="false"/>
		                </td>
	                </tr> 
	          
	                
	              <tr height="35px">
	                
	                <td align="right">出单日期：</td>
	                    <td > 
	                     <input id="fk_cd_date" name="fk_cd_date" valueType="string" class="mini-datepicker" style="width:150px;" format="yyyy-MM-dd" showTime="false"  allowInput="false"/>
	                    </td>
	                   <td style="width:150px;"align="right">未出单原因：</td>
		                <td >   
		                <input id="wcd_mesg" name="wcd_mesg"class="mini-textbox"  allowInput="false"/> 
		                </td>
		               
	                </tr> 
	                 <tr height="35px">
		                
		                 	<td align="right">测成日期：</td>
	                    <td > 
	                      <input id="success_date" name="success_date" valueType="string" class="mini-datepicker" style="width:150px;" format="yyyy-MM-dd" showTime="false" allowInput="false"/>
	                    </td>
	                    
	                        <td style="width:150px;" align="right">未测成原因：</td>
		                <td>  
		                 <input id="fail_mesg" name="fail_mesg"class="mini-textbox"  />   
		                </td>
	                    
		         
	                </tr> 
	             <tr height="35px">
	                
	                   <td style="width:150px;" align="right">测试要求日注水量：</td>
		                <td colspan="3">  
		                 <input id="cs_water_injection" name="cs_water_injection"class="mini-textbox" allowInput="false" />   
		                </td>
	                </tr> 
	              <tr height="35px">
	                	<td align="right">目的：</td>
	                	<td colspan="3">    
	                    	<input name="cs_purpose" id="cs_purpose" class="mini-textarea" style="width:100%" required="true" allowInput="false"/>
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
	var wellType='';
	var site_id,action;
	time = getTodayMonth();
	date=getTodayDate();
	mini.parse();
	 mini.get("stime").setValue(time);
	 mini.get("tc_date").setValue(date);
	var siteObj = mini.get("site_id"); //工区
	var wellObj = mini.get("well_id"); //井号
	var ycObj = mini.get("yc_id"); //油藏
	var testObj = mini.get("cs_type_id"); //测试类型
	var form = new mini.Form("form1");
	//初始函数
	function SetData(obj){
		var data = mini.clone(obj);
		action = data.data.action;
		dataType = data.data.dataType;
		wellType=dataType;
		if(action == "edit"){
			form.setData(data);
        	form.setChanged(false);
        	getOrgList();
        	getWellList();
        	ycObj.setText(data.yc_name);
         	testObj.setText(data.cs_type_name);
        	testObj.setValue(data.cs_type_name);
        	//getDataTypeList(dataType);
		}else{
			getOrgList();
			getSiteList();
			getWellList();
			getDataTypeList(dataType);
		}
	}
	//油藏下拉框
	function getSiteList(){
		site_id = siteObj.getValue();
		var url = "<%=basePath%>block/getBlock?orgId=" + site_id;
		ycObj.setUrl(url);
	} 
	//检测类型下拉框
	function getDataTypeList(dataType){
			var url = "<%=basePath%>jcjhOption/getJCJHOptionWater";
			testObj.setUrl(url);
	} 
	
	
	//根据工区查询井，根据工区查询油藏
	
	function getWellListBySiteAndGetYcBySite(){
		getWellList();
		getSiteList();
	}
	
	//工区下拉框
	function getOrgList(){
		var url = "<%=basePath%>org/getAllOrg";
		siteObj.setUrl(url);
	}
	//井号下拉框根据工区查询
	function getWellList(){
		site_id = siteObj.getValue();
		var url ="<%=basePath%>well/getWellMsg?orgId=" + site_id;
		wellObj.setUrl(url);
	}
	//井号下拉框根据油藏查询
	function getWellListByYc(){
		state_id = siteObj.getValue();
		yc_id = ycObj.getValue();
		var url ="<%=basePath%>well/getWellMsg?orgId=" + state_id + "&siteId=" + yc_id;
		wellObj.setUrl(url);
	}
	//设置油藏值
	function setYc(e){
		mini.get("yc_id").setValue(e.selected.siteId);
		mini.get("yc_id").setText(e.selected.siteName);
		setWellMsg();
	}
	
//根据井名设置井段，层数，厚度等信息
	
	function setWellMsg(){
		var wellData=wellObj.data;
		var wellId=wellObj.getValue();
	for(var i=0;i<wellData.length;i++){
		if(wellData[i].wellId==wellId){
			mini.get("well_section").setValue(wellData[i].jd);
			mini.get("thickness").setValue(wellData[i].hd);
			mini.get("plies_num").setValue(wellData[i].cs);
		}
		
	}
		
	}
	
	//保存数据
	function saveData(){
		form.validate();
		if (form.isValid() == false){
			return;
		}
		var data = form.getData();
		data.well_name = wellObj.getText();
		data.site_name = siteObj.getText();
		data.cs_type_name =testObj.getText();
		data.yc_name=ycObj.getText();
		var url="<%=basePath%>waterjcjh/insert";
		$.ajax({
            url: url,
	        type: 'post',
	        dataType:'text',
            data: data,
            cache: false,
            dataType:"json",
            success: function (text) {
               	if(text.code==1){
               	//	notify(text.message)
              alert("操作成功！");
               	   CloseWindow("save");
                	}else{
                		 mini.alert(text.message); 
                		/* 	notify(text.message); */
                	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                mini.alert("保存失败");
            }
        });
	}
	  function notify(message) {

	        mini.showMessageBox({
	            showModal: false,
	            width: 250,
	            title: "提示",
	            iconCls: "mini-messagebox-warning",
	            message: message,
	            timeout: 2000
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
	function onCloseClick(e) {
        var obj = e.sender;
        obj.setText("");
        obj.setValue("");
    }
</script>
</html>