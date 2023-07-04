package com.mkrajcovic.mybooks.web;

import com.mkrajcovic.mybooks.enums.LibraryRole;

public interface RequestContext {

	public String getUsername();

	public boolean isUserInRole(LibraryRole role);

}
