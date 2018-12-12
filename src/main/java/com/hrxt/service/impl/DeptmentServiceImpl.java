package com.hrxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrxt.mapper.DeptmentMapper;
import com.hrxt.pojo.Deptment;
import com.hrxt.pojo.Menu;
import com.hrxt.pojo.Role;
import com.hrxt.pojo.User;
import com.hrxt.service.DeptmentService;

@Service
@Transactional
public class DeptmentServiceImpl implements DeptmentService {

	@Autowired
	private DeptmentMapper deptmentMapper;

	

	@Override
	public int addDeptment(Deptment deptment) {
		return deptmentMapper.insertDeptment(deptment);
	}

	@Override
	public int updDeptment(Deptment deptment) {
		return deptmentMapper.updateDeptment(deptment);
	}

	@Override
	public int delDeptmentByDeptment(Deptment deptment) {
		return deptmentMapper.deleteDeptmentByDeptment(deptment);
	}

	@Override
	public int delDeptmentByButtonList(List<Deptment> deptmentlist) {
		// TODO Auto-generated method stub
		return 0;//deptmentMapper;
	}

	@Override
	public List<Deptment> findDeptmentsByUser(User user) {
		return deptmentMapper.getDeptmentsByUser(user);
	}

	@Override
	public List<Deptment> findDeptmentsByRole(Role role) {
		return deptmentMapper.getDeptmentsByRole(role);
	}

	@Override
	public List<Deptment> findDeptmentsByMenu(Menu menu) {
		return deptmentMapper.getDeptmentsByMenu(menu);
	}

	@Override
	public List<Deptment> findDeptmentsByDeptment(Deptment deptment) {
		return deptmentMapper.getDeptmentsByDeptment(deptment);
	}

	
	
	
	
	
}
