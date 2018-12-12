package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.hrxt.App;
import com.hrxt.pojo.User;
import com.hrxt.service.impl.UserServiceImpl;

/**
 * SpringBoot测试类
 *@RunWith:启动器 
 *SpringJUnit4ClassRunner.class：让junit与spring环境进行整合
 *
 *@SpringBootTest(classes={App.class}) 1,当前类为springBoot的测试类
 *@SpringBootTest(classes={App.class}) 2,加载SpringBoot启动类。启动springBoot
 *
 *junit与spring整合 @Contextconfiguartion("classpath:applicationContext.xml")
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes={App.class})
public class UserServiceTest {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Test
	public void testAddUser(){
		for(int i=5;i<20;i++) {
			User users = new User();
			users.setId(""+i);
			users.setName("zhaoqin-"+i);
			users.setNickname("赵钦--"+i);
			users.setGender(0);
			users.setAddress("河北采油三厂--"+i);
			users.setTelNumber("133334");
			users.setDeptId("1");
			users.setDeptName("部门000"+i);
			users.setPassWord("123456");
			this.userServiceImpl.addUser(users);
		}
	}
	@Test
	public void testUpdUser(){
		User users = new User();
		users.setId("1");
		users.setName("zhaoqin33");
		users.setNickname("赵钦33");
		users.setGender(0);
		users.setAddress("河北采油三厂333");
		users.setTelNumber("131313131333===");
		users.setDeptId("122");
		users.setDeptName("部门000122");
		users.setPassWord("123456222");
		this.userServiceImpl.updateUser(users);
	}
	
	@Test
	public void testGetAllUser(){
		
		List<User> userlist = this.userServiceImpl.findUserAll();
		for (User user : userlist) {
			System.out.println(JSON.toJSONString(user));
		}
	}
	
	
	@Test
	public void testGetUseByrUser(){
		User user = new User();
		user.setDeptId("");
		user.setId("1");
		user.setName("");
		user.setNickname("");
		user.setGender(0);
		user.setAddress("");
		user.setTelNumber("");
		user.setDeptId("");
		user.setDeptName("");
		user.setPassWord("");
		List<User> userList =  this.userServiceImpl.findUserByUser(user);
			
		
		System.out.println(JSON.toJSONString(user));
	}
}
