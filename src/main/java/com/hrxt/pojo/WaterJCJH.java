package com.hrxt.pojo;

import java.sql.Date;

/**
 * 水井检测计划
 * 
 * @author ZhaoQin
 *
 */
public class WaterJCJH {
	private String seq;	                  //  序列--唯一标识
    private String site_id;               //  工区ID
    private String site_name;             //  工区名称
    private String yc_id;                 //  断块ID
    private String yc_name;               //  断块名称 --同指油藏
    private String well_id;               //  井ID
    private String well_name;             //  井名称
    private String cs_type_id;            //  测试类型ID
    private String cs_type_name;          //  测试类型名称
    private String well_section;          //  测试井段
    private Double thickness;             //NUMBER 厚度
    private String cs_purpose;            //  测试目的
    private String cs_explaim;            //  测试说明
    private String cs_claim;              //  测试要求
    private String last_success_date;     //  上次测成时间
    private String recently_fail_date;    //  最近一次未测成时间
    private String recently_revise_mesg;  //  最近一次整改情况
    private Integer level_demand;          //NUMBER 需求程度（0：正常；1：急需；2：可缓）
    private String tc_date;               //  提出日期
    private String fk_cd_date;            //  生产反馈出单日期
    private String wcd_mesg;              //  未出单原因
    private String success_date;          //  测成日期
    private String fail_mesg;             //  未测成原因
    private String create_date;           //DATE 创建时间
    private String update_date;           //DATE 更新时间
    private String tj_status;             //NUMBER 提交状态
    private String sh_status;             //NUMBER 审核状态
    private String stime;                 //       数据时间
    private Integer plies_num;             //NUMBER 层数
    private Double cs_water_injection;    //测试要求日注水量        <<<<<<<<<<<<<<<<<<水井检测计划  独有的
  //请求过程的参数  不是实体bean的数据  startDate:startDate,endDate:endDate,dateType:dateType,
    private String startDate; 
    private String endDate; 
    private String dateType; 
    

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public Double getCs_water_injection() {
		return cs_water_injection;
	}
	public void setCs_water_injection(Double cs_water_injection) {
		this.cs_water_injection = cs_water_injection;
	}
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
	public String getCs_type_id() {
		return cs_type_id;
	}
	public void setCs_type_id(String cs_type_id) {
		this.cs_type_id = cs_type_id;
	}
	public String getCs_type_name() {
		return cs_type_name;
	}
	public void setCs_type_name(String cs_type_name) {
		this.cs_type_name = cs_type_name;
	}
	public String getWell_section() {
		return well_section;
	}
	public void setWell_section(String well_section) {
		this.well_section = well_section;
	}
	public Double getThickness() {
		return thickness;
	}
	public void setThickness(Double thickness) {
		this.thickness = thickness;
	}
	public String getCs_purpose() {
		return cs_purpose;
	}
	public void setCs_purpose(String cs_purpose) {
		this.cs_purpose = cs_purpose;
	}
	public String getCs_explaim() {
		return cs_explaim;
	}
	public void setCs_explaim(String cs_explaim) {
		this.cs_explaim = cs_explaim;
	}
	public String getCs_claim() {
		return cs_claim;
	}
	public void setCs_claim(String cs_claim) {
		this.cs_claim = cs_claim;
	}
	public String getLast_success_date() {
		return last_success_date;
	}
	public void setLast_success_date(String last_success_date) {
		this.last_success_date = last_success_date;
	}
	public String getRecently_fail_date() {
		return recently_fail_date;
	}
	public void setRecently_fail_date(String recently_fail_date) {
		this.recently_fail_date = recently_fail_date;
	}
	public String getRecently_revise_mesg() {
		return recently_revise_mesg;
	}
	public void setRecently_revise_mesg(String recently_revise_mesg) {
		this.recently_revise_mesg = recently_revise_mesg;
	}
	public Integer getLevel_demand() {
		return level_demand;
	}
	public void setLevel_demand(Integer level_demand) {
		this.level_demand = level_demand;
	}
	public String getTc_date() {
		return tc_date;
	}
	public void setTc_date(String tc_date) {
		this.tc_date = tc_date;
	}
	public String getFk_cd_date() {
		return fk_cd_date;
	}
	public void setFk_cd_date(String fk_cd_date) {
		this.fk_cd_date = fk_cd_date;
	}
	public String getWcd_mesg() {
		return wcd_mesg;
	}
	public void setWcd_mesg(String wcd_mesg) {
		this.wcd_mesg = wcd_mesg;
	}
	public String getSuccess_date() {
		return success_date;
	}
	public void setSuccess_date(String success_date) {
		this.success_date = success_date;
	}
	public String getFail_mesg() {
		return fail_mesg;
	}
	public void setFail_mesg(String fail_mesg) {
		this.fail_mesg = fail_mesg;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
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
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public Integer getPlies_num() {
		return plies_num;
	}
	public void setPlies_num(Integer plies_num) {
		this.plies_num = plies_num;
	}
    
    
	
    
    
}