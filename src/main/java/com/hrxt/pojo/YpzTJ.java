package com.hrxt.pojo;

public class YpzTJ {

	private String org_id;
	private String type; //0 灰岩，1 沙岩 ，2 合计
	private String dzpz; //地质配注
	private String khpz; //考核配注
	private String upKhpz; //上月考核配注
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKhpz() {
		return khpz;
	}
	public void setKhpz(String khpz) {
		this.khpz = khpz;
	}
	public String getUpKhpz() {
		return upKhpz;
	}
	public void setUpKhpz(String upKhpz) {
		this.upKhpz = upKhpz;
	}
	public String getDzpz() {
		return dzpz;
	}
	public void setDzpz(String dzpz) {
		this.dzpz = dzpz;
	}
	@Override
	public String toString() {
		return "YpzTJ [org_id=" + org_id + ", type=" + type + ", dzpz=" + dzpz + ", khpz=" + khpz + ", upKhpz=" + upKhpz
				+ "]";
	}
	
	
	
}
