package com.mkrajcovic.mybooks.db;

import java.util.Map;
import java.util.Set;

public class QueryParams {

	private TypeMap params;

	public QueryParams() {
		this.params = new TypeMap();
	}

	public Object getParam(String name) {
		return params.get(name);
	}

	public Set<String> getParamNames() {
		return params.keySet();
	}

	/**
	 * NULL keys or values will not be inserted
	 */
	public void addParam(String name, Object value) {
		if (name != null && value != null) {
			params.put(name, value);
		}
	}

	public Object removeParam(String name) {
		return params.remove(name);
	}

	public Set<Map.Entry<String, Object>> getQueryEntries() {
		return this.params.entrySet();
	}

	public String getAsString(String paramName) {
		return params.getString(paramName);
	}
	
	public Integer getAsInteger(String paramName) {
		return params.getInteger(paramName);
	}

	public boolean isEmpty() {
		return params.isEmpty();
	}

	public void renameParam (String oldParamName, String newParamName) {
		Object value = this.params.remove(oldParamName);
		params.put(newParamName, value);
	}

	@Override
	public String toString() {
		return this.params.toString();
	}
}
