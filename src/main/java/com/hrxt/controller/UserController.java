package com.hrxt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hrxt.pojo.User;
import com.hrxt.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping("/addUser")
	@ResponseBody
	public String insetUser(User user){	
		user.setId(UUID.randomUUID().toString());
		int res  = this.userService.addUser(user);
		return res==0? "ok":" error ";
	}

	@RequestMapping("/updUser")
	@ResponseBody
	public String updUser(User user){	
		int res  = this.userService.updateUser(user);
		return res==0? "ok":" error ";
	}
	
	@RequestMapping("/delUserById")
	@ResponseBody
	public String delUserbyId(String id){	
		int res  = this.userService.deleteUserById(id);
		return res==0? "ok":" error ";
	}
	
	@RequestMapping("/getUser")
	@ResponseBody
	public String getUser(User user){	
		List<User> userList = this.userService.findUserAll();
		return JSON.toJSONString(userList);
	}
	
	//通过id查找用户信息
	@RequestMapping("/getUserById")
	@ResponseBody
	public String getUserBy(String id){	
		User user = this.userService.findUserById(id);
		return JSON.toJSONString(user);
	}
	
	//通过id查找用户信息
	/**
	 *  /user/getUserByUser
	 * @param user
	 * @return
	 */
	@RequestMapping("/getUserByUser")
	@ResponseBody
	public String getUserByuser(User user){	
		List<User> userlist = this.userService.findUserByUser(user);
		
		return JSON.toJSONString(userlist);
	}
	
	
	//检查用户名是否重复
	@RequestMapping("/getUserByName")
	@ResponseBody
	public String getUserByName(String name){
		User user = new User();
		user.setName(name);
		List<User> userlist = this.userService.findUserByUser(user);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("length", userlist.size());
		map.put("data", userlist);
		return JSON.toJSONString(map);
	}
	
}