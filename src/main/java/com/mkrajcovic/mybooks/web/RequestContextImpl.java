package com.mkrajcovic.mybooks.web;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.mkrajcovic.mybooks.enums.LibraryRole;

@Component
@RequestScope
public class RequestContextImpl implements RequestContext {

	@Override
	public String getUsername() {
		return SecurityContextHolder.getContext()
				.getAuthentication()
				.getName();
	}

	public boolean isUserInRole(LibraryRole role) {
		return listUserRoles().contains("ROLE_" + role);
	}

	private Set<String> listUserRoles() {
		return SecurityContextHolder.getContext()
				.getAuthentication()
				.getAuthorities()
			.stream()
			.map(e -> e.getAuthority())
			.collect(Collectors.toSet());
	}
}
