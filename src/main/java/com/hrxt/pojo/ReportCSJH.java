package com.hrxt.pojo;

public class ReportCSJH {
	
	private String cs_count; 	//--措施分组的总条数（一个措施下的记录行数）
    private String cs_index;	//--措施排序（一个措施下的排序）
    private String well_count;	//--水井或者油井数据总条数
    private String cs_sum;		//--一个措施下总的日产量
    private String yc_sum;		//--一个断块下总的日产量
    private String site_sum;	//--一个工区下总的日产量
    private String well_type_flag;//--0 油井 1水井
    private String site_index;	//----工区排序（一个工区下的排序）
    private String site_count;	//--工区下的记录总数（按照工区分组）
    private String cs_site_sum;	//--按照工区和措施类型分组，汇总日产量
    private String cs_id;		//--措施id
    private String cs_name;		//--措施名称
    private String seq;			// -- 序列--唯一标识, 
    private String site_id;		// -- 工区id, 
    private String site_name;	// -- 工区名称, 
    private String yc_id;		// --油藏id, 
    private String yc_name;		// --油藏名称, 
    private String well_id;		// -- 井id, 
    private String well_name;	// -- 井名称, 
    private String well_type;	// -- 井别id, 
    private String well_type_name;// -- 井名称, 
    private String well_status;	// --完成状态, 
    private String well_step_id;// -- 措施id, 
    private String well_step_name;// -- 措施名称, 
    private String prod_daily;	// -- 日增油, 
    private String mark;		// --备注, 
    private String create_date;	// --创建时间, 
    private String stime;		// --数据时间, 
    private String update_date;	// --更新时间, 
    private String tj_status;	// --提交状态, 
    private String sh_status;	// --审核状态, 
    private String step_type;	// --措施类型（0：常规措施计划；1：重点措施计划）
    private String new_well_count;//--新井数
    private String old_well_count;//--老井数
    private String count_all_well;//--总井数
    private String well_flag;	//--新老井标记 
    
    
	public String getCs_count() {
		return cs_count;
	}
	public void setCs_count(String cs_count) {
		this.cs_count = cs_count;
	}
	public String getCs_index() {
		return cs_index;
	}
	public void setCs_index(String cs_index) {
		this.cs_index = cs_index;
	}
	public String getWell_count() {
		return well_count;
	}
	public void setWell_count(String well_count) {
		this.well_count = well_count;
	}
	public String getCs_sum() {
		return cs_sum;
	}
	public void setCs_sum(String cs_sum) {
		this.cs_sum = cs_sum;
	}
	public String getYc_sum() {
		return yc_sum;
	}
	public void setYc_sum(String yc_sum) {
		this.yc_sum = yc_sum;
	}
	public String getSite_sum() {
		return site_sum;
	}
	public void setSite_sum(String site_sum) {
		this.site_sum = site_sum;
	}
	public String getWell_type_flag() {
		return well_type_flag;
	}
	public void setWell_type_flag(String well_type_flag) {
		this.well_type_flag = well_type_flag;
	}
	public String getSite_index() {
		return site_index;
	}
	public void setSite_index(String site_index) {
		this.site_index = site_index;
	}
	public String getSite_count() {
		return site_count;
	}
	public void setSite_count(String site_count) {
		this.site_count = site_count;
	}
	public String getCs_site_sum() {
		return cs_site_sum;
	}
	public void setCs_site_sum(String cs_site_sum) {
		this.cs_site_sum = cs_site_sum;
	}
	public String getCs_id() {
		return cs_id;
	}
	public void setCs_id(String cs_id) {
		this.cs_id = cs_id;
	}
	public String getCs_name() {
		return cs_name;
	}
	public void setCs_name(String cs_name) {
		this.cs_name = cs_name;
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
	public String getWell_type() {
		return well_type;
	}
	public void setWell_type(String well_type) {
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
	public String getProd_daily() {
		return prod_daily;
	}
	public void setProd_daily(String prod_daily) {
		this.prod_daily = prod_daily;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
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
	public String getStep_type() {
		return step_type;
	}
	public void setStep_type(String step_type) {
		this.step_type = step_type;
	}
	public String getNew_well_count() {
		return new_well_count;
	}
	public void setNew_well_count(String new_well_count) {
		this.new_well_count = new_well_count;
	}
	public String getOld_well_count() {
		return old_well_count;
	}
	public void setOld_well_count(String old_well_count) {
		this.old_well_count = old_well_count;
	}
	public String getCount_all_well() {
		return count_all_well;
	}
	public void setCount_all_well(String count_all_well) {
		this.count_all_well = count_all_well;
	}
	public String getWell_flag() {
		return well_flag;
	}
	public void setWell_flag(String well_flag) {
		this.well_flag = well_flag;
	}
    
    

}
