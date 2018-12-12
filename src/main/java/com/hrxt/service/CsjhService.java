package com.hrxt.service;

import java.util.List;
import java.util.Map;

import com.hrxt.pojo.Csjh;
import com.hrxt.pojo.CsjhOption;
import com.hrxt.pojo.Page;
import com.hrxt.pojo.ReportNote;

public interface CsjhService {
	
	
	
public Map<String,Object> getMonthPZList(Csjh csjh,Page<List<Csjh>> page);
	
	
	//csjh 基本操作
	public Map<String,Object>  insertOrUpdate(Csjh csjh);

	public Map<String,Object> insertOrUpdateByExcel(Csjh csjh);
	
	public List<String> batchInsertOrUpdate(List<Csjh> csjhlist);

	public Map<String,Object> delOneYPZBB(String sep);

	public Map<String,Object> delListYPZBB(String sep);
	
	
	/**
	 * 报表操作  
	 * @param site_ids  id逗号拼接
	 * @param cs_ids   id逗号拼接
	 * @param stime  必须传递 
	 * @return
	 */
	public Map<String,Object> getReportCSJHRecord(String site_ids,String cs_ids,String stime,String cs_type) ;
	
	//  审核操作
	public String auditOneCsjh(String seq);
	public String unAuditOneCsjh(String seq);
	//	提交操作
	public String submitCsjh(String seq);
	public String unSubmitCsjh(String seq);
	
	
	  
	// csjh  Option
	public Map<String,Object> insertOrUpdateCsjhOption(CsjhOption csjhOption);
	
	public Map<String,Object> deleteCsjhOption(String seq);
	
	List<CsjhOption> getCsjhOption(CsjhOption csjhOption);
	
	
	//报表的备注信息  增删改查
	public Map<String,Object> insertReportNote(ReportNote reportNote);
	
	public Map<String,Object> updateReportNote(ReportNote reportNote);
	
	public Map<String,Object> deleteReportNote(ReportNote reportNote);
	
	public Map<String,Object> selectReportNote(ReportNote reportNote);

} 
