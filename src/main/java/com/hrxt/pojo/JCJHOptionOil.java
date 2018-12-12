package com.hrxt.pojo;
/**
 * 水井检测计划  类型下拉框
 * 
 * 油井下拉列表
 * @author ZhaoQin
 *
 */
public class JCJHOptionOil {
	
	private String jcjh_id;		//监测计划ID
	private String jcjh_name;	//监测计划名称
	private String start_using;	//是否启用 Y:启用  N:未启用
	private String sort_order;	//排序     后台是number  用varchar接受
	private String joint_flag;	//是否拼接  Y 是 N否
	
	
	public String getJcjh_id() {
		return jcjh_id;
	}
	public void setJcjh_id(String jcjh_id) {
		this.jcjh_id = jcjh_id;
	}
	public String getJcjh_name() {
		return jcjh_name;
	}
	public void setJcjh_name(String jcjh_name) {
		this.jcjh_name = jcjh_name;
	}
	public String getStart_using() {
		return start_using;
	}
	public void setStart_using(String start_using) {
		this.start_using = start_using;
	}
	public String getSort_order() {
		return sort_order;
	}
	public void setSort_order(String sort_order) {
		this.sort_order = sort_order;
	}
	public String getJoint_flag() {
		return joint_flag;
	}
	public void setJoint_flag(String joint_flag) {
		this.joint_flag = joint_flag;
	}
	
	


}
