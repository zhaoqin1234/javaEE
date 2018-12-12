package com.hrxt.mapper;

import java.util.Map;
/**
 * 获取 心井生产运行科  信息
 * @author ZhaoQin
 *
 */
public interface OilJCJHMapper {
	
	/**
	 * 检测计划查询
	 * @param param
	 * @return
	 */
	String p_get_oil_jcjh(Map<String, Object> param);
	
	/**
	 * jcjh 监测计划月度报表导出
	 * @param param
	 * @return
	 */
	String p_get_jcjh_report(Map<String, Object> param);
	/**
	 * 油井检测  excel模板数据导出
	 * @param param
	 * @return
	 */
	String p_dzs_oil_jcjh_mb(Map<String, Object> param);
	
	/**
	 *  插入或者更新  oilJCJH
	 * @param param
	 * @return
	 */
	String p_jcjh_insert_or_update(Map<String, Object> param);

	/**
	 * excel批量上传
	 * @param param
	 * @return
	 */
	String p_oil_jcjh_insert_up_excel(Map<String, Object> param);
	
	
	/**
	 * 删除jcjh by Seq   ：string  ，号分隔  带单引号
	 * @param param
	 * @return
	 */
	Integer p_dzs_del_oil_jcjh(Map<String, Object> param);
	
	
	
	/**
	 * 提交记录为审核状态
	 * @param sqp
	 * @return
	 */
	int auditOneOilJCJH(String seq);
	
	int unAuditOneOilJCJH(String seq);
	/**
	 * 记录的提交
	 * @param seq
	 * @return
	 */
	int submitOilJCJH(String seq);
	
	int unSubmitOilJCJH(String seq);
	
}
