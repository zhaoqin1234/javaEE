package com.hrxt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSON;

public class BaseController {
	private HttpServletResponse response;
	//private HttpServletRequest request;
	@ModelAttribute
	public void setResAndReq(HttpServletResponse response,HttpServletRequest request){
		this.response=response;
		//this.request=request;
	}
	/***
	 *  
	 * @param object  
	 */
	public void writeJson(Object object) {		 
		try {
			String json=JSON.toJSONString(object);
			System.out.println("json"+json);
			this.response.setContentType("text/html;charset=utf-8");
			this.response.getWriter().write(json);
			this.response.getWriter().flush();
			this.response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
