package com.single.app.component.metadate;

public class PathInclude extends IncludeModule {

	private String path;
	
	public PathInclude(String path, String method){
		super(method);
		this.path = path;
	}
	
	public PathInclude(String path, MethodEnum method) {
		super(method);
		this.path = path;
	}

	public String getPath(){
		return path;
	}
	
	public PathInclude clone() {
		PathInclude include = null;
		try {
			include = (PathInclude) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return include;
	}
}
