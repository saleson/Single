package com.single.app.component.metadate;

import org.apache.commons.lang3.ArrayUtils;

public enum MethodEnum {
	ADD("add"),
	DELETE("delete"),
	REPLACE("replace");
	
	private String method;
	
	MethodEnum(String method){
		this.method = method;
	}
	
	public String getMethod(){
		return this.method;
	}

	public static void main(String[] args) {
		String[] s = {"1","2","3"};
		s = ArrayUtils.remove(s, 1);
		for(String v : s){
			System.out.println(v);
		}
	}
}
