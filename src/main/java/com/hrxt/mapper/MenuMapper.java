package com.hrxt.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hrxt.pojo.Menu;
import com.hrxt.pojo.Role;
import com.hrxt.pojo.User;

public interface MenuMapper {
	
	public int insertMenu(Menu menu);
	
	public int deleteMenuByMenu(Menu menu);
	
	public int deleteMenuById(String id);
	
	public int deleteMenuByIds(String [] ids);
	
	public int updateMenu(Menu menu);
	
	public List<Menu> getMenus();
	
	public List<Menu> getMenusByUser(User user);
	
	public List<Menu> getMenusByRole(Role role);
	
	public List<Menu> getMenuByMenu(Menu menu);
	
	public int deleteRole_MenuTableByUserId(String role_id);
	
	public int insertRole_MenuTable(Map<String, Object> param); 
	


}
