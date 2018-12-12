package com.hrxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrxt.mapper.UserMapper;
import com.hrxt.pojo.User;
import com.hrxt.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper usersMapper;
	
	@Override
	public int addUser(User users) {
		return this.usersMapper.insertUser(users);
	}

	@Override
	public List<User> findUserAll() {
		return this.usersMapper.selectUsersAll();
	}

	@Override
	public User findUserById(String id) {
		return this.usersMapper.selectUsersById(id);
	}

	@Override
	public List<User> findUserByUser(User user) {
		return this.usersMapper.findUserByUser(user);
	}  
	
	@Override
	public int updateUser(User users) {
		return this.usersMapper.updateUser(users);
	}

	@Override
	public int deleteUserById(String id) {
		return this.usersMapper.deleteUserById(id);
	}
}
