package com.hrxt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrxt.mapper.WellMapper;
import com.hrxt.pojo.Well;
import com.hrxt.service.WellService;

import oracle.jdbc.OracleTypes;



@Service
@Transactional
public class WellServiceImpl implements WellService {

	@Autowired
	private WellMapper wellMapper;
	
	/**
	 * 
	 * 
	 * pageIndex  pageSize  参数不为空时  就分页
	 * 空时部分页
	 */
	@Override
	public List<Well> callWellList(Well well) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_org_id", well.getOrgId());
		paramMap.put("i_site_id", well.getSiteId());
		paramMap.put("i_well", well.getWellCommonName());
		paramMap.put("o_cnt", null);
		paramMap.put("o_my_cur", OracleTypes.CURSOR);
		wellMapper.P_DZS_GQ_DK_DJ_N(paramMap);
		List<Well> adviceList = (List<Well>)paramMap.get("o_my_cur");
		String cnt = paramMap.get("o_cnt").toString();
		Integer cntt = Integer.parseInt(cnt);
		return adviceList;
	}
	

	
	
}
