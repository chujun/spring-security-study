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
	 * url mapping 通常有三种配置方式
	 * 
	 * 1: /*
	 * 2: /aaa/*, /aaa/bbb/*
	 * 3: *.do, *.action
	 * 
	 * 第一种配置方式 servlet Path 为空
	 * 第二种配置方式 servlet Path /aaa, /aaa/bbb/
	 * 第三种配置方式servlet Path /aaa/bbb.do, /aaa/bbb.action
	 * 
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[]{"*.do"};
	}
	//这里的配置不影响静态资源访问路径
	//http://localhost:8000/mvc/public/html/list.html

	//支持多servlet path访问
	//1.return new String[]{"/*"}
	//http://localhost:8000/mvc/home
//	==================
//			req.getRequestURI(): /mvc/home
//	req.getContextPath(): /mvc
//	req.getServletPath():
//			req.getPathInfo(): /home
//	==================


	//2.return new String[]{"/api/*"}
	//http://localhost:8000/mvc/api/home
//	==================
//			req.getRequestURI(): /mvc/api/home
//	req.getContextPath(): /mvc
//	req.getServletPath(): /api
//	req.getPathInfo(): /home
//	==================

	//3.return new String[]{"/v1/*","/v2/*"}
	//v1可以访问,v2也可以访问
	//http://localhost:8000/mvc/v1/home
	//http://localhost:8000/mvc/v2/home

//	==================
//			req.getRequestURI(): /mvc/v1/home
//	req.getContextPath(): /mvc
//	req.getServletPath(): /v1
//	req.getPathInfo(): /home
//	==================

//	==================
//			req.getRequestURI(): /mvc/v2/home
//	req.getContextPath(): /mvc
//	req.getServletPath(): /v2
//	req.getPathInfo(): /home
//	==================
	//4.new String[]{"*.do"}
	//http://localhost:8000/mvc/home.do
//	==================
//			req.getRequestURI(): /mvc/home.do
//			req.getContextPath(): /mvc
//	req.getServletPath(): /home.do
//			req.getPathInfo(): null
//			==================
}
