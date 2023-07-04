package com.mkrajcovic.mybooks.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mkrajcovic.mybooks.controller.RequestCallTimeInterceptor;

@Configuration
public class WebMvcHandlers implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RequestCallTimeInterceptor());
	}

}
