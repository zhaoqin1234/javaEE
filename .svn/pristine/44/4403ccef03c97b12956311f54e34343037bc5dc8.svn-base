package com.hrxt.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrxt.mapper.MenuMapper;
import com.hrxt.pojo.Menu;
import com.hrxt.pojo.Role;
import com.hrxt.pojo.User;
import com.hrxt.service.MenuService;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public int addMenu(Menu menu) {
		return menuMapper.insertMenu(menu);
	}

	@Override
	public int updMenu(Menu menu) {
		return menuMapper.updateMenu(menu);
	}

	@Override
	public int delMenu(Menu menu) {
		return menuMapper.deleteMenuByMenu(menu);
	}

	@Override
	public int delMenus(List<Menu> menulist) {
		
		return 0;//menuMapper.deleteMenuByIds(ids);
	}

	@Override
	public List<Menu> findMenuByUser(User user) {
		
		return menuMapper.getMenusByUser(user);
	}

	@Override
	public List<Menu> findMenuByMenu(Menu menu) {
		return menuMapper.getMenuByMenu(menu);
	}

	@Override
	public List<Menu> findMenuByRole(Role role) {
		return menuMapper.getMenusByRole(role);
	}

	
	@Override
	public String allotMenuForRole(String role_id, String menu_ids) {
		List<String> roleIds = Arrays.asList(menu_ids.split(","));
		int ret  = 0;
		
		ret = menuMapper.deleteRole_MenuTableByUserId(role_id);
		
		
		for (String id : roleIds) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("role_id", role_id);
			paramMap.put("menu_id", id);
			ret = menuMapper.insertRole_MenuTable(paramMap);
		}
		
		return ret == 1?"ok":"error";
	}
	
	
	
	
	
}
