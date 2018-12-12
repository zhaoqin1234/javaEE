package com.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.hrxt.App;
import com.hrxt.pojo.Page;
import com.hrxt.pojo.Xjscyxk;
import com.hrxt.service.impl.XjscyxkServiceImpl;

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
public class XjscyxkServiceTest {

	@Autowired
	private XjscyxkServiceImpl xjscyxkServiceImpl;
	
	@Test
	public void testGetXjscyxk(){
		Xjscyxk xjscyxk = new Xjscyxk();
		//xjscyxk.setStime("2018-11");
		Page<List<Xjscyxk>> page = new Page<List<Xjscyxk>>();
		Map<String,Object> mp = this.xjscyxkServiceImpl.getXjscyxkList(xjscyxk, page);
		
		System.out.println(JSON.toJSONString(mp));
	}
	
	
}
