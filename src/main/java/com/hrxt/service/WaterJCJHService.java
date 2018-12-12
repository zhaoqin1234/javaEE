package com.hrxt.service;

import java.util.List;
import java.util.Map;

import com.hrxt.pojo.OilJCJH;
import com.hrxt.pojo.Page;
import com.hrxt.pojo.WaterJCJH;

/**
 *  油井  检测计划
 * @author ZhaoQin
 *
 */
public interface WaterJCJHService {
	
	
	
	public Map<String,Object> getWaterJCJHList(WaterJCJH waterJCJH,Page<List<WaterJCJH>> page);
	
	public List<WaterJCJH> getOilJCJHExcelMoBan(WaterJCJH waterJCJH);
	
	public Map<String,Object>  insertOrUpdate(WaterJCJH waterJCJH);
	
	public Map<String,Object> insertOrUpdateByExcel(WaterJCJH waterJCJH);

	public List<String> batchInsertOrUpdate(List<WaterJCJH> waterJCJH);
	
	

	public Map<String,Object> delOneWaterJCJH(String sep);
	

	public Map<String,Object> delListWaterJCJH(String sep);

	
	//  审核操作
	public String auditOneWaterJCJH(String sep);
	public String unAuditOneWaterJCJH(String sep);
	//	提交操作
	public String submitWaterJCJH(String sep);
	public String unSubmitWaterJCJH(String sep);
	
} 
