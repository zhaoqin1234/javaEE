package com.hrxt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hrxt.mapper.XjscyxkMapper;
import com.hrxt.pojo.Page;
import com.hrxt.pojo.Xjscyxk;
import com.hrxt.pojo.XjscyxkReport;
import com.hrxt.pojo.XjscyxkType;
import com.hrxt.service.XjscyxkService;

import oracle.jdbc.OracleTypes;



@Service
@Transactional
public class XjscyxkServiceImpl implements XjscyxkService {

	@Autowired
	private XjscyxkMapper xjscyxkMapper;

	@Override
	public Map<String,Object> getXjscyxkList(Xjscyxk xjscyxk, Page<List<Xjscyxk>> page) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_seq", xjscyxk.getSeq());
		paramMap.put("i_site_id", xjscyxk.getSite_id());
		paramMap.put("i_yc_id", xjscyxk.getYc_id());
		paramMap.put("i_stime", xjscyxk.getStime());
		paramMap.put("i_well_id", xjscyxk.getWell_id());
		paramMap.put("i_well_type", xjscyxk.getWell_type());
		paramMap.put("i_tj_starts", xjscyxk.getTj_status());
		paramMap.put("i_sh_starts", xjscyxk.getSh_status());
		paramMap.put("i_p_index", page.getPageIndex());
		paramMap.put("i_p_size", page.getPageSize());
		paramMap.put("v_totalrecords", null);
		paramMap.put("o_my_cur", OracleTypes.CURSOR);
		xjscyxkMapper.p_dzs_get_xjscyxk(paramMap);
		List<Xjscyxk> blockList = (List<Xjscyxk>)paramMap.get("o_my_cur");
		Integer cnt = (Integer)paramMap.get("v_totalrecords");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", blockList);
		map.put("total", cnt);
		return map;
	}

	
	
	/**
	 * 获取新井生产运行科  报表数据
	 * @param stime  xxxx-xx
	 * @param site_id  id逗号分隔
	 * @return
	 */
	@Override
	public List<XjscyxkReport> getXjscyxkReport(String stime,String site_id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_stime", stime);
		paramMap.put("i_site_id", site_id);
		paramMap.put("o_my_cur", OracleTypes.CURSOR);
		xjscyxkMapper.p_get_scyxk_report(paramMap);
		List<XjscyxkReport> reportList = (List<XjscyxkReport>)paramMap.get("o_my_cur");
		return reportList;
	}
	
	/**
	 *   获取新井生产运行科的   井类型数据
	 * @param type_code
	 * @param type_name
	 * @param new_old_flag
	 * @param oil_water_flag
	 * @return
	 */
	@Override
	public List<XjscyxkType> getXjscyxkType(String type_code,String type_name,String new_old_flag,String oil_water_flag) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_type_code", type_code);
		paramMap.put("i_type_name", type_name);
		paramMap.put("i_new_old_flag", new_old_flag);
		paramMap.put("i_oil_water_flag", oil_water_flag);
		paramMap.put("o_my_cur", OracleTypes.CURSOR);
		xjscyxkMapper.p_dzs_scyxk_type_select(paramMap);
		List<XjscyxkType> typeList = (List<XjscyxkType>)paramMap.get("o_my_cur");
		return typeList;
	}
	
	/**
	 * 插入一个新的  新井生产运行科   类型数据
	 */
	@Override
	public Map<String,Object> insertXjscyxkType(XjscyxkType xjscyxkType) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_type_code", xjscyxkType.getType_code());
		paramMap.put("i_type_name", xjscyxkType.getType_name());
		paramMap.put("i_new_old_flag", xjscyxkType.getNew_old_flag());
		paramMap.put("i_oil_water_flag", xjscyxkType.getOil_water_flag());
		xjscyxkMapper.p_dzs_scyxk_type_insert(paramMap);
		String message  = (String)paramMap.get("o_mesg");
		String code 	= (String)paramMap.get("o_out_numb");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}
	
	/**
	 * 跟新一个新井生产云新科  类型数据
	 */
	@Override
	public Map<String,Object> updateXjscyxkType(XjscyxkType xjscyxkType) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_seq", xjscyxkType.getId());
		paramMap.put("i_type_code", xjscyxkType.getType_code());
		paramMap.put("i_type_name", xjscyxkType.getType_name());
		paramMap.put("i_new_old_flag", xjscyxkType.getNew_old_flag());
		paramMap.put("i_oil_water_flag", xjscyxkType.getOil_water_flag());
		xjscyxkMapper.p_dzs_scyxk_type_update(paramMap);
		String message  = (String)paramMap.get("o_mesg");
		String code 	= (String)paramMap.get("o_out_numb");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}
	
	/**
	 * 删除  井别类型
	 */
	@Override
	public Map<String,Object> deleteXjscyxkType(XjscyxkType xjscyxkType) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_seq", xjscyxkType.getId());
		xjscyxkMapper.p_dzs_scyxk_type_del(paramMap);
		String message  = (String)paramMap.get("o_mesg");
		String code 	= (String)paramMap.get("o_out_numb");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}
	
	
	
	@Override
	public Map<String,Object> insertOrUpdate(Xjscyxk xjscyxk) {
		System.out.println(JSON.toJSONString(xjscyxk));
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_seq", xjscyxk.getSeq());
		paramMap.put("i_site_name", xjscyxk.getSite_name());
		paramMap.put("i_yc_name", xjscyxk.getYc_name());
		paramMap.put("i_stime", xjscyxk.getStime());
		paramMap.put("i_well_type_name", xjscyxk.getWell_type_name());
		paramMap.put("i_well_name", xjscyxk.getWell_name());
		paramMap.put("i_sh_starts", xjscyxk.getSh_status());
		paramMap.put("i_tj_starts", xjscyxk.getTj_status());
		paramMap.put("i_prod_plan_day", xjscyxk.getProd_plan_day());
		paramMap.put("i_condition", xjscyxk.getCondition());
		paramMap.put("i_mark", xjscyxk.getMark());
		paramMap.put("o_out_numb", "");
		paramMap.put("o_mesg", "");
		xjscyxkMapper.p_dzs_insert_up_xjscyxk(paramMap);
		String message  = (String)paramMap.get("o_mesg");
		String code 	= (String)paramMap.get("o_out_numb");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}

	
	
	/**
	 * excel  上传新增   
	 */
	@Override
	public Map<String,Object> insertOrUpdateByExcel(Xjscyxk xjscyxk) {
		System.out.println(JSON.toJSONString(xjscyxk));
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_site_name", xjscyxk.getSite_name());
		paramMap.put("i_yc_name", xjscyxk.getYc_name());
		paramMap.put("i_stime", xjscyxk.getStime());
		paramMap.put("i_well_type_name", xjscyxk.getWell_type_name());
		paramMap.put("i_well_name", xjscyxk.getWell_name());
		paramMap.put("i_sh_starts", xjscyxk.getSh_status());
		paramMap.put("i_tj_starts", xjscyxk.getTj_status());
		paramMap.put("i_prod_plan_day", xjscyxk.getProd_plan_day());
		paramMap.put("i_condition", xjscyxk.getCondition());
		paramMap.put("i_mark", xjscyxk.getMark());
		paramMap.put("o_out_numb", "");
		paramMap.put("o_mesg", "");
		xjscyxkMapper.p_dzs_insert_up_xjscyxk_excel(paramMap);
		String message  = (String)paramMap.get("o_mesg");
		String code 	= (String)paramMap.get("o_out_numb");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}
	
	
	@Override
	public List<String> batchInsertOrUpdate(List<Xjscyxk> xjscyxklist) {
		List<String> list = new ArrayList<String>();
		for (Xjscyxk xjscyxk : xjscyxklist) {
			String result = null;//TODO insertOrUpdate(xjscyxk);
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
		xjscyxkMapper.p_dzs_del_xjscyxk(paramMap);
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

	
}
