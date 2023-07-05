package com.mkrajcovic.mybooks.web;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class RequestCallTimeInterceptor implements HandlerInterceptor {

	private static final Logger LOG = Logger.getAnonymousLogger();

	private long start;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse respone, Object handler) {
		start = System.currentTimeMillis();
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {
		long processingTime = System.currentTimeMillis() - start;
		LOG.info("["+ request.getUserPrincipal().getName() + "] " + request.getRequestURL() + " (" + processingTime + "ms)");
	}

}
