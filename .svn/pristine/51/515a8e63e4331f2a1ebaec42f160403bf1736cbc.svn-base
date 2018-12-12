package com.hrxt.mapper;

import java.util.List;
import java.util.Map;

import com.hrxt.pojo.Role;
import com.hrxt.pojo.User;

public interface RoleMapper {
	
	
	public int insertRole(Role role);
	
	public int deleteRole(Role role);
	
	public int deleteRoleById(String id);
	
	public int deleteRoleByIds(String [] ids);
	
	public int updateRole(Role role);
	
	public List<Role> getRoles(Role role);
	
	public List<Role> getRolesByUser(User user);
	
	public Role getRoleByRole(Role role);
	
	public int insertUser_roleTable(Map<String, Object> param);
	
	public int deleteUser_roleTableByUserId(String user_id);

}
