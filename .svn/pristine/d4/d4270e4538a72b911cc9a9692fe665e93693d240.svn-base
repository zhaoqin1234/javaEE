package com.hrxt.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrxt.mapper.RoleMapper;
import com.hrxt.pojo.Role;
import com.hrxt.pojo.User;
import com.hrxt.service.RoleService;

import oracle.jdbc.OracleTypes;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public int addRole(Role role) {
		return roleMapper.insertRole(role);
	}

	@Override
	public int updRole(Role role) {
		return roleMapper.updateRole(role);
	}

	@Override
	public int delRole(Role role) {
		return roleMapper.deleteRole(role);
	}

	@Override
	public int delRole(List<Role> rolelist) {
		// TODO
		return 0;//roleMapper.deleteRoleByIds(ids);
	}

	@Override
	public List<Role> findRolesByUser(User user) {
		return roleMapper.getRolesByUser(user);
	}

	@Override
	public Role findRoleBy(Role role) {
		return roleMapper.getRoleByRole(role);
	}

	@Override
	public List<Role> findRoles(Role role) {
		return roleMapper.getRoles(role);
	}
 
	/**
	 *  为某个用户赋角色
	 *  user_id
	 *  role_ids id序列逗号分隔
	 */
	@Override
	public String allotRoleForUser(String user_id, String role_ids) {
		
		List<String> roleIds = Arrays.asList(role_ids.split(","));
		int ret  = 0;
		
		ret = roleMapper.deleteUser_roleTableByUserId(user_id);
		
		
		for (String id : roleIds) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("user_id", user_id);
			paramMap.put("role_id", id);
			ret = roleMapper.insertUser_roleTable(paramMap);
		}
		
		return ret == 1?"ok":"error";
	}
	
	
	
	
}
