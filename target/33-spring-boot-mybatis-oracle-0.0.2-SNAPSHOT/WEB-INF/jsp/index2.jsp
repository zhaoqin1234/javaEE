<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/Inc.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>

<html>
<head>    
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title></title>
    <script src="scripts/boot.js" type="text/javascript"></script>
    <link href="res/third-party/scrollbar/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
    <script src="res/third-party/scrollbar/jquery.mCustomScrollbar.concat.min.js" type="text/javascript"></script>
    <link href="res/menu/menu.css" rel="stylesheet" type="text/css" />
    <script src="res/menu/menu.js" type="text/javascript"></script>
    <script src="res/menutip.js" type="text/javascript"></script>
    <link href="res/tabs.css" rel="stylesheet" type="text/css" />
    <link href="res/frame.css" rel="stylesheet" type="text/css" />
    <link href="res/index.css" rel="stylesheet" type="text/css" />
    
</head>
<body>
    
<div class="navbar">
    <div class="navbar-header">
        <div class="navbar-brand">地质所</div>
        <div class="navbar-brand navbar-brand-compact">DZ</div>
    </div>
    <ul class="nav navbar-nav">
        <li><a id="toggle"><span class="fa fa-bars" ></span></a></li>
        <li><h2>报表管理系统</h2></li>
        <!--  
        <li class="icontop"><a href="#"><i class="fa fa-hand-pointer-o"></i><span >系统演示</span></a></li>
        <li class="icontop"><a href="#"><i class="fa fa-puzzle-piece"></i><span >开发文档</span></a></li>
        <li class="icontop"><a href="#"><i class="fa fa-sort-amount-asc"></i><span >人力资源</span></a></li>
        <li class="icontop"><a href="#"><i class="fa  fa-cog"></i><span >系统设置</span></a></li>
        -->
    </ul>
    <ul class="nav navbar-nav navbar-right">
    	<!--  
        <li ><a href="#"><i class="fa fa-paper-plane"></i> 代办事项</a></li>
        <li><a href="#"><i class="fa fa-pencil-square-o"></i> 修改密码</a></li>
        -->
        <li class="dropdown">
            <a class="dropdown-toggle userinfo">
                <img class="user-img" src="res/images/user.jpg" />个人资料<i class="fa fa-angle-down"></i>
            </a>
            <ul class="dropdown-menu pull-right">
                <li ><a href="javaScript:openWindow()"><i class="fa fa-eye "></i> 用户信息</a></li>
                <li><a href="javaScript:tuichudenglu()"><i class="fa fa-user" ></i> 退出登录</a></li>
            </ul>
        </li>
    </ul>
</div>

<div class="container">
    <div class="sidebar">
        <div class="sidebar-toggle"><i class = "fa fa-fw fa-dedent" ></i></div>
        <div id="mainMenu"></div>
    </div>

    <div class="main">
        <div id="mainTabs" class="mini-tabs main-tabs" activeIndex="0" style="height:100%;" plain="false"
             buttons="#tabsButtons" arrowPosition="side" >
            <div name="index" iconCls="fa-android" title="控制台">
                MiniUI导航框架
            </div>
        </div>
        <div id="tabsButtons">
            <a class="tabsBtn"><i class="fa fa-home"></i></a>
            <a class="tabsBtn"><i class="fa fa-refresh"></i></a>
            <a class="tabsBtn"><i class="fa fa-remove"></i></a>
            <a class="tabsBtn"><i class="fa fa-arrows-alt"></i></a>
        </div>   
    </div>
   
</div>


</body>
</html>
<script>

    function activeTab(item) {
        var tabs = mini.get("mainTabs");
        var tab = tabs.getTab(item.id);
        if (!tab) {
            tab = { name: item.id, title: item.text, url: item.url, iconCls: item.iconCls, showCloseButton: true };
            tab = tabs.addTab(tab);
        }
        tabs.activeTab(tab);
    }

    $(function () {
        //menu
        var menu = new Menu("#mainMenu", {
            itemclick: function (item) {
                if (!item.children) {
                    activeTab(item);
                }
            }
        });

        $(".sidebar").mCustomScrollbar({ autoHideScrollbar: true });

        new MenuTip(menu);
		var menuurl = "<%=basePath%>menu/getMenuStructByUser?id=${ id }"
        $.ajax({
            url:menuurl,// "data/menu2.txt",
            success: function (text) {
                var data = mini.decode(text);
                menu.loadData(data);
            }
        })

        //toggle
        $("#toggle, .sidebar-toggle").click(function () {
            $('body').toggleClass('compact');
            mini.layout();
        });

        //dropdown
        $(".dropdown-toggle").click(function (event) {
            $(this).parent().addClass("open");
            return false;
        });

        $(document).click(function (event) {
            $(".dropdown").removeClass("open");
        });
    });

    function tuichudenglu(){
    
    	window.location.href = "<%=basePath%>/login.html";
    }
    
    function openWindow(){
		mini.open({
	        url:"<%=basePath%>user/userEditPwd",
	        title: "编辑用户信息", 
	        width: 550, 
	        height: 350,
	        onload: function () {
	            var iframe = this.getIFrameEl();
	            var data ="${ id }";
	          	$.ajax({
	            	type:"get",
	            	url:"<%=basePath%>user/getUserById",
	            	data:{id:data},
	            	dataType:"json",
	            	success:function (data){
	            iframe.contentWindow.SetData(data);
	            	}
	            	});
	        },
	        ondestroy: function (action) {
	        	search();
	        }
		});
    }
    
    	var user="";
    function getUserById(userId){
    	$.ajax({
    	type:"get",
    	url:"<%=basePath%>user/getUserById",
    	data:{id:userId},
    	dataType:"text",
    	success:function (data){
    		user=data;
    	return  user;
    	}
    	});
    }
    
</script>