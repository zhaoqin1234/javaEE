package com.hrxt.pojo;

/**
 * 按钮entry
 * @author ZhaoQin
 *
 */
public class Button {
	
	private String id;		//主键id
	private String key;		//按钮id
	private String name;	//按钮名称
	private String status;	//按钮是否启用
	private String mark;	//备注信息
	private String menuId;	//备注信息
	private String menuName;	//备注信息
	
	
	public String getId() {
		return id;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	
	
	
}
