package com.single.app.component.metadate;

import com.single.commons.util.ArrayUtils;

public class ContentModuleInclude extends IncludeModule{

	protected String[] names;
	
	public ContentModuleInclude(String method){
		super(method);
		instantiationNames(null);
	}
	
	public ContentModuleInclude(String[] names, MethodEnum method){
		super(method);
		instantiationNames(names);
	}
	
	public ContentModuleInclude(String[] names, String method){
		super(method);
		instantiationNames(names);
	}
	
	private void instantiationNames(String[] names){
		if(names==null){
			this.names = new String[0];
		}else{
			this.names = names.clone();
		}
	}
	
	public void setNames(String[] names){
		this.names = names;
	}
	
	public void addNames(String... names){
		this.names = ArrayUtils.addAllNotSame(this.names, names);
	}
	
	public void removeNames(String... names){
		this.names = ArrayUtils.removeAll(this.names, names);
	}
	
	public String[] getNames(){
		return ArrayUtils.clone(names);
	}
	
	@Override
	protected ContentModuleInclude clone() {
		ContentModuleInclude include = null;
		try {
			include = (ContentModuleInclude) super.clone();
			include.names = this.names.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return include;
	}
}
