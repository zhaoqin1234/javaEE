<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../../Inc.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>新井生产运行科审核</title>
</head>
<body style="width: 98%;height: 97%">
	<div id="div1" style="width: 100%;height: 100%">
		<div class="mini-toolbar" id="toolbar1">
			<span>工区:</span>
           	<input id="site_id" class="mini-combobox" textField="name" valueField="id" showClose="true" onvaluechanged="getWellList" value="HBn4JTnVa2" 
				style="width:150px;" allowinput="true" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick"/>
			<span>井号:</span>
			<input id="well_id" class="mini-combobox" textField="wellCommonName" valueField="wellId" showClose="true"
				style="width:150px;" allowinput="true" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick"/>
			<span>井类型:</span>
			<input id="well_type" class="mini-combobox"  showClose="true"  url="<%=basePath%>xjscyxk/getXjscyxkTypeData" textField="type_name" valueField="type_code" 
				style="width:150px;" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick"/>
			<span>日期:</span>
			<input name="time" type="text" id="time" class="mini-monthpicker" format="yyyy-MM" allowinput="true"/>
			<span>状态:</span>
			<input id="state_id" class="mini-combobox" showClose="true" url="<%=basePath%>/data/audit.txt" 
				style="width:150px;" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick"/>
			<a class="mini-button" iconCls="icon-search" onclick="search()">查询</a>
			<div style="float:right">
		        <a class="mini-button" iconCls="icon-ok" onclick="auditData()">批量审核</a>
		        <a class="mini-button" iconCls="icon-download" onclick="exportData()">导出</a>
        	</div>
		</div>
		<div id="datagrid" class="mini-datagrid" style="width:100%;height:97%;" url="<%=basePath%>xjscyxk/getXjscyxkList" pageSize="20" showPager="true" sizeList="[10,20,30,40,50,100,200,500,1000]"
			idField="id" allowResize="true" sortMode="client" multiSelect="true" showReloadButton="false" allowUnselect="true" >
			<div property="columns">
				<div type="checkcolumn" ></div>
				<div type="indexcolumn" width="50" headeralign="center" headerstyle="font-weight: bold;">序号</div>
          		<div field="site_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">工区</div>
          		<div field="yc_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">油藏</div>
          		<div field="well_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">井号</div>
          		<div field="well_type_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">井别</div>
          		<div field="prod_plan_day" headerAlign="center" align="center" headerStyle="font-weight: bold;">预计日产油<br />（t）</div>
          		<div field="condition" headerAlign="center" align="center" headerStyle="font-weight: bold;">目前工况</div>
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
	//document.getElementById("div1").style.height = HeightEx - 50 + "px";
	var time,title,site_id,well_id,state_id,well_type;
	time = getTodayMonth();
	mini.parse();
	mini.get("time").setValue(time);
	var grid = mini.get("datagrid");
	var siteObj = mini.get("site_id");   //工区
	var wellObj = mini.get("well_id");   //井号
	grid.frozenColumns(0,4);
	getSiteList();
	getWellList();
	search();
	function getSiteList(){
		var url = "<%=basePath%>org/getAllOrg";
		siteObj.setUrl(url);
	}
	function getWellList(){
		site_id = siteObj.getValue();
		var url ="<%=basePath%>well/getWellMsg?orgId=" + site_id;
		wellObj.setUrl(url);
	}
	function search(){
		site_id = siteObj.getValue();
		well_id = wellObj.getValue();
		state_id = mini.get("state_id").getValue();
		well_type = mini.get("well_type").getValue();
		time = mini.get("time").getFormValue();
		if(time == ""){
			mini.alert("日期不能为空");
			return;
		}
		grid.load({site_id:site_id,well_id:well_id,well_type:well_type,stime:time,sh_starts:state_id,tj_starts:"1"});
	}
	function auditData(){
		var rows = grid.getSelecteds();
		if(rows.length > 0){
			var ids = [];
	    	$.each(rows,function(index,i){
	    		if(i.sh_starts == "未审核"){
	    			ids.push("\'" + i.seq + "\'");
	    		}
	        });
	    	var seqlist = ids.join(",");
	    	if(seqlist.length > 0){
	    		$.ajax({
	                url: "<%=basePath%>monthPZ/",
	                dataType:'text',
	                data: {
	                	seqlist: seqlist
	                },
	                success: function (text) {
	              		search();
	                }
	            });
	    	}else{
	    		mini.alert("请选择未审核的数据");
	    		return;
	    	}
		}else{
			mini.alert("请选中一条记录");
		}
	}
	function exportData(){
		site_id = siteObj.getValue();
		well_id = wellObj.getValue();
		state_id = mini.get("state_id").getValue();
		well_type = mini.get("well_type").getValue();
		time = mini.get("time").getFormValue();
		if(time == ""){
			mini.alert("日期不能为空");
			return;
		}
		title = time.replace("-","年") + "月新井生产运行科";
		var url='<%=basePath%>xjscyxk/dwnloadXjscyxkTmp?stime='+time+'&sh_starts='+state_id+'&fileName='+title+
				'&well_id='+well_id+'&well_type='+well_type+'&site_id='+site_id+'&tj_starts=1';
		window.location.href =encodeURI(url);
	}
</script>
</html>