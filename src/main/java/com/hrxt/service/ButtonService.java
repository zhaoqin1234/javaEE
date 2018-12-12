package com.hrxt.service;

import java.util.List;

import com.hrxt.pojo.Button;
import com.hrxt.pojo.Menu;
import com.hrxt.pojo.Role;
import com.hrxt.pojo.User;

public interface ButtonService {
	
	int addButton(Button button);
	
	int updButton(Button button);
	
	int delButtonByButton(Button button);
	
	int delButtonByButtonList(List<Button> buttonlist);
	
	List<Button> findButtonsByUser(User user);
	
	List<Button> findButtonsByRole(Role role);
	
	List<Button> findButtonsByMenu(Menu menu);
	
	List<Button> findButtonsByButton(Button button);

} 