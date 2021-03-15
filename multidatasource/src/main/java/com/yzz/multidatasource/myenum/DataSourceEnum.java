package com.yzz.multidatasource.myenum;

public enum DataSourceEnum {
	
	DEFAULT("defaultDataSource"),
	PROD_A("prodADataSource"),
	PROD_B("prodBDataSource"),
	PROD_C("prodCDataSource");
	
	private String value;
	DataSourceEnum(String value){this.value=value;}
	public String getValue() {
		return value;
	}
}
