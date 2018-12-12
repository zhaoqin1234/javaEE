package com.hrxt.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hrxt.pojo.Button;
import com.hrxt.pojo.Deptment;
import com.hrxt.pojo.Menu;
import com.hrxt.pojo.Role;
import com.hrxt.pojo.User;
import com.hrxt.service.impl.DeptmentServiceImpl;

@Controller
@RequestMapping("/deptment")
public class DeptmentController {

	@Autowired
	private DeptmentServiceImpl deptmentServiceImpl;
	
	/**
	 * /deptment/opendept
	 * 	打开deptment的  管理界面
	 * @return
	 */
	@RequestMapping("/opendept")
	public String opendept(){	
		return "/authority/deptment";
	}
	
	@RequestMapping("/addDeptment")
	@ResponseBody
	public String insetDeptment(Deptment deptment){	
		deptment.setId(UUID.randomUUID().toString());
		int res  = this.deptmentServiceImpl.addDeptment(deptment);
		return res==0? "ok":" error ";
	}

	@RequestMapping("/updDeptment")
	@ResponseBody
	public String updDeptment(Deptment deptment){	
		int res  = this.deptmentServiceImpl.updDeptment(deptment);
		return res==0? "ok":" error ";
	}
	
	@RequestMapping("/delDeptmentByDeptment")
	@ResponseBody
	public String delDeptmentByDeptment(Deptment deptment){	
		int res  = this.deptmentServiceImpl.delDeptmentByDeptment(deptment);
		return res==0? "ok":" error ";
	}
	
	@RequestMapping("/getDeptmentsByUser")
	@ResponseBody
	public String getDeptmentsByUser(User user){	
		List<Deptment> deptmentsList = this.deptmentServiceImpl.findDeptmentsByUser(user);
		return JSON.toJSONString(deptmentsList);
	}
	
	@RequestMapping("/getDeptmentsByDeptment")
	@ResponseBody
	public String getDeptmentsByDeptment(Deptment deptment){	
		List<Deptment> deptmentsList = this.deptmentServiceImpl.findDeptmentsByDeptment(deptment);
		return JSON.toJSONString(deptmentsList);
	}
	
	//--未 开发
	@RequestMapping("/getDeptmentsByRole")
	@ResponseBody
	public String getDeptmentsByRole(Role role){	
		List<Deptment> deptmentsList = this.deptmentServiceImpl.findDeptmentsByRole(role);
		return JSON.toJSONString(deptmentsList);
	}
	//--未 开发
	@RequestMapping("/getDeptmentsByMenu")
	@ResponseBody
	public String getDeptmentsByMenu(Menu menu){	
		List<Deptment> deptmentsList = this.deptmentServiceImpl.findDeptmentsByMenu(menu);
		return JSON.toJSONString(deptmentsList);
	}
	
	

	

	

	
}
