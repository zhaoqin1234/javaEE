package com.hrxt.service;

import java.util.List;

import com.hrxt.pojo.DzsPzReportSummarkRemark;

public interface  DzsPzReportSummarkRemarkService {
	
	/**
	 * 根据时间查询备注表信息
	 */
	public List<DzsPzReportSummarkRemark> getSummarkRemark(String date);
	
	/**
	 * 根据时间修改备注表
	 */
	int updateSummarkremarkByDate(DzsPzReportSummarkRemark record,String date);
	
	/**
	 * 添加备注
	 */
	
	int insterDzsPzReportSummarkRemark(DzsPzReportSummarkRemark record);
}
