package com.single.app.component.metadate;

public class ScriptInclude extends ContentModuleInclude{

	public ScriptInclude(String method) {
		super(method);
	}
	
	public ScriptInclude(String[] cssNames, MethodEnum method){
		super(cssNames, method);
	}
	
	public ScriptInclude(String[] cssNames, String method){
		super(cssNames, method);
	}
	
	@Override
	protected ScriptInclude clone() {
		return (ScriptInclude) super.clone();
	}
	
}
