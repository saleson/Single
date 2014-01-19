package com.single.app.component.metadate;

public abstract class IncludeModule implements Cloneable{

	protected MethodEnum method;
	
	public IncludeModule(String method){
		this(MethodEnum.valueOf(method.toUpperCase()));
		
	}
	
	public IncludeModule(MethodEnum method){
		this.method = method;
	}
	
	public void setMethod(MethodEnum method){
		this.method = method;
	}
	
	public void setMethod(String method){
		this.method = MethodEnum.valueOf(method.toUpperCase());
	}
	
	public MethodEnum getMethodEnum(){
		return method;
	}
	
	public String getMethod(){
		return method.getMethod();
	}
}
