package com.hrxt.service;

import java.util.List;

import com.hrxt.pojo.Deptment;
import com.hrxt.pojo.Menu;
import com.hrxt.pojo.Role;
import com.hrxt.pojo.User;

public interface DeptmentService {
	
	int addDeptment(Deptment button);
	
	int updDeptment(Deptment button);
	
	int delDeptmentByDeptment(Deptment button);
	
	int delDeptmentByButtonList(List<Deptment> buttonlist);
	
	List<Deptment> findDeptmentsByUser(User user);
	
	List<Deptment> findDeptmentsByRole(Role role);
	
	List<Deptment> findDeptmentsByMenu(Menu menu);
	
	List<Deptment> findDeptmentsByDeptment(Deptment deptment);

} 
