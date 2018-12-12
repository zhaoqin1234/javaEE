package com.hrxt.pojo;
/**
 * 地质所配注总结 文字
 * @author admin
 *
 */
public class Dzs_pz_report_summary{
	
	private String  id;
	private String 	note;
	private String  orderPort;
	private String  stime;
	private String  serialNumber;
	private int  zydzpz;
	private	int  zykhpz;
	private	int  zyhySum;
	private	int  zysySum;
	private	int  khhySum;
	private	int  khsySum;
	private	int  hzkfdzpz;
	private	int  hzkfkhpz ;
		
		
	public int getZydzpz() {
		return zydzpz;
	}
	public void setZydzpz(int zydzpz) {
		this.zydzpz = zydzpz;
	}
	public int getZykhpz() {
		return zykhpz;
	}
	public void setZykhpz(int zykhpz) {
		this.zykhpz = zykhpz;
	}
	public int getZyhySum() {
		return zyhySum;
	}
	public void setZyhySum(int zyhySum) {
		this.zyhySum = zyhySum;
	}
	public int getZysySum() {
		return zysySum;
	}
	public void setZysySum(int zysySum) {
		this.zysySum = zysySum;
	}
	public int getKhhySum() {
		return khhySum;
	}
	public void setKhhySum(int khhySum) {
		this.khhySum = khhySum;
	}
	public int getKhsySum() {
		return khsySum;
	}
	public void setKhsySum(int khsySum) {
		this.khsySum = khsySum;
	}
	public int getHzkfdzpz() {
		return hzkfdzpz;
	}
	public void setHzkfdzpz(int hzkfdzpz) {
		this.hzkfdzpz = hzkfdzpz;
	}
	public int getHzkfkhpz() {
		return hzkfkhpz;
	}
	public void setHzkfkhpz(int hzkfkhpz) {
		this.hzkfkhpz = hzkfkhpz;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getOrderPort() {
		return orderPort;
	}
	public void setOrderPort(String orderPort) {
		this.orderPort = orderPort;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	@Override
	public String toString() {
		return "Dzs_pz_report_summary [id=" + id + ", note=" + note + ", orderPort=" + orderPort + ", stime=" + stime
				+ "]";
	}
	


	
	
	
}
