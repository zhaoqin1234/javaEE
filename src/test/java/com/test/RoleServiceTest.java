package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.hrxt.App;
import com.hrxt.pojo.Role;
import com.hrxt.service.impl.RoleServiceImpl;

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
public class RoleServiceTest {

	@Autowired
	private RoleServiceImpl roleServiceImpl;
	
	@Test
	public void testAddRole(){
		Role role = new Role();
		role.setId("3");
		role.setName("注水管理员3333");
		role.setDeptId("2");
		role.setMark("wojiushi 33333");
		this.roleServiceImpl.addRole(role);
	}
	
	
	@Test
	public void testUpdateRole(){
		Role role = new Role();
		role.setId("1");
		role.setName("注水管理员3333");
		role.setDeptId("2");
		role.setMark("wojiushi修改成功");
		this.roleServiceImpl.updRole(role);
	}

	@Test
	public void testGetRoleByRole(){
		Role role = new Role();
		role.setId("1");
	
		Role role1 = this.roleServiceImpl.findRoleBy(role);
		
		System.out.println(JSON.toJSONString(role1));
	}
	
	
	@Test
	public void testDelRole(){
		Role role = new Role();
		role.setId("1");
	
		int res = this.roleServiceImpl.delRole(role);
		
		System.out.println("res:=="+JSON.toJSONString(res));
	}
}
