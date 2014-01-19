package com.single.app.view;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;


public abstract class BaseView implements View {

	protected String path;
	protected String pageCode;
	protected String title;
	protected Map<String, String> attributeManager;
	
	
	
	public BaseView(String path, String pageCode){
		this(path, pageCode, null);
	}
	
	public BaseView(String path, String pageCode, String title){
		this.path = path;
		this.pageCode = pageCode;
		this.title = title;
		this.attributeManager = new HashMap<String, String>();
	}
	
	public String getAttribute(String name){
		return attributeManager.get(name);
	}
	
	public void setAttribute(String name, String value){
		attributeManager.put(name, value);
	}
	
	public boolean removeAttribute(String name){
		return attributeManager.remove(name)!=null;
	}
	
	public Map<String, String> getAttributes(){
		return MapUtils.unmodifiableMap(attributeManager);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public String getPageCode() {
		return pageCode;
	}
	
	
}
