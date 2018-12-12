package com.hrxt.pojo;
/**
 * 菜单实体 entry
 * @author ZhaoQin
 *
 */
public class Menu {
	
	private String id;		//urltable的主键
	private String name;	//菜单显示名字
	private String urlType;	//应用？模块？菜单？
	private String appType;//url是否 在同一个server上发布
	private String hostName;//主机ip
	private String hostPoart;//远程调用的主机名字
	private String appName;//应用的name
	private String pid;		//父pid
	private String url;		//前台url
	private int status;		//url的状态信息
	private String mark;	// 备注信息
	private String imgName;	//显示的图标的名字
	private String orderNumber;	//显示的排序
	
	
	
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrlType() {
		return urlType;
	}
	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
	

	
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getHostPoart() {
		return hostPoart;
	}
	public void setHostPoart(String hostPoart) {
		this.hostPoart = hostPoart;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	

}
