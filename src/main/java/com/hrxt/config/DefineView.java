package com.hrxt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@Configuration
public class DefineView extends WebMvcConfigurerAdapter{
	
	@Override
	public void addViewControllers(ViewControllerRegistry register) {
		register.addViewController("/").setViewName("forward:/login,html");
		register.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(register);
	}


}
