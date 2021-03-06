<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <jsp:include page="../../../Inc.jsp"></jsp:include>
  <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
		$(function(){
		mini.parse();
var grid = mini.get("datagrid");
var startDate = mini.get("startDate");
var endDate = mini.get("endDate");
	date=getTodayDate(); //获取当前日期
	endDate.setValue(date);
	time = getTodayByUpMonth(); //获取上月日期
	startDate.setValue(time);
	getWellList();
	grid.load();
	 var jsonData = [{ 'id': 'success_date', 'text': '测成日期'},{ 'id': 'tc_date', 'text': '提出日期'},{ 'id': 'fk_cd_date', 'text': '出单日期'},{ 'id': 'stime', 'text': '措施计划月份'}, { 'id': 'last_success_date', 'text': '上次测成时间'}, { 'id': 'recently_fail_date', 'text': '最近未测成时间'}];
	 var jsonData2 = [{ 'id': '1', 'text': '已提交'},{ 'id': '0', 'text': '未提交'}];
	  mini.get("dateType").load(jsonData);
	  mini.get("tj_status").load(jsonData2);
		})
		

		
			//新增
	function add(){
		mini.open({
            url: "<%=basePath%>monitor/monitorAddAndEdit",
            title: "新增测试计划井号", 
            width: 800, 
            height:550,
            onload: function () {
                var iframe = this.getIFrameEl();
               var data = {data:{action: "new",dataType:"oil"}};
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
            	//if (action == "ok") {   
            		 var grid = mini.get("datagrid");
            		grid.reload(); 
            	//}
            }
        });
	}
			//编辑
	function edit(){
		mini.open({
            url: "<%=basePath%>monitor/monitorAddAndEdit",
            title: "编辑测试计划井号", 
            width:  800, 
            height: 550,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {action: "edit"};
                var grid = mini.get("datagrid");
                var rowObj=grid.getSelecteds();
                if(rowObj.length>1){
                	mini.alert("只能选择一行");
                	return ;
                }
                var rowData=rowObj[0];
              	 rowData.data={action: "edit",dataType:"oil"};
                iframe.contentWindow.SetData(rowData);
            },
            ondestroy: function (action) {
           	var grid = mini.get("datagrid");
        		grid.reload(); 
            }
        });
	}
		
	//查询
	function search(){
		startDate = mini.get("startDate").getFormValue();
		endDate = mini.get("endDate").getFormValue();
	 /* 	if(startDate == ""){
			mini.alert("开始日期不能为空");
			return;
		}  */
	 	
	/*  	if(endDate == ""){
	 		endDate=getTodayDate();
		}  */
	 	
		var dateType= mini.get("dateType").getValue(); //查询日期类型
		if(startDate!=""||endDate!=""){
		if(dateType == ""){
			mini.alert("日期类型不能为空");
			return;
		} 
		}
		if(dateType=="stime"){
			startDate=startDate.substring(0,7);  //措施计划时间设置为按月份查询
			endDate=endDate.substring(0,7);
		}
		var siteId = mini.get("site_id").getValue(); //工区Id
		var wellId = mini.get("well_id").getValue(); //井号Id
		var cs_type_id = mini.get("cs_type_id").getValue();  //测试类型Id
		var tj_status = mini.get("tj_status").getValue();  //提交状态
		var grid = mini.get("datagrid");
		var pIndex = grid.pageIndex;
		var pSize = grid.pageSize;
		grid.load({site_id:siteId,well_id:wellId,cs_type_id:cs_type_id,startDate:startDate,endDate:endDate,dateType:dateType,tj_status:tj_status,pIndex:pIndex,pSize:pSize});
	}
	
	//井号下拉框根据工区查询
	function getWellList(){
	var wellObj = mini.get("well_id"); //井号
	var ycObj = mini.get("yc_id"); //油藏
	var siteObj = mini.get("site_id"); //工区
		site_id = siteObj.getValue();
		var url ="<%=basePath%>well/getWellMsg?orgId=" + site_id;
		wellObj.setUrl(url);
	}		
	
	//删除测试计划
	function remove(){
		var grid = mini.get("datagrid");
		var rowsObj=grid.getSelecteds();
		if(rowsObj.length==0){
			return;
		}
		if (confirm("确定删除选中记录？")){
		var seqs=[];
		for(var i=0;i<rowsObj.length;i++){
			seqs.push("'"+rowsObj[i].seq+"'");
		}
		$.ajax({
			type:"get",
			url:"<%=basePath%>oiljcjh/delOneOilJCJH?seq="+seqs,
			dataType:"text",
			success:function(data){
			 	var grid = mini.get("datagrid");
        		grid.reload(); 
			}
		})
		}
	}
	
	//批量导入
	function openUploadFileDialog(){
		mini.open({
            url:"<%=basePath%>monitor/monitorExportOil",
            title: "油井监测注数据批量导入", 
            width: 550, 
            height: 350,
            onload: function () {
            },
            ondestroy: function (action) {
            	setTimeout(function(){
            		grid = mini.get("datagrid");
            		grid.load();
            	}, 1000);
            
            	
            }
        });
	}
	
	//批量提交
	function submitData(){
		var grid = mini.get("datagrid");
		var rowsObj=grid.getSelecteds();
		if(rowsObj.length==0){
			return;
		}
		var seqs=[];
		for(var i=0;i<rowsObj.length;i++){
			seqs.push("'"+rowsObj[i].seq+"'");
		}
		$.ajax({
			type:"get",
			url:"<%=basePath%>oiljcjh/submitOilJCJH?seqlist="+seqs,
			dataType:"text",
			success:function(data){
			 	var grid = mini.get("datagrid");
        		grid.reload(); 
			}
		})
		
	}
	//批量撤销
	function revocationData(){
		var grid = mini.get("datagrid");
		var rowsObj=grid.getSelecteds();
		if(rowsObj.length==0){
			return;
		}
		var seqs=[];
		for(var i=0;i<rowsObj.length;i++){
			if(rowsObj[0].sh_status=="1"){
				mini.alert("包含已审核内容，无法撤销提交！");
				return ;
			}
			seqs.push("'"+rowsObj[i].seq+"'");
		}
		$.ajax({
			type:"get",
			url:"<%=basePath%>oiljcjh/unSubmitOilJCJH?seqlist="+seqs,
			dataType:"text",
			success:function(data){
			 	var grid = mini.get("datagrid");
        		grid.reload(); 
			}
		})
	}
	
	function ondrawcell(e){
		field=e.field;
		value=e.value;
		if(field=="tj_status"){
			if(value=="0"){
				e.cellHtml="未提交";
			}else{
				e.cellHtml="已提交";
			}
			
		}
		if(field=="sh_status"){
			if(value=="0"){
				e.cellHtml="未审核";
			}else{
				e.cellHtml="已审核";
			}
		}
		
		if(field=="level_demand"){
			if(value=="0"){
				e.cellHtml="正常";
			}else if(value=="1"){
				e.cellHtml="急需";
			}else if(value=="2"){
				e.cellHtml="可缓";
				
			}
		}
		
		
		
		
	}
	
	function exportData(){
		startDate = mini.get("startDate").getFormValue();
		endDate = mini.get("endDate").getFormValue();
		var dateType= mini.get("dateType").getValue(); //查询日期类型
		if(dateType=="stime"){
			if(startDate!=""){
			startDate=startDate.substring(0,7);  //措施计划时间设置为按月份查询
			}
			if(endDate!=""){
			endDate=endDate.substring(0,7);
			}
		}
		var siteId = mini.get("site_id").getValue(); //工区Id
		var wellId = mini.get("well_id").getValue(); //井号Id
		var cs_type_id = mini.get("cs_type_id").getValue();  //测试类型Id
		var tj_status = mini.get("tj_status").getValue();  //提交状态
		var grid = mini.get("datagrid");
		var pIndex = grid.pageIndex;
		var pSize = grid.pageSize;
		
	var data={site_id:siteId,well_id:wellId,cs_type_id:cs_type_id,startDate:startDate,endDate:endDate,dateType:dateType,tj_status:tj_status,pIndex:pIndex,pSize:pSize};
		 DownLoadExcel("<%=basePath%>oiljcjh/dwnloadQueryData?number="+Math.random(), data, false);
	}
	
	
	/**
	 * post 将数据提交到服务器 调用poi生成excel
	 */
	function DownLoadExcel(url, data, callback) {
	    //创建Form
	    var submitfrm = document.createElement("form");
	    submitfrm.action = url;
	    submitfrm.method = "post";
	    submitfrm.target = "_blank";
	    document.body.appendChild(submitfrm);
	    if (data) {
	        //创建隐藏域用来保存要上传的数据。
	        var input = mini.append(submitfrm, "<input type='hidden' name='data'>");
	        input.value = data;
	    }
	    //提交
	    submitfrm.submit();
	    //
	    setTimeout(function () {
	        submitfrm.parentNode.removeChild(submitfrm);
	        if (callback) callback();
	    }, 1000);
	}

	function onCloseClick(e) {
        var obj = e.sender;
        obj.setText("");
        obj.setValue("");
    }
	
	
