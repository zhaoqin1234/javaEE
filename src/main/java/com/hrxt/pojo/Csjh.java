package com.hrxt.pojo;

import java.util.Date;

/**
 * 措施计划 enitry
 * 
 * @author ZhaoQin
 *
 */
public class Csjh {

	private String seq;				//序列--唯一标识
	private String site_id;			//工区id
	private String site_name;		//工区名称
	private String yc_id;			//油藏ID
	private String yc_name;			//油藏名称
	private String well_id;			//井ID
	private String well_name;		//井名称
	private Integer well_type;		//井别ID
	private String well_type_name;	//井名称
	private String well_status;		//完成状态
	private String well_step_id;	//措施ID
	private String well_step_name;	//措施名称
	private Double prod_daily;		//日增油
	private String mark;			//备注
	private Date create_date;		//创建时间
	private String stime;			//数据时间
	private Date update_date;		//更新时间
	private String tj_status;		//提交状态
	private String sh_status;		//审核状态
	private String step_type;		//措施类型（0：常规措施计划；1：重点措施计划）
	
	
	
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
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

	public String getYc_id() {
		return yc_id;
	}

	public void setYc_id(String yc_id) {
		this.yc_id = yc_id;
	}

	public String getYc_name() {
		return yc_name;
	}

	public void setYc_name(String yc_name) {
		this.yc_name = yc_name;
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

	public Integer getWell_type() {
		return well_type;
	}

	public void setWell_type(Integer well_type) {
		this.well_type = well_type;
	}

	public String getWell_type_name() {
		return well_type_name;
	}

	public void setWell_type_name(String well_type_name) {
		this.well_type_name = well_type_name;
	}

	public String getWell_status() {
		return well_status;
	}

	public void setWell_status(String well_status) {
		this.well_status = well_status;
	}

	public String getWell_step_id() {
		return well_step_id;
	}

	public void setWell_step_id(String well_step_id) {
		this.well_step_id = well_step_id;
	}

	public String getWell_step_name() {
		return well_step_name;
	}

	public void setWell_step_name(String well_step_name) {
		this.well_step_name = well_step_name;
	}

	public Double getProd_daily() {
		return prod_daily;
	}

	public void setProd_daily(Double prod_daily) {
		this.prod_daily = prod_daily;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getTj_status() {
		return tj_status;
	}

	public void setTj_status(String tj_status) {
		this.tj_status = tj_status;
	}

	public String getSh_status() {
		return sh_status;
	}

	public void setSh_status(String sh_status) {
		this.sh_status = sh_status;
	}

	public String getStep_type() {
		return step_type;
	}

	public void setStep_type(String step_type) {
		this.step_type = step_type;
	}

	public Csjh() {
	
	}
	
	
	public Csjh( String site_name, String yc_name, String well_name,String well_type_name) {
		this.site_name = site_name;
		this.yc_name = yc_name;
		this.well_name = well_name;
		this.well_type_name = well_type_name;
	}
	
	
	
	

	

}