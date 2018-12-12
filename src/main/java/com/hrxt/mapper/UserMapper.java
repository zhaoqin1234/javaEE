package com.hrxt.mapper;

import java.util.List;

import com.hrxt.pojo.User;

public interface UserMapper {
	
	int insertUser(User users);
	
	List<User> selectUsersAll();
	
	List<User> findUserByUser(User user);
	
	User selectUsersById(String id);
	
	int updateUser(User users);
	
	int deleteUserById(String id);
}
