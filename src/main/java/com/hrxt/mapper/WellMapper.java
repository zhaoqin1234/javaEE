package com.hrxt.mapper;

import java.util.Map;

public interface WellMapper {
	
	/**
	 * 获取井列表信息
	 * 	携带了工区 单井信息    
	 * @param param
	 * @return
	 */
	String P_DZS_GQ_DK_DJ_N(Map<String, Object> param);
	
	
	
	/**
	 * 带分页
	 * @param param
	 * @return
	 */
	String P_DZS_GQ_DK_DJ(Map<String, Object> param);
	
	
	
	
}