</script>
</head>
<body style="width: 98%;height: 97%">
		<div class="mini-toolbar" id="toolbar1">
			<span>工区:</span>
           	<input id="site_id" class="mini-combobox" textField="name" valueField="id"  onvaluechanged="getWellList()" value="HBn4JTnVa2" 
				style="width:120px;" allowinput="true" shownullitem="false" emptyText="请选择..."  showClose="true" oncloseclick="onCloseClick"
				url="<%=basePath%>org/getAllOrg" />
				<span class="separator"></span> 
			<span>井号:</span>
			<input id="well_id" class="mini-combobox" textField="wellCommonName" valueField="wellId"  showClose="true" oncloseclick="onCloseClick"
				style="width:120px;" allowinput="true" shownullitem="false" emptyText="请选择..." />
			<span class="separator"></span> 
			<span>测试类型:</span>
			<input id="cs_type_id" class="mini-combobox" textField="jcjh_name" valueField="jcjh_id"  showClose="true" oncloseclick="onCloseClick" url="<%=basePath%>jcjhOption/getJCJHOptionOil"
				style="width:120px;" allowinput="true" shownullitem="false" emptyText="默认全选" />
			<span class="separator"></span> 
			<span>日期:</span>
			<input name="startDate" type="text" id="startDate" style="width:120px;" class="mini-datepicker" format="yyyy-MM-dd" allowinput="false" emptyText="请选择..."  showClose="true" oncloseclick="onCloseClick"/>
			-
			<input name="endDate" type="text" id="endDate" style="width:120px;" class="mini-datepicker" format="yyyy-MM-dd" allowinput="false" emptyText="请选择..."  showClose="true" oncloseclick="onCloseClick"/>
			<span class="separator"></span> 
			<input id="dateType" class="mini-combobox" textField="text" valueField="id"  emptyText="日期类型" style="width:120px;" allowinput="true" shownullitem="false"  showClose="true" value="tc_date" oncloseclick="onCloseClick"/>
			<span class="separator"></span> 
			<input id="tj_status" class="mini-combobox" textField="text" valueField="id" showClose="false"  style="width:65px;" allowinput="false" shownullitem="false" emptyText="状态"/>
	
		</div>
		<div class="mini-toolbar" id="toolbar1">
			<div align="right">
			<a class="mini-button" iconCls="icon-search" onclick="search()">查询</a>
			<span class="separator"></span> 
			<a class="mini-button" iconCls="icon-download" onclick="exportData()">导出数据</a>
			<span class="separator"></span> 
			<a class="mini-button" iconCls="icon-ok" onclick="submitData()">批量提交</a> 
			<span class="separator"></span> 
			<a class="mini-button" iconCls="icon-no" onclick="revocationData()">批量撤销</a> 
		        <a class="mini-button" iconCls="icon-add" onclick="add()">新增</a>
		        <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
		        <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
		        <a class="mini-button" iconCls="icon-upload" onclick="openUploadFileDialog()">批量导入</a>
			</div>
		</div>
		
		<div id="datagrid" class="mini-datagrid" style="width:100%;height:90%;" url="<%=basePath%>oiljcjh/getOilJCJHList" sizeList="[10,20,30,40,50,100,200,500,1000]" ondrawcell="ondrawcell" allowCellWrap="true" enableHotTrack="true"
			idField="id" allowResize="true" sortMode="client" multiSelect="true" showReloadButton="false" pageSize="20" showPager="true" allowUnselect="true" frozenStartColumn="0" frozenEndColumn="4">
			<div property="columns">
			 <div header="" headerAlign="center" headerStyle="font-weight: 900"><font size="5">测试计划井号</font>
			<div property="columns">
				<div type="checkcolumn" ></div>
				<div type="indexcolumn" width="50" headeralign="center" headerstyle="font-weight: bold;">序号</div>
          		<div field="site_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">工区</div>
          		<div field="yc_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">油藏</div>
          		<div field="well_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">井号</div>
