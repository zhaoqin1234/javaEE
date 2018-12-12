package com.hrxt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hrxt.pojo.Org;
import com.hrxt.pojo.Site;
import com.hrxt.service.OrgService;
import com.hrxt.utils.JSONutils;

@Controller
@RequestMapping("/org")
public class OrgController {

	@Autowired
	private OrgService orgService;
	 
	
	//  /org/getAllOrg
	@RequestMapping("/getAllOrg")
	@ResponseBody
	public List<Org> getWell(){
		List<Org> orglist = this.orgService.getAllOrgMsg();
		//System.out.println(JSONutils.Object2String(orglist));
		return orglist;
	}
	
	//  /org/getjsp
	@RequestMapping("/getjsp")
	public ModelAndView test(String orgId,ModelAndView model){
		
		String orglist = this.orgService.getOrgMsgByOrgId(orgId);
		
		model.addObject("list", orglist);
		model.setViewName("hello");
		return model;
	}
	
	/**
	 *   /org/getPublicSite
	 *   获取常用site 的
	 *   bean的字段
	 *   	site_id
	 *   	site_name
	 * @return
	 */
	@RequestMapping("/getPublicSite")
	@ResponseBody
	public String getPublicSite(){
		List<Site> orglist = this.orgService.getPublicSite();
		return JSONutils.Object2String(orglist);
	}
	
	
}