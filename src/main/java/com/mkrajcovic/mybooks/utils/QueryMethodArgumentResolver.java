package com.mkrajcovic.mybooks.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.mkrajcovic.mybooks.db.QueryParams;

public class QueryMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return QueryParams.class.equals(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
								  ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest,
								  WebDataBinderFactory binderFactory) throws Exception {

		Map<String, String[]> queryMap = webRequest.getNativeRequest(HttpServletRequest.class).getParameterMap();
		QueryParams queryParams = new QueryParams();
		queryMap.forEach((key, values) -> addParamWithBasicTypeConversion(queryParams, key, values));
		return queryParams;
	}

	private void addParamWithBasicTypeConversion(QueryParams queryParams, String key, String[] values) {
		if (key.endsWith("id")) {
			// TODO: process list of values..
			queryParams.addParam(key, Integer.valueOf(values[0]));
		} else if ("truefalse".contains(values[0])) {
			queryParams.addParam(key, Boolean.valueOf(values[0]));
		} else {
			queryParams.addParam(key, values[0]);
		}
	}
}
