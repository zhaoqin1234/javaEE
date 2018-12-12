package com.hrxt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hrxt.pojo.Menu;
import com.hrxt.pojo.Role;
import com.hrxt.pojo.User;
import com.hrxt.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	
	
	@RequestMapping("/addMenu")
	@ResponseBody
	public String insetMenu(Menu menu){	
		menu.setId(UUID.randomUUID().toString());
		int res  = this.menuService.addMenu(menu);
		return res==0? "ok":" error ";
	}

	@RequestMapping("/updMenu")
	@ResponseBody
	public String updMenu(Menu menu){	
		int res  = this.menuService.updMenu(menu);
		return res==0? "ok":" error ";
	}
	
	@RequestMapping("/delMenuByMenu")
	@ResponseBody
	public String delMenu(Menu menu){	
		int res  = this.menuService.delMenu(menu);
		return res==0? "ok":" error ";
	}
	
	/**
	 *   /menu/getMenuByUser?id=123
	 * @param user
	 * @return
	 */
	@RequestMapping("/getMenuByUser")
	@ResponseBody
	public String getMenuByUser(User user){	
		List<Menu> menuList = this.menuService.findMenuByUser(user);
		return JSON.toJSONString(menuList);
	}
	
	/**  /menu/getMenuStructByUser
	 * 获取该用户的 菜单结构
	 * @param user
	 * @return
	 */
	@RequestMapping("/getMenuStructByUser")
	@ResponseBody
	public String getMenuByUserStruct(User user){	
		List<Menu> menuList = this.menuService.findMenuByUser(user);
		//一级目录
		List<Object> firstArray = new ArrayList<Object>();
		for (Menu menu : menuList) {
			if(menu.getPid() == null) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", menu.getId());
				map.put("text", menu.getName());
				map.put("url", menu.getUrl());
				map.put("iconCls", menu.getImgName());
				firstArray.add(map);
			}
		}
		
		for (int i = 0; i < firstArray.size(); i++) {
			Map<String,Object> firstmap = (Map<String, Object>) firstArray.get(i);
			List<Object> children = new ArrayList<Object>();
			
			for (Menu menu : menuList) {
				if(firstmap.get("id").equals(menu.getPid())) {
					Map<String,Object> mapchild = new HashMap<String, Object>();
					mapchild.put("id", menu.getId());
					mapchild.put("text", menu.getName());
					mapchild.put("url", menu.getUrl());
					mapchild.put("iconCls", menu.getImgName());
					children.add(mapchild);
				}
			}
			firstmap.put("children", children);
		}
		
		
		return JSON.toJSONString(firstArray);
	}
	
	
	@RequestMapping("/getMenuByRole")
	@ResponseBody
	public String getMenuByRole(Role role){	
		List<Menu> menuList = this.menuService.findMenuByRole(role);
		return JSON.toJSONString(menuList);
	}

	@RequestMapping("/getMenuByMenu")
	@ResponseBody
	public String getMenuByMenu(Menu menu){	
		
		return JSON.toJSONString(this.menuService.findMenuByMenu(menu));
	}
	
	
	
	/** 
	 *   /role/allotMenu
	 *   给角色分配菜单 
	 * @param user_id ： 用户id 
	 * @param role_ids ： 角色id，逗号分隔
	 * @return
	 */
	@RequestMapping("/allotMenu")
	@ResponseBody
	public String allotMenuForRole(String role_id,String menu_ids){	
		String res  = this.menuService.allotMenuForRole(role_id,menu_ids);
		return res;
	}

	

	

	
}
