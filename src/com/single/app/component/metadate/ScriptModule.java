package com.single.app.component.metadate;

/**
 * Script的内容模型.比如javascript.
 * @author Saleson
 *
 */
public class ScriptModule extends ContentModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * JavaScript 的类型.
	 */
	public static final String JAVASCRIPT_TYPE = "text/javascript";
	
	
	private String type;
	
	public ScriptModule(String groupName, String name) {
		super(groupName, name);
	}
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public ScriptModule clone() {
		return (ScriptModule) super.clone();
	}

}
