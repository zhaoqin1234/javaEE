package com.hrxt.pojo;

/**
 * 组织机构菜单  tree树形结构
 * @author ZhaoQin
 *
 */
public class Deptment {
	
	private String id;			//主键id
	private String name;		//部门名称
	private String image_name;	//图标名称
	private String pid;			//父级id
	private String status;		//
	private String mark;		//
	
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
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
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	
}
