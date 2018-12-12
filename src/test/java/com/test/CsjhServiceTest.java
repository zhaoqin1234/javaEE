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
import com.hrxt.pojo.Csjh;
import com.hrxt.pojo.Page;
import com.hrxt.service.impl.CsjhServiceImpl;

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
public class CsjhServiceTest {

	@Autowired
	private CsjhServiceImpl CsjhServiceImpl;
	
	@Test
	public void testGetCsjh(){
		Csjh csjh = new Csjh();
		csjh.setStime("2018-11");
		Page<List<Csjh>> page = new Page<List<Csjh>>();
		Map<String,Object> mp =this.CsjhServiceImpl.getMonthPZList(csjh, page);
		System.out.println(JSON.toJSONString(mp));
	}
	
	@Test
	public void testInsertCsjh(){
		Csjh csjh1 =  new Csjh("西柳工区","西柳10断块","西柳10-120X","油井");
		Csjh csjh2 =  new Csjh("西柳工区","西柳10断块","西柳10-6","油井");
		Csjh csjh3 =  new Csjh("西柳工区","西柳10断块","西柳10-87","油井");
		Csjh csjh4 =  new Csjh("西柳工区","西柳10断块","西柳10-122X","油井");
		Csjh csjh5 =  new Csjh("西柳工区","西柳10断块","西柳10-167X","油井");
		Csjh csjh6 =  new Csjh("西柳工区","西柳10断块","西柳10-200","油井");
		Csjh csjh7 =  new Csjh("西柳工区","西柳10断块","西柳10-77X","油井");
		Csjh csjh8 =  new Csjh("西柳工区","西柳10断块","西柳10-149X","油井");
		Csjh csjh9 =  new Csjh("西柳工区","西柳10断块","西柳10-76X","油井");
		Csjh csjh10 = new Csjh("西柳工区","西柳10断块","西柳10-88","油井");
		Csjh csjh11 = new Csjh("西柳工区","西柳10断块","西柳10平4","油井");                                              
	/*
		String mp1 = this.CsjhServiceImpl.insertOrUpdate(csjh1);
		String mp2 = this.CsjhServiceImpl.insertOrUpdate(csjh2);
		String mp3 = this.CsjhServiceImpl.insertOrUpdate(csjh3);
		String mp4 = this.CsjhServiceImpl.insertOrUpdate(csjh4);
		String mp5 = this.CsjhServiceImpl.insertOrUpdate(csjh5);
		String mp6 = this.CsjhServiceImpl.insertOrUpdate(csjh6);
		String mp7 = this.CsjhServiceImpl.insertOrUpdate(csjh7);
		String mp8 = this.CsjhServiceImpl.insertOrUpdate(csjh8);
		String mp9 = this.CsjhServiceImpl.insertOrUpdate(csjh9);
		String mp10 =this.CsjhServiceImpl.insertOrUpdate(csjh10);
		String mp11 =this.CsjhServiceImpl.insertOrUpdate(csjh11);
		*/
		//System.out.println(JSON.toJSONString(mp));
	}
}
