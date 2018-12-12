package com.hrxt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


/**
 * 
 * @author ZhaoQin
 *
 */
@WebFilter(filterName="FirstFilter",urlPatterns={"/button/*","/block/*","/csjh/*","/deptment/*","/jcjhOption/*","/menu/*","/monthPZ/*","/oiljcjh/*","/waterjcjh/*","/xjscyxk/*"})
//@WebFilter(filterName="FirstFilter",urlPatterns="/first")
public class FirstFilter implements Filter {
	@Override
	public void destroy() {
	// TODO Auto-generated method stub
	}
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,FilterChain arg2)
							throws IOException, ServletException {
		//arg0.get
		System.out.println("进入 Filter");
		arg2.doFilter(arg0, arg1);
		System.out.println("离开 Filter");
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	// TODO Auto-generated method stub
	}
} 