<!--           		<div field="cs_explaim" headerAlign="center" align="center" headerStyle="font-weight: bold;">测试内容</div> -->
          		<div field="cs_type_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">测试类型</div>
          		<div field="stime" headerAlign="center" align="center" headerStyle="font-weight: bold;">措施计划月份</div>
          		<div field="well_section" headerAlign="center" align="center" headerStyle="font-weight: bold;">生产井段（m）</div>
          		<div field="thickness" headerAlign="center" align="center" headerStyle="font-weight: bold;">厚度（m）</div>
          		<div field="plies_num" headerAlign="center" align="center" headerStyle="font-weight: bold;">层数<br />（t）</div>
          		<div field="cs_purpose" headerAlign="center" align="center" headerStyle="font-weight: bold;">测试目的</div>
          		<div field="cs_claim" headerAlign="center" align="center" headerStyle="font-weight: bold;">测试要求</div>
          		<div field="last_success_date" headerAlign="center" align="center" headerStyle="font-weight: bold;">上次测成时间</div>
          		<div field="recently_fail_date" headerAlign="center" align="center" headerStyle="font-weight: bold;">(最近一次<br>)未测成时间</div>
          		<div field="recently_revise_mesg" headerAlign="center" align="center" headerStyle="font-weight: bold;">(最近一次<br>)整改情况</div>
          		<div field="level_demand" headerAlign="center" align="center" headerStyle="font-weight: bold;">需求程度</div>
          		<div field="tc_date" headerAlign="center" align="center" headerStyle="font-weight: bold;">提出日期</div>
          		<div field="fk_cd_date" headerAlign="center" align="center" headerStyle="font-weight: bold;">出单日期</div>
          		<div field="wcd_mesg" headerAlign="center" align="center" headerStyle="font-weight: bold;">未出单原因</div>
          		<div field="success_date" headerAlign="center" align="center" headerStyle="font-weight: bold;">测成日期</div>
          		<div field="fail_mesg" headerAlign="center" align="center" headerStyle="font-weight: bold;">未测成原因</div>
          		<div field="tj_status" headerAlign="center" align="center" headerStyle="font-weight: bold;">提交状态</div>
          		<div field="sh_status" headerAlign="center" align="center" headerStyle="font-weight: bold;">审核状态</div>
            </div>
            </div>
            </div>
		</div>
		
</body>
</html>