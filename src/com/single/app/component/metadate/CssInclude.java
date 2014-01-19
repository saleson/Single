package com.single.app.component.metadate;

public class CssInclude extends ContentModuleInclude{

	public CssInclude(String method) {
		super(method);
	}
	
	public CssInclude(String[] cssNames, MethodEnum method){
		super(cssNames, method);
	}
	
	public CssInclude(String[] cssNames, String method){
		super(cssNames, method);
	}
	
	@Override
	protected CssInclude clone() {
		return (CssInclude) super.clone();
	}
}
