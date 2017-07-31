package com.edu.security;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *  系统初始化类
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{WebAppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	//   /*,   /aaa/*,   *.do
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/*"};
	}
}
