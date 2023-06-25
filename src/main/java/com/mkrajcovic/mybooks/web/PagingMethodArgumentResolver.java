package com.mkrajcovic.mybooks.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.mkrajcovic.mybooks.db.Paging;

public class PagingMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return Paging.class.equals(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
								  ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest,
								  WebDataBinderFactory binderFactory) throws Exception {

		HttpServletRequest nativeRequest = webRequest.getNativeRequest(HttpServletRequest.class);
		String rangeString = nativeRequest.getHeader("Range");

		if (rangeString == null) {
			return null; // this is null safe to pass paging object to DB
			// or use default?
			// return new Paging(0, 100);
		}

		return parsePagingRange(rangeString);
	}

	private Paging parsePagingRange(String rangeHeaderValue) {
		String[] rangeParts = rangeHeaderValue.split("-");
		int from = Integer.parseInt(rangeParts[0]);
		int to = Integer.parseInt(rangeParts[1]);
		Assert.isTrue(from >= 0 && to >= 0, "Range header start and end values must be both equal or greater than zero");
		Assert.isTrue(from <= to, "The Range header end value must be equal or greater than the start value");
		return new Paging(from, to);
	}

}
