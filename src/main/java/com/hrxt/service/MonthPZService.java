package com.hrxt.service;

import java.util.List;
import java.util.Map;

import com.alibaba.druid.mock.handler.MockExecuteHandler;
import com.hrxt.pojo.Dzs_pz_report_summary;
import com.hrxt.pojo.MonthPZ;
import com.hrxt.pojo.Page;

public interface MonthPZService {
	
	/**
	 * 查询配注信息
	 * @param orgId
	 * @return
	 */
	public Map<String,Object> getMonthPZList(MonthPZ monthPZ,Page<List<MonthPZ>> page);
	
	/**
	 * 获取模板信息
	 * @param monthPZ
	 * @return
	 */
	public List<MonthPZ> getMonthPZMB(MonthPZ monthPZ);
	
	//废弃
	public Map<String,Object>  insertOrUpdate(MonthPZ monthPZ);
	
	//新增
	public Map<String,Object> insertMonthPZ(MonthPZ monthPZ);
	//更细腻
	public Map<String,Object> updateMonthPZ(MonthPZ monthPZ);
	
	
	
	/**
	 *  批量数据 insert 或者  updata
	 * @param monthPZlist
	 * @return
	 */
	public List<String> batchInsertOrUpdate(List<MonthPZ> monthPZlist);
	
	/**
	 * 月配注  ByExcel  新增或者更新
	 * @param monthPZ
	 * @return
	 */
	public Map<String,Object> insertOrUpdateByExcel(MonthPZ monthPZ);
	
	/**
	 * 删除报表记录
	 * @param sep
	 */
	public Map<String,Object> delOneYPZBB(String sep);
	
	/**
	 * 循环删除数据
	 * @param sep
	 */
	public Map<String,Object> delListYPZBB(String sep);
	
	
	/**
	 * 删除报表记录
	 * @param sep
	 */
	public String auditOneYPZBB(String sep);
	public String unAuditOneYPZBB(String sep);
	
	public String submitMonthPZ(String sep);
	public String unSubmitMonthPZ(String sep);
	
	/**
	 * 根据工区和时间查询月配注报表
	 */
	
	public Map<String,Object> getMonthPZBySiteIdAndDate(String site_id,String date);
	
	/**
	 * 查询报表全部内容
	 */
	
	
	public List<Dzs_pz_report_summary> getAllDzspzreportsummary();
} 
