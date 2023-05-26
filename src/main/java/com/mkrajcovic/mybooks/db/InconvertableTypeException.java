package com.mkrajcovic.mybooks.db;

public class InconvertableTypeException extends RuntimeException{
	
	public InconvertableTypeException(Object value, Class<?> type) {
		super( value +  " cannot be converted to " + type);
	}

}
