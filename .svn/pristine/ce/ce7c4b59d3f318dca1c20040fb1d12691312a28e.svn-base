package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.hrxt.App;
import com.hrxt.pojo.Deptment;
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
public class DeptmentServiceTest {

	
	
	@Autowired
	private DeptmentServiceImpl deptmentServiceImpl;
	
	@Test
	public void testAddDeptment(){
		for (int i = 11; i < 15; i++) {
			Deptment deptment = new Deptment();
			deptment.setId(i+"");
			deptment.setName("部门-"+i);
			deptment.setPid("");
			deptment.setStatus("启动"+i);
			deptment.setImage_name("image-"+i);
			this.deptmentServiceImpl.addDeptment(deptment);
		}
	}
	@Test
	public void testAddDeptment22(){
		//for (int i = 11; i < 15; i++) {
			Deptment deptment = new Deptment();
			deptment.setId(11+"");
			deptment.setName("部门-");
			deptment.setPid("");
			deptment.setStatus("启动");
			deptment.setImage_name("image-");
			this.deptmentServiceImpl.addDeptment(deptment);
		//}
	}
	@Test
	public void testupdateDeptment(){
		
		for (int i = 7; i < 10; i++) {
			Deptment deptment = new Deptment();
			deptment.setId(i+"");
			deptment.setName("部--门-"+i);
			deptment.setPid("-aa-"+(i-6));
			deptment.setStatus("启--动"+i);
			deptment.setImage_name("i-m-a-g-e-"+i);
			deptment.setMark("b-z-"+i);
			this.deptmentServiceImpl.updDeptment(deptment);
		}
		
	}
	
	
	@Test  
	public void testDeleteDeptment(){
		
		
		for (int i = 0; i < 5; i++) {
			Deptment deptment = new Deptment();
			deptment.setId(""+i);
			this.deptmentServiceImpl.delDeptmentByDeptment(deptment);
		}
		
	}
	
	@Test  
	public void testGetAllDeptment(){
		
			Deptment deptment = new Deptment();
	
			List<Deptment> list = this.deptmentServiceImpl.findDeptmentsByDeptment(deptment);
		
			System.out.println(JSON.toJSONString(list));
	}
	
	
}
