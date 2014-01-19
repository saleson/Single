package com.single.app.view.handler;

import java.util.HashMap;
import java.util.Map;

import com.single.app.view.View;

public class ViewManager {

	private static ViewManager manager = new ViewManager();
	
	private Map<String, View> mapping;
	
	private ViewManager(){
		mapping = new HashMap<String, View>();
	}
	
	public static ViewManager getManager(){
		return manager;
	}
	
	public View getView(String viewPath){
		View view = getViewPrototype(viewPath);
		return view;
	}
	
	public void putView(View view){
		putView(view.getPath(), view);
	}
	
	public void putView(String viewPath, View view){
		mapping.put(viewPath, view);
	}
	
	public View removeView(String viewPath){
		return mapping.remove(viewPath);
	}
	
	public View getViewPrototype(String viewPath){
		return mapping.get(viewPath);
	}
}
