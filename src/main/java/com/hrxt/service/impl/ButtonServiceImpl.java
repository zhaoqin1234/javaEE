package com.hrxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrxt.mapper.ButtonMapper;
import com.hrxt.pojo.Button;
import com.hrxt.pojo.Menu;
import com.hrxt.pojo.Role;
import com.hrxt.pojo.User;
import com.hrxt.service.ButtonService;

@Service
@Transactional
public class ButtonServiceImpl implements ButtonService {

	@Autowired
	private ButtonMapper buttonMapper;

	@Override
	public int addButton(Button button) {
		return buttonMapper.insertButton(button);
	}

	@Override
	public int updButton(Button button) {
		return buttonMapper.updateButton(button);
	}

	@Override
	public int delButtonByButton(Button button) {
		return buttonMapper.deleteButtonByButton(button);
	}

	@Override
	public int delButtonByButtonList(List<Button> buttonlist) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Button> findButtonsByUser(User user) {
		return buttonMapper.getButtonsByUser(user);
	}

	@Override
	public List<Button> findButtonsByRole(Role role) {
		return buttonMapper.getButtonsByRole(role);
	}

	@Override
	public List<Button> findButtonsByMenu(Menu menu) {
		return buttonMapper.getButtonsByMenu(menu);
	}

	@Override
	public List<Button> findButtonsByButton(Button button) {
		return buttonMapper.getButtonsByButton(button);
	}
	
	
	
	
	
	
}
