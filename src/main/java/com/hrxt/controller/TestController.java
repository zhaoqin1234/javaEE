package com.hrxt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {


	@RequestMapping("/openjsp")
	public String editUser(){
		
		
        
		return "userList";
	}
	



}
