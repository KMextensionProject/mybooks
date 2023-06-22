package com.mkrajcovic.mybooks.web;

import static com.mkrajcovic.mybooks.utils.StringUtils.isBoolean;
import static com.mkrajcovic.mybooks.utils.StringUtils.isNumeric;

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
		queryMap.forEach((key, values) -> queryParams.addParam(key, values[0].isEmpty() ? null : reType(values[0])));
		return queryParams;
	}

	private Object reType(String paramValue) {
		if (isBoolean(paramValue)) {
			return Boolean.valueOf(paramValue);
		} else if (isNumeric(paramValue)) {
			return Integer.valueOf(paramValue);
		} else {
			return paramValue;
		}
	}
}
