<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../../Inc.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>井类型维护</title>
</head>
<body style="width: 100%;height: 100%">
	<div id="div1" style="width: 100%;height: 100%">
		<div class="mini-toolbar" id="toolbar1">
			<span>油/水:</span>
				<input id="waterOilWell"  class="mini-combobox"  showClose="true" url="<%=basePath%>data/xjscyxkNewOldType.txt" textField="text" valueField="id"  required="true"
							style="width:150px;" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick"/>
			<span>新/老:</span>
			<input id="newOldWell"  class="mini-combobox"  showClose="true" url="<%=basePath%>data/xjscyxkOilWaterType.txt" textField="text" valueField="id"  required="true"
							style="width:150px;" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick"/>
			<a class="mini-button" iconCls="icon-search" onclick="search()">查询</a>
			<a class="mini-button" iconCls="icon-add" onclick="add()">添加</a>
			<a class="mini-button" iconCls="icon-edit" onclick="edit()">修改</a>
			<a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
		</div>
		<div id="datagrid" class="mini-datagrid" style="width:100%;height:100%;" url="<%=basePath%>xjscyxk/getXjscyxkTypeData"  showPager="false"  ondrawcell="ondrawcell"
			idField="id" allowResize="true" sortMode="client" multiSelect="true" showReloadButton="false"  allowCellSelect="true" allowUnselect="true">
			<div property="columns">
				<div type="checkcolumn" ></div>
				<div type="indexcolumn" width="50" headeralign="center" headerstyle="font-weight: bold;">序号</div>
				<div field="new_old_flag" headerAlign="center" align="center" headerStyle="font-weight: bold;">新井/老井</div>
				<div field="oil_water_flag" headerAlign="center" align="center" headerStyle="font-weight: bold;">油井/水井</div>
          		<div field="type_name" headerAlign="center" align="center" headerStyle="font-weight: bold;">类型名称</div>
            </div>
		</div>
	</div>
	<!-- 弹出框开始 -->
<div id="win1" class="mini-window" title="井类型维护" style="width:300px;height:250px;" 
    showMaxButton="true" 
    showToolbar="true" showFooter="true" showModal="true" allowResize="true" allowDrag="true" >
    <div align="center" style="width: 100%;height: 100%">
    <form id="form1" method="post">
     <fieldset style="border:solid 1px #aaa;padding:3px;">
      <legend >井类型维护</legend>
	    	<table>
			<tr height="35px">
		
				<td align="center">类型名称:</td>
				<td>
				<input id="id" name="id" class="mini-hidden" />
					<input id="typeName" name="type_name" class="mini-textbox" style="width:150px;" shownullitem="false" required="true" />
				</td>
			</tr>
			<tr height="35px">
				<td align="center">新老井:</td>
				<td>
					<input id="newOldId" name="new_old_flag" class="mini-combobox"  showClose="true" url="<%=basePath%>data/xjscyxkNewOldType.txt" textField="text" valueField="id"  required="true"
							style="width:150px;" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick"/>
				</td>
			
			</tr>
			<tr>
				<td align="center">油水井:</td>
				<td>
					<input id="oilWaterId" name="oil_water_flag" class="mini-combobox"  showClose="true" url="<%=basePath%>data/xjscyxkOilWaterType.txt" textField="text" valueField="id"  required="true"
							style="width:150px;" shownullitem="false" emptyText="请选择..." oncloseclick="onCloseClick"/>
				</td>
			</tr>
			<tr height="75px">
				<td colspan="2" align="center">	
				<a class="mini-button" onclick="saveOrUpdate" style="width:60px;margin-right:20px;">提交</a>       
				<a class="mini-button" onclick="onCancel" style="width:60px;">返回</a> 
	    		 </td>
			</tr>
		</table>
     </fieldset>
    </form>
    </div>

	</div>
</body>
<script type="text/javascript">
	mini.parse();
	var grid = mini.get("datagrid");
		grid.load();
		var form = new mini.Form("#form1"); 
	var win = mini.get("win1");
	
	function search(){
		var waterOilWell=mini.get("waterOilWell").getValue();
		var newOldWell=mini.get("newOldWell").getValue();
		grid.load({new_old_flag:waterOilWell,oil_water_flag:newOldWell});
	}
	
	
	function remove(){
		if (confirm("确定删除选中记录？")){
		var rows = grid.getSelecteds();
		var ids="";
		for(var i=0;i<rows.length;i++){
			if(i!=rows.length-1){
			ids+=rows[i].id+",";
			}else{
				ids+=rows[i].id;
			}
		}
		
		$.ajax({
			type:"get",
			url:'<%=basePath%>xjscyxk/deleteXjscyxkType',
			data:{id:ids},
			dataType:"json",
			success:function(data){
				if(data.code==1){
					mini.alert(data.message);
					grid.reload();
					win.hide();
				}else{
					mini.alert(data.message);
				}
			}
		});
		}
	
	}
	function ondrawcell(e){
		field=e.field;
		value=e.value;
		if(field=="new_old_flag"){
			if(value=="0"){
				e.cellHtml="新井";
			}else if(value=="1"){
				e.cellHtml="老井";
			}
		}
		if(field=="oil_water_flag"){
			if(value=="0"){
				e.cellHtml="油井";
			}else if(value=="1"){
				e.cellHtml="水井";
			}
		}
	}
	
	
	function add(){
		win.show();
		form.clear();
	};
	//编辑数据
	function edit(){
		form.clear();
		rows = grid.getSelecteds();
		form.setData(rows[0]);
		win.show();
		
	}
	
	//保存数据
	function saveOrUpdate(){
		form.validate();
		if (form.isValid() == false){
			return;
		}
	var data=form.getData();
	if(data.id==""||data.id==null||data.id==undefined){
	 url="<%=basePath%>xjscyxk/insertXjscyxkType";
	}else{
	 url="<%=basePath%>xjscyxk/updateXjscyxkType";
	}
	$.ajax({
		type:"post",
		url:url,
		data:data,
		dataType:"json",
		success:function (data){
			if(data.code==1){
				mini.alert(data.message);
				grid.reload();
				win.hide();
			}else{
				mini.alert(data.message);
			}
		}
	})
	}
	
</script>
</html>