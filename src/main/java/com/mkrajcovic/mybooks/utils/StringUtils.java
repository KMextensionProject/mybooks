package com.mkrajcovic.mybooks.utils;

public class StringUtils {

	public static boolean isBoolean(String val) {
		if ("true".equalsIgnoreCase(val) || "false".equalsIgnoreCase(val)) {
			return true;
		}
		return false;
	}

	/**
	 * Check if the specified string contains only digits
	 * @param val
	 * @return
	 */
	public static boolean isNumeric(String val) {
		if (val == null) {
			return false;
		}
		for (int i = 0; i < val.length(); i++) {
			if (!Character.isDigit(val.charAt(i))) { // . -
				return false;
			}
		}
		return true;
	}

//	public static boolean isDateTime(String val) {
//		if (val == null) {
//			return false;
//		}
//	}

}
