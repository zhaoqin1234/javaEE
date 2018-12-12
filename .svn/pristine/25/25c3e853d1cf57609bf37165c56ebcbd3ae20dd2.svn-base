package com.hrxt.service;

import java.util.List;

import com.hrxt.pojo.Role;
import com.hrxt.pojo.User;

public interface RoleService {
	
	int addRole(Role role);
	
	int updRole(Role role);
	
	int delRole(Role role);
	
	int delRole(List<Role> rolelist);
	
	List<Role> findRolesByUser(User user);
	
	List<Role> findRoles(Role role);
	
	Role findRoleBy(Role role);
	
	/**
	 *  为某个用户分配角色
	 * @param user_id	:   用户id
	 * @param role_ids	:  roleids  逗号分隔
	 * @return
	 */
	public String allotRoleForUser(String user_id,String role_ids);

} 
