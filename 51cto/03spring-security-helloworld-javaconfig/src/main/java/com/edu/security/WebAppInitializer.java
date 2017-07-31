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

	/**
	 * /
	 * 拦截所有资源,包括静态资源,动态资源,除了jsp页面
	 * /*
	 * 拦截所有资源,动态资源,包括jsp页面
	 * @return
     */
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

}
