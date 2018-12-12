package com.hrxt.mapper;

import java.util.List;

import com.hrxt.pojo.Button;
import com.hrxt.pojo.Deptment;
import com.hrxt.pojo.Menu;
import com.hrxt.pojo.Role;
import com.hrxt.pojo.User;

public interface DeptmentMapper {
	
	public int insertDeptment(Deptment deptment);

	public int updateDeptment(Deptment deptment);
	
	public int deleteDeptmentByDeptment(Deptment deptment);
	
	public int deleteDeptmentnById(String id);
	
	public int deleteDeptmentByIds(String [] ids);
	
	public List<Deptment> getDeptments();
	
	public List<Deptment> getDeptmentsByUser(User user);
	
	public List<Deptment> getDeptmentsByRole(Role role);
	
	public List<Deptment> getDeptmentsByMenu(Menu menu);
	
	public List<Deptment> getDeptmentsByDeptment(Deptment deptment);
	
	
	public Button getDeptmentByDeptment(Button button);
	
	public Button getDeptmentById(String id);

}
