package com.hrxt.mapper;

import java.util.Map;

public interface OrgMapper {
	/**
	 * 获取所有工区的 id name
	 * @param param
	 * @return
	 */
	String getAllOrgMsg(Map<String, Object> param);;
	
	
	/**
	 * 根据orgid获取子id name
	 * @param param
	 * @return
	 */
	String getOrgMsgByOrgId(Map<String, Object> param);
	
	
	/**
	 *  javabean 对应 site  数据库为 org  
	 *  bean的字段为
	 *  site_id
	 *  site_name
	 * @param param
	 * @return
	 */
	String p_get_public_Site(Map<String, Object> param);
	
	
}
