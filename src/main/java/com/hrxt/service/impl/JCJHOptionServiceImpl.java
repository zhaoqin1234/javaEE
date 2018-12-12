package com.hrxt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrxt.mapper.JCJHOptionMapper;
import com.hrxt.pojo.JCJHOptionOil;
import com.hrxt.pojo.JCJHOptionWater;
import com.hrxt.service.JCJHOptionService;

import oracle.jdbc.OracleTypes;

@Service
@Transactional
public class JCJHOptionServiceImpl implements JCJHOptionService {

	@Autowired
	private JCJHOptionMapper jCJHOptionMapper;

	@Override
	public String insertOrUpdateJCJHOptionWater(JCJHOptionWater jCJHOption) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_jcjh_id", jCJHOption.getJcjh_id());
		paramMap.put("i_jcjh_name", jCJHOption.getJcjh_name());
		paramMap.put("i_start_using", jCJHOption.getStart_using());
		paramMap.put("v_mesg", null);
		jCJHOptionMapper.insertOrUpdateJCJHOptionWater(paramMap);
		return (String)paramMap.get("v_mesg");
	}

	@Override
	public String insertOrUpdateJCJHOptionOil(JCJHOptionOil jCJHOption) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_jcjh_id", jCJHOption.getJcjh_id());
		paramMap.put("i_jcjh_name", jCJHOption.getJcjh_name());
		paramMap.put("i_start_using", jCJHOption.getStart_using());
		paramMap.put("v_mesg", null);
		jCJHOptionMapper.insertOrUpdateJCJHOptionWater(paramMap);
		return (String)paramMap.get("v_mesg");
	}

	@Override
	public String deleteJCJHOptionWAter(String seq) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_jcjh_id", seq);
		paramMap.put("v_mesg", null);
		jCJHOptionMapper.deleteJCJHOptionWater(paramMap);
		return (String) paramMap.get("v_mesg");
	}

	@Override
	public String deleteJCJHOptionOil(String seq) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_jcjh_id", seq);
		paramMap.put("v_mesg", null);
		jCJHOptionMapper.deleteJCJHOptionOil(paramMap);
		return (String) paramMap.get("v_mesg");
	}


	@Override
	public List<JCJHOptionWater> getJCJHOptionWater(JCJHOptionWater jCJHOption) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("o_my_cur", OracleTypes.CURSOR);
		
		jCJHOptionMapper.getJCJHOptionWater(paramMap);
		List<JCJHOptionWater> optionList = (List<JCJHOptionWater>)paramMap.get("o_my_cur");
		return optionList;
	}

	@Override
	public List<JCJHOptionOil> getJCJHOptionOil(JCJHOptionOil jCJHOption) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("o_my_cur", OracleTypes.CURSOR);
		
		jCJHOptionMapper.getJCJHOptionOil(paramMap);
		List<JCJHOptionOil> optionList = (List<JCJHOptionOil>)paramMap.get("o_my_cur");
		return optionList;
	}

	

	
	
	
	
	
}
