package com.hrxt.service;

import java.util.List;

import com.hrxt.pojo.Well;

public interface WellService {
	
	/**
	 * 获取单井信息
	 * @param well
	 * @return
	 */
	List<Well> callWellList(Well well);

} 
