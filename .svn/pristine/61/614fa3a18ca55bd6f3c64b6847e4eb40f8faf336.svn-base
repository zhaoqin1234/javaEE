package com.hrxt.service;

import java.util.List;
import java.util.Map;

import com.hrxt.pojo.Page;
import com.hrxt.pojo.Xjscyxk;
import com.hrxt.pojo.XjscyxkReport;
import com.hrxt.pojo.XjscyxkType;

public interface XjscyxkService {
	
	
	// 获取数据
	public Map<String,Object> getXjscyxkList(Xjscyxk monthPZ,Page<List<Xjscyxk>> page);
	
	//新井生产运行科 报表数据
	public List<XjscyxkReport> getXjscyxkReport(String stime,String site_id);
	
	//获取新井生产运行科   油井类型  油井  水井   水井转注 的等
	public List<XjscyxkType> getXjscyxkType(String type_code,String type_name,String new_old_flag,String oil_water_flag);
	//插入一个新的类型
	public Map<String,Object> insertXjscyxkType(XjscyxkType xjscyxkType);
	//插入一个新的类型
	public Map<String,Object> updateXjscyxkType(XjscyxkType xjscyxkType);
	//删除
	public Map<String,Object> deleteXjscyxkType(XjscyxkType xjscyxkType);
	
	
	
	public Map<String,Object>  insertOrUpdate(Xjscyxk monthPZ);
	
	public Map<String,Object> insertOrUpdateByExcel(Xjscyxk xjscyxk);
	
	public List<String> batchInsertOrUpdate(List<Xjscyxk> monthPZlist);
	
	

	public Map<String,Object> delOneYPZBB(String sep);
	

	public Map<String,Object> delListYPZBB(String sep);

} 
