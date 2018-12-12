<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../../Inc.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>月配注数据审核</title>
</head>
<body>
	<div id="div1">
		<div class="mini-toolbar" id="toolbar1">
			<span>工区:</span>
			<input id="org_id" class="mini-combobox" textField="name" valueField="id" showClose="true" onvaluechanged="getSiteList"
				style="width:150px;" allowinput="true" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick"/>
			<span>油藏:</span>
			<input id="site_id" class="mini-combobox" textField="name" valueField="id" showClose="true"
				style="width:150px;" url="" allowinput="true" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick"/>
			<span>日期:</span>
			<input name="time" type="text" id="time" class="mini-monthpicker" format="yyyy-MM" allowinput="true"/>
			<span>状态:</span>
			<input id="state_id" class="mini-combobox" showClose="true" style="width:150px;" url="<%=basePath%>/data/audit.txt" 
				allowinput="true" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick"/>
			<a class="mini-button" iconCls="icon-search" onclick="search()">查询</a>
            <div style="float:right">
		        <a class="mini-button" iconCls="icon-ok" onclick="auditData()">批量审核</a>
		        <a class="mini-button" iconCls="icon-undo" onclick="unAuditData()">撤销审核</a>
		        <a class="mini-button" iconCls="icon-download" onclick="exportData()">导出</a>
        	</div>
		</div>
		<div id="datagrid" class="mini-datagrid" style="width:100%;height:98%;" url="<%=basePath%>monthPZ/getMonthPZList" pageSize="20" showPager="true" sizeList="[10,20,30,40,50,100,200,500,1000]"
			idField="id" allowResize="true" sortMode="client" multiSelect="true" showReloadButton="false"  allowCellSelect="true" allowUnselect="true">
			<div property="columns">
				<div type="checkcolumn" ></div>
				<div type="indexcolumn" width="50" headeralign="center" headerstyle="font-weight: bold;">序号</div>
				<div field="site_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">工区</div>
          		<div field="yc_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">油藏</div>
          		<div field="well_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">井号</div>
          		<div field="dzpz" headerAlign="center" align="center" headerStyle="font-weight: bold;">地质配注</div>
          		<div field="khpz" headerAlign="center" align="center" headerStyle="font-weight: bold;">考核配注</div>
          		<div field="last_kh" headerAlign="center" align="center" headerStyle="font-weight: bold;">上月考核配注</div>
          		<div header="差值" headeralign="center" headerstyle="font-weight: bold;">
          			<div property="columns">
	                    <div field="cz_sj_jh" headeralign="center" width="120" headerstyle="font-weight: bold;"
	                        align="center">本月地质<br />与本月考核</div>
	                    <div field="cz_sj_sj" headeralign="center" width="120" headerstyle="font-weight: bold;"
	                        align="center">本月考核<br />与上月考核</div>
	                   <!--  <div field="cz_jh_jh" headeralign="center" width="120" headerstyle="font-weight: bold;"
	                        align="center">本月计划<br />与上月计划</div> -->
	                </div>
          		</div>
          		<div field="stime" headerAlign="center" align="center" headerStyle="font-weight: bold;">数据日期</div>  
    <!-- 	<div field="tj_starts" headerAlign="center" align="center" headerStyle="font-weight: bold;">提交状态</div> -->
          		<div field="sh_starts" headerAlign="center" align="center" headerStyle="font-weight: bold;">审核状态</div>
          		<div field="mark" headerAlign="center" align="center" headerStyle="font-weight: bold;">备注</div>
            </div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var HeightEx = document.documentElement.clientHeight;
	document.getElementById("div1").style.height = HeightEx - 50 + "px";
	var time,title,org_id,site_id;
	time = getTodayMonth();
	mini.parse();
	mini.get("time").setValue(time);
	var grid = mini.get("datagrid");
	var orgObj = mini.get("org_id");   //工区
	var siteObj = mini.get("site_id"); //断块
	var stateObj = mini.get("state_id");
	grid.frozenColumns(0,4);	
	getOrgList();
	//getSiteList();
	search();
	function getOrgList(){
		var url = "<%=basePath%>org/getAllOrg";
		orgObj.setUrl(url);
	}
	function getSiteList(){
		org_id =  orgObj.getValue();
		var url = "<%=basePath%>block/getBlock?orgId=" + org_id;
		siteObj.setUrl(url);
	}
	function search(){
		org_id = orgObj.getValue();
		site_id = siteObj.getValue();
		time = mini.get("time").getFormValue();
		if(time == ""){
			mini.alert("日期不能为空");
			return;
		}
		state_id = stateObj.getValue();
		var pIndex = grid.pageIndex;
		var pSize = grid.pageSize;
		grid.load({stime:time,sh_starts:state_id,site_id:org_id,yc_id:site_id,tj_starts:"1"});
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
	                url: "<%=basePath%>monthPZ/auditMonthP",
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
	    		mini.alert("数据已审核");
	    	}
		}else{
			mini.alert("请选中一条记录");
		}
	}
	//撤销审核
	function unAuditData(){
		var rows = grid.getSelecteds();
		if(rows.length > 0){
			var ids = [];
	    	$.each(rows,function(index,i){
	    		if(i.sh_starts == "已审核"){
	    			ids.push("\'" + i.seq + "\'");
	    		}
	        });
	    	var seqlist = ids.join(",");
	    	if(seqlist.length > 0){
	    		$.ajax({
	                url: "<%=basePath%>monthPZ/unAuditMonthP",
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
	    		mini.alert("请选择已审核的数据");
	    	}
		}else{
			mini.alert("请选中一条记录");
		}
	}
	function exportData(){
		time = mini.get("time").getFormValue();
		if(time == ""){
			mini.alert("日期不能为空");
			return;
		}
		org_id = orgObj.getValue();
		site_id = siteObj.getValue();
		state_id = stateObj.getValue();
		title = time.replace("-","年") + "月配注数据审核";
		var url='<%=basePath%>monthPZ/dwnloadQueryData?stime='+time+'&sh_starts='+state_id+'&fileName='+title+'&orgId='+org_id+'&siteId='+site_id+'&tj_starts=1';
		window.location.href =encodeURI(url);
	}
	function mergeCell(e){
		var marges=[];
		var rowIndex=0;
		var columnIndex=0;
		var rowSpan=1;
		var colSpan=1;
		var firstValue="";
		var fields=["1","2"];
		var currentValue="";
		if(e.data.length>1){
			for(var k=0;k<2;k++){
				firstValue=e.data[0].field[k];
				columnIndex=k;
				rowIndex=0;
				rowSpan=1;
				for(var i=1;i<e.data.length;i++){
					currentValue=e.data[i].field[k];
					if(firstValue==currentValue){
						rowSpan++;
					}else{
						firstValue=e.data[i].field[k];
						if(rowSpan > 1){
							marges.push({rowIndex:rowIndex,columnIndex:columnIndex,rowSpan:rowSpan,colSpan:colSpan});
						}else if(i==e.data.length-1){
							if( currentValue.indexOf("合计")>0 && k==0){
								marges.push({rowIndex:rowIndex,columnIndex:columnIndex,rowSpan:rowSpan,colSpan:4});
							}
							if(currentValue.indexOf("小计")>0 && k==1){
								marges.push({rowIndex:rowIndex,columnIndex:columnIndex,rowSpan:rowSpan,colSpan:3});
							}
						}
						rowSpan=1;
						rowIndex=i;
					}
				}
			}
		}
		grid.mergeCells(marges);
	}
</script>
</html>