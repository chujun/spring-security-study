package com.edu.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 入口类
 */
@EnableWebMvc
@EnableWebSecurity
@ComponentScan("com.edu.security")
public class WebAppConfig {

}
