//清除值
function onCloseClick(e){
	var obj = e.sender;
	obj.setText("");
	obj.setValue("");
}
//获取当前日期 年月日****-**-**
function getTodayDate(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var date2=date.getDate();
	month = month < 10? "0" + month: month;
	date2 = month < 10? "0" + date2: date2;
	return year + "-" + month+"-"+date2;
}
//获取当前日期 年月****-**
function getTodayMonth(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	month = month < 10? "0" + month: month;
	return year + "-" + month;
}
//获取上月日期 年月****-**
function getTodayByUpMonth(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth();
	month = month < 10? "0" + month: month;
	return year + "-" + month;
}
//采用正则表达式获取地址栏参数
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var url = decodeURI(window.location.search);
	var r = url.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

//获取年月 ****-** 
function getMonth(date){
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	month = month < 10? "0" + month: month;
	return year + "-" + month;
}