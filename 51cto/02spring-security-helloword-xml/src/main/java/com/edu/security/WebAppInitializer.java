package com.edu.security;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

/**
 *  系统初始化类
 */
public class WebAppInitializer extends AbstractDispatcherServletInitializer {

	@Override
	protected WebApplicationContext createServletApplicationContext() {
		XmlWebApplicationContext context = new XmlWebApplicationContext();
		context.setConfigLocation("classpath:applicationContext.xml");
		return context;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/*"};
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		return null;
	}
	
	protected String getServletName() {
		return DEFAULT_SERVLET_NAME;
	}
}
