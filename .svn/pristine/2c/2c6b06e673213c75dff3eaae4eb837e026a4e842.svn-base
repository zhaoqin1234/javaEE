package com.hrxt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrxt.mapper.OrgMapper;
import com.hrxt.pojo.Org;
import com.hrxt.pojo.Site;
import com.hrxt.pojo.Well;
import com.hrxt.service.OrgService;
import com.hrxt.utils.JSONutils;

import oracle.jdbc.OracleTypes;



@Service
@Transactional
public class OrgServiceImpl implements OrgService {

	@Autowired
	private OrgMapper orgMapper;
	



	/**
	 * 获取所有的工区信息
	 */
	@Override
	public List<Org> getAllOrgMsg() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("o_my_cur", OracleTypes.CURSOR);
		orgMapper.getAllOrgMsg(paramMap);
		List<Org> orgList = (List<Org>)paramMap.get("o_my_cur");
		return orgList;
	}

	/**
	 * json格式返回所有的  org
	 */
	@Override
	public String getALLOrgBYJSONString() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("o_my_cur", OracleTypes.CURSOR);
		orgMapper.getAllOrgMsg(paramMap);
		List<Org> orgList = (List<Org>)paramMap.get("o_my_cur");
		
		return JSONutils.Object2String(orgList);
	}
	
	/**
	 * 根据 org_id向自己钻取
	 */
	@Override
	public String getOrgMsgByOrgId(String orgId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("i_org_id", orgId);
		paramMap.put("o_my_cur", OracleTypes.CURSOR);
		orgMapper.getAllOrgMsg(paramMap);
		List<Org> orgList = (List<Org>)paramMap.get("o_my_cur");
		return JSONutils.Object2String(orgList);
	}

	
	/**
	 * 后台获取经常使用
	 * bean的字段      
	 * site_id
	 * site_name
	 */
	@Override
	public List<Site> getPublicSite() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("o_my_cur", OracleTypes.CURSOR);
		orgMapper.p_get_public_Site(paramMap);
		List<Site> siteList = (List<Site>)paramMap.get("o_my_cur");
		return siteList;
	}

	
	
}
