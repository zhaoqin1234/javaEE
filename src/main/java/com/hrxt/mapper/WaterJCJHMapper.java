package com.hrxt.mapper;

import java.util.Map;
/**
 * 获取 心井生产运行科  信息
 * @author ZhaoQin
 *
 */
public interface WaterJCJHMapper {
	
	
	String p_get_water_jcjh(Map<String, Object> param);
	/**
	 * 导出Excel模板数据
	 * @param param
	 * @return
	 */
	String p_dzs_water_jcjh_mb(Map<String, Object> param);
	
	
	/**
	 *  插入或者更新  oilJCJH
	 * @param param
	 * @return
	 */
	String p_water_jcjh_insert_up(Map<String, Object> param);
	
	/**
	 * Excel 批量新增
	 * @param param
	 * @return
	 */
	String p_water_jcjh_insert_up_excel(Map<String, Object> param);

	
	Integer p_del_dzs_water_jcjh(Map<String, Object> param);
	
	
	
	/**
	 * 提交记录为审核状态
	 * @param sqp
	 * @return
	 */
	int auditOneWaterJCJH(String seq);
	
	int unAuditOneWaterJCJH(String seq);
	/**
	 * 记录的提交
	 * @param seq
	 * @return
	 */
	int submitWaterJCJH(String seq);
	
	int unSubmitWaterJCJH(String seq);
	
	
	
}