package com.single.app.view;

import java.util.Map;


public interface View {

	public String getPageCode();
	
	public void setTitle(String title);
	
	public String getTitle();
	
	public String getPath();
	
	public String getAttribute(String name);
	
	public void setAttribute(String name, String value);
	
	public boolean removeAttribute(String name);
	
	public Map<String, String> getAttributes();
	
	public ViewTable[] getViewTables();
	
}
