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
<body>
	
	<input id="helloBtn" class="mini-button" text="Hello" onclick="onHelloClick"/>
    <script type="text/javascript">
        function onHelloClick(e) {
            var button = e.sender;
            mini.alert("Hello MiniUI!12");
        }
    </script>
    <br>
   界面点击
    <a href="/waterInjection/monthleDataAudit" >monthleDataAudit</a><br>
    <a href="/waterInjection/monthlyConsolidatedQuery" >monthlyConsolidatedQuery</a><br>
    <a href="/waterInjection/monthlyDataManagement" >monthlyDataManagement</a><br>
    <a href="/waterInjection/monthlyDataManagementEdit" >monthlyDataManagementEdit</a><br>
    <hr>
     <a href="/measure/keyMeasuresPlan" >keyMeasuresPlan</a><br>
     <a href="/measure/keyMeasuresPlanAudit" >keyMeasuresPlanAudit</a><br>
     <a href="/measure/keyMeasuresPlanEdit" >keyMeasuresPlanEdit</a><br>
     <a href="/measure/keyMeasuresPlanQuery" >keyMeasuresPlanQuery</a><br>
     <a href="/measure/newWellProduction" >newWellProduction</a><br>
     <a href="/measure/newWellProductionAudit" >newWellProductionAudit</a><br>
     <a href="/measure/newWellProductionEdit" >newWellProductionEdit</a><br>
     <a href="/measure/newWellProductionQuery" >newWellProductionQuery</a><br>
     <hr>方法测试
     <a href="/monthPZ/getMonthPZList" >getMonthPZList</a><br>
     <a href="/well/getWellMsg" >getWellMsg</a><br>
     <a href="/well/dwnloadTmp" >dwnloadTmp</a><br>
     <a href="/measure/newWellProductionQuery" >newWellProductionQuery</a><br>
     
     
    
你好，我是hello
</body>
</html>