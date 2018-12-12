package com.hrxt.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hrxt.pojo.Role;
import com.hrxt.pojo.User;
import com.hrxt.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	
	@Autowired
	private RoleService roleService;
	
	
	/**
	 *   /role/openRole
	 *   打开role查询界面  
	 * @param role
	 * @return
	 */
	@RequestMapping("/openRole")
	public String openRole(Role role){	
		return "/authority/role";
	}

	@RequestMapping("/addRole")
	@ResponseBody
	public String insetRole(Role role){	
		role.setId(UUID.randomUUID().toString());
		int res  = this.roleService.addRole(role);
		return res==0? "ok":" error ";
	}

	@RequestMapping("/updRole")
	@ResponseBody
	public String updRole(Role role){	
		int res  = this.roleService.updRole(role);
		return res==0? "ok":" error ";
	}
	
	@RequestMapping("/delRole")
	@ResponseBody
	public String delRole(Role role){	
		int res  = this.roleService.delRole(role);
		return res==0? "ok":" error ";
	}
	
	
	/** 
	 *   /role/allotRole
	 *   给用户分配权限   
	 * @param user_id ： 用户id 
	 * @param role_ids ： 角色id，逗号分隔
	 * @return
	 */
	@RequestMapping("/allotRole")
	@ResponseBody
	public String allotRoleForUser(String user_id,String role_ids){	
		String res  = this.roleService.allotRoleForUser(user_id,role_ids);
		return res;
	}
	
	
	/**
	 * 获取所有的role 
	 * @param role
	 * @return
	 */
	@RequestMapping("/getRoles")
	@ResponseBody
	public String getRoles(Role role){	
		return JSON.toJSONString(this.roleService.findRoles(role));
	}
	
	
	
	@RequestMapping("/getRoleByRole")
	@ResponseBody
	public String getRoleByRole(Role role){	
		Role role2= this.roleService.findRoleBy(role);
		return JSON.toJSONString(role2);
	}
	
	
	@RequestMapping("/getRoleByUser")
	@ResponseBody
	public String getRoleByUser(User user){	
		List<Role> roleList = this.roleService.findRolesByUser(user);
		return JSON.toJSONString(roleList);
	}
	
	

	

	

	
}
