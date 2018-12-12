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
<body style="width: 98%;height: 96%">
	<div id="div1" style="width: 100%;height: 100%">
		<div class="mini-toolbar" id="toolbar1">
			<span>工区:</span>
			<input id="site_id" class="mini-combobox" textField="name" valueField="id" showClose="true" value="HBn4JTnVa2"   url="<%=basePath%>org/getAllOrg"
				style="width:150px;" allowinput="true" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick"/>
			<span>措施:</span>
			<input id="well_step_id" class="mini-combobox" textField="" valueField="" showClose="true"
				style="width:120px;" url="" allowinput="true" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick"/>
			<span>井别:</span>
			<input id="well_type" class="mini-combobox"  showClose="true" url="<%=basePath%>/data/wellType.txt" 
				style="width:100px;" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick"/>
			<span>日期:</span>
			<input style="width:120px;" name="time" type="text" id="time" class="mini-monthpicker" format="yyyy-MM" allowinput="true"/>
			<span>状态:</span>
			<input id="state_id" class="mini-combobox" showClose="true" url="<%=basePath%>/data/state.txt" 
				style="width:100px;" allowinput="true" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick"/>
			<input type="radio" value="1" name="well_purpose" checked="checked" />重点措施
			<input type="radio" value="0" name="well_purpose" />常规措施
			<a style="margin-left:10px;"class="mini-button" iconCls="icon-search" onclick="search()">查询</a>
			<a class="mini-button" iconCls="icon-download" onclick="exportData()">导出</a>
			<br />
			<a style="margin-left:0px;margin-top:8px;" class="mini-button" iconCls="icon-ok" onclick="submitData()">提交</a>
			<a style="margin-left:0px;margin-top:8px;" class="mini-button" iconCls="icon-ok" onclick="unSubmitData()">撤销提交</a>
	        <a style="margin-left:10px;margin-top:8px;" class="mini-button" iconCls="icon-add" onclick="add()">新增</a>
	        <a style="margin-left:10px;margin-top:8px;" class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
	        <a style="margin-left:10px;margin-top:8px;" class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
	        <a style="margin-left:10px;margin-top:8px;" class="mini-button" iconCls="icon-upload" onclick="openUploadFileDialog()">导入</a>
			<!-- <div style="float:right;margin-top:8px;margin-bottom:8px;" class="mini-toolbar" >
				
				
        	</div> -->
		</div>
		<div id="datagrid" class="mini-datagrid" style="width:100%;height:96%;" url="<%=basePath%>csjh/getCsjhList" pageSize="20" showPager="true" sizeList="[10,20,30,40,50,100,200,500,1000]"
			idField="id" allowResize="true" showPager="false" sortMode="client" multiSelect="true" allowRowSelect="true" allowUnselect="true" showReloadButton="false">
			<div property="columns">
				<div type="checkcolumn" ></div>
				<div type="indexcolumn" width="50" headeralign="center" headerstyle="font-weight: bold;">序号</div>
				<div field="well_type_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">井别</div>
				<div field="well_step_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">措施</div>
          		<div field="site_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">工区</div>
          		<div field="well_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">井号</div>
          		<div field="prod_daily" headerAlign="center" align="center" headerStyle="font-weight: bold;">日增油</div>
          		<div field="tj_status" headerAlign="center" align="center" headerStyle="font-weight: bold;">提交状态</div>
          		<div field="sh_status" headerAlign="center" align="center" headerStyle="font-weight: bold;">审核状态</div>
          		<div field="stime" headerAlign="center" align="center" headerStyle="font-weight: bold;">数据日期</div>
          		<div field="mark" headerAlign="center" align="center" headerStyle="font-weight: bold;">备注</div>
            </div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var HeightEx = document.documentElement.clientHeight;
	//document.getElementById("div1").style.height = HeightEx - 70 + "px";
	var time,title,site_id,well_step_id,state_id,well_type,data_type;
	time = getTodayMonth();
	mini.parse();
	var grid = mini.get("datagrid");
	var siteObj = mini.get("site_id");   //工区
	var stepObj = mini.get("well_step_id");   //措施
	var typeObj = mini.get("well_type");  //井别
	var stateObj = mini.get("state_id");
	var timeObj = mini.get("time");  //日期
	timeObj.setValue(time);
	//search();
	function search(){
		site_id = siteObj.getValue();
		well_step_id = stepObj.getValue();
		well_type = typeObj.getValue();
		time = timeObj.getFormValue();
		state_id = stateObj.getValue();
		data_type = $("input[type='radio']:checked").val();
		if(time == ""){
			mini.alert("请选择时间");
			return;
		}
		grid.load({site_id:site_id,well_step_id:well_step_id,well_type:well_type,
			stime:time,sh_status:state_id,step_type:data_type});	//	,tj_status:"1"
	}
	//新增
	function add(){
		data_type = $("input[type='radio']:checked").val();
		mini.open({
            url: "<%=basePath%>measure/keyMeasuresPlanEdit",
            title: "新增措施计划数据", 
            width: 550, 
            height: 380,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = {action: "new",step_type: data_type};
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
          		search();
            }
        });
	}
	//修改
	function edit(){
		var rows = grid.getSelecteds();
		data_type = $("input[type='radio']:checked").val();
		if(rows.length > 0){
			console.log(rows);
			if(rows[0].tj_status == "未提交"){
				rows[0].step_type = data_type;
				mini.open({
	                url:"<%=basePath%>measure/keyMeasuresPlanEdit",
	                title: "编辑措施计划数据", 
	                width: 550, 
	                height: 380,
	                onload: function () {
	                    var iframe = this.getIFrameEl();
	                    var data = { action: "edit", row: rows[0]};
	                    iframe.contentWindow.SetData(data);
	                },
	                ondestroy: function (action) {
	                	search();
	                }
	            });
			}else{
				alert("已提交的数据不可修改");
			}
		}else{
			alert("请选中一条记录");
		}
	}
	//删除
	function remove(){
		var rows = grid.getSelecteds();
		if (rows.length > 0){
			var ids = [];
	    	$.each(rows,function(index,i){
	    		if(i.tj_status == "未提交"){
	    			ids.push("\'" + i.seq + "\'");
	    		}
	        });
	    	var seqlist = ids.join(",");
	    	if(seqlist.length > 0){
	    		if (confirm("确定删除选中记录？")){
					$.ajax({
	                    url: "<%=basePath%>csjh/delCsjhList",
	                    dataType:'text',
	                    async: false,
	                    data: {
	                    	seqlist: seqlist
	                    },
	                    success: function (text) {
	                  		search();
	                    }
	                });
				}
	    	}else{
	    		alert("已提交的数据不可删除");
	    	}
		}else{
			mini.alert("请选中一条记录");
		}
	}
	//导出
	function exportData(){
		site_id = siteObj.getValue();
		well_step_id = stepObj.getValue();
		well_type = typeObj.getValue();
		time = timeObj.getFormValue();
		state_id = stateObj.getValue();
		data_type = $("input[type='radio']:checked").val();
		if(time == ""){
			mini.alert("请选择时间");
			return;
		}
		title = time.replace("-","年") + "月";
		if(data_type == "0"){
			title += "常规措施计划表";
		}else{
			title += "重点措施计划表";
		}
		var url='<%=basePath%>?stime='+time+'&tj_status='+state_id+'&fileName='+title+'&well_step_id='+well_step_id
			+'&well_type='+well_type+'&site_id='+site_id+'&step_type='+data_type;
		window.location.href =encodeURI(url);
	}
	//批量提交
	function submitData(){
		var rows = grid.getSelecteds();
		if(rows.length > 0){
			var ids = [];
			$.each(rows,function(index,i){
				if(i.tj_status == "未提交"){
					ids.push("\'" + i.seq + "\'");
				}
	        });
			var seqlist = ids.join(",");
			if(seqlist.length > 0){
				$.ajax({
	                url: "<%=basePath%>csjh/submitCsjh",
	                contentType: 'application/json',
	                dataType:'text',
	                async: false,
	                data: {
	                	seqlist: seqlist
	                },
	                success: function (text) {
	              		search();
	                }
	            });
			}else{
				mini.alert("请选择未提交的数据");
			}			
		}else{
			mini.alert("请至少选择一条数据");
		}
	}
	//撤销提交
	function unSubmitData(){
		var rows = grid.getSelecteds();
		if(rows.length > 0){
			var ids = [];
			$.each(rows,function(index,i){
				if(i.tj_status == "已提交"){
					ids.push("\'" + i.seq + "\'");
				}
				if(i.sh_status == "已审核"){
					alert("包含已经审核数据，无法撤销");
					return false;
				}
	        });
			var seqlist = ids.join(",");
			if(seqlist.length > 0){
				$.ajax({
	                url: "<%=basePath%>csjh/submitCsjh",
	                contentType: 'application/json',
	                dataType:'text',
	                async: false,
	                data: {
	                	seqlist: seqlist
	                },
	                success: function (text) {
	              		search();
	                }
	            });
			}else{
				mini.alert("请选择未提交的数据");
			}			
		}else{
			mini.alert("请至少选择一条数据");
		}
	}
	//批量导入
	function openUploadFileDialog(){
		data_type = $("input[type='radio']:checked").val();
		mini.open({
            url:"<%=basePath%>measure/monthlyConsolidatedExport",
            title: "月配注数据管理批量导入", 
            width: 550, 
            height: 350,
            onload: function () {
            	var iframe = this.getIFrameEl();
                var data = {step_type: data_type};
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
            	search();
            }
        });
	}
</script>
</html>