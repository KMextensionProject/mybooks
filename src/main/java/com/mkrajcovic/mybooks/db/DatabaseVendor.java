package com.mkrajcovic.mybooks.db;

public enum DatabaseVendor {

	ORACLE,
	POSTGRES,
	MYSQL,
	UNKNOWN;

	public static DatabaseVendor getByName(String databaseVendorName) {
		if (databaseVendorName == null) {
			return UNKNOWN;
		}
		switch(databaseVendorName.toLowerCase()) {
		case "oracle":
			return ORACLE;
		case "postgresql":
			return POSTGRES;
		case "mysql":
			return MYSQL;
		default :
			return UNKNOWN;
		}
	}
}
