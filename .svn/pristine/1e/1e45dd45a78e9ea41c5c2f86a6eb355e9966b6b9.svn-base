<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Hello MiniUI!</title>
    <!--jQuery js-->
    <script src="/resources/js/jquery-1.7.2.js" type="text/javascript"></script>
    <!--MiniUI-->
    <link href="/resources/miniui/themes/default/miniui.css" rel="stylesheet" type="text/css" />        
    <script src="/resources/miniui/miniui.js" type="text/javascript"></script>
</head>
<body>  <!-- style="width:300px;height:200px;" -->
<!--撑满页面-->
    <div class="mini-fit" style="width:100px;height:100px;">
        
        <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" 
            url="../data/AjaxService.aspx?method=SearchEmployees"  idField="id"
            sizeList="[5,10,20,50]" pageSize="10"
        >
            <div property="columns">
                <div type="indexcolumn" ></div>
                <div field="loginname" width="120" headerAlign="center" allowSort="true">员工帐号</div>    
                <div field="name" width="120" headerAlign="center" allowSort="true">姓名</div>                            
                <div field="gender" width="100" renderer="onGenderRenderer" align="center" headerAlign="center">性别</div>
                <div field="salary" width="100" allowSort="true">薪资</div>                                    
                <div field="age" width="100" allowSort="true">年龄</div>
                <div field="createtime" width="100" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="true">创建日期</div>                
            </div>
        </div>

    </div>
</body>
</html>