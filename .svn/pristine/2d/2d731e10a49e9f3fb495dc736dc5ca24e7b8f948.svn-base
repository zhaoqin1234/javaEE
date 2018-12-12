package com.hrxt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hrxt.pojo.User;
import com.hrxt.service.UserService;

@Controller
public class IndexController {

	
	@Autowired
	private UserService userService;
	
	
	//测试
	@RequestMapping("/index")
	public String indexJsp(){
		return "index";
	}
	
	
	//测试
	@RequestMapping("/index2")
	public String indexJsp2(String id,HttpServletResponse response,HttpServletRequest request){
		
		if(id == null) {
			return "redirect:login.html";
		}
		
		HttpSession session = request.getSession();
		User user = userService.findUserById(id);
		session.setAttribute("user",user);
	       
		request.setAttribute("id", id);  //携带用户的id
		return "index2";
	}

	
	
	@RequestMapping("/root/{page}")
	public String showPage3(@PathVariable String page){
		return  page;
	}

	
}
