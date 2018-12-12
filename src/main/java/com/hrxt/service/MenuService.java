package com.hrxt.service;

import java.util.List;

import com.hrxt.pojo.Menu;
import com.hrxt.pojo.Role;
import com.hrxt.pojo.User;

public interface MenuService {
	
	int addMenu(Menu menu);
	
	int updMenu(Menu menu);
	
	int delMenu(Menu menu);
	
	int delMenus(List<Menu> menulist);
	
	List<Menu> findMenuByUser(User user);
	
	List<Menu> findMenuByMenu(Menu menu);
	
	List<Menu> findMenuByRole(Role role);
	
	public String allotMenuForRole(String role_id,String menu_ids);
	
} 