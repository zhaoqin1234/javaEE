package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hrxt.App;
import com.hrxt.pojo.Button;
import com.hrxt.pojo.Deptment;
import com.hrxt.service.impl.ButtonServiceImpl;
import com.hrxt.service.impl.DeptmentServiceImpl;

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
public class ButtonServiceTest {

	@Autowired
	private ButtonServiceImpl buttonServiceImpl;
	
	@Test
	public void testAddButton(){
		
		
		for (int i = 0; i < 10; i++) {
			Button button = new Button();
			button.setId(""+i);
			button.setKey("update--"+i);
			button.setMark("XX"+i+i+"管理页面提交按钮");
			button.setName("提交--"+i);
			//button.setSuatus("00--"+i);//按钮处于启用状态
			this.buttonServiceImpl.addButton(button);
		}
		
	}
	
	@Test
	public void testupdateButton(){
		
		
		for (int i = 0; i < 10; i++) {
			Button button = new Button();
			button.setId(""+i);
			button.setKey("upd-upd-"+i);
			button.setMark("XX"+i+i+"==修改面提交按钮");
			button.setName("提交-update-"+i);
			//button.setSuatus("00-update-"+i);//按钮处于启用状态
			this.buttonServiceImpl.updButton(button);
		}
		
	}
	
	
	@Test
	public void testDeleteButton(){
		
		
		for (int i = 0; i < 5; i++) {
			Button button = new Button();
			button.setId(""+i);
			this.buttonServiceImpl.delButtonByButton(button);
		}
		
	}
	
	
}
