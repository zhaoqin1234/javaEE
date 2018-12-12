package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.hrxt.App;
import com.hrxt.pojo.Menu;
import com.hrxt.pojo.User;
import com.hrxt.service.impl.MenuServiceImpl;

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
public class MenuServiceTest {

	@Autowired
	private MenuServiceImpl menuServiceImpl;
	
	@Test
	public void testAddMenu(){
		Menu menu = new Menu();
		menu.setId("2");
		menu.setName("菜单02");
		menu.setUrl("/menu/002");
		menu.setAppName("app02");
		menu.setHostName("127.0.0.1");
		menu.setHostPoart("8081");
		menu.setImgName("imge02");
		menu.setMark("mark002");
		this.menuServiceImpl.addMenu(menu);
	}
	
	@Test
	public void testUpddMenu(){
		Menu menu = new Menu();
		menu.setId("1");
		menu.setName("菜单0001");
		menu.setUrl("/menu/000001");
		menu.setAppName("app00001");
		menu.setHostName("127.0.0.10000");
		menu.setHostPoart("80810000");
		menu.setImgName("imge00001");
		menu.setMark("mark000001");
		int res = this.menuServiceImpl.updMenu(menu);
		System.out.println(res);
	}
	
	
	@Test
	public void testFindMenu(){
		User user = new User();
		user.setId("1");
		List<Menu> menuList = this.menuServiceImpl.findMenuByUser(user);
		System.out.println(JSON.toJSONString(menuList));
	}
	
}
