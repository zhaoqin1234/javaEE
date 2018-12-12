package com.hrxt.service;

import java.util.List;

import com.hrxt.pojo.User;

public interface UserService {
	
	int addUser(User users);
	
	List<User> findUserAll();
	
	List<User> findUserByUser(User user);
	
	User findUserById(String id);
	
	int updateUser(User users);
	
	int deleteUserById(String id);
} 
