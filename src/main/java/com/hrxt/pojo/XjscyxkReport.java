package com.hrxt.pojo;

import java.util.Date;

/**
 * 心井生产运行科 bean
 * @author ZhaoQin
 *
 */
public class XjscyxkReport {
		
	private String flag;		   //0数据 1 小计  2 合计
	private String type_name;	   //0数据 1 小计  2 合计
	private String site_id;		   //工区id
	private String site_name;	   //工区名称
	private String well_id;		   //井id
	private String well_name;	   //井名称
	private Double prod_plan_day;  //预计日产油
	private String condition;	   //目前工况
	private String mark;		   //备注 
	private String index_num;		   //备注 
	
	

	public String getIndex_num() {
		return index_num;
	}
	public void setIndex_num(String index_num) {
		this.index_num = index_num;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getSite_id() {
		return site_id;
	}
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	public String getSite_name() {
		return site_name;
	}
	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}
	public String getWell_id() {
		return well_id;
	}
	public void setWell_id(String well_id) {
		this.well_id = well_id;
	}
	public String getWell_name() {
		return well_name;
	}
	public void setWell_name(String well_name) {
		this.well_name = well_name;
	}

	public Double getProd_plan_day() {
		return prod_plan_day;
	}
	public void setProd_plan_day(Double prod_plan_day) {
		this.prod_plan_day = prod_plan_day;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	
	
		
}