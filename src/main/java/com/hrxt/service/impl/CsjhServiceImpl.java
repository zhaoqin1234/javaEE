package com.hrxt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrxt.mapper.CsjhMapper;
import com.hrxt.pojo.Csjh;
import com.hrxt.pojo.CsjhOption;
import com.hrxt.pojo.Page;
import com.hrxt.pojo.ReportCSJH;
import com.hrxt.pojo.ReportNote;
import com.hrxt.pojo.Site;
import com.hrxt.service.CsjhService;

import oracle.jdbc.OracleTypes;



@Service
@Transactional
public class CsjhServiceImpl implements CsjhService {

	@Autowired
	private CsjhMapper csjhMapper;
	
	
	@Override
	public Map<String,Object> getMonthPZList(Csjh xjscyxk, Page<List<Csjh>> page) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_seq", xjscyxk.getSeq());
		paramMap.put("i_site_id", xjscyxk.getSite_id());
		paramMap.put("i_yc_id", xjscyxk.getYc_id());
		paramMap.put("i_well_id", xjscyxk.getWell_id());
		paramMap.put("i_well_step_id", xjscyxk.getWell_step_id());
		paramMap.put("i_well_type", xjscyxk.getWell_type());
		paramMap.put("i_stime", xjscyxk.getStime());
		paramMap.put("i_well_status", xjscyxk.getWell_status());
		paramMap.put("i_step_type", xjscyxk.getStep_type());
		paramMap.put("i_tj_status", xjscyxk.getTj_status());
		paramMap.put("i_sh_status", xjscyxk.getSh_status());
		paramMap.put("i_p_index", page.getPageIndex());
		paramMap.put("i_p_size", page.getPageSize());
		paramMap.put("v_totalrecords", null);
		paramMap.put("o_my_cur", OracleTypes.CURSOR);
		csjhMapper.p_get_csjh(paramMap);
		List<Csjh> blockList = (List<Csjh>)paramMap.get("o_my_cur");
		Integer cnt = (Integer)paramMap.get("v_totalrecords");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", blockList);
		map.put("total", cnt);
		return map;
	}
	
	
	/**
	 *  舍弃   2018年11月19日18:21:26
	 * @param site_ids
	 * @param stime
	 * @return
	 */
	public Map<String,Object> getCsjhReport(String site_ids,String stime) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_site_ids", site_ids);
		paramMap.put("i_stime", stime);
		paramMap.put("o_my_cur", OracleTypes.CURSOR);
		csjhMapper.p_get_dzs_cs_report(paramMap);
		List<Csjh> blockList = null;
				paramMap.get("o_my_cur");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", blockList);
		return map;
	}
	
	/**
	 * 获取测试计划的  报表信息    java后台处理   合并单元   excel导出    及  页面展示
	 * 
	 * @param site_ids
	 * @param stime
	 * 		  cs_type:  0  是常规措施   1 是重点措施
	 * @return
	 */
	public Map<String,Object> getReportCSJHRecord(String site_ids,String cs_ids,String stime,String cs_type) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_site_id", site_ids);
		paramMap.put("i_cs_id", cs_ids);
		paramMap.put("i_stime", stime);
		paramMap.put("i_cs_type", cs_type);
		paramMap.put("o_out_mes", "");
		paramMap.put("o_out_note_id", ""); // 
		paramMap.put("o_my_cur_site", OracleTypes.CURSOR);
		paramMap.put("o_my_cur", OracleTypes.CURSOR);
		csjhMapper.p_dzs_csjh_report(paramMap);
		String o_out_mes = (String)paramMap.get("o_out_mes");
		String o_out_note_id = (String)paramMap.get("o_out_note_id");
		List<Site> siteList = (List<Site>)paramMap.get("o_my_cur_site");
		List<ReportCSJH> recordList = (List<ReportCSJH>)paramMap.get("o_my_cur");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", recordList);
		map.put("sitedata", siteList);
		map.put("o_out_mes", o_out_mes);
		map.put("o_out_note_id", o_out_note_id);
		return map;
	}

	
	@Override
	public Map<String,Object> insertOrUpdate(Csjh csjh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_seq", csjh.getSeq());
		paramMap.put("i_site_name", csjh.getSite_name());
		paramMap.put("i_yc_name", csjh.getYc_name());
		paramMap.put("i_well_name", csjh.getWell_name());
		paramMap.put("i_well_type_name", csjh.getWell_type_name());
		paramMap.put("i_well_status", csjh.getWell_status());
		paramMap.put("i_well_step_name", csjh.getWell_step_name());
		paramMap.put("i_prod_daily", csjh.getProd_daily());
		paramMap.put("i_mark", csjh.getMark());
		paramMap.put("i_stime", csjh.getStime());
		paramMap.put("i_tj_starts", csjh.getTj_status());
		paramMap.put("i_sh_starts", csjh.getSh_status());
		paramMap.put("o_out_numb", "");
		paramMap.put("o_mesg", "");
		csjhMapper.p_dzs_insert_up_csjh(paramMap);
		String message  = (String)paramMap.get("o_mesg");
		String code 	= (String)paramMap.get("o_out_numb");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}
	
	@Override
	public Map<String,Object> insertOrUpdateByExcel(Csjh csjh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_seq", csjh.getSeq());
		paramMap.put("i_site_name", csjh.getSite_name());
		paramMap.put("i_yc_name", csjh.getYc_name());
		paramMap.put("i_well_name", csjh.getWell_name());
		paramMap.put("i_well_type_name", csjh.getWell_type_name());
		paramMap.put("i_well_status", csjh.getWell_status());
		paramMap.put("i_well_step_name", csjh.getWell_step_name());
		paramMap.put("i_prod_daily", csjh.getProd_daily());
		paramMap.put("i_mark", csjh.getMark());
		paramMap.put("i_stime", csjh.getStime());
		paramMap.put("i_tj_starts", csjh.getTj_status());
		paramMap.put("i_sh_starts", csjh.getSh_status());
		paramMap.put("o_out_numb", "");
		paramMap.put("o_mesg", "");
		csjhMapper.p_dzs_insert_up_csjh(paramMap);
		String message  = (String)paramMap.get("o_mesg");
		String code 	= (String)paramMap.get("o_out_numb");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}
	
	

	@Override
	public List<String> batchInsertOrUpdate(List<Csjh> xjscyxklist) {
		List<String> list = new ArrayList<String>();
		for (Csjh xjscyxk : xjscyxklist) {
			String result = null;
			//TODO
					Map<String,Object> map = insertOrUpdate(xjscyxk);
			if(result != null) {
				list.add(result);
			}else {
				list.add("");   //避免空值  序列不对饮问题
			}
		}
		return list;
	}

	
	
	@Override
	public Map<String,Object> delOneYPZBB(String sep) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_seq", sep);
		paramMap.put("o_out_numb", "");
		paramMap.put("o_mesg", sep);
		csjhMapper.p_dzs_del_csjh(paramMap);
		String message  = (String)paramMap.get("o_mesg");
		String code 	= (String)paramMap.get("o_out_numb");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}

	
	@Override
	public Map<String,Object> delListYPZBB(String sep) {
		 return delOneYPZBB(sep);
	}

	@Override
	public String auditOneCsjh(String seq) {
		int res = csjhMapper.auditOneCsjh(seq);
		return res == 1 ? "ok" :"error";
	}

	@Override
	public String unAuditOneCsjh(String seq) {
		int res = csjhMapper.unAuditOneCsjh(seq);
		return res == 1 ? "ok" :"error";
	}

	@Override
	public String submitCsjh(String seq) {
		int res = csjhMapper.submitCsjh(seq);
		return res == 1 ? "ok" :"error";
	}

	@Override
	public String unSubmitCsjh(String seq) {
		int res = csjhMapper.unSubmitCsjh(seq);
		return res == 1 ? "ok" :"error";
	}

	
	//  cdjh——option 
	@Override
	public Map<String,Object> insertOrUpdateCsjhOption(CsjhOption csjhOption) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_seq", csjhOption.getCs_id());
		paramMap.put("i_cs_name", csjhOption.getCs_name());
		paramMap.put("i_cs_type", csjhOption.getCs_type());
		paramMap.put("i_cs_flag", csjhOption.getCs_flag());
		paramMap.put("o_out_numb", "");
		paramMap.put("v_mesg", null);
		csjhMapper.p_zds_well_cs_in_up(paramMap);
		String message  = (String)paramMap.get("o_mesg");
		String code 	= (String)paramMap.get("o_out_numb");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}
	
	/**
	 * 删除csjh option 
	 */
	@Override
	public Map<String,Object> deleteCsjhOption(String seq) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_sql", seq);
		paramMap.put("o_out_numb", "");
		paramMap.put("o_mesg", null);
		csjhMapper.p_dzs_well_cs_del(paramMap);
		String message  = (String)paramMap.get("o_mesg");
		String code 	= (String)paramMap.get("o_out_numb");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}

	/**
	 * 获取 csjh option的下拉框
	 */
	@Override
	public List<CsjhOption> getCsjhOption(CsjhOption csjhOption) { 
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_cs_type", csjhOption.getCs_type());
		paramMap.put("o_my_cur", OracleTypes.CURSOR);
		csjhMapper.p_zds_well_cs_get(paramMap);
		List<CsjhOption> optionList = (List<CsjhOption>)paramMap.get("o_my_cur");
		return optionList;
	}

	
	
	
	
	
	
    //以下是报表备注信息的增删改查
	@Override
	public Map<String, Object> insertReportNote(ReportNote reportNote) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_type", reportNote.getType());
		paramMap.put("i_note", reportNote.getNote());
		paramMap.put("i_stime", reportNote.getStime());
		paramMap.put("o_out_numb", "");
		paramMap.put("o_mesg", null);
		csjhMapper.p_dzs_report_note_insert(paramMap);
		String message  = (String)paramMap.get("o_mesg");
		String code 	= (String)paramMap.get("o_out_numb");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}


	@Override
	public Map<String, Object> updateReportNote(ReportNote reportNote) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_seq", reportNote.getId());
		paramMap.put("i_type", reportNote.getType());
		paramMap.put("i_note", reportNote.getNote());
		paramMap.put("i_stime", reportNote.getStime());
		paramMap.put("o_out_numb", "");
		paramMap.put("o_mesg", null);
		csjhMapper.p_dzs_report_note_update(paramMap);
		String message  = (String)paramMap.get("o_mesg");
		String code 	= (String)paramMap.get("o_out_numb");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}


	@Override
	public Map<String, Object> deleteReportNote(ReportNote reportNote) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_seq", reportNote.getId());
		paramMap.put("o_out_numb", "");
		paramMap.put("o_mesg", null);
		csjhMapper.p_dzs_report_note_delete(paramMap);
		String message  = (String)paramMap.get("o_mesg");
		String code 	= (String)paramMap.get("o_out_numb");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}

	
	@Override
	public Map<String, Object> selectReportNote(ReportNote reportNote) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_type", reportNote.getType());
		paramMap.put("i_note", reportNote.getNote());
		paramMap.put("i_stime", reportNote.getStime());
		paramMap.put("o_mesg", null);
		csjhMapper.p_dzs_report_note_select(paramMap);
		List<ReportNote> dataList  = (List<ReportNote>)paramMap.get("o_mesg");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", dataList);
		return map;
	}

	
}