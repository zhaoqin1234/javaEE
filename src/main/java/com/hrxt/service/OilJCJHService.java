package com.hrxt.service;

import java.util.List;
import java.util.Map;

import com.hrxt.pojo.OilJCJH;
import com.hrxt.pojo.Page;

/**
 *  油井  检测计划
 * @author ZhaoQin
 *
 */
public interface OilJCJHService {
	
	
	
	public Map<String,Object> getOilJCJHList(OilJCJH oilJCJH,Page<List<OilJCJH>> page);
	
	public List<OilJCJH> getOilJCJHExcelMoBan(OilJCJH oilJCJH);
	
	public List<OilJCJH> getOilJCJHReport(String site_id,String  date);
	
	public Map<String,Object>  insertOrUpdate(OilJCJH oilJCJH);
	

	public Map<String,Object> insertOrUpdateOilJCJHByExcel(OilJCJH oilJCJH);
	
	public List<String> batchInsertOrUpdate(List<OilJCJH> oilJCJHlist);
	
	

	public Map<String,Object> delOneOilJCJH(String sep);
	

	public Map<String,Object> delListOilJCJH(String sep);
	
	
	//  审核操作
	public String auditOneOilJCJH(String sep);
	public String unAuditOneOilJCJH(String sep);
	//	提交操作
	public String submitOilJCJH(String sep);
	public String unSubmitOilJCJH(String sep);
	

} 
