package com.hrxt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrxt.mapper.OilJCJHMapper;
import com.hrxt.pojo.OilJCJH;
import com.hrxt.pojo.Page;
import com.hrxt.service.OilJCJHService;

import oracle.jdbc.OracleTypes;


/**
 * 油井 月配注报表
 * @author ZhaoQin
 *
 */
@Service
@Transactional
public class OilJCJHServiceImpl implements OilJCJHService {

	@Autowired
	private OilJCJHMapper oilJCJHMapper;

	@Override
	public Map<String, Object> getOilJCJHList(OilJCJH oilJCJH, Page<List<OilJCJH>> page) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_seq", oilJCJH.getSeq());
		paramMap.put("i_site_id", oilJCJH.getSite_id());
		paramMap.put("i_yc_id", oilJCJH.getYc_id());
		paramMap.put("i_well_id", oilJCJH.getWell_id());
		paramMap.put("i_cs_type_id", oilJCJH.getCs_type_id());
		paramMap.put("i_tj_status", oilJCJH.getTj_status());
		paramMap.put("i_sh_status", oilJCJH.getSh_status());
		paramMap.put("i_startdate", oilJCJH.getStartDate());
		paramMap.put("i_enddate", oilJCJH.getEndDate());
		paramMap.put("datetype", oilJCJH.getDateType());
		paramMap.put("i_p_index", page.getPageIndex());
		paramMap.put("i_p_size", page.getPageSize());
		paramMap.put("v_totalrecords", null);
		paramMap.put("o_my_cur", OracleTypes.CURSOR);
		oilJCJHMapper.p_get_oil_jcjh(paramMap);
		List<OilJCJH> blockList = (List<OilJCJH>)paramMap.get("o_my_cur");
		Integer cnt = (Integer)paramMap.get("v_totalrecords");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", blockList);
		map.put("total", cnt);
		return map;
	}
	
	/**
	 * 导出模板传递以下4个参数过滤
	 * i_site_id
	 * i_yc_id
	 * i_stime
	 * i_well_id
	 * @param oilJCJH
	 * @return
	 */
	@Override
	public List<OilJCJH> getOilJCJHExcelMoBan(OilJCJH oilJCJH) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_site_id", oilJCJH.getSite_id());
		paramMap.put("i_yc_id", oilJCJH.getYc_id());
		paramMap.put("i_stime", oilJCJH.getStime());
		paramMap.put("i_well_id", oilJCJH.getWell_id());
		paramMap.put("o_my_cur", OracleTypes.CURSOR);
		oilJCJHMapper.p_dzs_oil_jcjh_mb(paramMap);
		List<OilJCJH> blockList = (List<OilJCJH>)paramMap.get("o_my_cur");
		return blockList;
	}
	
	/**
	 * site_id:  工区id，逗号分隔
	 * date：时间  yyyy-mm
	 */
	@Override
	public List<OilJCJH> getOilJCJHReport(String site_id,String  date) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_date", date);
		paramMap.put("i_site_id", site_id);
		paramMap.put("o_my_cur", OracleTypes.CURSOR);
		oilJCJHMapper.p_get_jcjh_report(paramMap);
		List<OilJCJH> oilJCJH = (List<OilJCJH>)paramMap.get("o_my_cur");
		return oilJCJH;
	}
	
	

	
	/**
	 * 新增或者更新  油井检测计划
	 */
	@Override
	public Map<String,Object> insertOrUpdate(OilJCJH oilJCJH) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_seq", oilJCJH.getSeq());
		paramMap.put("i_site_name", oilJCJH.getSite_name());
		paramMap.put("i_yc_name", oilJCJH.getYc_name());
		paramMap.put("i_well_name", oilJCJH.getWell_name());
		paramMap.put("i_cs_type_name", oilJCJH.getCs_type_name());
		paramMap.put("i_well_section", oilJCJH.getWell_section());
		paramMap.put("i_thickness", oilJCJH.getThickness());
		paramMap.put("i_cs_purpose", oilJCJH.getCs_purpose());
		paramMap.put("i_cs_explaim", oilJCJH.getCs_explaim());
		paramMap.put("i_last_success_date", oilJCJH.getLast_success_date());
		paramMap.put("i_recently_fail_date", oilJCJH.getRecently_fail_date());
		paramMap.put("i_recently_revise_mesg", oilJCJH.getRecently_revise_mesg());// function 给时间
		paramMap.put("i_level_demand", oilJCJH.getLevel_demand());
		paramMap.put("i_tc_date", oilJCJH.getTc_date());
		paramMap.put("i_fk_cd_date", oilJCJH.getFk_cd_date());
		paramMap.put("i_wcd_mesg", oilJCJH.getWcd_mesg());
		paramMap.put("i_success_date", oilJCJH.getSuccess_date());
		paramMap.put("i_fail_mesg", oilJCJH.getFail_mesg());
		paramMap.put("i_stime", oilJCJH.getStime());
		paramMap.put("i_plies_num", oilJCJH.getPlies_num());
		paramMap.put("i_cs_claim", oilJCJH.getCs_claim());
		paramMap.put("o_out_numb", "");
		paramMap.put("o_mesg", "");
		oilJCJHMapper.p_jcjh_insert_or_update(paramMap);
		String message  = (String)paramMap.get("o_mesg");
		String code 	= (String)paramMap.get("o_out_numb");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}

	
	@Override
	public Map<String,Object> insertOrUpdateOilJCJHByExcel(OilJCJH oilJCJH) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_site_name", oilJCJH.getSite_name());
		paramMap.put("i_yc_name", oilJCJH.getYc_name());
		paramMap.put("i_well_name", oilJCJH.getWell_name());
		paramMap.put("i_cs_type_name", oilJCJH.getCs_type_name());
		paramMap.put("i_well_section", oilJCJH.getWell_section());
		paramMap.put("i_thickness", oilJCJH.getThickness());
		paramMap.put("i_cs_purpose", oilJCJH.getCs_purpose());
		paramMap.put("i_cs_explaim", oilJCJH.getCs_explaim());
		paramMap.put("i_last_success_date", oilJCJH.getLast_success_date());
		paramMap.put("i_recently_fail_date", oilJCJH.getRecently_fail_date());
		paramMap.put("i_recently_revise_mesg", oilJCJH.getRecently_revise_mesg());// 
		paramMap.put("i_level_demand", oilJCJH.getLevel_demand());
		paramMap.put("i_tc_date", oilJCJH.getTc_date());
		paramMap.put("i_fk_cd_date", oilJCJH.getFk_cd_date());
		paramMap.put("i_wcd_mesg", oilJCJH.getWcd_mesg());
		paramMap.put("i_success_date", oilJCJH.getSuccess_date());
		paramMap.put("i_fail_mesg", oilJCJH.getFail_mesg());
		paramMap.put("i_stime", oilJCJH.getStime());
		paramMap.put("i_plies_num", oilJCJH.getPlies_num());
		paramMap.put("i_cs_claim", oilJCJH.getCs_claim());
		paramMap.put("o_out_numb", "");
		paramMap.put("o_mesg", "");
		oilJCJHMapper.p_oil_jcjh_insert_up_excel(paramMap);
		String message  = (String)paramMap.get("o_mesg");
		String code 	= (String)paramMap.get("o_out_numb");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}
	
	
	
	/**
	 *  新增或者更新
	 */
	@Override
	public List<String> batchInsertOrUpdate(List<OilJCJH> oilJCJHlist) {
		List<String> reslist = new ArrayList<String>();
		for (OilJCJH oilJCJH : oilJCJHlist) {
			String res =null ;//TODO insertOrUpdate(oilJCJH);
			reslist.add(res);
		}
		return reslist;
	}

	
	
	@Override
	public Map<String,Object> delOneOilJCJH(String sep) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_seq", sep);
		paramMap.put("o_out_numb", "");
		paramMap.put("o_mesg", sep);
		oilJCJHMapper.p_dzs_del_oil_jcjh(paramMap);
		String message  = (String)paramMap.get("o_mesg");
		String code 	= (String)paramMap.get("o_out_numb");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}

	@Override
	public Map<String,Object> delListOilJCJH(String sep) {
		return delOneOilJCJH(sep);
	}

	
	@Override
	public String auditOneOilJCJH(String sep) {
		int res = oilJCJHMapper.auditOneOilJCJH(sep);
		return res == 1 ? "ok" :"error";
	}

	@Override
	public String unAuditOneOilJCJH(String sep) {
		int res = oilJCJHMapper.unAuditOneOilJCJH(sep);
		return res == 1 ? "ok" :"error";
	}

	@Override
	public String submitOilJCJH(String sep) {
		int res = oilJCJHMapper.submitOilJCJH(sep);
		return res == 1 ? "ok" :"error";
	}

	@Override
	public String unSubmitOilJCJH(String sep) {
		int res = oilJCJHMapper.unSubmitOilJCJH(sep);
		return res == 1 ? "ok" :"error";
	}

	

	
}