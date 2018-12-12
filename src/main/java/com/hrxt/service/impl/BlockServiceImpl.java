package com.hrxt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrxt.mapper.BlockMapper;
import com.hrxt.pojo.Block;
import com.hrxt.pojo.Well;
import com.hrxt.service.BlockService;
import com.hrxt.utils.JSONutils;

import oracle.jdbc.OracleTypes;



@Service
@Transactional
public class BlockServiceImpl implements BlockService {

	@Autowired
	private BlockMapper blockMapper;

	/**
	 * 根据某个orgId  获取下面的所有断块信息
	 */
	@Override
	public String callWellList(String orgId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_org_id", orgId);
		paramMap.put("o_my_cur", OracleTypes.CURSOR);
		blockMapper.p_DZS_GET_SUB_DK(paramMap);
		List<Block> blockList = (List<Block>)paramMap.get("o_my_cur");
		Integer cnt = (Integer)paramMap.get("o_cnt");
		return JSONutils.Object2String(blockList);
	}
	

	
}
