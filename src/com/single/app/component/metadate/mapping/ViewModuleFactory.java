package com.single.app.component.metadate.mapping;

import com.single.app.component.metadate.ViewModule;

public class ViewModuleFactory {

	private static ViewModule module = new ViewModule("", "", "");
	
	public static ViewModule newViewModule(){
		return module.clone();
	}
}
