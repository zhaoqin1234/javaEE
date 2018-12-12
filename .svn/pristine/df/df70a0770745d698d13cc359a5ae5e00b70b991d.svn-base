package com.test;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.hrxt.pojo.Deptment;

public class Test {
	
	public static void main(String[] args) {
		
		String str = "'MD5Hegy5rB','Vw6AoEs6zm'";
		String [] strlist =str.split(",");
		for (String string : strlist) {
			System.out.println(string);
		}
		List strliat = Arrays.asList(strlist);
		
		Deptment deptment = new Deptment();
		deptment.setId("");
		deptment.setName("部门-");
		deptment.setPid("");
		deptment.setStatus("启动");
		deptment.setImage_name("image-");
		
		System.out.println("---------------"+JSON.toJSONString(deptment));
		
		
		
	}

}
