package com.hrxt.service;

import java.util.List;

import com.hrxt.pojo.Org;
import com.hrxt.pojo.Site;

public interface OrgService {

	
	List<Org> getAllOrgMsg();
	
	String getALLOrgBYJSONString();
	
	String getOrgMsgByOrgId(String orgId);
	
	
	List<Site> getPublicSite();
} 
 