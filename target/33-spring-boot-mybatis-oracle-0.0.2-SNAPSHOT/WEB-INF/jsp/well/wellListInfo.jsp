<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../../Inc.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>单井基本信息</title>
</head>
<body>
	<div id="div1">
		<div class="mini-toolbar" id="toolbar1">
			<span>工区:</span>
           	<input id="site_id" class="mini-combobox" textField="name" valueField="id"  url="<%=basePath%>org/getAllOrg" oncloseclick="onCloseClick"
				style="width:150px;" allowinput="true" shownullitem="false" emptyText="请选择..." onvaluechanged="getSiteList" />
			<span>油藏:</span>
			<input id="yc_id" class="mini-combobox" textField="name" valueField="id" emptyText="请选择..." oncloseclick="onCloseClick"
				style="width:150px;"  allowinput="true" shownullitem="false" onvaluechanged="getWellList" />
			<span>井号:</span>
			<input id="well_id" class="mini-combobox" textField="wellCommonName" valueField="wellId" showClose="true"
				style="width:150px;" allowinput="true" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick"/>
			<a class="mini-button" iconCls="icon-search" onclick="search()">查询</a>
            <a class="mini-button" iconCls="icon-download" onclick="download()">导出</a>
		</div>
		<div id="datagrid" class="mini-datagrid" style="width:100%;height:100%;" url="<%=basePath%>" pageSize="20" showPager="true" sizeList="[10,20,30,40,50,100,200,500,1000]"
			idField="id" allowResize="true" sortMode="client" multiSelect="false" showReloadButton="false">
			<div property="columns">
				<div type="indexcolumn" width="50" headeralign="center" headerstyle="font-weight: bold;">序号</div>
          		<div field="site_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">工区</div>
          		<div field="yc_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">油藏</div>
          		<div field="well_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">井号</div>
          		<div field="well_type_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">井别</div>
          		<div field="" headerAlign="center" align="center" headerStyle="font-weight: bold;">井深</div>
          		<div field="mark" headerAlign="center" align="center" headerStyle="font-weight: bold;">备注</div>
            </div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var HeightEx = document.documentElement.clientHeight;
	document.getElementById("div1").style.height = HeightEx - 50 + "px";
	var title,site_id,well_id,yc_id;
	mini.parse();
	var grid = mini.get("datagrid");
	var siteObj = mini.get("site_id");   //工区
	var ycObj = mini.get("yc_id");   //油藏
	var wellObj = mini.get("well_id");   //井号
	search();
	function getYcList(){
		site_id =  siteObj.getValue();
		var url = "<%=basePath%>block/getBlock?orgId=" + site_id;
		ycObj.setUrl(url);
	}
	function getWellList(){
		site_id =  siteObj.getValue();
		yc_id = ycObj.getValue();
		var url ="<%=basePath%>well/getWellMsg?orgId=" + site_id + '&siteId=' + yc_id;
		wellObj.setUrl(url);
	}
	function search(){
		site_id =  siteObj.getValue();
		yc_id = ycObj.getValue();
		well_id = wellObj.getValue();
		grid.load({site_id:site_id,well_id:well_id,yc_id:yc_id});
	}
	function download(){
		site_id =  siteObj.getValue();
		yc_id = ycObj.getValue();
		well_id = wellObj.getValue();
		title = "单井基本信息";
		var url='<%=basePath%>?site_id='+site_id+'&yc_id='+yc_id+'&fileName='+title+'&well_id='+well_id;
		window.location.href =encodeURI(url);
	}
</script>
</html>