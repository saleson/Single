package com.oscache.test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestT implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Map<String, String> map = new HashMap<String, String>();
	
	
	public void put(String key, String value){
		map.put(key, value);
	}
	
	public String get(String key){
		return map.get(key);
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
}
