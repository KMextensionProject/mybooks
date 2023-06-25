package com.mkrajcovic.mybooks.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mkrajcovic.mybooks.web.PagingMethodArgumentResolver;
import com.mkrajcovic.mybooks.web.QueryMethodArgumentResolver;

@EnableWebMvc
public class WebMvcCustomConfig implements WebMvcConfigurer {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new QueryMethodArgumentResolver());
		resolvers.add(new PagingMethodArgumentResolver());
	}

	@Bean
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}
